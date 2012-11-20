package logdog.ErrorReport.DTO;

import javax.jdo.annotations.Persistent;

/**
 * 	유저 리포트 정보 중 상세 정보에 해당하는 정보들을 묶은 객체 
 * @since 2012. 11. 19.오전 8:05:35
 * TODO
 * @author Karuana
 */
public class UserReportData { 
	/**
	 * 디바이스 정보 
	 */
	private String DeviceName;
	/**
	 *  GPS  정보 
	 */
	private boolean GPSState; 
	/**
	 * 와이파이 정보 
	 */
	private boolean WifiState;
	/**
	 * 3G 정보 
	 */
	private boolean ProviderNetworkState;
	/**
	 * 스크린 
	 */
	private int ScreanWidth;
	/**
	 * 스크린 
	 */
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
