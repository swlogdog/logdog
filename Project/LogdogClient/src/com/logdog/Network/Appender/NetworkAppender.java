package com.logdog.Network.Appender;

import com.logdog.ErrorReport.ReportData.ClientReportData;

public abstract class NetworkAppender {

	public NetworkAppender() {
		// TODO Auto-generated constructor stub
	}
	public abstract void SendMessage(String URL,ClientReportData data);

}
