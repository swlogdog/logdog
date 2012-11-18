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

/**
 * 시스템 셋팅에 관련된 Commu 객체이다. 
 * @since 2012. 11. 19.오전 8:07:31
 * TODO
 * @author Karuana
 */
@Path("/System")
public class SystemSetting {
	
	/**
	 * Uri 정보를 얻어오기 위한 클래스 
	 */
	@Context UriInfo uriInfo;
	
	/**
	 *	로그 셋팅 정보를 변경해주는 커뮤니케이션 
	 * @since 2012. 11. 19.오전 8:08:05
	 * TODO
	 * @author Karuana
	 * @param Log
	 * @param servletResponse
	 * @return
	 * @throws IOException
	 */
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
	  
	  /**
	 *	새로운 개발자를 추가한다. 
	 * @since 2012. 11. 19.오전 8:09:46
	 * TODO
	 * @author Karuana
	 * @param UserInfo
	 * @param servletResponse
	 * @return
	 * @throws IOException
	 */
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
