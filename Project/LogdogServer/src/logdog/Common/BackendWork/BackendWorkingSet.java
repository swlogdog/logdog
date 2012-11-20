package logdog.Common.BackendWork;

import logdog.Common.BackendWork.DTO.BackendSettingData;

/**
 * 	Backend 작업을 처리해주는 클래스를 추상화하기 위한 인터페이스
 * @since 2012. 11. 15.오전 5:48:11
 * TODO
 * @author Karuana
 */
public interface BackendWorkingSet {

	/**
	 *  Backend 설정 정보를 받아서 해당 설정에 맞게 pushqueue에 작업을 넣는다. 이 요소는 Map형태에 데이터를 보낼 때 사용된다.
	 * @since 2012. 11. 15.오전 5:48:36
	 * TODO
	 * @author Karuana
	 * @param BackendInfo
	 * @return result 결과
	 */
	public boolean CreateBackendWork(BackendSettingData BackendInfo);
	/**
	 * Backend 설정 정보를 받아서 해당 설정에 맞게 pushqueue에 작업을 넣는다. 이 요소는 Json 데이터를 보낼 때 사용된다.
	 * @since 2012. 11. 15.오전 5:49:56
	 * TODO
	 * @author Karuana
	 * @param BackendInfo
	 * @return
	 */
	public boolean CreateBackendWorkJson(BackendSettingData BackendInfo);
	/**
	 * Backend 설정 정보를 받아서 해당 설정에 맞게 pushqueue에 작업을 넣는다. 이 요소는 text/plain을 보낼 때 사용된다.
	 * @since 2012. 11. 15.오전 5:50:12
	 * TODO
	 * @author Karuana
	 * @param BackendInfo
	 * @return
	 */
	public boolean CreateBackendWorkTextData(BackendSettingData BackendInfo);
	/**
	 * Backend 설정 정보를 받아서 해당 설정에 맞게 pushqueue에 작업을 넣는다. 데이터는 보내지 않고 단순 요청만 할 때 사용된다.
	 * @since 2012. 11. 15.오전 5:50:34
	 * TODO
	 * @author Karuana
	 * @param BackendInfo
	 * @return
	 */
	public boolean CreateBackendWorkNoData(BackendSettingData BackendInfo);
}
