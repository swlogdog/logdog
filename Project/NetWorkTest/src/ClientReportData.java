import javax.xml.bind.annotation.XmlAttribute;


public class ClientReportData {
	
	/**
	* �ڵ��� �� ��
	*/
	
	public String Model;
	/**
	* ������
	*/
	
	public String National;
	/**
	* ���� �̸�
	*/

	public String ErrorName;
	/**
	* ������ �߻��� Ŭ���� �̸�
	*/

	public String ErrorClassName;
	/**
	* �� ����
	*/
	
	public String AppVersion;
	/**
	* OS ����
	*/
	
	public String OSVersion;

	/**
	* GPS On/Off
	*/
	@XmlAttribute
	public boolean GPS;
	/**
	* WiFi On/Off
	*/

	public boolean WiFi;
	/**
	* MobileNetwork(3G) On/Off
	*/

	public boolean MobileNetwork;
	/**
	* ȭ�� ���� ũ��
	*/

	public int ScreenWidth;
	/**
	* ȭ�� ���� ũ��
	*/

	public int ScreenHeight;

	/**
	* ���� �ݽ��� ���� �̸�
	*/
	
	public String CallStackFileName;

	/**
	* ���� �α� ���� �̸�
	*/

	public String LogFileName;

		public ClientReportData(int temp) {
			// TODO Auto-generated constructor stub
			Model = "iPhone 4";
			National = "KR";
			ErrorName = "Devided by Zero";
			ErrorClassName="Apple.java : 10";
	
			AppVersion = "2.0";
			OSVersion = "1.0";
			
			GPS = true;
			WiFi = true;
			MobileNetwork = true;
			ScreenWidth = 300;
			ScreenHeight = 200;
			CallStackFileName = "11";
			LogFileName = "00";
		}		



		public ClientReportData() {
			// TODO Auto-generated constructor stub
		}
}

