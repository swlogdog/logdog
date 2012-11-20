package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

import logdog.Common.TimeUtil;

/**
 * 	일별 에러량을 그리기 위한 데이터를 가지고 있는 JSON 변환용 DTO 객체이다.
 * 	 이 객체는 Highchart용으로 제작 되었다.
 * @since 2012. 11. 15.오전 6:29:03
 * TODO
 * @author Karuana
 */
public class DayReport {
	/**
	 *  그래프에 표시될 일자가 저장된다.
	 */
	private ArrayList<String> Day;
	/**
	 * 각 요일별 에러량이 저장된다. Day 필드와 각 인덱스가 매칭되는 것이, 해당 날 발생한 에러량이다.
	 */
	private ArrayList<Integer> ReportRate;
	
	/**
	 * Day, ReportRate 두 리스트를 생성한다.
	 * @since 2012. 11. 15.오전 6:31:54
	 * TODO
	 * @author Karuana
	 */
	public DayReport() {
		super();
		Day = new ArrayList<String>();
		ReportRate = new ArrayList<Integer>();
		
	}
	/**
	 * 날짜를 더한다. 이때 DayCode 방식으로 입력하면 자동으로 변환하여 추가한다.
	 * 자동으로 년도는 생략한다.
	 * @since 2012. 11. 15.오전 6:32:14
	 * TODO
	 * @author Karuana
	 * @param Daycode
	 */
	public void AddDay(int Daycode)
	{
		int Month=Daycode/100;
		int Days = Daycode%100;
		
		Day.add(Month+" / "+Days);
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
