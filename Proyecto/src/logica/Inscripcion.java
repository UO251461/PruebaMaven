package logica;

public class Inscripcion {

	String id_participante;
	String id_competicion;
	String id_organizador;
	String pagado;
	String fecha;
	String categoria;
	int dorsal;
	int tiempo;
	
	public Inscripcion(String idPart, String idCompeticion, String idOrganizador, String pagado, String fecha, String categoria) {
		this.id_participante = idPart;
		this.id_competicion = idCompeticion;
		this.id_organizador = idOrganizador;
		this.pagado = pagado;
		this.fecha = fecha;
		this.categoria = categoria;
	}
	
	
	public String getId_participante() {
		return id_participante;
	}
	public void setId_participante(String id_participante) {
		this.id_participante = id_participante;
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
	public String isPagado() {
		return pagado;
	}
	public void setPagado(String pagado) {
		this.pagado = pagado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
}
