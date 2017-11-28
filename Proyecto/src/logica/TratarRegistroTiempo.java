package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import igu.VentanaCarreras;
import igu.Ventana_Inscripcion_Club;

public class TratarRegistroTiempo {

	
	private ArrayList<RegistroTiempo> registro;
	
	private Carrera carrera;
	private Corredor corredor;
	

	
	VentanaCarreras vc;

	public TratarRegistroTiempo(VentanaCarreras vc) {
		this.vc = vc;
	}


	public void leerFichero(File fichero) {
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(fichero));
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(";");
				carrera = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
				int dorsal = Integer.valueOf(fields[0]);
				String dni = vc.getBase().getBaseInscripciones().obtenerDNIPorDorsalYCompeticion(carrera, dorsal);
				
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
