package logica;

import java.util.Date;

public class Inscripcion {
	
		private Carrera carrera; // EN ESTA CLASE HAY UN ATRIBUTO QUE ES EL ORGANIZADOR, PRECIO(Samuel)
		private String estado; //PRE-INSCRITO , PENDIANTE-DE-PAGO,INSCRITO
		Date fecha;
		private String categoria;
		private int dorsal;
		
		private double tiempo;
		private Corredor corredor;//CORREDOR TIENE ID, DNI
		
		
		public Inscripcion(String idCompeticion, String idOrganizador, String dni, String pagado, Date fecha, String categoria) {
			corredor = new Corredor(dni, 0, "", "", "");
			Organizador organizador = new Organizador("", idOrganizador);
			carrera = new Carrera("", 0, null, null, null, 0, "", organizador, 0, idCompeticion);
			this.estado = pagado;
			this.fecha = fecha;
			this.categoria = categoria;
		}
		
		public Inscripcion(String idCompeticion, String idOrganizador, String dni, String pagado, Date fecha, String categoria,String nombre) {
			corredor = new Corredor(dni, 0, "", "", "");
			Organizador organizador = new Organizador("", idOrganizador);
			carrera = new Carrera("", 0, null, null, null, 0, "", organizador, 0, idCompeticion);
			this.estado = pagado;
			this.fecha = fecha;
			this.categoria = categoria;
		}
		
		public Inscripcion(String idCompeticion, String idOrganizador, String dni, String pagado, Date fecha, int dorsal, double tiempo) {
			corredor = new Corredor(dni, 0, "", "", "");
			Organizador organizador = new Organizador("", idOrganizador);
			carrera = new Carrera("", 0, null, null, null, 0, "", organizador, 0, idCompeticion);
			this.estado = pagado;
			this.fecha = fecha;
			this.dorsal=dorsal;
			this.tiempo=tiempo;
		}
		
		public Inscripcion(String idCompeticion, String idOrganizador, String dni, String pagado, Date fecha, int dorsal, double tiempo, String categoria) {
			corredor = new Corredor(dni, 0, "", "", "");
			Organizador organizador = new Organizador("", idOrganizador);
			carrera = new Carrera("", 0, null, null, null, 0, "", organizador, 0, idCompeticion);
			this.estado = pagado;
			this.fecha = fecha;
			this.dorsal=dorsal;
			this.tiempo=tiempo;
			this.categoria=categoria;
		}
		
		
		public Inscripcion(String idCarrera, String idOrganizador, String dni, double pre) {
			Organizador organizador = new Organizador("", idOrganizador);
			carrera = new Carrera("", pre, null, null, null, 0, "", organizador, 0, idCarrera);
			corredor = new Corredor(dni, 0, "", "", "");
		}

		public Carrera getCarrera() {
			return carrera;
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


		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public void setTiempo(double tiempo) {
			this.tiempo = tiempo;
		}

		@SuppressWarnings("deprecation")
		public void asignarCategoria(Date fechaNacimiento,Carrera carrera){			
			int actual = new Date().getYear();			
			int edad = actual - fechaNacimiento.getYear();
			for(int i=0;i<carrera.getCategorias().length;i++){
				if(edad >= carrera.getCategorias()[i].getLimiteInferior() && edad < carrera.getCategorias()[i].getLimiteSuperior()){
					this.categoria = carrera.getCategorias()[i].getCategoria();
				}
			}
		}
		
		@Override
		public String toString() {
			return this.corredor.getDni() + " " + this.corredor.getNombre() + " "+ this.categoria + " "+ this.fecha + "------------->"+ this.estado;
		}

		public Corredor getCorredor() {
			return corredor;
		}
	
}

