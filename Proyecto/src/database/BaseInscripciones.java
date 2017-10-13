package database;

import java.util.ArrayList;
import logica.*;

public class BaseInscripciones {

	
	public BaseInscripciones() {
		inscripciones = new ArrayList<Inscripcion>();
	}
	
	private ArrayList<Inscripcion> inscripciones;
	
	
	public static void cambiarEstado(String estado) {
		//metodo que llama a la base de datos y cambia por consulta el estado de la inscripcion por uno que se le pase
	}
}
