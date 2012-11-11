package logdog.ErrorReport.Communicator;

import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import logdog.Common.ServiceType;
import logdog.Common.BackendWork.BackendFactory;
import logdog.Common.BackendWork.BackendWorkingSet;
import logdog.Common.BackendWork.DTO.BackendSettingData;
import logdog.Common.Json.BooleanResult;
import logdog.ErrorReport.Controller.ErrorReportRegister;
import logdog.ErrorReport.Controller.ErrorTypeClassifier;
import logdog.ErrorReport.Controller.ServerSettingGetter;
import logdog.ErrorReport.DTO.ClientReportData;
import logdog.ErrorReport.DTO.ErrorUniqueID;
import logdog.ErrorReport.DTO.TypeMatchingInfo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.gson.Gson;

@Path("/Report")
public class ErrorReport {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/LogSetting")
	public String isLogFile()
	{	   
		ServerSettingGetter ServerSetter = new ServerSettingGetter();
		
		return ServerSetter.getLogFileSetting();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ErrorType/{errName}/{className}/{line}")
	public String IsErrorType(
			@PathParam("errName") final String errName,
			@PathParam("className") final String ClassName,
			@PathParam("line") final String cLine
			)
	{	  
		System.out.print("왔다 ㅇ");
		Gson gson = new Gson();
		BooleanResult isType =new BooleanResult(false);

		try{
			
		String Name = URLDecoder.decode(errName,"UTF-8"); 
		String cName = URLDecoder.decode(ClassName,"UTF-8"); 
		int line = new Integer(URLDecoder.decode(cLine,"UTF-8")); 
		ErrorUniqueID errType = new ErrorUniqueID(Name,cName,line);
		System.out.print(line);
		ErrorTypeClassifier eTypeClassifier = new ErrorTypeClassifier();
		isType.setResult(eTypeClassifier.IsErrorType(errType));
	
		}catch(Exception e)
		{
			throw new WebApplicationException(500);
		}
		
		return gson.toJson(isType);
	}
	
	@POST
	@Path("/ErrorType")
	@Consumes("application/json")
	public Response CreateErrorType(String callstack) {
		System.out.print("데이터 등록");
		BackendWorkingSet backendService = BackendFactory.GetBackendService(ServiceType.GOOGLE_APP_ENGINE);
		BackendSettingData BackendInfo = BackendFactory.GetDefaltSettingData("/logdog/ReportBackend/ErrorType", Method.POST, callstack);
		backendService.CreateBackendWorkJson(BackendInfo);

		//백엔드로 타입 생
		return Response.status(202).entity("Request Accepted").build();
 
	}

	
	@POST
	@Path("/UserInfo")
	@Consumes("application/json")
	public Response UploadUserErrorInfo(ClientReportData userInfo) {
		
		ErrorReportRegister errRegister = new ErrorReportRegister();
		Key reportKey = errRegister.insertErrorReport(userInfo);	
		String reportKey_str = KeyFactory.keyToString(reportKey);
		
		Gson gson = new Gson();
		TypeMatchingInfo matchingInfo= new TypeMatchingInfo(userInfo.ErrorName,userInfo.ErrorClassName,userInfo.line,reportKey_str);

		BackendWorkingSet backendService = BackendFactory.GetBackendService(ServiceType.GOOGLE_APP_ENGINE);
		BackendSettingData BackendInfo = BackendFactory.GetDefaltSettingData("/logdog/ReportBackend/TypeMatching", Method.POST, gson.toJson(matchingInfo));
		backendService.CreateBackendWorkJson(BackendInfo);
		
		//백엔드로 타입 생
		return Response.status(202).entity(KeyFactory.keyToString(reportKey)).build();
 
	}

	@PUT
	@Path("/UserInfo/Key={key}")
	@Consumes("text/plain")
	public Response UploadUserLog(
			@PathParam("key") final String reportKey,
									String logData) {
		
		System.out.println("로그 데이터 저장하기 호출");
		BackendWorkingSet backendService = BackendFactory.GetBackendService(ServiceType.GOOGLE_APP_ENGINE);
		BackendSettingData BackendInfo = BackendFactory.GetDefaltSettingData("/logdog/ReportBackend/LogUpdate/KEY="+reportKey, Method.PUT, logData);
		backendService.CreateBackendWorkTextData(BackendInfo);
		
		//백엔드로 타입 생
		return Response.status(202).entity("Log Update Accepted").build();
 
	}

	
	
}
