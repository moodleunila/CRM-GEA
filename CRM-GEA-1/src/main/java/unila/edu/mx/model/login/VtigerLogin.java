package unila.edu.mx.model.login;

public class VtigerLogin {
	
	private String success;
	
	private ResultSession result;	

	public VtigerLogin() {
		super();
	}

	public VtigerLogin(String success, ResultSession result) {
		super();
		this.success = success;
		this.result = result;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ResultSession getResult() {
		return result;
	}

	public void setResult(ResultSession result) {
		this.result = result;
	}
	
	
}
