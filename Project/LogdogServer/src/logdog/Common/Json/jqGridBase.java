package logdog.Common.Json;

/**
 * JqGrid에 데이터를 전송하기 위해서 기본적으로 사용하는 필드들을 추가해놓은 추상객체이다.
 * 이를 상속받고, 추가적인 데이터들을 넣어 jqGrid에서 사용하는 데이터를 담는 Json객체를 만든다.
 * @since 2012. 11. 15.오전 6:03:56
 * TODO
 * @author Karuana
 */
public class jqGridBase {
	/**
	 * 해당  json파일이 몇번째 페이지인지 저장한다.
	 */
	private int page;
	/**
	 *  총 페이지가 몇페이지인지 설정한다.
	 */
	private int total;
	/**
	 *   기록되는 레코드가 총 몇게인지 저장한다.
	 */
	private int recodes;
	/**
	 * Recode 값을 기록한다.
	 * @since 2012. 11. 15.오전 6:08:34
	 * TODO
	 * @author Karuana
	 * @param recodes
	 */
	public void setRecodes(int recodes) {
		this.recodes = recodes;
		// TODO Auto-generated constructor stub

	}
	/**
	 * 기본적으로 이 프로젝트는 1페이지만 사용하므로 page와 total의 값을 1로 초기화한다.
	 * @since 2012. 11. 15.오전 6:08:53
	 * TODO
	 * @author Karuana
	 */
	public jqGridBase() {
		super();
		// TODO Auto-generated constructor stub
		page=1;
		total=1;
	}
	
}
