package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import logica.Corredor;
import logica.Inscripcion;

public class BaseCorredor {
	private final static String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private static final String USERNAME = "IPS_2018_5";
	private static final String PASSWORD = "hola2018";
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection con;
	private ArrayList<Corredor> corredores;
	
	
	
	public BaseCorredor() {
		corredores = new ArrayList<Corredor>();		
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
	
	public void corredoresCompeticionOrdenadosTiempo() {
		try {
			con = getConnection();
			ps = con.prepareStatement("Select c.dni,c.nombre,c.apellido,c.sexo from corredor c,inscripcion i where c.dni = i.dni order by time");
		}
		catch(SQLException sql) {
			
		}
	}
	
	public ArrayList<Corredor> getCorredores() {
		return corredores;
	}
	
	
	private Connection getConnection() throws SQLException {

		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
	}
	

}
