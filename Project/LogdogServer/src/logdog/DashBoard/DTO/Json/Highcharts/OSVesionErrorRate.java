package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;

/**
 *  OSVersion에 대한 정보를 저장하는 DTO 객체이다.
 *  HighChart를 위해 만든 용도이지만 이 자체만으로는 쓰이지 않고 VersionReportRate와 연동하여 사용한다.
 * @since 2012. 11. 15.오전 6:33:20
 * TODO
 * @author Karuana
 */
public class OSVesionErrorRate {

	/**
	 *  OSVersion 명 
	 */
	private String name;
	/**
	 *  이 OS version에서 발생한 에러량들, APP버젼에 맞게 데이터를 저장하기 위해 배열 형태로 되어 있다.
	 */
	private ArrayList<Integer> data;
	/**
	 *	생성자.
	 * @since 2012. 11. 15.오전 6:35:42
	 * TODO
	 * @author Karuana
	 * @param osVersion
	 */
	public OSVesionErrorRate(String osVersion) {
		super();
		name = osVersion;
		data = new ArrayList<Integer>();
	}
	
	/**
	 * 배열 구간을 초기화한다. HighChart에 그래프를 그릴때 특정 AppVersion에는 에러가 없을 수 있다. 
	 * 이렇게 비는 요소에는 0을 추가해줘야하는데, 이 작업을 초기화를 통해 해준다.
	 * @since 2012. 11. 15.오전 6:35:54
	 * TODO
	 * @author Karuana
	 * @param size
	 */
	public void initRate(int size)
	{
		for(int i=0;i<size;i++)
			data.add(0);
	}
	/**
	 *	에러량을 저장하는 리스트의 in번째 자리에 r 값을 저장한다.
	 * @since 2012. 11. 15.오전 6:36:56
	 * TODO
	 * @author Karuana
	 * @param in
	 * @param r
	 */
	public void setRate(int in,int r)
	{
		data.set(in,r);
	}
	/**
	 * 이 객체가 저장하고 있는 OS Version 을 얻어온다.
	 * @since 2012. 11. 15.오전 6:37:46
	 * TODO
	 * @author Karuana
	 * @return OS version
	 */
	public String getOsVersion() {
		return name;
	}
	
	/**
	 *	에러량을 저장하는 리스트의 길이를 리턴한다.
	 * @since 2012. 11. 15.오전 6:38:13
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public int getDatasize()
	{
		return data.size();
	}
	 
}
