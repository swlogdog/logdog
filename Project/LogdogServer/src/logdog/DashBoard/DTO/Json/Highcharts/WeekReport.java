package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

/**
 * Week Report를 그려주기 위한 Json 템플릿 클래스이다.
 * 
 * @since 2012. 11. 18.오후 8:33:19 TODO
 * @author Karuana
 */
public class WeekReport {
	/**
	 * Week의 명칭들이 들어간다.
	 */
	private ArrayList<String> Week;
	/**
	 * 각 점별 에러율을 저장한다.
	 */
	private ArrayList<Integer> ReportRate;

	/**
	 * 생성자
	 * 
	 * @since 2012. 11. 18.오후 8:35:21 TODO
	 * @author Karuana
	 */
	public WeekReport() {
		super();
		Week = new ArrayList<String>();
		ReportRate = new ArrayList<Integer>();

	}

	/**
	 *	입력받은 년도 및 Week 코드를 기반으로 해당날짜에 에러를 추가해준다.  
	 * @since 2012. 11. 18.오후 8:35:34
	 * TODO
	 * @author Karuana
	 * @param year
	 * @param week
	 */
	public void AddWeek(int year, int week) {
		Week.add(year + " / " + week + "w");
	}

	/**
	 * 에러율을 더한다.
	 * 
	 * @since 2012. 11. 15.오전 6:32:46 TODO
	 * @author Karuana
	 * @param rate
	 */
	public void AddReportRate(int rate) {
		ReportRate.add(rate);
	}

	/**
	 * 리포트의 사이즈를 얻어온다.
	 * 
	 * @since 2012. 11. 15.오전 6:32:57 TODO
	 * @author Karuana
	 * @return
	 */
	public int getSize() {
		return ReportRate.size();
	}
}
