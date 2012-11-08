package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.Common.Json.BooleanResult;
import logdog.Setting.DAO.LogdogSettingInfo;

import com.google.gson.Gson;

public class ServerSettingGetter {

	private PersistenceManager jdoConnector;
	
	public ServerSettingGetter() {
		super();
		this.jdoConnector = null;
	}

	public String getLogFileSetting()
	{
		jdoConnector = PMF.getPMF().getPersistenceManager();
	
		BooleanResult logfileResult = new BooleanResult(false);
		Query SearchQuery = jdoConnector.newQuery(LogdogSettingInfo.class);
		List<LogdogSettingInfo> setting=null;
		
		LogdogSettingInfo settingInfo=null;
		try{	
			setting = (List<LogdogSettingInfo>) SearchQuery.execute();
			if(setting.size()>0)
			{
				settingInfo = setting.get(0);
				logfileResult.setResult(settingInfo.isLogFile());				
			}
		}
		catch(Exception e){
				
		
		}
		finally{
			jdoConnector.close();
		}
		Gson gson = new Gson();
		
		return gson.toJson(logfileResult);
	}
}
