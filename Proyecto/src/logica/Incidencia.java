package logica;

import java.util.Date;

public class Incidencia {
	private String dni;
	private Date fecha;
	private String idCompeti;
	private double pago;
	private String organizador;
	
	public Incidencia(String dni,Date fecha, String idCompeti,String organizador,double pago) {
		this.dni = dni;
		this.fecha = fecha;
		this.idCompeti = idCompeti;
		this.pago = pago;
		this.organizador = organizador;
	}


	public String getDni() {
		return dni;
	}


	public Date getFecha() {
		return fecha;
	}


	public String getIdCompeti() {
		return idCompeti;
	}


	public double getPago() {
		return pago;
	}
	
	public String getOrganizador() {
		return this.organizador;
	}
	
	
}
