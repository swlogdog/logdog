package logdog.Setting.Communicator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/StartSetting")
public class StartSetting {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Setting")
	public String isLogFile()
	{	   
		return null;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Setting")
	public String GoogleLogin()
	{	   
		return null;
	}
}
