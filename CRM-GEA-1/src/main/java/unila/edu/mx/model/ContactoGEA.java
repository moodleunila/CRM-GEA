package unila.edu.mx.model;

public class ContactoGEA {
	
	private String contactNo;
	
	private String estatusGEA;
	

	public ContactoGEA() {
		super();
	}

	public ContactoGEA(String contactNo, String estatusGEA) {
		super();
		this.contactNo = contactNo;
		this.estatusGEA = estatusGEA;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEstatusGEA() {
		return estatusGEA;
	}

	public void setEstatusGEA(String estatusGEA) {
		this.estatusGEA = estatusGEA;
	}
}
