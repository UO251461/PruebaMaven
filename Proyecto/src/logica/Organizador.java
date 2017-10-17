package logica;

public class Organizador {
	
	private String nombre;
	private String idorganizador;
	
	public Organizador(String nombre,String idorganizador){
		this.nombre = nombre;
		this.idorganizador = idorganizador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdorganizador() {
		return idorganizador;
	}

	public void setIdorganizador(String idorganizador) {
		this.idorganizador = idorganizador;
	}
	
	

}
