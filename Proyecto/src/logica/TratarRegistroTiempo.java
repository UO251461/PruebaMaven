package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import igu.VentanaCarreras;


public class TratarRegistroTiempo {

	private ArrayList<RegistroTiempo> registro;
	int[] tiemposMaximos = new int[7];

	VentanaCarreras vc;

	public TratarRegistroTiempo(VentanaCarreras vc) {
		this.vc = vc;
		this.tiemposMaximos = vc.getBase().getBaseInscripciones().getTiemposMaximos(vc.getBase().getBaseCarrera().getCarreraSeleccionada());
	}

	public void leerFichero(File fichero) {
		BufferedReader br = null;
		int[] tiempos = new int[7];
		try {

			br = new BufferedReader(new FileReader(fichero));
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(";");
				Carrera carrera = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
				int dorsal = Integer.valueOf(fields[0]);
				String dni = vc.getBase().getBaseInscripciones().obtenerDNIPorDorsalYCompeticion(carrera, dorsal);
				for (int i = 0; i <= 6; i++) {
					tiempos[i] = Integer.parseInt(fields[i+1]);
				}
				String comentario = generaComentario(tiempos);
				vc.getBase().getBaseInscripciones().registrarTiemposCorredor(carrera, dni, tiempos, comentario);
				line = br.readLine();
			}
			JOptionPane.showMessageDialog(null, "Se han añadido los tiempos correctamente");
			br.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "El fichero no se ha generado, debido a que no ha acabado la carrera.");
		}catch(SQLException se) {
			JOptionPane.showMessageDialog(null, "Los datos registrados son erroneos o ya han sido insertados");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al insertar el fichero");
		}

	}

	private String generaComentario(int[] rt) {
		String coment = "";
		for(int i= 0; i< rt.length;i++) {
		
				if( i== 0 && rt[i] == -1 && tiemposMaximos[i] != -1 && rt[i+1] == -1 && tiemposMaximos[i+1] != -1 && rt[1] == -1) {
					coment = "DNS";
					break;
				}
				else if(i!= 0 && tiemposMaximos[i] != -1 && rt[i] == -1) {
					coment = "DNF";
					break;
				}
				else if(rt[i] > tiemposMaximos[i]) {
					coment = "FUERA_DE_TIEMPO";
					break;							
				}
				else {
					coment = "ACABADO";}
			
				
		}
		
		return coment;
	}
	


	public ArrayList<RegistroTiempo> getRegistrosTiempo() {
		return this.registro;
	}
}
