package logdog.Common.BackendWork;

import java.util.Map;

import logdog.Common.BackendWork.DTO.BackendSettingData;
import com.google.appengine.api.backends.BackendServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class GAEBackendWorker implements BackendWorkingSet {

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
