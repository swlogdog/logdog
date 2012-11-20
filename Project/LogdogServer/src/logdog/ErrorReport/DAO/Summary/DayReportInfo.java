package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
/**
 * 	일별 에러 정보를 기록하기 위한 DataStore 객체이다.
 * @since 2012. 11. 19.오전 7:44:51
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class DayReportInfo {
	/**
	 * 기본 키
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key DayRateKey;
	/**
	 * 	년도
	 */
	@Persistent
	private int Year;//YYYY
	/**
	 * 날자코드
	 */
	@Persistent
	private int MDay;//MMDD
	/**
	 * YearMMDD 형태로 저장되는 날짜 코드, 앱엔진의 부등호 두개 쿼리 안되는거 방지
	 */
	@Persistent
	private int TotalCode;
	/**
	 * 총 에러량 
	 */
	@Persistent 
	private int TotalOccurrences;
	public DayReportInfo(){}
	public DayReportInfo(int year, int mDay) {
		super();
		Year = year;
		MDay = mDay;
		TotalCode = Year*10000+MDay;
		TotalOccurrences=1;
	}
	public void UpdatedError()
	{
		TotalOccurrences+=1;
	}
	public Key getDayRateKey() {
		return DayRateKey;
	}
	public int getYear() {
		return Year;
	}
	public int getMDay() {
		return MDay;
	}
	public int getTotalOccurrences() {
		return TotalOccurrences;
	}
	
	
}
