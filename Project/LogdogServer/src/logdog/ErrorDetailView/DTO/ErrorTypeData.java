package logdog.ErrorDetailView.DTO;

import logdog.Common.TimeUtil;
import logdog.ErrorReport.DAO.ErrorTypeInfo;

public class ErrorTypeData {
	private String ErrorName;
	private String ErrorClass;
	private int CodeLine;
	private String ReportCount;
	private String LastUpdated;
	private String BugClear;
	
	public ErrorTypeData(ErrorTypeInfo info) {
		super();
		ErrorName = info.getErrorName();
		ErrorClass = info.getOccurrenceClass();
		CodeLine = info.getCodeLine();
		ReportCount = info.getTotalOccurrences();
		LastUpdated = TimeUtil.GetTime2String(info.getLastUpdateDay());
		BugClear = new Boolean(info.isBugClear()).toString();
		System.out.print(BugClear);
		}
	
}
