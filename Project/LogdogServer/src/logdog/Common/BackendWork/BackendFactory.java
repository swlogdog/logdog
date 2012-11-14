package logdog.Common.BackendWork;

import java.util.Map;

import logdog.Common.ServiceType;
import logdog.Common.BackendWork.DTO.BackendSettingData;

import com.google.appengine.api.taskqueue.TaskOptions.Method;

/**
 * Backend 작업에 필요한 클래스들을 생성해주는 펙토리 메소드를 모아둔 클래스
 * @since 2012. 11. 15.오전 5:42:55
 * TODO
 * @author Karuana
 */
public class BackendFactory {

	
	
	/**
	 *	Backend작업을 처리하는 객체를 리턴해준다. 현재 이 프로젝트는 구글 앱 엔진 기반으로만 구현되어있지만, 최대한 앞으로 확정 가능하도록 다른 서비스 타입이 존재한다고 가정하였다.
	 * @since 2012. 11. 15.오전 5:42:52
	 * TODO
	 * @author Karuana
	 * @param backType
	 * @return BackendWorkingSet (요청한 서비스 타입에 맞는 객체를 리턴해준다)
	 */
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
	
	/**
	 *	Backend 서비스 설정에 대한 정보를 가지는 DTO객체를 생성해 리턴해준다.
	 * @since 2012. 11. 15.오전 5:46:03
	 * TODO
	 * @author Karuana
	 * @param backendUrl
	 * @param Type
	 * @param json
	 * @return BackendSettingData(서비스 정보를 가지고 있는 DTO객체)
	 */
	public static BackendSettingData GetDefaltSettingData(String backendUrl, Method Type, String json)
	{
		return new BackendSettingData("TaskQueue","report-backend",backendUrl,Type,json);
	}
	/**
	 * Backend 서비스 설정에 대한 정보를 가지는 DTO객체를 생성해 리턴해준다.
	 * @since 2012. 11. 15.오전 5:47:41
	 * TODO
	 * @author Karuana
	 * @param backendUrl
	 * @param Type
	 * @param map
	 * @return BackendSettingData(서비스 정보를 가지고 있는 DTO객체)
	 */
	public static BackendSettingData GetDefaltSettingData(String backendUrl, Method Type, Map<String,String> map)
	{
		return new BackendSettingData("TaskQueue","report-backend",backendUrl,Type,map);
	}
	/**
	 * Backend 서비스 설정에 대한 정보를 가지는 DTO객체를 생성해 리턴해준다.
	 * @since 2012. 11. 15.오전 5:47:56
	 * TODO
	 * @author Karuana
	 * @param backendUrl
	 * @param Type
	 * @return BackendSettingData(서비스 정보를 가지고 있는 DTO객체)
	 */
	public static BackendSettingData GetDefaltSettingData(String backendUrl, Method Type)
	{
		return new BackendSettingData("TaskQueue","report-backend",backendUrl,Type);
	}
}
