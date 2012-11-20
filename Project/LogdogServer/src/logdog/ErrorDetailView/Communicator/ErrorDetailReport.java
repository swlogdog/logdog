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

/**	
 * 	Error의 디테일한 에러 정보를 출력해주기 위한 Web에서 정보를 가져오기 위한 Communicator이다.
 * @since 2012. 11. 15.오전 6:44:43
 * TODO
 * @author Karuana
 */
@Path("/error")
public class ErrorDetailReport {
	/**
	 *	상세히보고 있는 에러의 기본적인 타입 정보들을 가져온다.
	 *	결과는 Json으로 리턴된다. -> ErrorTypeData 참조
	 *	URL = /DetailView/error/type/Key=ErrorType-KeyNumber - GET 
	 * @since 2012. 11. 15.오전 6:46:06
	 * TODO
	 * @author Karuana
	 * @param Key (Error Type Key)
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/type/Key={key}")
	public String getErrorClassInfo(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getErrorTypeInfo(Key);
	}
	
	/**
	 *	에러의 콜스택 정보를 jqGrid의 Json 형식으로 리턴한다. -> CallStackReport 참조 
	 *	URL = /DetailView/error/callstack/Key=ErrorType-KeyNumber - GET 
	 * @since 2012. 11. 15.오전 6:52:07
	 * TODO
	 * @author Karuana
	 * @param Key  (Error Type Key)
	 * @return json
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/callstack/Key={key}")
	public String getCallStack(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getCallsatckInfo(Key);
	}
	
	/**
	 *	접수된 유저들의 에러 리포트의 요약 정보를 리턴한다. 이 데이터도 JqGrid 양식 JSon으로 리턴한다 -> UserSummaryData 참조
	 *  URL = /DetailView/error/summary/ErrorTypeKey=Error-KeyNumber - GET 
	 * @since 2012. 11. 15.오전 6:53:56
	 * TODO
	 * @author Karuana
	 * @param Key  (Error Type Key)
	 * @return json
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/summary/Key={key}")
	public String getSummary(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getUserReportList(Key);
	}
	/**
	 *	해당 에러의 일자별 접수량 그래프를 그리기 위한 데이터를 리턴한다.
	 *	URL = /DetailView/error/Day/Key=ErrorType-KeyNumber - GET 
	 * @since 2012. 11. 15.오전 6:56:07
	 * TODO
	 * @author Karuana
	 * @param Key (Error Type Key)
	 * @return json
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day/Key={key}")
	public String getDayVariation(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getDayVariation(Key);
	}	
	
	/**
	 *	특정 리포트 정보에 구체적인 정보와 로그의 위치를 저장한 객체를 json으로 리턴한다. -> UserReportinfo확인
	 *	URL = /DetailView/error/detail/UserReportType/Key=Error-KeyNumber - GET
	 * @since 2012. 11. 15.오전 6:57:39
	 * TODO
	 * @author Karuana
	 * @param Key (UserReportKey)
	 * @return json
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/detail/Key={key}")
	public String getDetail(@PathParam("key") final String Key)
	{	   		
		UserReportInfoGetter getter = new UserReportInfoGetter();
		System.out.print("Json 요청");
		return getter.getUserDetailReport(Key);
	}	
	
	/**
	 *	이 서블릿의 기본  Uri를 얻어오기 위한 UriInfo 객체이다. 
	 */
	@Context UriInfo uriInfo;
	
	  /**
	 *	해당 Error Type을 에러 수정 상태로 전환시킨후 메인 페이지로 이동한다.
	 *	URL = /DetailView/error/debug/Key=ErrorType-KeyNumber - GET 
	 * @since 2012. 11. 15.오전 7:01:10
	 * TODO
	 * @author Karuana
	 * @param Key (Error Type Key)
	 * @param servletResponse
	 * @return dash.html -> Main으로 이동 
	 * @throws IOException
	 */
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
