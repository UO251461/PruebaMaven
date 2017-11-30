package logica;

import java.sql.Date;
import java.util.ArrayList;

public class Carrera {
	private String nombre;
	private double precio;
	private Date fechaFinalizaInscripcion;
	private Date fechaEmpiezaInscripcion;
	private Date fechaCompeticion;
	private double distancia;
	private String tipo;
	private Organizador organizador;
	private int plazasDisponibles;
	private String idcarrera;
	private ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	private String lugar;	
	private ArrayList<Integer> tiemposControl = new ArrayList<Integer>();
	private boolean permiteCancelar;
	

	
	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera, String lugar,ArrayList<Categoria> cat,ArrayList<Integer> tControl) {

		this.nombre = nombre;
		this.precio = precio;
		this.fechaFinalizaInscripcion = fechaFI;
		this.fechaEmpiezaInscripcion = fechaEI;
		this.fechaCompeticion = fechaCompe;
		this.distancia = distancia;
		this.tipo = tipo;
		this.organizador = organizador;
		this.plazasDisponibles = plazasDisponibles;
		this.idcarrera = idcarrera;	
		this.lugar= lugar;
		this.categorias = cat;
		this.tiemposControl = tControl;
		
	}	
	
	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera, String lugar,ArrayList<Categoria> cat,ArrayList<Integer> tControl, String permiteCancelar){
		this(nombre,precio,fechaFI,fechaEI,fechaCompe,distancia,tipo,organizador,plazasDisponibles,idcarrera,lugar,cat,tControl);
	
		if(permiteCancelar!=null && permiteCancelar.equals("SI"))
			this.permiteCancelar = true;
		else
			this.permiteCancelar = false;
	}
	public ArrayList<Categoria> getCategorias(){
		return categorias;
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

	public String toString() {
		
		String[] fech = String.valueOf(this.fechaCompeticion).split("-");
		return this.getNombre() +" es en " + this.lugar + ", cuenta con una distancia de " + this.distancia + "km, tiene un precio de " + this.precio + " euros " + " y tendra lugar el " + fech[2]+"/"
				+ fech[1]  +"/" + fech[0];
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}

	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

	public String getIdcarrera() {
		return idcarrera;
	}

	public void setIdcarrera(String idcarrera) {
		this.idcarrera = idcarrera;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}	

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}	
	
	public ArrayList<Integer> getTiemposControl() {
		return tiemposControl;
	}	

	public void setTiemposControl(ArrayList<Integer> tiemposControl) {
		this.tiemposControl = tiemposControl;
	}

	public String infoCarrera(){
		String[] fech1 = String.valueOf(this.fechaFinalizaInscripcion).split("-");
		String info = "Nombre: " +nombre +  ".\n" + 
				"Precio: " + precio + " euros.\n" + 
				"Fecha Finalizacion del plazo de inscripcion actual: " + fech1[2] + "/" + fech1[1] + "/" + fech1[0] + ".\n" + 
				"Distancia: " + distancia + "km.\n" + 
				"Tipo: " + tipo + "\n" + 
				"Plazas disponibles: " + plazasDisponibles + ".\n" +
				"Lugar: " + lugar+".";
		return info;
	}

	public boolean isPermiteCancelar() {
		return permiteCancelar;
	}

	public void setPermiteCancelar(boolean permiteCancelar) {
		this.permiteCancelar = permiteCancelar;
	}
}
