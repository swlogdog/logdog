package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * 월별 에러 정보를 저장하기 위한 클래스
 * @since 2012. 11. 19.오전 7:48:59
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class MonthReportInfo {
	/**
	 * 기본 키 
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key MonthRateKey;
	/**
	 * 년도 
	 */
	@Persistent
	int Year;//YYYY
	/**
	 * 월
	 */
	@Persistent
	int Month;//MM
	/**
	 * YYYYMM
	 */
	@Persistent
	int TotalCode;//YYYYDD
	/**
	 * 에러 횟수
	 */
	@Persistent 
	private int TotalOccurrences;
	public MonthReportInfo(){}
	public MonthReportInfo(int year, int month) {
		super();
		Year = year;
		Month = month;
		TotalCode = Year*100+Month;
		TotalOccurrences=1;
	}
	public void UpdatedError()
	{
		TotalOccurrences+=1;
	}
	public Key getMonthRateKey() {
		return MonthRateKey;
	}
	public int getYear() {
		return Year;
	}
	public int getMonth() {
		return Month;
	}
	public int getTotalOccurrences() {
		return TotalOccurrences;
	}
}
