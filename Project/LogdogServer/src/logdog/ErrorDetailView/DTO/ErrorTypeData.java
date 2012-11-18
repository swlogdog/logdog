package logdog.ErrorDetailView.DTO;

import logdog.Common.TimeUtil;
import logdog.ErrorReport.DAO.ErrorTypeInfo;

/**
 * 	Error의 기본적인 타입 정보가 들어가 있다. 이를 ajax로 읽어 드려서 상세 에러 뷰에 값을 넣어주게 된다. 
 * @since 2012. 11. 18.오후 8:37:30
 * TODO
 * @author Karuana
 */
public class ErrorTypeData {
	/**
	 * 에러 명 
	 */
	private String ErrorName;
	/**
	 * 에러가 발생한 클래스 
	 */
	private String ErrorClass;
	/**
	 * 코드 라인
	 */
	private int CodeLine;
	/**
	 * 	발생횟수 + 최근 발생 횟수 
	 */
	private String ReportCount;
	/**
	 * 마지막 업데이트 일
	 */
	private String LastUpdated;
	/**
	 * 버그 클리어 여부
	 */
	private String BugClear;
	
	/**
	 *	생성자ㅡ ErrorTypeInfo 클래스를 입력으로 받아 데이터를 초기화한다. 
	 * @since 2012. 11. 18.오후 8:39:06
	 * TODO
	 * @author Karuana
	 * @param info
	 */
	public ErrorTypeData(ErrorTypeInfo info) {
		super();
		ErrorName = info.getErrorName();
		ErrorClass = info.getOccurrenceClass();
		CodeLine = info.getCodeLine();
		ReportCount = "Total ("+info.getTotalOccurrences()+") Weekly("+info.getWeeklyOccurrences()+")";
		LastUpdated = TimeUtil.GetTime2String(info.getLastUpdateDay());
		BugClear = new Boolean(info.isBugClear()).toString();
		System.out.print(BugClear);
		}
	
}
