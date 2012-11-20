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
	/**
	 * Backend에 작업을 전달하기 위한 Push 큐 이름을 의미
	 */
	private String pushQuereName;
	/**
	 *  Backend서비스를 처리할 요소의 이름을 의미
	 */
	private String BackendName;
	/**
	 *  작업을 처리할 URL을 의미
	 */
	private String workURL;
	/**
	 *  해당 URL에 어떠한 HTTP 메소드로 전달할 것인지 설정한다.  
	 */
	private Method methodType;
	/**
	 * 처리를 위한 데이터를 Map 형태로 보낸다. 
	 */
	private Map<String,String> sendData;
	/**
	 *  처리를 위한 데이터를 String 형태로 보낸다.
	 */
	private String sendText;
	
	/**
	 * 처리를 위한 데이터를 사용하지 않는 Backend 작업에서 사용되는 생성자
	 * @since 2012. 11. 15.오전 5:39:50
	 * TODO 
	 * @author Karuana
	 * @param pushQuereName
	 * @param backendName
	 * @param workURL
	 * @param methodType
	 */
	public BackendSettingData(String pushQuereName, String backendName,
			String workURL, Method methodType) {
		super();
		this.pushQuereName = pushQuereName;
		BackendName = backendName;
		this.workURL = workURL;
		this.methodType = methodType;
	}

	/**
	 * Json 형태의 데이터를 Backend 작업에 전달하기 위해 사용되는 생성자
	 * @since 2012. 11. 15.오전 5:40:29
	 * TODO
	 * @author Karuana
	 * @param pushQuereName
	 * @param backendName
	 * @param workURL
	 * @param methodType
	 * @param sendJson
	 */
	public BackendSettingData(String pushQuereName, String backendName,
			String workURL, Method methodType, String sendJson) {
		super();
		this.pushQuereName = pushQuereName;
		BackendName = backendName;
		this.workURL = workURL;
		this.methodType = methodType;
		this.sendText = sendJson;
	}
	
	/**
	 * Map 형태의 데이터를 Backend에 보내기 위해 사용되는 생성자
	 * @since 2012. 11. 15.오전 5:41:03
	 * TODO
	 * @author Karuana
	 * @param pushQuereName
	 * @param backendName
	 * @param workURL
	 * @param methodType
	 * @param sendData
	 */
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
