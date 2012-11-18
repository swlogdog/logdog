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
/**
 * 	에러 타입 정보를 저장하는 클래스 
 * @since 2012. 11. 19.오전 7:57:27
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class ErrorTypeInfo {
	/**
	 *  기본 키 
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key E_ClassificationCode;
	
	/**
	 * 에러 명 - 기본 분류 정보 
	 */
	@Persistent 
	private String ErrorName;

	/**
	 *  밠생 클래스 - 기본 분류 정보 
	 */
	@Persistent
	private String OccurrenceClass;
	
	/**
	 *  코드 라인 - 기본 분류 정보 
	 */
	@Persistent
	private int codeLine;
	
	/**
	 *  발생 수 
	 */
	@Persistent 
	private int TotalOccurrences;
	
	/**
	 * 주간 발생 수 
	 */
	@Persistent
	private int WeeklyOccurrences;
	
	/**
	 * 최종 업데이트 된 날 
	 */
	@Persistent
	private Date LastUpdateDay;
	
	/**
	 * 일주일 체크용 임시 날짜 
	 */
	@Persistent
	private Date WeeklyUpdateDay;
	
	/**
	 * 버그 수정 여부 
	 */
	@Persistent
	private boolean BugClear;
	
	/**
	 *  콜스택 정보 
	 */
	@Persistent
	private List<String> Callstack;
		
	public ErrorTypeInfo(ErrorUniqueID errinfo) {
		super();
		//데이터 초기화 
		ErrorName = errinfo.getName();
		OccurrenceClass = errinfo.getClassname();
		codeLine = errinfo.getLine();
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
	
	public final int getCodeLine() {
		return codeLine;
	}
	public void setCallstack(List<String> callstack) {
		Callstack = callstack;
	}
	public final boolean isBugClear() {
		return BugClear;
	}

	public final Key getE_ClassificationCode() {
		return E_ClassificationCode;
	}

	public final String getErrorName() {
		return ErrorName;
	}

	public final String getOccurrenceClass() {
		return OccurrenceClass;
	}

	public final int getTotalOccurrences() {
		return TotalOccurrences;
	}

	public final int getWeeklyOccurrences() {
		return WeeklyOccurrences;
	}

	public final Date getLastUpdateDay() {
		return LastUpdateDay;
	}



	public boolean updateError()
	{
		try{
			Date AfterWeekTime = TimeUtil.GetTimeWeekAdder(WeeklyUpdateDay);
			LastUpdateDay = TimeUtil.GetNowDate();
			TotalOccurrences+=1;
			BugClear=false;
			
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
	public void setBugClear(boolean bugClear) {
		BugClear = bugClear;
	}
	

}
