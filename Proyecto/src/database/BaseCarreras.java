package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logica.Carrera;

public class BaseCarreras {

	private final static String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private static final String USERNAME = "IPS_2018_5";
	private static final String PASSWORD = "hola2018";

	private ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	
	private Carrera carreraSeleccionada;

	public BaseCarreras() {
		inicializar();
	}

	public void inicializar() {
		inicializarCarreras();
	}

	public void inicializarCarreras() {
		ordenarCarreras();
	}

	private Connection getConnection() throws SQLException {

		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
	}

	/**
	 * Rellena el arrayList de Carreras de manera ordenada por la fecha final de
	 * inscripcion
	 */
	public void ordenarCarreras() {
		Connection con;
		try {
			con = getConnection();
			Statement st = con.createStatement();
			String consulta = "select * from competicion where CURRENT_DATE BETWEEN fecha_apertura_ins AND fecha_final_ins order by fecha_final_ins";

			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {

				carreras.add(new Carrera(rs.getString("Nombre_Competicion"), rs.getDouble("Precio"),
						rs.getDate("Fecha_apertura_ins"), rs.getDate("Fecha_final_ins"), rs.getDate("Fechacompeticion"),
						rs.getDouble("Distancia"), rs.getString("Tipo")));

			}
			st.close();
			con.close();
			rs.close();

		} catch (SQLException e) {

			System.err.println("Error al intentar crear el arrayList de carreras ordenadas");
			e.printStackTrace();

		}

	}

	public ArrayList<Carrera> getCarreras() {
		return carreras;
	}
	
	public void setCarreraSeleccionada(Carrera carrera){
		this.carreraSeleccionada = carrera;
	}
	public Carrera getCarreraSeleccionada(){
		return carreraSeleccionada;
	}

}
