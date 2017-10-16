package database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import logica.*;

public class BaseInscripciones {
	private final static String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private static final String USERNAME = "IPS_2018_5";
	private static final String PASSWORD = "hola2018";
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection con;
	private HashMap<String, String[]> ids;
	private ArrayList<String> nombres;
	private ArrayList<Inscripcion> inscripciones;
	
	
	
	public BaseInscripciones() {
		inscripciones = new ArrayList<Inscripcion>();		
	}	
	
	

	
	public void cambiarEstado(String estado, Inscripcion ins) {
		try{
			con = getConnection();			
			ps=con.prepareStatement("UPDATE INSCRIPCION SET ESTADO = ? WHERE IDCOMPETICION = ? AND IDORGANIZADOR = ? AND DNI = ?");
			ps.setString(1, estado);
			ps.setString(2, ins.getId_competicion());
			ps.setString(3, ins.getId_organizador());
			ps.setString(4, ins.getDni());
			ps.executeQuery();
			ins.setEstado(estado);
		}catch(SQLException sq){
			sq.printStackTrace();
		}
	}


	public void registrarCorredor(String dni, String nombre, String apellido,String fecha, String sexo, Inscripcion inscripcion) throws SQLException{//(String dni, String nombre, String aplllido, String fecha, String sexo)
		try{
			con = getConnection();
			
			//REGISTRAR E INSERTAR
			ps=con.prepareStatement("INSERT INTO CORREDOR VALUES(?,?,?,?,?)");
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setString(4, fecha);
			ps.setString(5, sexo);
			ps.executeQuery();
		}catch(SQLException sql){
			ps=con.prepareStatement("SELECT nombre FROM corredor WHERE dni = ?");
			ps.setString(1, dni);
			rs = ps.executeQuery();
			rs.next();
				inscripcion.setNombreCorredor(rs.getString(1));
			throw sql;
		}
		
		
	}
	
	public void inscribirCompeticion(String competicion, Inscripcion inscripcion) throws SQLException{
		String compet = ids.get(competicion)[0];
		String organizador = ids.get(competicion)[1];
		Double precio = Double.parseDouble(ids.get(competicion)[2]);
		
		con = getConnection();
		ps=con.prepareStatement("INSERT INTO INSCRIPCION VALUES(?,?,?,'PRE-INSCRITO',?)");	
		Date fecha = new Date();
		ps.setString(1, compet);
		ps.setString(2, organizador);
		ps.setString(3, inscripcion.getDni());
		ps.setString(4, new SimpleDateFormat("dd-MM-yyyy").format(fecha));
		ps.executeQuery();	
		
		//si no no esta inscrito
		inscripcion.setId_competicion(compet);
		inscripcion.setId_organizador(organizador);
		inscripcion.setEstado("PRE-INSCRITO");
		inscripcion.setFecha(fecha);
		inscripcion.setNombreCompeticion(competicion);
		inscripcion.setPrecio(precio);
	}
	
	public ArrayList<Inscripcion> getInscripciones() {
		return inscripciones;
	}
	
	
	private Connection getConnection() throws SQLException {

		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
	}
	
	public ArrayList<String> getNombreCompeticiones(){
		nombres = new ArrayList<String>();
		ids = new HashMap<String, String[]>();
		try{
			Connection con = getConnection();
			ps = con.prepareStatement("SELECT IDCOMPETICION, IDORGANIZADOR, NOMBRE_COMPETICION, PRECIO FROM COMPETICION ");
			rs = ps.executeQuery();
			while(rs.next()){
				nombres.add(rs.getString(3));
				ids.put(rs.getString(3), new String[]{rs.getString(1), rs.getString(2),  String.valueOf(rs.getDouble(4))});
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return nombres;
	}


	/**
	 * Devuelve la informacion del atleta de todas las carreras
	 * muestra el estado de su inscripcion
	 * tambien el tiempo y la posicion si dicha carrera ha finalizado.
	 * @param atleta identificador del atleta cuyos datos se desean obtener.
	 */
	public void getDatosAtleta(String atleta) {
		try {
			Connection con = getConnection();
			PreparedStatement ps=con.prepareStatement("SELECT * FROM INSCRIPCIONES WHERE DNI=? ORDER BY FECHA DESC");	//<-- aqui va la consulta que esta en proceso de crearse
			ps.setString(1, atleta);	//se introduce el identificador del atleta
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				//mientras se reciban datos, se almacenaran en un array para posteriormente tratarlos
				//en la logica o donde sea necesario
				//por lo que este metodo en vez de void devolvera un Array
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método que genera las clasificaciones absolutas y por sexo.
	 */
	public void generarClasificaciones(){
		try {
			ArrayList<Inscripcion> clasificacion = new ArrayList<Inscripcion>();
			ArrayList<Inscripcion> clasificacionMasc = new ArrayList<Inscripcion>();
			ArrayList<Inscripcion> clasificacionFem = new ArrayList<Inscripcion>();
			Connection con = getConnection();
			Statement st = con.createStatement();
			//se crean las clasificaciones absolutas
			ResultSet rs= st.executeQuery("SELECT * FROM INSCRIPCIONES WHERE DORSAL>0 ORDER BY TIEMPO");
			while(rs.next()){
				clasificacion.add(new Inscripcion(rs.getString("IDCOMPETICION"), rs.getString("IDORGANIZADOR"),rs.getString("DNI"),rs.getString("ESTADO"),rs.getDate("FECHA"),rs.getInt("DORSAL"),rs.getDouble("TIEMPO")));
			}
			//se crean las clasificaciones masculinas
			rs=st.executeQuery("select * FROM INSCRIPCION WHERE DNI IN(SELECT DNI FROM CORREDOR WHERE SEXO='HOMBRE') ORDER BY TIEMPO");
			while(rs.next()){
				clasificacionMasc.add(new Inscripcion(rs.getString("IDCOMPETICION"), rs.getString("IDORGANIZADOR"),rs.getString("DNI"),rs.getString("ESTADO"),rs.getDate("FECHA"),rs.getInt("DORSAL"),rs.getDouble("TIEMPO")));
			}
			//se crean las clasificaciones femeninas
			rs=st.executeQuery("select * FROM INSCRIPCION WHERE DNI IN(SELECT DNI FROM CORREDOR WHERE SEXO='MUJER') ORDER BY TIEMPO");
			while(rs.next()){
				clasificacionFem.add(new Inscripcion(rs.getString("IDCOMPETICION"), rs.getString("IDORGANIZADOR"),rs.getString("DNI"),rs.getString("ESTADO"),rs.getDate("FECHA"),rs.getInt("DORSAL"),rs.getDouble("TIEMPO")));
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
