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
	private ArrayList<Inscripcion> inscripcionesCarrera;
	
	
	
	public BaseInscripciones() {
		inscripciones = new ArrayList<Inscripcion>();		
		inscripcionesCarrera = new ArrayList<Inscripcion>();
	}	
	
	/**
	 * Cierra debidamente la conexion (Samuel)
	 */
	private void cerrarConexion(){
		try{
			ps.close();
			con.close();			
		}catch(SQLException sq){
			sq.printStackTrace();
		}
		
	}

	
	public void cambiarEstado(String estado, Inscripcion ins) {
		try{
			con = getConnection();			
			ps=con.prepareStatement("UPDATE INSCRIPCION SET ESTADO = ? WHERE IDCOMPETICION = ? AND IDORGANIZADOR = ? AND DNI = ?");
			ps.setString(1, estado);
			ps.setString(2, ins.getId_competicion());
			ps.setString(3, ins.getId_organizador());
			ps.setString(4, ins.getDni());
			rs = ps.executeQuery();
			ins.setEstado(estado);
			rs.close();
		}catch(SQLException sq){
			sq.printStackTrace();
		}finally{
			cerrarConexion();
		}
		
	}


	public void registrarCorredor(String dni, String nombre, String apellido,String fecha, String sexo, Inscripcion inscripcion) throws SQLException{//(String dni, String nombre, String aplllido, String fecha, String sexo)
		try{
			con = getConnection();
			
			//REGISTRAR E INSERTAR EN LA TABLA "CORREDOR"
			ps=con.prepareStatement("INSERT INTO CORREDOR VALUES(?,?,?,?,?)");
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setString(4, fecha);
			ps.setString(5, sexo);
			rs = ps.executeQuery();	
			rs.close();
		}catch(SQLException sql){
			//SI YA ESTÁ REGISTRADO NO HACE NADA
		}finally{
			inscribirCompeticion(inscripcion);
			cerrarConexion();
		}
		
		
	}
	
	/*
	 * Metodo que devuelve 
	 */
	public void getInscripcionPorCompeticion(String idCompeticion) {
		Connection con;
		try {
			
			con = getConnection();
			String consulta = "select i.idcompeticion, i.idorganizador,i.dni,i.estado,i.fecha, "
					+ "i.categoria,c.nombre from inscripcion i, corredor c where i.dni = c.dni and i.idcompeticion = ? and i.estado = 'INSCRITO' order by fecha";
			PreparedStatement pst = con.prepareStatement(consulta);
			
			pst.setString(1, idCompeticion);
			
			ResultSet rs1 = pst.executeQuery();
			
			while (rs1.next()) {
				inscripcionesCarrera.add(new Inscripcion(rs1.getString("idcompeticion"),rs1.getString("idorganizador"),rs1.getString("dni"),
						rs1.getString("estado"), rs1.getDate("fecha"),rs1.getString("categoria"),rs1.getString("nombre")));

			}
			
			consulta = "select i.idcompeticion, i.idorganizador,i.dni,i.estado,i.fecha, "
					+ "i.categoria,c.nombre from inscripcion i, corredor c where i.dni = c.dni and i.idcompeticion = ? and i.estado = 'PRE-INSCRITO' order by fecha";
			PreparedStatement pst2 = con.prepareStatement(consulta);
			
			pst2.setString(1, idCompeticion);
			
			ResultSet rs2 = pst2.executeQuery();
			
			while (rs2.next()) {
				inscripcionesCarrera.add(new Inscripcion(rs2.getString("idcompeticion"),rs2.getString("idorganizador"),rs2.getString("dni"),
						rs2.getString("estado"), rs2.getDate("fecha"),rs2.getString("categoria"),rs2.getString("nombre")));

			}
			
			consulta = "select i.idcompeticion, i.idorganizador,i.dni,i.estado,i.fecha, "
					+ "i.categoria,c.nombre from inscripcion i, corredor c where i.dni = c.dni and i.idcompeticion = ? and i.estado = 'PENDIENTE DE PAGO' order by fecha";
			PreparedStatement pst3 = con.prepareStatement(consulta);
			
			pst3.setString(1, idCompeticion);
			
			ResultSet rs3 = pst3.executeQuery();
			
			while (rs3.next()) {
				inscripcionesCarrera.add(new Inscripcion(rs3.getString("idcompeticion"),rs3.getString("idorganizador"),rs3.getString("dni"),
						rs3.getString("estado"), rs3.getDate("fecha"),rs3.getString("categoria"),rs3.getString("nombre")));

			}
			
			pst.close();
			rs1.close();
			pst2.close();
			rs2.close();
			pst3.close();
			rs3.close();
			con.close();
		


		} catch (SQLException e) {

			System.err.println("Error al intentar crear el arrayList de carreras ordenadas");
			e.printStackTrace();

		}
	}
	
	public void inscribirCompeticion(Inscripcion inscripcion) throws SQLException{
		try{
			//CONSULTA ADAPTADA PARA AÑADIRLE LA CATEGORIA
			con = getConnection();
			ps=con.prepareStatement("INSERT INTO INSCRIPCION(IDCOMPETICION,IDORGANIZADOR,DNI,ESTADO,FECHA,CATEGORIA) VALUES(?,?,?,'PRE-INSCRITO',?,?)");	
			Date fecha = new Date();
			ps.setString(1, inscripcion.getId_competicion());
			ps.setString(2, inscripcion.getId_organizador());
			ps.setString(3, inscripcion.getDni());
			ps.setString(4, new SimpleDateFormat("dd-MM-yyyy").format(fecha));		
			ps.setString(5, inscripcion.getCategoria());
			rs = ps.executeQuery();	
			
			//si no no esta inscrito(Samuel)
			inscripcion.setId_competicion(inscripcion.getId_competicion());
			inscripcion.setId_organizador(inscripcion.getId_organizador());
			inscripcion.setEstado("PRE-INSCRITO");
			inscripcion.setPrecio(inscripcion.getPrecio());
			inscripcion.setFecha(fecha);
			
			rs.close();
		}finally{
			cerrarConexion();
		}
		rs.close();
	}
	
	public ArrayList<Inscripcion> getInscripciones() {
		return inscripciones;
	}
	
	public ArrayList<Inscripcion> getInscripcionesCarrera() {
		return inscripcionesCarrera;
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
	
	
	/**
	 * Asigna el campo dorsal de la tabla inscripciones de aquellos que esten en estado INSCRITO(Samuel)
	 */
	public void asignarDorsal(){
		try{
			Connection con = getConnection();
			ps = con.prepareStatement("SELECT idcompeticion, idorganizador, dni FROM inscripcion WHERE estado = 'INSCRITO' ORDER BY FECHA ASC");
			PreparedStatement corredorInscrito = con.prepareStatement("UPDATE inscripcion SET dorsal = ? WHERE idcompeticion = ? AND idorganizador = ? AND dni = ?");
			rs = ps.executeQuery();
			//se dejan los 10 primeros (requisito)
			int numeroDorsal = 11;
			while(rs.next()){
				corredorInscrito.setInt(1, numeroDorsal);
				corredorInscrito.setString(2, rs.getString(1));
				corredorInscrito.setString(3, rs.getString(2));
				corredorInscrito.setString(4, rs.getString(3));
				corredorInscrito.executeQuery();
				numeroDorsal++;
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
}
