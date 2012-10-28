package com.logdog.server.Model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.logdog.server.Util.TimeUtil;

@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class ErrorClassifiedInfo {
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
	
	public ErrorClassifiedInfo(String errorName, String occurrenceClass) {
		super();
		ErrorName = errorName;
		OccurrenceClass = occurrenceClass;
		TotalOccurrences=1;
		WeeklyOccurrences=1;
		LastUpdateDay = TimeUtil.GetNowDate();
		WeeklyUpdateDay =LastUpdateDay;
		BugClear = false;
		
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
	
	

}
