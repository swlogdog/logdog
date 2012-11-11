package logdog.DashBoard.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.DashBoard.Controller.SummaryGetter;
import logdog.DashBoard.DTO.Json.ClassReportRate;
import logdog.DashBoard.DTO.Json.DayReport;
import logdog.DashBoard.DTO.Json.VersionReportRate;

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
		VersionReportRate rate = new VersionReportRate();
		rate.addAppVersion("App 1.0");
		rate.addAppVersion("App 2.0");
		rate.addAppVersion("App 3.0");
		rate.addAppVersion("App 4.0");
		
		
		rate.addOSerror("OS 2.0", 0, 15);
		rate.addOSerror("OS 2.0", 3, 6);
		rate.addOSerror("OS 1.0", 2, 5);
		rate.addOSerror("OS 4.0", 2, 20);
		rate.addOSerror("OS 4.0", 3, 6);
		rate.addOSerror("OS 4.0", 0, 5);
		
		Gson gson = new Gson();

		//ClassReportRate
		System.out.print("Json 요청");
		return gson.toJson(rate);
		
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Class")
	public String getClassErrorReport()
	{	   		
		ClassReportRate rate = new ClassReportRate();
		rate.addClassRate("Name.class", 10);
		rate.addClassRate("Android.class", 5);
		rate.addClassRate("Java.class", 1);
		rate.addClassRate("Templet.class", 2);
		rate.addClassRate("Test.class", 22);
		rate.addClassRate("Apple.class", 6);
		rate.addClassRate("New.class", 30);
		
		Gson gson = new Gson();

		//ClassReportRate
		System.out.print("Json 요청");
		return gson.toJson(rate);
		
		
	}
	
}

