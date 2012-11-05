package logdog.ErrorReport.DTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientReportData {
		/**
		 * 에러 발생 시간
		 */	
		public String ReportTime;

		/**
		* �ڵ��� �� ��
		*/
		@XmlAttribute
		public String Model;
		/**
		* ������
		*/
		@XmlAttribute
		public String National;
		/**
		* ���� �̸�
		*/
		@XmlAttribute
		public String ErrorName;
		/**
		* ������ �߻��� Ŭ���� �̸�
		*/
		@XmlAttribute
		public String ErrorClassName;
		/**
		* �� ����
		*/
		@XmlAttribute
		public String AppVersion;
		/**
		* OS ����
		*/
		@XmlAttribute
		public String OSVersion;

		/**
		* GPS On/Off
		*/
		@XmlAttribute
		public boolean GPS;
		/**
		* WiFi On/Off
		*/
		@XmlAttribute
		public boolean WiFi;
		/**
		* MobileNetwork(3G) On/Off
		*/
		@XmlAttribute
		public boolean MobileNetwork;
		/**
		* ȭ�� ���� ũ��
		*/
		@XmlAttribute
		public int ScreenWidth;
		/**
		* ȭ�� ���� ũ��
		*/
		@XmlAttribute
		public int ScreenHeight;

		/**
		* ���� �ݽ��� ���� �̸�
		*/
		@XmlAttribute
		public String CallStackFileName;

		/**
		* ���� �α� ���� �̸�
		*/
		@XmlAttribute
		public String LogFileName;
	
		public ClientReportData(boolean temp) {
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
