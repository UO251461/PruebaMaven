package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import igu.VentanaCarreras;

public class GestorExtractos {

	private VentanaCarreras vc;
	private ArrayList<Incidencia> incidencias;
	private double precio;
	
	public GestorExtractos(VentanaCarreras vc) {
		this.vc = vc;
		incidencias = new ArrayList<Incidencia>();
		precio = vc.getBase().getBaseCarrera().getCarreraSeleccionada().getPrecio();
	}
	
	public void leerFichero(File fichero) {
		BufferedReader br = null;
		Incidencia incidencia;
		try {

			br = new BufferedReader(new FileReader(fichero));
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				Carrera carrera = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha = formato.parse(fields[1]);
				incidencia = new Incidencia(fields[0],fecha,fields[2],fields[3],Double.parseDouble(fields[4]));
				incidencias.add(incidencia);
				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha habido un error al cargar el fichero");
		}
		
	}
	
	public void generarComentario() {

			vc.getBase().getBaseInscripciones().generarIncidencias(this);

	}
	
	
	public ArrayList<Incidencia> getIncidencias(){
		return this.incidencias;
	}
	
	public String comentario(Incidencia incidencia) {
		String inc = "";
		Inscripcion insc = vc.getBase().getBaseInscripciones().getInscripcionByIds(incidencia.getIdCompeti(), incidencia.getOrganizador(), incidencia.getDni());
		Date fecha =insc.getFecha();
		fecha.setDate(fecha.getDate()+2);
		double pago = incidencia.getPago() ;
		if(incidencia != null) { 
		if(incidencia.getFecha().after(fecha)) {
				inc = "PAGO_FUER_PLAZO";
			}
		else {	
			
			if(pago > precio) {
				inc = "PAGO_MAS";
			}
			else if(pago == precio)
				inc  = "PAGO_CORRECTO";
			else if(pago < precio  && pago >= 0) {
				inc = "PAGO_MENOS";
			}
		}
		}
		return inc;
	}
	
	
}
