package unila.edu.mx.model.create;

public class UserLite {
	
	private String nombre;
	
	private String aPaterno;
	
	private String correo;
	
	private String telefono;
	
	private String campus;
	
	private String carrera;
	
	private String nivel;
	

	public UserLite() {
		super();
	}	

	public UserLite(String nombre, String aPaterno, String correo, String telefono, String campus, String carrera,
			String nivel) {
		super();
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.correo = correo;
		this.telefono = telefono;
		this.campus = campus;
		this.carrera = carrera;
		this.nivel = nivel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
}
