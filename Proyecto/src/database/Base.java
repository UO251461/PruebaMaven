package database;

import java.sql.Connection;
import java.sql.*;

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
	
	/**
	 * Devuelve la informacion del atleta de todas las carreras
	 * muestra el estado de su inscripcion
	 * tambien el tiempo y la posicion si dicha carrera ha finalizado.
	 * @param atleta identificador del atleta cuyos datos se desean obtener.
	 */
	public void getDatosAtleta(String atleta) {
		try {
			Connection con = getConection();
			PreparedStatement ps=con.prepareStatement("");	//<-- aqui va la consulta que esta en proceso de crearse
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
	
}
