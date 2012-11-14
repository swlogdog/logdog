package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

import logdog.Common.TimeUtil;

public class DayReport {
	private ArrayList<String> Day;
	private ArrayList<Integer> ReportRate;
	
	public DayReport() {
		super();
		Day = new ArrayList<String>();
		ReportRate = new ArrayList<Integer>();
		
	}
	public void AddDay(int Daycode)
	{
		int Month=Daycode/100;
		int Days = Daycode%100;
		
		Day.add(Month+" / "+Days);
	}
	
	public void AddReportRate(int rate)
	{
		ReportRate.add(rate);
	}
	
	public int getSize()
	{
		return ReportRate.size();
	}

}
