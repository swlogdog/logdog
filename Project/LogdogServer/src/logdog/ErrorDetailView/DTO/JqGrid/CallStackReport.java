package logdog.ErrorDetailView.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;

/**
 *  JqGrid에 Callstack 정보를 출력하기 위한 json을 생성하는 객체이다.
 * @since 2012. 11. 15.오전 7:27:07
 * TODO
 * @author Karuana
 */
public class CallStackReport extends jqGridBase{
	/**
	 * CallStack Data가 들어 있는 배열이다.
	 */
	private ArrayList<HashMap<String,String>> callstack;
	public CallStackReport() {
		super();
		callstack = new ArrayList<HashMap<String,String>>();
	}

	/**
	 *	CallStack의 StackTrace를 입력받아 이를 기반으로 HashMap을 생성한뒤 해당 데이터를 배열에 삽입한다.
	 * @since 2012. 11. 15.오전 7:28:06
	 * TODO
	 * @author Karuana
	 * @param stacktrace
	 */
	public void addcallstack(String stacktrace)
	{
		HashMap<String,String> stack= new HashMap<String,String>();
		stack.put("line", ""+callstack.size());
		stack.put("stack", stacktrace);
		callstack.add(stack);
	}
}
