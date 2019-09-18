package unila.edu.mx.model;

public class Result {
	
private String token;
	
	private int serverTime;
	
	private int expireTime;
	
	

	public Result() {
		super();
	}

	public Result(String token, int serverTime, int expireTime) {
		super();
		this.token = token;
		this.serverTime = serverTime;
		this.expireTime = expireTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getServerTime() {
		return serverTime;
	}

	public void setServerTime(int serverTime) {
		this.serverTime = serverTime;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

}
