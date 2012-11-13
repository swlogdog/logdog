package logdog.ErrorReport.DTO;

import javax.jdo.annotations.Persistent;

public class UserReportData { 
	private String DeviceName;
	private boolean GPSState; 
	private boolean WifiState;
	private boolean ProviderNetworkState;
	private int ScreanWidth;
	private int ScreanHeight;

	public UserReportData(String deviceName, boolean gPSState,
			boolean wifiState, boolean providerNetworkState, int screanWidth,
			int screanHeight) {
		super();
		DeviceName = deviceName;
		GPSState = gPSState;
		WifiState = wifiState;
		ProviderNetworkState = providerNetworkState;
		ScreanWidth = screanWidth;
		ScreanHeight = screanHeight;
	}
	public String getDeviceName() {
		return DeviceName;
	}
	public boolean isGPSState() {
		return GPSState;
	}
	public boolean isWifiState() {
		return WifiState;
	}
	public boolean isProviderNetworkState() {
		return ProviderNetworkState;
	}
	public int getScreanWidth() {
		return ScreanWidth;
	}
	public int getScreanHeight() {
		return ScreanHeight;
	}
	

}
