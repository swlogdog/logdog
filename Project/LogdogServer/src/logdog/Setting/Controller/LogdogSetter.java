package logdog.Setting.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.Setting.DAO.LogdogSettingInfo;

import com.google.appengine.api.datastore.Key;

/**
 * 	설정 정보를 변경하는 클래스 
 * @since 2012. 11. 19.오전 8:13:14
 * TODO
 * @author Karuana
 */
public class LogdogSetter {

	/**
	 *	설정 정보를 초기화 한다. 
	 * @since 2012. 11. 3.오후 11:45:47
	 * TODO
	 * @author Karuana
	 * @param settingInfo
	 * @return Key(잘못되면 null을 반환)
	 */
	public Key InitLogdogSettingInfo(boolean logfile)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		LogdogSettingInfo settingInfo = new LogdogSettingInfo(logfile);

		try{	
			if(this.getSettingKey() == null)	
				jdoConnector.makePersistent(settingInfo);
			else 
				return getSettingKey();
		}
		catch(Exception e){
			e.printStackTrace();
			  System.out.println(e.getClass() + "  " + e.getCause());
			return null;
		}
		finally{

			jdoConnector.close();
			
		}
		
		return settingInfo.getLogdogSystemrKey();
	}
	
	/**
	 *	셋팅 정보를 변경한다. 
	 * @since 2012. 11. 19.오전 8:14:17
	 * TODO
	 * @author Karuana
	 * @param settingInfo
	 */
	public void changeLogdogInfo(boolean settingInfo)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		Key settingKey= getSettingKey();
		try{	
			LogdogSettingInfo Systemsetter = jdoConnector.getObjectById(LogdogSettingInfo.class, settingKey);
			Systemsetter.setLogFile(settingInfo);
		}
		catch(Exception e){
				
		
		}
		finally{
			jdoConnector.close();
		}

	}
	

	/**
	 *	셋팅 정보를 얻어온다. 
	 * @since 2012. 11. 19.오전 8:14:31
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public final LogdogSettingInfo getLogdogSettingInfo()
	{
		
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		Query SearchQuery = jdoConnector.newQuery(LogdogSettingInfo.class);
		List<LogdogSettingInfo> setting=null;
		
		LogdogSettingInfo settingInfo=null;
		try{	
			setting = (List<LogdogSettingInfo>) SearchQuery.execute();
			settingInfo = setting.get(0);
		}
		catch(Exception e){
				
		
		}
		finally{
			jdoConnector.close();
		}
		return settingInfo;
	}
	
	/**
	 *	셋팅의 키 값을 가져온다.
	 * @since 2012. 11. 3.오후 11:42:44
	 * TODO
	 * @author Karuana
	 * @return Key(존재하지 않으면 null을 반환)
	 */
	private Key getSettingKey()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
	
		Query SearchQuery = jdoConnector.newQuery(LogdogSettingInfo.class);
		List<LogdogSettingInfo> setting=null;
		
		LogdogSettingInfo settingInfo = null;
		try{	
			setting = (List<LogdogSettingInfo>) SearchQuery.execute();
			if(setting.size()>0)
				settingInfo = setting.get(0);
		}
		catch(Exception e){
				
		
		}
		finally{
			jdoConnector.close();
		}
		return (settingInfo == null) ? null: settingInfo.getLogdogSystemrKey();
	}
}
