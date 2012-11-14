package logdog.Common.DataStore;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * 	DataStore에 접근하기 위한 싱글턴 객체이다. 
 * @since 2012. 11. 15.오전 5:57:30
 * TODO
 * @author Karuana
 */
public final class PMF {
	private PMF(){}

	/**
	 * 싱글턴 객체 
	 */
	private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	/**
	 * PMF를 리턴한다.
	 * @since 2012. 11. 15.오전 6:01:44
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public static PersistenceManagerFactory getPMF()
	{
		return pmfInstance;
	}
	
}