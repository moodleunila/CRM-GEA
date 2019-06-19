package unila.edu.mx.model;

public class Importacion {
	
	private String contactNo;
	
	private String nombre;
	
	private String apellidos;
	
	private String telefonoPrincipal;
	
	private String telefonoMovil;
	
	private String email;
	
	private String fuente;
	
	private int campusId;
	
	private String carrera;
	

	public Importacion() {
		super();
	}

	public Importacion(String contactNo, String nombre, String apellidos, String telefonoPrincipal, String telefonoMovil, String email,
			String fuente, int campusId, String carrera) {
		super();
		this.contactNo = contactNo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefonoPrincipal = telefonoPrincipal;
		this.telefonoMovil = telefonoMovil;
		this.email = email;
		this.fuente = fuente;
		this.campusId = campusId;
		this.carrera = carrera;
	}

	public String getcontactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefonoPrincipal() {
		return telefonoPrincipal;
	}

	public void setTelefonoPrincipal(String telefonoPrincipal) {
		this.telefonoPrincipal = telefonoPrincipal;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	
}
