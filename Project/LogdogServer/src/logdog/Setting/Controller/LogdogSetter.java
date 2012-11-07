package logdog.Setting.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.Setting.DAO.LogdogSettingInfo;

import com.google.appengine.api.datastore.Key;

public class LogdogSetter {

	/**
	 *
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
	 *
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
