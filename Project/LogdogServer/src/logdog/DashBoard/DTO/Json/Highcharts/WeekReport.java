package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

public class WeekReport {
	private ArrayList<String> Week;
	
	private ArrayList<Integer> ReportRate;
	
	public WeekReport() {
		super();
		Week = new ArrayList<String>();
		ReportRate = new ArrayList<Integer>();
		
	}
	public void AddWeek(int year, int week)
	{
		Week.add(year+"/"+week+"-Weeks");
	}
	/**
	 *	에러율을 더한다.
	 * @since 2012. 11. 15.오전 6:32:46
	 * TODO
	 * @author Karuana
	 * @param rate
	 */
	public void AddReportRate(int rate)
	{
		ReportRate.add(rate);
	}
	
	/**
	 * 리포트의 사이즈를 얻어온다.
	 * @since 2012. 11. 15.오전 6:32:57
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public int getSize()
	{
		return ReportRate.size();
	}
}
