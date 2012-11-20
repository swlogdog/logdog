package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

public class MonthReport {
	/**
	 *  그래프에 표시될 일자가 저장된다.
	 */
	private ArrayList<String> Months;
	/**
	 * 각 요일별 에러량이 저장된다. Day 필드와 각 인덱스가 매칭되는 것이, 해당 날 발생한 에러량이다.
	 */
	private ArrayList<Integer> ReportRate;
	public MonthReport() {
		super();
		Months = new ArrayList<String>();
		ReportRate = new ArrayList<Integer>();
		
	}
	public void AddMonth(int year,int month)
	{
		Months.add(year+"-"+month);
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
