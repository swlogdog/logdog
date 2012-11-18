package logdog.ErrorReport.DAO;

import java.util.Calendar;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import logdog.Common.TimeUtil;
import logdog.ErrorReport.DTO.ClientReportData;
import logdog.ErrorReport.DTO.UserReportData;
import logdog.ErrorReport.DTO.UserSummaryInfo;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

/**
 * 유저의 에러 리포트 데이터를 저장하기 위한 클래스
 * 
 * @since 2012. 11. 19.오전 7:51:46 TODO
 * @author Karuana
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ErrorReportInfo {
	/**
	 * 기본 키
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key E_ReportCode;

	// �ܷ�Ű
	/**
	 * 에러 타입 키 
	 */
	@Persistent
	private Key E_ClassificationCode;

	/**
	 *  로그 파일 키 
	 */
	@Persistent
	private BlobKey LogBolbKey;
	// �Ӽ�
	/**
	 * 국가 코드 
	 */
	@Persistent
	private String CountryName;

	/**
	 * 앱 버젼 
	 */
	@Persistent
	private String AppVersion;

	/**
	 *  OS 버젼 
	 */
	@Persistent
	private String OSVersion;

	/**
	 * 디바이스 코드 
	 */
	@Persistent
	private String DeviceName;

	/**
	 *  GPS 정보 
	 */
	@Persistent
	private boolean GPSState;

	/**
	 * 와이파이 켜짐 여부 
	 */
	@Persistent
	private boolean WifiState;

	/**
	 * 3g 켜짐 여부
	 */
	@Persistent
	private boolean ProviderNetworkState;

	/**
	 * 스크린 크기 
	 */
	@Persistent
	private int ScreanWidth;

	/**
	 * 스크린 크기 
	 */
	@Persistent
	private int ScreanHeight;

	/**
	 * 접수된 년도 
	 */
	@Persistent
	private int YearCode;

	/**
	 * 접수된 달과 일 
	 */
	@Persistent
	private int TimeCode;

	/**
	 * 달 
	 */
	@Persistent
	int Month;// MM
	/**
	 * 주 
	 */
	@Persistent
	int Week;// Week OF Year Max 52 Or 53

	public ErrorReportInfo() {
		super();

	}

	/**
	 * 
	 * @since 2012. 11. 2.오전 4:55:34 TODO ClassificationCode는 Backend로 갱신하기
	 * @author Karuana
	 * @param report
	 */
	public ErrorReportInfo(ClientReportData report) {
		super();
		CountryName = report.National;
		AppVersion = report.AppVersion;
		OSVersion = report.OSVersion;
		DeviceName = report.Model;
		GPSState = report.GPS;
		WifiState = report.WiFi;
		ProviderNetworkState = report.MobileNetwork;
		ScreanWidth = report.ScreenWidth;
		ScreanHeight = report.ScreenHeight;
		YearCode = TimeUtil.GetNowYear();
		TimeCode = TimeUtil.GetNowTimeCode();
		Month = TimeCode / 100;
		Week = TimeUtil.GetWeek();
	}

	/**
	 * TestCase용 생성자
	 * 
	 * @since 2012. 11. 17.오후 6:54:14 TODO
	 * @author Karuana
	 * @param report
	 * @param TestCase
	 */
	public ErrorReportInfo(ClientReportData report, boolean TestCase) {
		super();
		try {
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
					"yyyy-MM-dd.HH.mm.ss");

			java.util.Date date = format.parse(report.ReportTime);
			int Month = date.getMonth() + 1;
			int days = date.getDate();
			int Timecode = Month * 100 + days;
			Calendar Adder = Calendar.getInstance();
			Adder.setTime(date);

			CountryName = report.National;
			AppVersion = report.AppVersion;
			OSVersion = report.OSVersion;
			DeviceName = report.Model;
			GPSState = report.GPS;
			WifiState = report.WiFi;
			ProviderNetworkState = report.MobileNetwork;
			ScreanWidth = report.ScreenWidth;
			ScreanHeight = report.ScreenHeight;
			YearCode = date.getYear() + 1900;
			TimeCode = Timecode;
			this.Month = Month;
			Week = Adder.get(Calendar.WEEK_OF_YEAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final Key getE_ReportCode() {
		return E_ReportCode;
	}

	public void setE_ClassificationCode(Key e_ClassificationCode) {
		E_ClassificationCode = e_ClassificationCode;
	}

	public final Key getE_ClassificationCode() {
		return E_ClassificationCode;
	}

	public void setLogBolbKey(BlobKey logBolbKey) {
		LogBolbKey = logBolbKey;
	}

	public BlobKey getLogBolbKey() {
		return LogBolbKey;
	}

	public UserReportData getUserData() {
		return new UserReportData(DeviceName, GPSState, WifiState,
				ProviderNetworkState, ScreanWidth, ScreanHeight);
	}

	public UserSummaryInfo getSummary() {
		return new UserSummaryInfo(CountryName, AppVersion, OSVersion,
				YearCode, TimeCode);
	}

}
