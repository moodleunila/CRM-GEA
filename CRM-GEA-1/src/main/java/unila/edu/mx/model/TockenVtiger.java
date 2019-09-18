package unila.edu.mx.model;

public class TockenVtiger {
	
	private String success;
	
	private Result result;
	

	public TockenVtiger() {
		super();
	}

	public TockenVtiger(String success, Result result) {
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

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}	
	
}
