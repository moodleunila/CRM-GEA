package unila.edu.mx.model.login;

public class ResultSession {
	
	private String sessionName;
	
	private String userId;
	
	private String version;
	
	private String vtigerVersion;	

	public ResultSession() {
		super();
	}

	public ResultSession(String sessionName, String userId, String version, String vtigerVersion) {
		super();
		this.sessionName = sessionName;
		this.userId = userId;
		this.version = version;
		this.vtigerVersion = vtigerVersion;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVtigerVersion() {
		return vtigerVersion;
	}

	public void setVtigerVersion(String vtigerVersion) {
		this.vtigerVersion = vtigerVersion;
	}
}
