package logdog.ErrorReport.DAO;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import logdog.Common.TimeUtil;
import logdog.ErrorReport.DTO.ErrorUniqueID;

import com.google.appengine.api.datastore.Key;
// callStack����� �ȱ�� 
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class ErrorTypeInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key E_ClassificationCode;
	
	@Persistent 
	private String ErrorName;

	@Persistent
	private String OccurrenceClass;
	
	@Persistent 
	private int TotalOccurrences;
	
	@Persistent
	private int WeeklyOccurrences;
	
	@Persistent
	private Date LastUpdateDay;
	
	@Persistent
	private Date WeeklyUpdateDay;
	
	@Persistent
	private boolean BugClear;
	
	@Persistent
	private List<String> Callstack;
		
	public ErrorTypeInfo(ErrorUniqueID errinfo) {
		super();
		//데이터 초기화 
		ErrorName = errinfo.getName();
		OccurrenceClass = errinfo.getClassname();
		TotalOccurrences=0;
		WeeklyOccurrences=0;
		LastUpdateDay = TimeUtil.GetNowDate();
		WeeklyUpdateDay =LastUpdateDay;
		BugClear = false;
		Callstack=null; 
		
	}
	public ErrorTypeInfo(){}
	
	
	public List<String> getCallstack() {
		return Callstack;
	}
	public void setCallstack(List<String> callstack) {
		Callstack = callstack;
	}
	public boolean isBugClear() {
		return BugClear;
	}

	public void setBugClear(boolean bugClear) {
		BugClear = bugClear;
	}

	public Key getE_ClassificationCode() {
		return E_ClassificationCode;
	}

	public String getErrorName() {
		return ErrorName;
	}

	public String getOccurrenceClass() {
		return OccurrenceClass;
	}

	public int getTotalOccurrences() {
		return TotalOccurrences;
	}

	public int getWeeklyOccurrences() {
		return WeeklyOccurrences;
	}

	public Date getLastUpdateDay() {
		return LastUpdateDay;
	}

	public Date getWeeklyUpdateDay() {
		return WeeklyUpdateDay;
	}


	public boolean updateError()
	{
		try{
			Date AfterWeekTime = TimeUtil.GetTimeWeekAdder(WeeklyUpdateDay);
			LastUpdateDay = TimeUtil.GetNowDate();
			TotalOccurrences+=1;
			
			if(TimeUtil.CheckNowTime(AfterWeekTime))
			{
				WeeklyUpdateDay = LastUpdateDay;
				WeeklyOccurrences=1;
			}
			else{
				WeeklyOccurrences+=1;
			}
	
		}
		catch(Exception e)
		{	
			return false;
		}
		return true;
	}
	

}
