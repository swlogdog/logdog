package logdog.Common.BackendWork.DTO;
import java.util.Map;

import com.google.appengine.api.taskqueue.TaskOptions.Method;
/**
 *  GAE의 Backend 작업을 위한 셋팅정보를 담는 DTO객체이다. 
 * @since 2012. 11. 2.오후 5:26:54
 * TODO	GAE Backend Setting
 * @author Karuana
 */
public class BackendSettingData {
	private String pushQuereName;
	private String BackendName;
	private String workURL;
	private Method methodType;
	private Map<String,String> sendData;
	private String sendText;
	
	public BackendSettingData(String pushQuereName, String backendName,
			String workURL, Method methodType) {
		super();
		this.pushQuereName = pushQuereName;
		BackendName = backendName;
		this.workURL = workURL;
		this.methodType = methodType;
	}

	public BackendSettingData(String pushQuereName, String backendName,
			String workURL, Method methodType, String sendJson) {
		super();
		this.pushQuereName = pushQuereName;
		BackendName = backendName;
		this.workURL = workURL;
		this.methodType = methodType;
		this.sendText = sendJson;
	}
	
	public BackendSettingData(String pushQuereName, String backendName,
			String workURL, Method methodType, Map<String, String> sendData) {
		super();
		this.pushQuereName = pushQuereName;
		BackendName = backendName;
		this.workURL = workURL;
		this.methodType = methodType;
		this.sendData = sendData;
	}
	public String getBackendName() {
		return BackendName;
	}
	public String getSendText() {
		return sendText;
	}
	public String getPushQuereName() {
		return pushQuereName;
	}
	public String getWorkURL() {
		return workURL;
	}
	public Method getMethodType() {
		return methodType;
	}
	public Map<String, String> getSendData() {
		return sendData;
	}
	
}
