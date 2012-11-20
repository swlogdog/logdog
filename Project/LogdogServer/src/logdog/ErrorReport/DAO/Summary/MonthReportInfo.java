package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class MonthReportInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key MonthRateKey;
	@Persistent
	int Year;//YYYY
	@Persistent
	int Month;//MM
	@Persistent
	int TotalCode;//YYYYDD
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
