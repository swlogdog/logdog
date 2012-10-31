
public class ClientReportData {
	
		/**
		* 핸드폰 모델 명
		*/
		public String Model;
		/**
		* 국가명
		*/
		public String National;
		/**
		* 에러 이름
		*/
		public String ErrorName;
		/**
		* 에러가 발생한 클래스 이름
		*/
		public String ErrorClassName;
		/**
		* 앱 버젼
		*/
		public String AppVersion;
		/**
		* OS 버젼
		*/
		public String OSVersion;

		/**
		* GPS On/Off
		*/
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
		* 화면 가로 크기
		*/
		public int ScreenWidth;
		/**
		* 화면 세로 크기
		*/
		public int ScreenHeight;

		/**
		* 보낼 콜스택 파일 이름
		*/
		public String CallStackFileName;

		/**
		* 보낼 로그 파일 이름
		*/
		public String LogFileName;
		public ClientReportData(int temp) {
			// TODO Auto-generated constructor stub
			Model = "iPhone 4";
			National = "KR";
			ErrorName = "divide by zero";
			ErrorClassName = "fule";
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

