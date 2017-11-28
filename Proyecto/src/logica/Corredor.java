package logica;

import java.util.Date;

public class Corredor {

	private String dni;
	private int edad;
	private String sexo;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;

	public Corredor(String dni, int edad, String sexo, String nombre, String apellido) {
		this.dni = dni;
		this.edad = edad;
		this.sexo = sexo;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Corredor(String dni, Date fecha, String sexo, String nombre, String apellido) {
		this.dni = dni;
		this.fechaNacimiento = fecha;
		Date fechaHoy = new Date();
		if(fecha!=null)
			this.edad = fechaHoy.getYear() - fecha.getYear();
		this.sexo = sexo;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}
	
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
	

	
	


