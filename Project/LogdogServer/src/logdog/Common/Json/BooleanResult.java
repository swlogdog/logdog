package logdog.Common.Json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BooleanResult {
	
	@XmlAttribute
	private boolean result;
	public BooleanResult(){}
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
