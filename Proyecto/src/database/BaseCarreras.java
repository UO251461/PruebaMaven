package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import logica.Carrera;
import logica.Organizador;

public class BaseCarreras {

	private final static String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private static final String USERNAME = "IPS_2018_5";
	private static final String PASSWORD = "hola2018";
	private Connection con;
	private PreparedStatement ps;
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
		
		try {
			con = getConnection();
			Statement st = con.createStatement();
			String consulta = "select nombreorganizador,idcompeticion,competicion.idorganizador,fechacompeticion,fecha_apertura_ins,fecha_final_ins,precio,"
					+ " nombre_competicion,distancia,tipo,plazas_disponibles,lugar "
					+ " from competicion,organizador where organizador.idorganizador = competicion.idorganizador AND "
					+ " CURRENT_DATE BETWEEN fecha_apertura_ins AND fecha_final_ins order by fecha_final_ins";

			ResultSet rs = st.executeQuery(consulta);
			while (rs.next()) {
//				System.out.println("Fecha competicion: " + rs.getDate("fechacompeticion"));
				
				
				carreras.add(new Carrera(rs.getString("Nombre_Competicion"), rs.getDouble("Precio"),
						rs.getDate("Fecha_apertura_ins"), rs.getDate("Fecha_final_ins"), rs.getDate("Fechacompeticion"),
						rs.getDouble("Distancia"), rs.getString("Tipo"),
						new Organizador(rs.getString("nombreorganizador"),rs.getString("idorganizador")),
						rs.getInt("plazas_disponibles"),rs.getString("idcompeticion"),rs.getString("lugar")));

			}
			
//			System.out.println("Fecha competicion creada: " + carreras.get(0).getFechaCompeticion());
//			System.out.println("prueba string value of: " + String.valueOf(carreras.get(0).getFechaCompeticion()));
			st.close();
			con.close();
			rs.close();

		} catch (SQLException e) {

			System.err.println("Error al intentar crear el arrayList de carreras ordenadas");
			e.printStackTrace();

		}

	}
	
	public void crearCarrera(String nombre,double precio,Date fechaCompeticion,Date fechaEI,Date fechaFI,double distancia,String tipo,int plazasDisponibles,String lugar){
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR) VALUES(1,?,?,?,?,?,?,?,?,?)";
			
			ps = con.prepareStatement(consulta);
		
			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));			
			ps.setDate(2, new java.sql.Date(fechaEI.getTime() ));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime() ));
			ps.setDouble(4, precio);
			ps.setString(5,nombre);
			ps.setDouble(6,distancia);
			ps.setString(7,tipo);
			ps.setInt(8,plazasDisponibles);
			ps.setString(9, lugar);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
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
	

	/**
	 * Metodo que devuelve el nombre de la carrera pasado el idorganizador y el idcarrera (Samuel)
	 * @param id_competicion
	 * @param id_organizador
	 * @return String, nombre de la carrera
	 */
	public String getNombreCarrera(String id_competicion, String id_organizador) {
		String ret = "";
		try{
			con = getConnection();
			PreparedStatement st = con.prepareStatement("SELECT nombre_competicion FROM COMPETICION WHERE idcompeticion = ? AND idorganizador = ?");
			st.setString(1, id_competicion);
			st.setString(2, id_organizador);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				ret = rs.getString(1);
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			cerrarConexion();
		}
		return ret;
	}

	/**
	 * Retorna el nombre del corredor (Samuel)
	 * @param dni del corredor
	 * @return nombre del coredor
	 */
	public String getNombreCorredor(String dni) {
		String ret = "";
		try{
			con = getConnection();
			ps = con.prepareStatement("SELECT nombre FROM CORREDOR WHERE dni = ? ");
			ps.setString(1, dni);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				ret = rs.getString(1);
			}
			rs.close();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			cerrarConexion();
		}
		return ret;
	}
	/**
	 * Cierra la conexion (samuel)
	 */

	private void cerrarConexion(){
		try{

			con.close();			
		}catch(SQLException sq){
			sq.printStackTrace();
		}
		
	}
}
