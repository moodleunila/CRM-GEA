package unila.edu.mx.model;

public class Importacion {
	
	private VtigerContactdetails contacto;
	
	private VtigerCf864 programa;	

	public Importacion() {
		super();
	}

	public Importacion(VtigerContactdetails contacto, VtigerCf864 programa) {
		super();
		this.contacto = contacto;
		this.programa = programa;
	}

	public VtigerContactdetails getContacto() {
		return contacto;
	}

	public void setContacto(VtigerContactdetails contacto) {
		this.contacto = contacto;
	}

	public VtigerCf864 getPrograma() {
		return programa;
	}

	public void setPrograma(VtigerCf864 programa) {
		this.programa = programa;
	}
}
