package logdog.Common.BackendWork;

import logdog.Common.BackendWork.DTO.BackendSettingData;

public interface BackendWorkingSet {

	public boolean CreateBackendWork(BackendSettingData BackendInfo);
	public boolean CreateBackendWorkJson(BackendSettingData BackendInfo);
	public boolean CreateBackendWorkTextData(BackendSettingData BackendInfo);
}
