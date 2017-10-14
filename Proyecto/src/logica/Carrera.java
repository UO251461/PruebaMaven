package logica;

import java.sql.Date;

public class Carrera {
	private String nombre;
	private double precio;
	private Date fechaFinalizaInscripcion;
	private Date fechaEmpiezaInscripcion;
	private Date fechaCompeticion;
	private double distancia;
	private String tipo;
	
	public Carrera(String nombre,double precio,Date fechaFI,Date fechaEI, 
			Date fechaCompe,double distancia,String tipo){
		
		this.nombre = nombre;
		this.precio = precio;
		this.fechaFinalizaInscripcion = fechaFI;
		this.fechaEmpiezaInscripcion = fechaEI;
		this.fechaCompeticion = fechaCompe;
		this.distancia = distancia;	
		this.tipo = tipo;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaFinalizaInscripcion() {
		return fechaFinalizaInscripcion;
	}

	public void setFechaFinalizaInscripcion(Date fechaFinalizaInscripcion) {
		this.fechaFinalizaInscripcion = fechaFinalizaInscripcion;
	}

	public Date getFechaEmpiezaInscripcion() {
		return fechaEmpiezaInscripcion;
	}

	public void setFechaEmpiezaInscripcion(Date fechaEmpiezaInscripcion) {
		this.fechaEmpiezaInscripcion = fechaEmpiezaInscripcion;
	}

	public Date getFechaCompeticion() {
		return fechaCompeticion;
	}

	public void setFechaCompeticion(Date fechaCompeticion) {
		this.fechaCompeticion = fechaCompeticion;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String toString(){
		return this.getNombre();
		
	}
	
	
	

}
