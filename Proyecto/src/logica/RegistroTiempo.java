package logica;

public class RegistroTiempo {
	

	private String idCompeticion,idOrganizador;
	private int[] tiempos;
	private int dorsal;

	public RegistroTiempo(int[] tiempos,String idCompeticion, String idOrganizador, int dorsal) {
		this.tiempos = tiempos;	
		this.idCompeticion = idCompeticion;
		this.idOrganizador = idOrganizador;
		this.dorsal = dorsal;
	}
	


	public int[] getTiempos() {
		return this.tiempos;
	}

	public String getIdCompeticion() {
		return idCompeticion;
	}

	public String getIdOrganizador() {
		return idOrganizador;
	}

	public int getDorsal() {
		return dorsal;
	}


	
	 
	

}
