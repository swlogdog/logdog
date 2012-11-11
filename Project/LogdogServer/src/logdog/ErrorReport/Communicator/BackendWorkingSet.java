package logdog.ErrorReport.Communicator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import logdog.Common.ServiceType;
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

@Path("/ReportBackend")
public class BackendWorkingSet {

	
	/**
	 *
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
		ErrorUniqueID uid = new ErrorUniqueID(callstack.getName(),callstack.getClassname());
		if(!errClassifier.IsErrorType(uid))
		{
			errClassifier.InsertErrorType(uid);		
			errClassifier.LinkCallStackData(callstack);		
		}
		//백엔드로 타입 생
		return Response.status(200).entity("ErrorType Create end").build();
 
	}
	
	
	@POST
	@Path("/TypeMatching")
	@Consumes("application/json")
	public Response ErrorTypeMatching(TypeMatchingInfo matchingdata) { 
		
		System.out.println("타입 매칭 시작");
		
		
		ErrorUniqueID uid = new ErrorUniqueID(matchingdata.getName(),matchingdata.getClassname());
		ErrorReportRegister eReport = new ErrorReportRegister();
		Key ReportKey = KeyFactory.stringToKey(matchingdata.getReportKey());
		
		//타입 매칭  리포트를 갱신한다.
		ReportSummaryUpdaer  reporter = new ReportSummaryUpdaer();
		UserSummaryInfo Temp =eReport.getSummaryInfo(ReportKey);
		
		if(Temp != null)
			reporter.UpdatedReportError(Temp);
		else
			return Response.status(400).entity("Matching Error").build();
	
		//System.out.print("Backend Start");
		eReport.MatchingErrorType(ReportKey, uid);
		

		return Response.status(200).entity("ErrorTypeMatching end").build();
 
	}
	
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
