package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
/**
 * 	주간 에러 량을 저장하기 위한 클래스 
 * @since 2012. 11. 19.오전 7:50:02
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class WeekReportInfo {
	/**
	 * 	기본 키
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key WeekRateKey;
	/**
	 * 년도 
	 */
	@Persistent
	private int Year;//YYYY
	/**
	 * 주 
	 */
	@Persistent
	private int Week;//Week OF Year Max 52 Or 53
	/**
	 * YYYYWW
	 */
	@Persistent
	private	int TotalCode;//YYYYWW
	/**
	 *  에러 횟수
	 */
	@Persistent 
	private int TotalOccurrences;
	public WeekReportInfo(){}
	public WeekReportInfo(int year, int week) {
		super();
		Year = year;
		this.Week = week;
		TotalCode = Year*100+week;
		TotalOccurrences=1;
	}
	public void UpdatedError()
	{
		TotalOccurrences+=1;
	}
	public Key getMonthRateKey() {
		return WeekRateKey;
	}
	public int getYear() {
		return Year;
	}
	public int getWeek() {
		return Week;
	}
	public int getTotalOccurrences() {
		return TotalOccurrences;
	}
}
