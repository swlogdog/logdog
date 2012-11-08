package logdog.Setting.DTO;

public class LoginStateInfo {
	
	private LogInResult loginstate;
	
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
