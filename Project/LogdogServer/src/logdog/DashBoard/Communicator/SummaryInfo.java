package logdog.DashBoard.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.DashBoard.Controller.SummaryGetter;
import logdog.DashBoard.DTO.Json.Highcharts.ClassReportRate;
import logdog.DashBoard.DTO.Json.Highcharts.DayReport;
import logdog.DashBoard.DTO.Json.Highcharts.VersionReportRate;

import com.google.gson.Gson;

/**
 * Web페이지와 서버내의 그래프를 그리기 위한 요약 데이터를 주가 받기 위한 Communicator 객체이다.
 * @since 2012. 11. 15.오전 6:18:05
 * TODO
 * @author Karuana
 */
@Path("/summary")
public class SummaryInfo {
	/**
	 * 일별 그래프를 그리기위한 데이터를 넘겨주는 메소드이다. 
	 * URL = /Board/summary/Day=구간길이 - GET 
	 * @since 2012. 11. 15.오전 6:19:05
	 * TODO
	 * @author Karuana
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day={interval}")
	public String getDaySummary(@PathParam("interval") final int interval)
	{	   		
		SummaryGetter getter = new SummaryGetter();
		System.out.print("Json 요청");
		return getter.getDayErrorRate(interval);
	}
	/**
	 *	Week 그래프를 그리기 위한 데이터를 넘겨주는 메소드이다.
	 *	URL = /Board/summary/Week=구간길이 - GET 
	 * @since 2012. 11. 18.오후 8:28:56
	 * TODO
	 * @author Karuana
	 * @param interval 구간 길이
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Week={interval}")
	public String getWeekSummary(@PathParam("interval") final int interval)
	{	   		
		SummaryGetter getter = new SummaryGetter();
		System.out.print("Json 요청");
		return getter.getWeekDayErrorRate(interval);
	}
	
	/**
	 *	Month 그래프를 그리기 위한 데이터를 넘겨주는 메소드이다.
	 *	URL = /Board/summary/Month=구간길이 - GET  
	 * @since 2012. 11. 18.오후 8:30:05
	 * TODO
	 * @author Karuana
	 * @param interval 구간 길이
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Month={interval}")
	public String getMonthSummary(@PathParam("interval") final int interval)
	{	   		
		SummaryGetter getter = new SummaryGetter();
		System.out.print("Json 요청");
		return getter.getMonthErrorDate(interval);
	}
	/**
	 *	버젼별 에러량 그래프를 그리기 위한 데이터를 JSon으로 넘겨주는 메소드이다.
	 *	URL = /Board/summary/Version - GET 
	 * @since 2012. 11. 15.오전 6:20:39
	 * TODO
	 * @author Karuana
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Version")
	public String getVersionErrorRate()
	{	   		
		SummaryGetter getter = new SummaryGetter();
		System.out.print("Json 요청");
		return getter.getVersionRate();
	}
	/**
	 *	class별 에러량 그래프를 그리기 위한 데이터를 Json으로 넘겨주는 메소드이다.
	 *	URL = /Board/summary/Class - GET 
	 * @since 2012. 11. 15.오전 6:21:22
	 * TODO
	 * @author Karuana
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Class")
	public String getClassErrorReport()
	{	   		

		SummaryGetter getter = new SummaryGetter();
		System.out.print("Json 요청");
		return getter.getClassErrorRate();
		
		
	}
	
}

