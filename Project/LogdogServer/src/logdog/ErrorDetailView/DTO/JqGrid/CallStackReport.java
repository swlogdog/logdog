package logdog.ErrorDetailView.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;

public class CallStackReport extends jqGridBase{
	private ArrayList<HashMap<String,String>> callstack;
	public CallStackReport() {
		super();
		callstack = new ArrayList<HashMap<String,String>>();
	}

	public void addcallstack(String stacktrace)
	{
		HashMap<String,String> stack= new HashMap<String,String>();
		stack.put("line", ""+callstack.size());
		stack.put("stack", stacktrace);
		callstack.add(stack);
	}
}
