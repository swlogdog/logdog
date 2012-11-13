package logdog.ErrorDetailView.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.ErrorDetailView.Controller.UserReportInfoGetter;

@Path("/error")
public class ErrorDetailReport {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/type/Key={key}")
	public String getClassErrorList(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getErrorTypeInfo(Key);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/callstack/Key={key}")
	public String getCallStack(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getCallsatckInfo(Key);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/summary/Key={key}")
	public String getSummary(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getUserReportList(Key);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day/Key={key}")
	public String getDayVariation(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getDayVariation(Key);
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/detail/Key={key}")
	public String getDetail(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getUserDetailReport(Key);
	}	
}
