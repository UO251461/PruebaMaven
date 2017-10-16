package database;

import java.sql.Connection;
import java.sql.*;

public class Base {
	private static final String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.99:1521:desa";
	
	private static final String USERNAME="IPS_2018_5";
	private static final String PASSWORD="hola2018";
	
	private BaseCarreras baseCarrera;
	private BaseInscripciones baseInscripciones;
	
	
	public static Connection getConnection() throws SQLException{
		if(DriverManager.getDriver(CONNECTION_STRING)==null)
			if(CONNECTION_STRING.contains("oracle"))
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		return DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);
	}
	
	public Base(){
		inicializar();
	}
	
	public void inicializar(){
		baseCarrera = new BaseCarreras();
		baseInscripciones = new BaseInscripciones();
	}	
	
	public BaseCarreras getBaseCarrera(){
		return baseCarrera;
	}
	

	
	/**
	 *Metodo que devuelve el listado de todas las inscripciones
	 *Sera tratado luego en una clase a parte el que devolver y como tratarla
	 */
	public void getDatosInscripcion() {
		try {
			Connection con = getConnection();
			PreparedStatement ps=con.prepareStatement("");	
			
			ResultSet rs = ps.executeQuery();		
			
			while(rs.next()) {
				//Se creara una inscripcion y se añadira a la clase que contendra todas las inscripciones
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public BaseInscripciones getBaseInscripciones() {
		return baseInscripciones;
	}

	
	
	
}
