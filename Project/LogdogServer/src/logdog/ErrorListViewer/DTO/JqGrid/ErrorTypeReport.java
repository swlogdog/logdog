package logdog.ErrorListViewer.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorTypeReport {

	private int page;
	private int total;
	private int recodes;
	private ArrayList<HashMap<String,Object>> errors;
	public ErrorTypeReport() {
		super();
		// TODO Auto-generated constructor stub
		page=1;
		total=1;
		errors = new ArrayList<HashMap<String,Object>>();
	}
	public void setRecodes(int recodes) {
		this.recodes = recodes;
	}
	
	public void addError(HashMap<String,Object> Error)
	{
		errors.add(Error);
	}
	
	
}
