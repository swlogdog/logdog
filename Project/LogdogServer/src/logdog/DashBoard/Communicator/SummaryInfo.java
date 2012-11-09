package logdog.DashBoard.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.ErrorReport.Controller.ServerSettingGetter;

@Path("/summary")
public class SummaryInfo {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day")
	public String isLogFile()
	{	   

		
		return null;
	}
}
