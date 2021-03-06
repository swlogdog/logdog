package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.Common.Json.BooleanResult;
import logdog.Setting.DAO.LogdogSettingInfo;

import com.google.gson.Gson;

/**
 * 서버에 정보를 얻어오는 클래스로, 로그파일받기 여부를 가져오기 위해 사용한다.
 * @since 2012. 11. 19.오전 7:41:03
 * TODO
 * @author Karuana
 */
public class ServerSettingGetter {



	/**
	 *	로그 전송 여부 정보를 얻어온다.
	 * @since 2012. 11. 19.오전 7:41:30
	 * TODO
	 * @author Karuana
	 * @return
	 */
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
