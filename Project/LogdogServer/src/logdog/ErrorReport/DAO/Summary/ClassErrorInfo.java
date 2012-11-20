package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 *  클래스마다의 에러 전송 정보를 보관하는 DataStore 객체이다.
 * @since 2012. 11. 19.오전 7:42:36
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class ClassErrorInfo {
	/**
	 * 분류 키 
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key ErrorKey;
	
	/**
	 *  에러가 발견된 클래스 명 
	 */
	@Persistent
	private String OccurrenceClass;
	
	/**
	 *  발견 횟수 
	 */
	@Persistent 
	private int TotalOccurrences;

	public ClassErrorInfo(){}
	public ClassErrorInfo(String occurrenceClass) {
		super();
		OccurrenceClass = occurrenceClass;
		TotalOccurrences = 1;
	}
	public Key getErrorKey() {
		return ErrorKey;
	}
	public String getOccurrenceClass() {
		return OccurrenceClass;
	}
	public int getTotalOccurrences() {
		return TotalOccurrences;
	}
	
	public void UpdatedError()
	{
		TotalOccurrences+=1;
	}
	
}
