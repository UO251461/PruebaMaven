package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import database.Base;
import igu.Ventana_Inscripcion_Club;

public class InscripcionesClub {

	private ArrayList<Inscripcion> inscripciones;
	private Ventana_Inscripcion_Club vi;
	
	public InscripcionesClub(Ventana_Inscripcion_Club vi) {
		inscripciones = new ArrayList<Inscripcion>();
		this.vi = vi;
		
	}

	public void leerFichero(File fichero) {
		BufferedReader br = null;
		Inscripcion inscripcion;
		try {

			br = new BufferedReader(new FileReader(fichero));
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				Carrera carrera = vi.getVc().getBase().getBaseCarrera().getCarreraSeleccionada();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha = formato.parse(fields[4]);
				inscripcion = new Inscripcion(carrera.getIdcarrera(), carrera.getOrganizador().getIdorganizador() , fields[2],"",fecha,"" ); 
				inscripciones.add(inscripcion);
				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha habido un error al cargar el fichero");
		}
		
		

	}
	
	
	public ArrayList<Inscripcion> getInscripcionesClub(){
		return this.inscripciones;
	}
	
	
}
