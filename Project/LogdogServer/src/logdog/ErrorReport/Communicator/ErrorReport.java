package logdog.ErrorReport.Communicator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import logdog.Common.ServiceType;
import logdog.Common.BackendWork.BackendFactory;
import logdog.Common.BackendWork.BackendWorkingSet;
import logdog.Common.BackendWork.DTO.BackendSettingData;
import logdog.Common.Json.BooleanResult;
import logdog.ErrorReport.Controller.ErrorReportRegister;
import logdog.ErrorReport.Controller.ErrorTypeClassifier;
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
	@Path("/ErrorType/{errName}/{className}")
	public String IsErrorType(
			@PathParam("errName") final String errName,
			@PathParam("className") final String ClassName
			)
	{	   
		Gson gson = new Gson();
		ErrorUniqueID errType = new ErrorUniqueID(errName,ClassName);
		
		ErrorTypeClassifier eTypeClassifier = new ErrorTypeClassifier();
		BooleanResult isType = new BooleanResult(eTypeClassifier.IsErrorType(errType));
		
		return gson.toJson(isType);
	}
	
	@POST
	@Path("/ErrorType")
	@Consumes("application/json")
	public Response CreateErrorType(String callstack) {

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
		TypeMatchingInfo matchingInfo= new TypeMatchingInfo(userInfo.ErrorName,userInfo.ErrorClassName,reportKey_str);

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
		
	
		BackendWorkingSet backendService = BackendFactory.GetBackendService(ServiceType.GOOGLE_APP_ENGINE);
		BackendSettingData BackendInfo = BackendFactory.GetDefaltSettingData("/logdog/ReportBackend/LogUpdate/KEY="+reportKey, Method.PUT, logData);
		backendService.CreateBackendWorkTextData(BackendInfo);
		
		//백엔드로 타입 생
		return Response.status(202).entity("Log Update Accepted").build();
 
	}
	/**
	 *
	 * @since 2012. 11. 2.오전 3:08:37
	 * TODO 요청받은 데이터 기록 작업을 Backend로 진행한다.
	 * @author Karuana
	 * @param userReport
	 * @return Response 처리에 대한 응답
	 */
	@POST
	@Path("/DataRegist")
	@Consumes("application/json")
	public Response ErrorRegister(ClientReportData userReport) {
		Gson gson = new Gson();
	//	ErrorTypeInfo Temp = new ErrorTypeInfo("aaa","bbb");
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
 
	}
	
	
}