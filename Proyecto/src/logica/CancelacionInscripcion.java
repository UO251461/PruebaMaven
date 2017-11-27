package logica;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import database.Base;

public class CancelacionInscripcion {

	private Inscripcion inscripcion;
	private Base base;

	public CancelacionInscripcion(Inscripcion inscripcion, Base base) {
		this.inscripcion = inscripcion;
		this.base = base;
	}

	public void cancelaInscripcion() {
		float cantidad_abonada = -1;
		float porcentaje = -1;
		int precio_inscripcion = -1;
		SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		Date fecha = new Date();
		df.format(fecha);
		try {
			cantidad_abonada = base.getBaseInscripciones().cantidadInscripcion(inscripcion);
			porcentaje = base.getBaseInscripciones().porcentajeCancelacion(inscripcion, fecha);
			precio_inscripcion = base.getBaseInscripciones().precioInscripcion(inscripcion);
			float precio = cantidad_abonada + porcentaje * precio_inscripcion;
			base.getBaseInscripciones().cancelarInscripcion(inscripcion, precio);
			base.getBaseInscripciones().aumentarPlazasDisponibles(inscripcion);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No ha sido posible cancelar su inscripcion, intentelo más tarde");
		}
	}
}
