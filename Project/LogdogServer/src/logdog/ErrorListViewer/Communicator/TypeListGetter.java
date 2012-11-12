package logdog.ErrorListViewer.Communicator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logdog.Common.TimeUtil;
import logdog.ErrorListViewer.Controller.ErrorTypeReportGetter;

@Path("/errlist")
public class TypeListGetter {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/classname={id}")
	public String getClassErrorList(@PathParam("id") final String ClassName)
	{	   		
		ErrorTypeReportGetter getter = new ErrorTypeReportGetter();
		System.out.print("Json 요청");
		return getter.getClassErrorReport(ClassName);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Day={code}")
	public String getDayErrorList(@PathParam("code") final String day)
	{	   		
		String code = day;
		code= code.replace("-", "");
		int daycode = new Integer(code);
		ErrorTypeReportGetter getter = new ErrorTypeReportGetter();
		System.out.print("Json 요청");
		return getter.getDayErrorReport(TimeUtil.GetNowYear(), daycode);
	}
	
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
