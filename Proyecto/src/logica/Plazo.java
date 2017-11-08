package logica;

import java.sql.Date;

import javax.swing.JOptionPane;

public class Plazo {
	
	private Date fechaEmpiezaInscripcion;
	private Date fechaFinalizaInscripcion;
	private double precio;
	
	public Plazo(Date fechaEI,Date fechaFI,double precio){
			this.fechaEmpiezaInscripcion = fechaEI;
			this.fechaFinalizaInscripcion = fechaFI;
			this.precio = precio;
		}

	public Date getFechaEmpiezaInscripcion() {
		return fechaEmpiezaInscripcion;
	}

	public void setFechaEmpiezaInscripcion(Date fechaEmpiezaInscripcion) {
		this.fechaEmpiezaInscripcion = fechaEmpiezaInscripcion;
	}

	public Date getFechaFinalizaInscripcion() {
		return fechaFinalizaInscripcion;
	}

	public void setFechaFinalizaInscripcion(Date fechaFinalizaInscripcion) {
		this.fechaFinalizaInscripcion = fechaFinalizaInscripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	

}
