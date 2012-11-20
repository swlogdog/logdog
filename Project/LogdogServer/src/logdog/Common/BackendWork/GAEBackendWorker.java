package logdog.Common.BackendWork;

import java.util.Map;

import logdog.Common.BackendWork.DTO.BackendSettingData;
import com.google.appengine.api.backends.BackendServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

/**
 *  GAE Backend 작업을 위해 Push Queue에 작업을 넣는 과정을 처리하는 클래스이다.
 *  이 클래스는 BackendWorkingSet라는 인터페이스를 구현받고 있으며, Backend팩토리를 통하여 생성해야만 한다. 
 * @since 2012. 11. 15.오전 5:51:02
 * TODO
 * @author Karuana
 */
public class GAEBackendWorker implements BackendWorkingSet {

	/* (non-Javadoc)
	 * @see logdog.Common.BackendWork.BackendWorkingSet#CreateBackendWork(logdog.Common.BackendWork.DTO.BackendSettingData)
	 */
	public boolean CreateBackendWork(BackendSettingData BackendInfo)
	{
	  	Queue queue = QueueFactory.getQueue(BackendInfo.getPushQuereName());
	  	TaskOptions taskOptions = TaskOptions.Builder.withUrl(BackendInfo.getWorkURL());
	  	
	  	Map<String,String> sendData = BackendInfo.getSendData();
	  	Object[] Keylist= sendData.keySet().toArray();
	  	
	  	for(int i=0;i<sendData.size();i++)
	  	{
	  		taskOptions.param((String)Keylist[i],sendData.get(Keylist[i]));
	  	}
	  	
	  	taskOptions.header("Host", BackendServiceFactory.getBackendService().getBackendAddress(BackendInfo.getBackendName()))
          .method(BackendInfo.getMethodType());
		
	  	queue.add(taskOptions);
		return true; 
		
	}
	/* (non-Javadoc)
	 * @see logdog.Common.BackendWork.BackendWorkingSet#CreateBackendWorkJson(logdog.Common.BackendWork.DTO.BackendSettingData)
	 */
	public boolean CreateBackendWorkJson(BackendSettingData BackendInfo)
	{
		try{
	  	Queue queue = QueueFactory.getQueue(BackendInfo.getPushQuereName());
	  	TaskOptions taskOptions = TaskOptions.Builder.withUrl(BackendInfo.getWorkURL());
	  	
	  	taskOptions.payload(BackendInfo.getSendText().getBytes(),"application/json");

	  	taskOptions.header("Host", BackendServiceFactory.getBackendService().getBackendAddress(BackendInfo.getBackendName()))
          .method(BackendInfo.getMethodType());
		
	  	queue.add(taskOptions);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return false;
		}
	  	return true; 
	}
	/* (non-Javadoc)
	 * @see logdog.Common.BackendWork.BackendWorkingSet#CreateBackendWorkTextData(logdog.Common.BackendWork.DTO.BackendSettingData)
	 */
	public boolean CreateBackendWorkTextData(BackendSettingData BackendInfo)
	{
		try{
	  	Queue queue = QueueFactory.getQueue(BackendInfo.getPushQuereName());
	  	TaskOptions taskOptions = TaskOptions.Builder.withUrl(BackendInfo.getWorkURL());
	  	
	  	taskOptions.payload(BackendInfo.getSendText().getBytes(),"text/plain");

	  	taskOptions.header("Host", BackendServiceFactory.getBackendService().getBackendAddress(BackendInfo.getBackendName()))
          .method(BackendInfo.getMethodType());
		
	  	queue.add(taskOptions);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return false;
		}
	  	return true; 
	}
	
	/* (non-Javadoc)
	 * @see logdog.Common.BackendWork.BackendWorkingSet#CreateBackendWorkNoData(logdog.Common.BackendWork.DTO.BackendSettingData)
	 */
	public boolean CreateBackendWorkNoData(BackendSettingData BackendInfo)
	{
		try{
		  	Queue queue = QueueFactory.getQueue(BackendInfo.getPushQuereName());
		  	TaskOptions taskOptions = TaskOptions.Builder.withUrl(BackendInfo.getWorkURL());

		  	taskOptions.header("Host", BackendServiceFactory.getBackendService().getBackendAddress(BackendInfo.getBackendName()))
	          .method(BackendInfo.getMethodType());
			
		  	queue.add(taskOptions);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		  	return true; 
	}
}
