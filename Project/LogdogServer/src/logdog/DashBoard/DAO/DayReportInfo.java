package logdog.DashBoard.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class DayReportInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key DayRateKey;
	@Persistent
	int Year;//YYYY
	@Persistent
	int MDay;//MMDD
	@Persistent 
	private int TotalOccurrences;
	public DayReportInfo(){}
	public DayReportInfo(int year, int mDay) {
		super();
		Year = year;
		MDay = mDay;
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
