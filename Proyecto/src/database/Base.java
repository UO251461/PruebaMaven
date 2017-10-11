package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {
	private static final String CONNECTION_STRING = "jdbc:oracle:thin:@156.35.94.99:1521:desa";
	
	private static final String USERNAME="IPS_2018_5";
	private static final String PASSWORD="hola2018";
	
	private BaseCarreras baseCarrera;
	
	
	public static Connection getConection() throws SQLException{
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
	}	
	
	public BaseCarreras getBaseCarrera(){
		return baseCarrera;
	}
	
}
