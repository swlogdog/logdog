package logdog.Setting.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.ErrorReport.DAO.ErrorTypeInfo;
import logdog.Setting.DAO.LogdogSettingInfo;

import com.google.appengine.api.datastore.Key;

public class LogdogSetter {
	private PersistenceManager jdoConnector;
	
	public LogdogSetter()
	{
		super();
		jdoConnector=null;
	}
	/**
	 *
	 * @since 2012. 11. 3.오후 11:45:47
	 * TODO
	 * @author Karuana
	 * @param settingInfo
	 * @return Key(잘못되면 null을 반환)
	 */
	public Key InitLogdogSettingInfo(LogdogSettingInfo settingInfo)
	{
		jdoConnector = PMF.getPMF().getPersistenceManager();


		try{	

			jdoConnector.makePersistent(settingInfo);
		}
		catch(Exception e){
				 
			return null;
		}
		finally{

			jdoConnector.close();
			
		}
		
		return settingInfo.getLogdogSystemrKey();
	}
	
	public void changeLogdogInfo(Key settingKey, LogdogSettingInfo settingInfo)
	{
		jdoConnector = PMF.getPMF().getPersistenceManager();
		
		try{	
			LogdogSettingInfo Systemsetter = jdoConnector.getObjectById(LogdogSettingInfo.class, settingKey);
			
			if(settingInfo.getDeveloperTeam()!=null)
			{
				Systemsetter.setDeveloperTeam(settingInfo.getDeveloperTeam());
			}
			if(settingInfo.getAppName()!=null)
			{
				Systemsetter.setAppName(settingInfo.getAppName());
			}
			Systemsetter.setLogFile(settingInfo.isLogFile());
		}
		catch(Exception e){
				
		
		}
		finally{
			jdoConnector.close();
		}

	}
	

	public final LogdogSettingInfo getLogdogSettingInfo()
	{
		
		jdoConnector = PMF.getPMF().getPersistenceManager();
		
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
	public Key getSettingKey()
	{
		jdoConnector = PMF.getPMF().getPersistenceManager();
	
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
		return (setting == null) ? null: settingInfo.getLogdogSystemrKey();
	}
}
