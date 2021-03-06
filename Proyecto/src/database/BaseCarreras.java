package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import logica.Carrera;
import logica.Categoria;
import logica.Organizador;
import logica.Plazo;

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
			
			String consultaCarrera  ="select competicion.*,p.fecha_inicio,p.fecha_fin,p.precio,organizador.* from competicion,plazo_inscripcion p,organizador "
					+ " where competicion.idcompeticion = p.idcompeticion and competicion.idorganizador = p.idorganizador and"
					+ " competicion.idorganizador = p.idorganizador and current_date between p.fecha_inicio and p.fecha_fin order by fechacompeticion";
			String consultaCategoria = "select c.nombre,c.limite_inferior,c.limite_superior,c.sexo from categoria c where c.IDCOMPETICION = ? and c.IDORGANIZADOR = ?";
			String consultaTiemposControl = "select tiempo_inicio,tiempo_final,tiempo1,tiempo2,tiempo3,tiempo4,tiempo5 from tiempos_control t where t.IDCOMPETICION = ? and t.IDORGANIZADOR = ?";
					
			ps = con.prepareStatement(consultaCarrera);	
			
			ResultSet rs1 = ps.executeQuery();
			while(rs1.next()){	
				ArrayList<Categoria> categorias = new ArrayList<Categoria>();
				ps = con.prepareStatement(consultaCategoria);
				ps.setString(1, rs1.getString("idcompeticion"));
				ps.setString(2, rs1.getString("idorganizador"));
				ResultSet rs2 = ps.executeQuery();
				while(rs2.next()){
					categorias.add(new Categoria(rs2.getInt("limite_inferior"),rs2.getInt("limite_superior"),rs2.getString("nombre"),rs2.getString("sexo")));
				}
				ArrayList<Integer> tControl = new ArrayList<Integer>();
				ps = con.prepareStatement(consultaTiemposControl);
				ps.setString(1, rs1.getString("idcompeticion"));
				ps.setString(2, rs1.getString("idorganizador"));
				ResultSet rs3 = ps.executeQuery();
				while(rs3.next()){
					tControl.add(rs3.getInt("tiempo_inicio"));
					tControl.add(rs3.getInt("tiempo_final"));
					//Se meten los 5 tiempos de control al array
					for(int i=1;i<6;i++){
						int n1 = rs3.getInt("tiempo" + i);
						if(n1 !=-1)
							tControl.add(n1);
					}
				
						
				}
				carreras.add(new Carrera(rs1.getString("nombre_competicion"), rs1.getDouble("precio"), rs1.getDate("fecha_fin"), rs1.getDate("fecha_inicio"), rs1.getDate("fechacompeticion"),
						rs1.getDouble("distancia"), rs1.getString("tipo"), new Organizador(rs1.getString("nombreorganizador"),rs1.getString("idorganizador")),
						rs1.getInt("plazas_disponibles"), rs1.getString("idcompeticion"), rs1.getString("lugar"), categorias,tControl));				
				rs2.close();
			}
			
			con.close();			
			rs1.close();

		} catch (SQLException e) {

			System.err.println("Error al intentar crear el arrayList de carreras ordenadas");
			e.printStackTrace();

		}

	}

	public void crearCarrera(String nombre, Date fechaCompeticion, double distancia, String tipo, int plazasDisponibles,
			String lugar, ArrayList<Plazo> plazos, ArrayList<Categoria> categorias,ArrayList<Integer> tControl) {
		try {
			con = getConnection();
			String consultaCarrera = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, NOMBRE_COMPETICION,DISTANCIA,TIPO,PLAZAS_DISPONIBLES,LUGAR,CEDER_DORSAL)"
					+ " VALUES(1,?,?,?,?,?,?,'SI')";
			String consultaPlazo = "INSERT INTO PLAZO_INSCRIPCION (FECHA_INICIO,FECHA_FIN,PRECIO,IDORGANIZADOR,IDCOMPETICION) "
					+ "VALUES(?,?,?,1,?)";
			String consultaCategorias = "INSERT INTO CATEGORIA (NOMBRE,LIMITE_INFERIOR,LIMITE_SUPERIOR,IDORGANIZADOR,IDCOMPETICION,SEXO) "
					+ "VALUES(?,?,?,1,?,?)";
			String consultaIdCarerra = "SELECT SECUENCIAIDCOMPETICION.CURRVAL as idcarrera FROM DUAL";
			
			String consultaTiemposControl = "INSERT INTO TIEMPOS_CONTROL (IDORGANIZADOR,IDCOMPETICION,TIEMPO_INICIO,TIEMPO1,TIEMPO2,TIEMPO3,TIEMPO4,TIEMPO5,TIEMPO_FINAL) "
					+ "VALUES(1,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consultaCarrera);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setString(2, nombre);
			ps.setDouble(3, distancia);
			ps.setString(4, tipo);
			ps.setInt(5, plazasDisponibles);
			ps.setString(6, lugar);
			ps.executeQuery();

			ps = con.prepareStatement(consultaIdCarerra);
			ResultSet rs = ps.executeQuery();
			String idCarrera = "";
			while (rs.next()) {
				idCarrera = rs.getString("idcarrera");
			}
			if (idCarrera.isEmpty()) {
				System.err.println("------------------IDCARRERA VACIO---------------");
				return;
			}
			
			for(int i=0;i<plazos.size();i++){
				ps = con.prepareStatement(consultaPlazo);			
				ps.setDate(1, new java.sql.Date(plazos.get(i).getFechaEmpiezaInscripcion().getTime()));
				ps.setDate(2, new java.sql.Date(plazos.get(i).getFechaFinalizaInscripcion().getTime()));
				ps.setDouble(3, plazos.get(i).getPrecio());
				ps.setString(4, idCarrera);
				ps.executeQuery();
			}
			
			for(int i=0;i<categorias.size();i++){
				ps = con.prepareStatement(consultaCategorias);
				ps.setString(1, categorias.get(i).getCategoria());
				ps.setInt(2, categorias.get(i).getLimiteInferior());
				ps.setInt(3, categorias.get(i).getLimiteSuperior());
				ps.setString(4, idCarrera);
				ps.setString(5, categorias.get(i).getSexo());
				ps.executeQuery();
			}
			ps = con.prepareStatement(consultaTiemposControl);
			ps.setString(1, idCarrera);
			for(int i=0;i<tControl.size();i++){
				ps.setInt(i+2, tControl.get(i));
			}
			ps.executeQuery();

		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}
	}	

	public ArrayList<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreraSeleccionada(Carrera carrera) {
		this.carreraSeleccionada = carrera;
	}

	public Carrera getCarreraSeleccionada() {
		return carreraSeleccionada;
	}

	/**
	 * Metodo que devuelve el nombre de la carrera pasado el idorganizador y el
	 * idcarrera (Samuel)
	 * 
	 * @param id_competicion
	 * @param id_organizador
	 * @return String, nombre de la carrera
	 */
	public String getNombreCarrera(String id_competicion, String id_organizador) {
		String ret = "";
		try {
			con = getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT nombre_competicion FROM COMPETICION WHERE idcompeticion = ? AND idorganizador = ?");
			st.setString(1, id_competicion);
			st.setString(2, id_organizador);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				ret = rs.getString(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return ret;
	}

	/**
	 * Retorna el nombre del corredor (Samuel)
	 * 
	 * @param dni
	 *            del corredor
	 * @return nombre del coredor
	 */
	public String getNombreCorredor(String dni) {
		String ret = "";
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT nombre FROM CORREDOR WHERE dni = ? ");
			ps.setString(1, dni);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ret = rs.getString(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			cerrarConexion();
		}
		return ret;
	}

	/**
	 * Cierra la conexion (samuel)
	 */

	private void cerrarConexion() {
		try {

			con.close();
		} catch (SQLException sq) {
			sq.printStackTrace();
		}

	}

	public int getDosalesReservados(String idCarrera, String idOrganizador) {
		int dorsales=-1;
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement("select dorsalReservado from competicion where idcompeticion=? and idorganizador=?");
			st.setString(1, idCarrera);
			st.setString(2, idOrganizador);
			ResultSet rs=st.executeQuery();
			if(rs.next())
				dorsales=rs.getInt("dorsalReservado");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dorsales;
	}
	
	public boolean setDorsal(String idCarrera, String idOrganizador,int dorsal){
		Connection con;
		try {
			con = getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE competicion SET dorsalReservado = ? WHERE idcompeticion = ? AND idorganizador = ?");
			ps.setString(2, idCarrera);
			ps.setString(3, idOrganizador);
			ps.setInt(1, dorsal);
			ps.executeQuery();
			//System.out.println("toy aki con "+idCarrera+"  "+idOrganizador+"  "+dorsal);
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
}
