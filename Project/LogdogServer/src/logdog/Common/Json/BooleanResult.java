package logdog.Common.Json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 	 클라이언트 통신에서 true, false를 Json으로 리턴하기 위해서 사용한 Boolean  객체이다.
 * @since 2012. 11. 15.오전 6:02:09
 * TODO
 * @author Karuana
 */
@XmlRootElement
public class BooleanResult {
	
	/**
	 *  결과값: true, false만 저장 가능한 boolean 타입이다.
	 */
	@XmlAttribute
	private boolean result;
	
	/**
	 * 일반 생성자 
	 * @since 2012. 11. 15.오전 6:03:17
	 * TODO
	 * @author Karuana
	 */
	public BooleanResult(){}
	/**
	 * boolean값을 받아 초기화한다.
	 * @since 2012. 11. 15.오전 6:03:27
	 * TODO
	 * @author Karuana
	 * @param result
	 */
	public BooleanResult(boolean result) {
		super();
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
}
