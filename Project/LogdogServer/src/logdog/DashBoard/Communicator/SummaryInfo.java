package logdog.DashBoard.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.DashBoard.Controller.SummaryGetter;
import logdog.DashBoard.DTO.Json.Highcharts.ClassReportRate;
import logdog.DashBoard.DTO.Json.Highcharts.DayReport;
import logdog.DashBoard.DTO.Json.Highcharts.VersionReportRate;

import com.google.gson.Gson;

@Path("/summary")
public class SummaryInfo {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day")
	public String getDaySummary()
	{	   		
		SummaryGetter getter = new SummaryGetter();
		System.out.print("Json 요청");
		return getter.getDayErrorRate();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Version")
	public String getVersionErrorRate()
	{	   		
		SummaryGetter getter = new SummaryGetter();
		System.out.print("Json 요청");
		return getter.getVersionRate();
	}
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

