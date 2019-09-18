package unila.edu.mx.model.create;

public class VtigerCreate {
	
	private String success;
	
	private User result;
	
	public VtigerCreate() {
		super();
	}

	public VtigerCreate(String success, User result) {
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

	public User getResult() {
		return result;
	}

	public void setResult(User result) {
		this.result = result;
	}
}
