package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import logica.*;

public class BaseInscripciones {
	private final static String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private static final String USERNAME = "IPS_2018_5";
	private static final String PASSWORD = "hola2018";
	private PreparedStatement ps;
	private ResultSet rs;
	private HashMap<String, String[]> ids;
	private ArrayList<String> nombres;
	private ArrayList<Inscripcion> inscripciones;
	
	
	
	public BaseInscripciones() {
		inscripciones = new ArrayList<Inscripcion>();
		
	}	
	
	

	
	public static void cambiarEstado(String estado) {
		//metodo que llama a la base de datos y cambia por consulta el estado de la inscripcion por uno que se le pase
	}

	public void registrarCorredor(String dni, String nombre, String apellido,String fecha, String sexo) throws SQLException{//(String dni, String nombre, String aplllido, String fecha, String sexo)
		
		Connection con = getConnection();
		
		//REGISTRAR E INSERTAR
		ps=con.prepareStatement("INSERT INTO CORREDOR VALUES(?,?,?,?,?)");
		ps.setString(1, dni);
		ps.setString(2, nombre);
		ps.setString(3, apellido);
		ps.setString(4, fecha);
		ps.setString(5, sexo);
		ps.executeQuery();	
		
		
	}
	
	public void inscribirCompeticion(String competicion, String dni) throws SQLException{
		String compet = ids.get(competicion)[0];
		String organizador = ids.get(competicion)[1];
		
		Connection con = getConnection();
		ps=con.prepareStatement("INSERT INTO INSCRIPCION VALUES(?,?,?,'PRE-INSCRITO')");	
		ps.setString(1, compet);
		ps.setString(2, organizador);
		ps.setString(3, dni);
		rs = ps.executeQuery();		
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
			ps = con.prepareStatement("SELECT IDCOMPETICION, IDORGANIZADOR, NOMBRE_COMPETICION FROM COMPETICION ");
			rs = ps.executeQuery();
			while(rs.next()){
				nombres.add(rs.getString(3));
				ids.put(rs.getString(3), new String[]{rs.getString(1), rs.getString(2)});
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return nombres;
	}


}
