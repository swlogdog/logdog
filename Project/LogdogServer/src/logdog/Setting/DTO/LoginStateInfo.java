package logdog.Setting.DTO;

/**
 * 	로그인 정보를 담는 객체이다.
 * @since 2012. 11. 19.오전 8:16:36
 * TODO
 * @author Karuana
 */
public class LoginStateInfo {
	
	/**
	 * 로그인 상태 
	 */
	private LogInResult loginstate;
	
	/**
	 * 유저 명 
	 */
	private String UserNmae;

	public LoginStateInfo(LogInResult loginstate, String userNmae) {
		super();
		this.loginstate = loginstate;
		UserNmae = userNmae;
	}

	public LogInResult getLoginstate() {
		return loginstate;
	}

	public String getUserNmae() {
		return UserNmae;
	}
	
	
}
