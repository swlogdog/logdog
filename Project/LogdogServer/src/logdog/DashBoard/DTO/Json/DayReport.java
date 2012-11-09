package logdog.DashBoard.DTO.Json;

import java.util.ArrayList;

public class DayReport {
	private ArrayList<String> Day;
	private ArrayList<Integer> ReportRate;
	
	public DayReport() {
		super();
		Day = new ArrayList<String>();
		ReportRate = new ArrayList<Integer>();
		
	}
	public void AddDay(String DayName)
	{
		Day.add(DayName);
	}
	
	public void AddReportRate(int rate)
	{
		ReportRate.add(rate);
	}
}
