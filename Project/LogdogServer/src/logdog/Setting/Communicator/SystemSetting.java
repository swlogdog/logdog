package logdog.Setting.Communicator;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import logdog.Setting.Controller.DeveloperChecker;
import logdog.Setting.Controller.LogdogSetter;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Path("/System")
public class SystemSetting {
	
	@Context UriInfo uriInfo;
	
	  @POST
	  @Path("/logchage")
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public Response changeLog(
	      @FormParam("LogSetting") String Log,
	      @Context HttpServletResponse servletResponse) throws IOException {

		  UriBuilder RedirectPath = uriInfo.getBaseUriBuilder();
		  try{
			  System.out.print(Log);
		  boolean setting = new Boolean(Log);
	
		  LogdogSetter setter = new LogdogSetter();
		  
		  
		  setter.changeLogdogInfo(setting);
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
	  
	  @POST
	  @Path("/addUser")
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public Response addUser(
	      @FormParam("user") String UserInfo,
	      @Context HttpServletResponse servletResponse) throws IOException {

		  UriBuilder RedirectPath = uriInfo.getBaseUriBuilder();
		  try{
	
		  
		  
			  DeveloperChecker dveChecker = new DeveloperChecker();
			  
			  dveChecker.insertDeveloper(UserInfo);
			  
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
