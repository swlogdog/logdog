package logdog.ErrorListViewer.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;

public class ErrorTypeReport extends jqGridBase{


	private ArrayList<HashMap<String,Object>> errors;
	public ErrorTypeReport() {
		super();

		errors = new ArrayList<HashMap<String,Object>>();
	}

	public void addError(HashMap<String,Object> Error)
	{
		errors.add(Error);
	}
	
	
}
