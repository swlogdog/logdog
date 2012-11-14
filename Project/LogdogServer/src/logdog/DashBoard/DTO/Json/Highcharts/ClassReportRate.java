package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

/**
 * 	HighChart기반으로 데이터를 그리기 위한 JSON 생성용 DTO객체이다.
 * @since 2012. 11. 15.오전 6:27:03
 * TODO
 * @author Karuana
 */
public class ClassReportRate {

	/**
	 * 	Class별 에러량을 담는 객체이다.
	 */
	private ArrayList<ArrayList<Object>> ClassErrors;

	public ClassReportRate() {
		super();
		// TODO Auto-generated constructor stub
		ClassErrors = new ArrayList<ArrayList<Object>> ();
	}
	
	/**
	 *	주어진 데이터로 ArryList를 만들고 이를  ClassErrors에 추가한다.
	 * @since 2012. 11. 15.오전 6:28:09
	 * TODO
	 * @author Karuana
	 * @param ClassName - 클래스 명 
	 * @param Count -에러 횟수
	 */
	public void addClassRate(String ClassName,int Count)
	{
		ArrayList<Object> classList = new ArrayList<Object>();
		classList.add(ClassName);
		classList.add(Count);
		
		ClassErrors.add(classList);
	}
	
	
}
