package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.Common.Json.BooleanResult;
import logdog.Setting.DAO.LogdogSettingInfo;

import com.google.gson.Gson;

public class ServerSettingGetter {



	public String getLogFileSetting()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
	
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
			SearchQuery.closeAll();
			jdoConnector.close();
		}
		Gson gson = new Gson();
		
		return gson.toJson(logfileResult);
	}
}
