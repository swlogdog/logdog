package logdog.ErrorListViewer.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.Common.TimeUtil;
import logdog.ErrorListViewer.Controller.ErrorTypeReportGetter;

/**
 * 	에러 그래프와 에러 요약 리스트를 뿌려주는 View애서 에러 리스트 부분을 담당하는 Commu이다.
 * 	이 Commu를 바탕으로 listView에서 데이터를 뿌려주게 된다. 
 * @since 2012. 11. 18.오후 8:39:36
 * TODO
 * @author Karuana
 */
@Path("/errlist")
public class TypeListGetter {
	/**
	 *	입력받은 클래스에서 발생한 에러 리스트를 반환한다. 
	 *	URL = /list/errlist/classname=ClassName - GET 
	 * @since 2012. 11. 18.오후 8:40:51
	 * TODO
	 * @author Karuana
	 * @param ClassName  가져올 클래스이름
	 * @return json
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/classname={id}")
	public String getClassErrorList(@PathParam("id") final String ClassName)
	{	   		
		ErrorTypeReportGetter getter = new ErrorTypeReportGetter();
		System.out.print("Json 요청");
		return getter.getClassErrorReport(ClassName);
	}
	
	/**
	 *	입력받은 날짜에 발생한 에러 리스트를 반환한다. 
	 *	URL = /list/errlist/Day=날짜(Ex=11-11) - GET 
	 * @since 2012. 11. 18.오후 8:41:40
	 * TODO
	 * @author Karuana
	 * @param day
	 * @return json
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day={code}")
	public String getDayErrorList(@PathParam("code") final String day)
	{	   		
		String code = day;
		code= code.replace("-", "");
		int daycode = new Integer(code);
		ErrorTypeReportGetter getter = new ErrorTypeReportGetter();
		System.out.print("Json 요청 ㅇㅇ");
		return getter.getDayErrorReport(TimeUtil.GetNowYear(), daycode);
	}
	
	/**
	 *	입력받은 주에 발생한 에러 리스트를 반환하다.
	 *	URL = /list/errlist/Week=날짜  (Ex=2012-1 <== 2012년 1번째 주) - GET 
	 * @since 2012. 11. 18.오후 8:42:05
	 * TODO
	 * @author Karuana
	 * @param day
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Week={code}")
	public String getWeekErrorList(@PathParam("code") final String day)
	{	   		
		String code = day;
		String Year =  code.split("-")[0];
		String week =  code.split("-")[1];
		int yearcode = new Integer(Year);
		int weekCode = new Integer(week.split("w")[0]);
		ErrorTypeReportGetter getter = new ErrorTypeReportGetter();
		System.out.print(yearcode+"  "+weekCode);
		return getter.getWeekErrorReport(yearcode, weekCode);
	}
	
	/**
	 *	입력받은 달에 발생한 에러 리스트를 반환한다.
	 *  URL = /list/errlist/Month=날짜  (Ex: 2012 - 1 =>2012년 1월) - GET 
	 * @since 2012. 11. 18.오후 8:42:24
	 * TODO
	 * @author Karuana
	 * @param day
	 * @return json
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Month={code}")
	public String getMonthErrorList(@PathParam("code") final String day)
	{	   		
		String code = day;
		String Year =  code.split("-")[0];
		String month =  code.split("-")[1];
		int yearcode = new Integer(Year);
		int monthCode = new Integer(month);
		ErrorTypeReportGetter getter = new ErrorTypeReportGetter();
		System.out.print(yearcode+"  "+monthCode);
		return getter.getMonthErrorReport(yearcode, monthCode);
	}
	
	/**
	 *	입력받은 App, OS 버젼에서 발생한 에러 데이터를 반환한다.
	 *	URL = /list/errlist/App=앱버젼/OS=OS버젼 - GET 
	 * @since 2012. 11. 18.오후 8:42:41
	 * TODO
	 * @author Karuana
	 * @param AppVer
	 * @param OSVer
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/App={aver}/OS={osver}")
	public String getVersionErrorList(@PathParam("aver") final String AppVer,
			@PathParam("osver") final String OSVer)
	{	   		

		ErrorTypeReportGetter getter = new ErrorTypeReportGetter();
		System.out.print("Json 요청");
		return getter.getVersionErrorReport(AppVer, OSVer);
	}
}
