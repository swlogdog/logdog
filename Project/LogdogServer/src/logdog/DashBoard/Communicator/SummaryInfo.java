package logdog.DashBoard.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.DashBoard.DTO.Json.DayReport;

import com.google.gson.Gson;

@Path("/summary")
public class SummaryInfo {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day")
	public String getDaySummary()
	{	   		
		DayReport Test = new DayReport();
		Test.AddDay(1110);
		Test.AddDay(1109);
		Test.AddDay(1108);
		Test.AddDay(1107);
		Test.AddDay(1106);
		Test.AddDay(1105);
	
		Test.AddReportRate(5);
		Test.AddReportRate(10);
		Test.AddReportRate(5);
		Test.AddReportRate(10);
		Test.AddReportRate(20);
		Test.AddReportRate(20);
	
		Gson gson = new Gson();

		System.out.print("Json 요청");
		return gson.toJson(Test);
	}
}
