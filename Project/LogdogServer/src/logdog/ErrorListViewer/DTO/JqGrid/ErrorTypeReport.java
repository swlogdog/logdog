package logdog.ErrorListViewer.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;

/**
 * 	JqGrid 기반으로 에러 간단 리스트를 그려줄 수 있는 json을 만들기위한 기본 템플릿이다.
 * @since 2012. 11. 18.오후 8:45:05
 * TODO
 * @author Karuana
 */
public class ErrorTypeReport extends jqGridBase{


	/**
	 * 데이터 정보를 가지고 있는 리스트로 HashMap<String,Object>을 객체로 가지고 있다.
	 */
	private ArrayList<HashMap<String,Object>> errors;
	/**
	 *	일반 생성자. 리스트를 초기화한다.
	 * @since 2012. 11. 18.오후 8:46:35
	 * TODO
	 * @author Karuana
	 */
	public ErrorTypeReport() {
		super();

		errors = new ArrayList<HashMap<String,Object>>();
	}

	/**
	 *	주어진 해쉬맵을 리스트에 추가한다.
	 * @since 2012. 11. 18.오후 8:46:48
	 * TODO
	 * @author Karuana
	 * @param Error
	 */
	public void addError(HashMap<String,Object> Error)
	{
		errors.add(Error);
	}
	
	
}
