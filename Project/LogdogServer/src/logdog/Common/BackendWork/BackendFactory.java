package logdog.Common.BackendWork;

import java.util.Map;

import logdog.Common.ServiceType;
import logdog.Common.BackendWork.DTO.BackendSettingData;

import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class BackendFactory {

	
	
	public static BackendWorkingSet GetBackendService(ServiceType backType)
	{
		BackendWorkingSet product=null;
		
		switch(backType)
		{
			case GOOGLE_APP_ENGINE:
				product = new GAEBackendWorker();
				break;
			case NON:
				product = null;
				break;
		}
		return product;
	}
	
	public static BackendSettingData GetDefaltSettingData(String backendUrl, Method Type, String json)
	{
		return new BackendSettingData("TaskQueue","report-backend",backendUrl,Type,json);
	}
	public static BackendSettingData GetDefaltSettingData(String backendUrl, Method Type, Map<String,String> map)
	{
		return new BackendSettingData("TaskQueue","report-backend",backendUrl,Type,map);
	}
}
