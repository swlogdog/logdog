package logdog.ErrorDetailView.Communicator;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.google.appengine.api.taskqueue.TaskOptions.Method;

import logdog.Common.ServiceType;
import logdog.Common.BackendWork.BackendFactory;
import logdog.Common.BackendWork.BackendWorkingSet;
import logdog.Common.BackendWork.DTO.BackendSettingData;
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
	
		@Context UriInfo uriInfo;
	
	  @GET
	  @Path("/debug/Key={key}")
	  @Produces(MediaType.TEXT_HTML)
	  public Response deleteData(
			  @PathParam("key") String Key,
	      @Context HttpServletResponse servletResponse) throws IOException {

		  UriBuilder RedirectPath = uriInfo.getBaseUriBuilder();
		  try{
				UserReportInfoGetter getter = new UserReportInfoGetter();
				getter.onBugDataClear(Key);
		  RedirectPath.path("DashboardRedireter.html");
		  }
		  catch(Exception e)
		  {
			  RedirectPath.path("NotPermission.html");
			  e.printStackTrace();
			  System.out.println(e.getClass() + "  " + e.getCause());
		  }
		  return Response.seeOther(RedirectPath.build()).build();

	  }
}
