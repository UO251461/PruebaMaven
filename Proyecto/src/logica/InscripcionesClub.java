package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class InscripcionesClub {

	ArrayList<Inscripcion> inscripciones;

	public InscripcionesClub(File fichero) {
		inscripciones = leerFichero(fichero);
	}

	private ArrayList<Inscripcion> leerFichero(File fichero) {
		BufferedReader br = null;
		ArrayList<Inscripcion> ins = new ArrayList<Inscripcion>();
		try {

			br = new BufferedReader(new FileReader(fichero));
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				//ins.add(new Inscripcion(fields[0],field[1],field[2],field[3],"PRE-INSCRITO")) //creo una inscripcion y la agrego
				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha habido un error al cargar el fichero");
		}
		
		return ins;

	}
	
	
	public ArrayList<Inscripcion> getInscripcionesClub(){
		return this.inscripciones;
	}
	
	
}
