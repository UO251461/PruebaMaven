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
	private Categoria[] categorias;
	

	public Carrera(String nombre, double precio, Date fechaFI, Date fechaEI, Date fechaCompe, double distancia,
			String tipo, Organizador organizador,int plazasDisponibles,String idcarrera) {

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
		rellenarCategorias(crearLimiteInferior(),crearLimiteSuperior(),crearStringCategoria());
	}
	
	//Como los limites y las categorias son generales los inicializo aqui, aunque se deberian pasar en el constructor
	// para hacer bien el rellenar categorias
	private int[] crearLimiteInferior(){
		int[] limiteInferior = {0,18,35,40};		
		return limiteInferior;
	}
	
	private int[] crearLimiteSuperior(){
		int[] limiteSuperior = {18,35,40,200};		
		return limiteSuperior;
	}
	
	private String[] crearStringCategoria(){
		String[] categorias = {"Menor de edad","Senior","Veterano A","Veterano B"};		
		return categorias;
	}	
	
	private void rellenarCategorias(int[]limitesInferiores,int[]limitesSuperiores,String[]categ){		
		
		//Obtenemos los datos de las categorias y son correctos
		if(limitesInferiores.length == limitesSuperiores.length && limitesSuperiores.length == categ.length){
			//inicializar array de categorias si todo es correcto
			this.categorias = new Categoria[limitesSuperiores.length];
			for(int i=0;i<categ.length;i++){
				if(limitesInferiores[i] < limitesSuperiores[i]){
					this.categorias[i] = new Categoria(limitesInferiores[i],limitesSuperiores[i],categ[i]);
				}				
			}
		}
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
		return this.getNombre();
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
	
	
	

}
