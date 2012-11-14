package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

public class ClassReportRate {

	private ArrayList<ArrayList<Object>> ClassErrors;

	public ClassReportRate() {
		super();
		// TODO Auto-generated constructor stub
		ClassErrors = new ArrayList<ArrayList<Object>> ();
	}
	
	public void addClassRate(String ClassName,int Count)
	{
		ArrayList<Object> classList = new ArrayList<Object>();
		classList.add(ClassName);
		classList.add(Count);
		
		ClassErrors.add(classList);
	}
	
	
}
