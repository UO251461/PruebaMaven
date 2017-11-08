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
	private Organizador organizador;
	private int plazasDisponibles;
	private String idcarrera;
	private Categoria[] categorias = new Categoria[4];
	private String lugar;
	
	private double precio2;
	private Date fechaFinalizaInscripcion2;
	private Date fechaEmpiezaInscripcion2;
	
	private double precio3;
	private Date fechaFinalizaInscripcion3;
	private Date fechaEmpiezaInscripcion3;
	
	private double precio4;
	private Date fechaFinalizaInscripcion4;
	private Date fechaEmpiezaInscripcion4;
	
	private double precio5;
	private Date fechaFinalizaInscripcion5;
	private Date fechaEmpiezaInscripcion5;
	
	

	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera, String lugar,Categoria[] cat) {

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
	}
	
	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera, String lugar,double precio2,Date fechaFinalizaInscripcion2,Date fechaEmpiezaInscripcion2,Categoria[]cat) {

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
		this.precio2 = precio2;
		this.fechaEmpiezaInscripcion2 = fechaEmpiezaInscripcion2;
		this.fechaFinalizaInscripcion2 = fechaFinalizaInscripcion2;
	}
	
	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera, String lugar,double precio2,Date fechaFinalizaInscripcion2,Date fechaEmpiezaInscripcion2,
			double precio3,Date fechaFinalizaInscripcion3,Date fechaEmpiezaInscripcion3,Categoria[]cat) {

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
		this.precio2 = precio2;
		this.fechaEmpiezaInscripcion2 = fechaEmpiezaInscripcion2;
		this.fechaFinalizaInscripcion2 = fechaFinalizaInscripcion2;
		this.precio3 = precio3;
		this.fechaEmpiezaInscripcion3 = fechaEmpiezaInscripcion3;
		this.fechaFinalizaInscripcion3 = fechaFinalizaInscripcion3;
	}
	
	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera, String lugar,double precio2,Date fechaFinalizaInscripcion2,Date fechaEmpiezaInscripcion2,
			double precio3,Date fechaFinalizaInscripcion3,Date fechaEmpiezaInscripcion3,double precio4,Date fechaFinalizaInscripcion4,Date fechaEmpiezaInscripcion4,Categoria[] cat) {

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
		this.precio2 = precio2;
		this.fechaEmpiezaInscripcion2 = fechaEmpiezaInscripcion2;
		this.fechaFinalizaInscripcion2 = fechaFinalizaInscripcion2;
		this.precio3 = precio3;
		this.fechaEmpiezaInscripcion3 = fechaEmpiezaInscripcion3;
		this.fechaFinalizaInscripcion3 = fechaFinalizaInscripcion3;
		this.precio4 = precio4;
		this.fechaEmpiezaInscripcion4 = fechaEmpiezaInscripcion4;
		this.fechaFinalizaInscripcion4 = fechaFinalizaInscripcion4;
	}

	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera, String lugar,double precio2,Date fechaFinalizaInscripcion2,Date fechaEmpiezaInscripcion2,
			double precio3,Date fechaFinalizaInscripcion3,Date fechaEmpiezaInscripcion3,double precio4,Date fechaFinalizaInscripcion4,Date fechaEmpiezaInscripcion4,
			double precio5,Date fechaFinalizaInscripcion5,Date fechaEmpiezaInscripcion5,Categoria[] cat) {

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
		this.precio2 = precio2;
		this.fechaEmpiezaInscripcion2 = fechaEmpiezaInscripcion2;
		this.fechaFinalizaInscripcion2 = fechaFinalizaInscripcion2;
		this.precio3 = precio3;
		this.fechaEmpiezaInscripcion3 = fechaEmpiezaInscripcion3;
		this.fechaFinalizaInscripcion3 = fechaFinalizaInscripcion3;
		this.precio4 = precio4;
		this.fechaEmpiezaInscripcion4 = fechaEmpiezaInscripcion4;
		this.fechaFinalizaInscripcion4 = fechaFinalizaInscripcion4;
		this.precio5 = precio5;
		this.fechaEmpiezaInscripcion5 = fechaEmpiezaInscripcion5;
		this.fechaFinalizaInscripcion5 = fechaFinalizaInscripcion5;
	}
	
	public Categoria[] getCategorias(){
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

	public double getPrecio2() {
		return precio2;
	}

	public void setPrecio2(double precio2) {
		this.precio2 = precio2;
	}

	public Date getFechaFinalizaInscripcion2() {
		return fechaFinalizaInscripcion2;
	}

	public void setFechaFinalizaInscripcion2(Date fechaFinalizaInscripcion2) {
		this.fechaFinalizaInscripcion2 = fechaFinalizaInscripcion2;
	}

	public Date getFechaEmpiezaInscripcion2() {
		return fechaEmpiezaInscripcion2;
	}

	public void setFechaEmpiezaInscripcion2(Date fechaEmpiezaInscripcion2) {
		this.fechaEmpiezaInscripcion2 = fechaEmpiezaInscripcion2;
	}

	public double getPrecio3() {
		return precio3;
	}

	public void setPrecio3(double precio3) {
		this.precio3 = precio3;
	}

	public Date getFechaFinalizaInscripcion3() {
		return fechaFinalizaInscripcion3;
	}

	public void setFechaFinalizaInscripcion3(Date fechaFinalizaInscripcion3) {
		this.fechaFinalizaInscripcion3 = fechaFinalizaInscripcion3;
	}

	public Date getFechaEmpiezaInscripcion3() {
		return fechaEmpiezaInscripcion3;
	}

	public void setFechaEmpiezaInscripcion3(Date fechaEmpiezaInscripcion3) {
		this.fechaEmpiezaInscripcion3 = fechaEmpiezaInscripcion3;
	}

	public double getPrecio4() {
		return precio4;
	}

	public void setPrecio4(double precio4) {
		this.precio4 = precio4;
	}

	public Date getFechaFinalizaInscripcion4() {
		return fechaFinalizaInscripcion4;
	}

	public void setFechaFinalizaInscripcion4(Date fechaFinalizaInscripcion4) {
		this.fechaFinalizaInscripcion4 = fechaFinalizaInscripcion4;
	}

	public Date getFechaEmpiezaInscripcion4() {
		return fechaEmpiezaInscripcion4;
	}

	public void setFechaEmpiezaInscripcion4(Date fechaEmpiezaInscripcion4) {
		this.fechaEmpiezaInscripcion4 = fechaEmpiezaInscripcion4;
	}

	public double getPrecio5() {
		return precio5;
	}

	public void setPrecio5(double precio5) {
		this.precio5 = precio5;
	}

	public Date getFechaFinalizaInscripcion5() {
		return fechaFinalizaInscripcion5;
	}

	public void setFechaFinalizaInscripcion5(Date fechaFinalizaInscripcion5) {
		this.fechaFinalizaInscripcion5 = fechaFinalizaInscripcion5;
	}

	public Date getFechaEmpiezaInscripcion5() {
		return fechaEmpiezaInscripcion5;
	}

	public void setFechaEmpiezaInscripcion5(Date fechaEmpiezaInscripcion5) {
		this.fechaEmpiezaInscripcion5 = fechaEmpiezaInscripcion5;
	}

	public void setCategorias(Categoria[] categorias) {
		this.categorias = categorias;
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
}
