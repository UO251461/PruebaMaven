package logica;

import java.util.Date;

public class Inscripcion {

	private String dni;
	private String id_competicion;
	private String id_organizador;
	private String estado; //PRE-INSCRITO , PENDIANTE-DE-PAGO,INSCRITO
	Date fecha;
	//String categoria;
	private String nombreCorredor;
	private String nombreCompeticion;
	private double precio;
	private int dorsal;
	private double tiempo;
	
	public Inscripcion( String dni) {
		this.dni = dni;
	}
	
	public Inscripcion(String idCompeticion, String idOrganizador, String dni, String pagado, Date fecha, String categoria) {
		this.dni = dni;
		this.id_competicion = idCompeticion;
		this.id_organizador = idOrganizador;
		this.estado = pagado;
		this.fecha = fecha;
	}
	
	public Inscripcion(String idCompeticion, String idOrganizador, String dni, String pagado, Date fecha, int dorsal, double tiempo) {
		this.dni = dni;
		this.id_competicion = idCompeticion;
		this.id_organizador = idOrganizador;
		this.estado = pagado;
		this.fecha = fecha;
		this.dorsal=dorsal;
		this.tiempo=tiempo;
	}
	
	
	public String getDni() {
		return dni;
	}
	public void setDni(String id_participante) {
		this.dni = id_participante;
	}
	public String getId_competicion() {
		return id_competicion;
	}
	public void setId_competicion(String id_competicion) {
		this.id_competicion = id_competicion;
	}
	public String getId_organizador() {
		return id_organizador;
	}
	public void setId_organizador(String id_organizador) {
		this.id_organizador = id_organizador;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String pagado) {
		this.estado = pagado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getNombreCorredor() {
		return nombreCorredor;
	}

	public void setNombreCorredor(String nombreCorredor) {
		this.nombreCorredor = nombreCorredor;
	}

	public String getNombreCompeticion() {
		return nombreCompeticion;
	}

	public void setNombreCompeticion(String nombreCompeticion) {
		this.nombreCompeticion = nombreCompeticion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double importe) {
		this.precio = importe;
	}
}
