package logica;

import java.util.Date;

public class PlazoCancelacion {
	
	private Date fecha;
	private float porcentaje;
	
	
	public PlazoCancelacion(Date object, float object2) {
		this.fecha = object;
		this.porcentaje = object2;
	}
	


	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
}
