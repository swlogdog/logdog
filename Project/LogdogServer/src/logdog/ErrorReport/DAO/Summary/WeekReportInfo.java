package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class WeekReportInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key WeekRateKey;
	@Persistent
	int Year;//YYYY
	@Persistent
	int Week;//Week OF Year Max 52 Or 53
	@Persistent
	int TotalCode;//YYYYWW
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
