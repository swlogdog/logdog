package logdog.ErrorReport.Communicator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import logdog.Common.ServiceType;
import logdog.Common.BackendWork.BackendFactory;
import logdog.Common.BackendWork.BackendWorkingSet;
import logdog.Common.BackendWork.DTO.BackendSettingData;
import logdog.Common.BlobStore.BlobFileWriter;
import logdog.Common.BlobStore.BlobWriterFactory;
import logdog.ErrorReport.Controller.ErrorReportRegister;
import logdog.ErrorReport.Controller.ErrorTypeClassifier;
import logdog.ErrorReport.Controller.ReportSummaryUpdaer;
import logdog.ErrorReport.DTO.CallStackInfo;
import logdog.ErrorReport.DTO.ErrorUniqueID;
import logdog.ErrorReport.DTO.TypeMatchingInfo;
import logdog.ErrorReport.DTO.UserSummaryInfo;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.gson.Gson;

/**
 * 입력받은 Report를 처리하기 위한 Backend작업을 처리하는 Commu이다.
 * @since 2012. 11. 18.오후 8:47:08
 * TODO
 * @author Karuana
 */
@Path("/ReportBackend")
public class BackendWorker {

	
	/**
	 *	에러 타입 정보를 등록한다.
	 *	URL = /logdog/ReportBackend/ErrorType - GET 
	 * @since 2012. 11. 3.오전 3:27:11
	 * 에러 타입 등을 Backend를 통해 작업한다.
	 * @author Karuana
	 * @param callstack
	 * @return 성공시 200
	 */
	@POST
	@Path("/ErrorType")
	@Consumes("application/json")
	public Response RegistErrorType(CallStackInfo callstack) {
		System.out.println("에러타입 백엔드 등");
		ErrorTypeClassifier errClassifier = new ErrorTypeClassifier();
		ErrorUniqueID uid = new ErrorUniqueID(callstack.getName(),callstack.getClassname(),callstack.getLine());
		if(!errClassifier.IsErrorType(uid))
		{
			errClassifier.InsertErrorType(uid);		
			errClassifier.LinkCallStackData(callstack);		
		}
		//백엔드로 타입 생
		return Response.status(200).entity("ErrorType Create end").build();
 
	}
	
	
	/**
	 *	등록된 유저정보의 에러타입의 매칭 작업을 진행한다.
	 *	URL = /logdog/ReportBackend/TypeMatching - GET 
	 * @since 2012. 11. 19.오전 5:56:56
	 * TODO
	 * @author Karuana
	 * @param matchingdata
	 * @return
	 */
	@POST
	@Path("/TypeMatching")
	@Consumes("application/json")
	public Response ErrorTypeMatching(TypeMatchingInfo matchingdata) { 
		
		ErrorUniqueID uid = new ErrorUniqueID(matchingdata.getName(),matchingdata.getClassname(),matchingdata.getLine());
		ErrorReportRegister eReport = new ErrorReportRegister();
		Key ReportKey = KeyFactory.stringToKey(matchingdata.getReportKey());
		if(matchingdata.isRematching()==false)
		{
			ReportSummaryUpdaer  reporter = new ReportSummaryUpdaer();
			UserSummaryInfo Temp =eReport.getSummaryInfo(ReportKey);

			if(Temp != null)
				reporter.UpdatedReportError(Temp,matchingdata.getClassname());
			else
				return Response.status(400).entity("Matching Error").build();
		}

	
		//System.out.print("Backend Start");
		if(eReport.MatchingErrorType(ReportKey, uid)==null)	//타입 매칭이 실패한 경우 재시도... Ex)실패하는 경우: DataStore
		{
			Gson gson = new Gson();
			matchingdata.setRematching(true);
			BackendWorkingSet backendService = BackendFactory.GetBackendService(ServiceType.GOOGLE_APP_ENGINE);
			BackendSettingData BackendInfo = BackendFactory.GetDefaltSettingData("/logdog/ReportBackend/TypeMatching", Method.POST, gson.toJson(matchingdata));
			backendService.CreateBackendWorkJson(BackendInfo);
		}
		

		return Response.status(200).entity("ErrorTypeMatching end").build();
 
	}
	
	/**
	 *	Log를  BlobStore에 기록한다.
	 *	URL = /logdog/ReportBackend/LogUpdate/KEY=리포트 키 - GET 
	 * @since 2012. 11. 19.오전 6:00:16
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 * @param logData
	 * @return
	 */
	@PUT
	@Path("/LogUpdate/KEY={key}")
	@Consumes("text/plain")
	public Response LogWriter(
			@PathParam("key") final String reportKey,
									String logData) { 
		BlobFileWriter blobwriter = BlobWriterFactory.GetBlobService(ServiceType.GOOGLE_APP_ENGINE);
		BlobKey FileKey = blobwriter.TextWrite(logData);	
		System.out.print("Log");
		ErrorReportRegister eReport = new ErrorReportRegister();
		eReport.MatchingLogFile(KeyFactory.stringToKey(reportKey), FileKey);
		

		return Response.status(200).entity("Log Updated end").build();
 
	}
}
