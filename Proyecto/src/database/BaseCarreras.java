package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import logica.Carrera;
import logica.Categoria;
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
					+ " nombre_competicion,distancia,tipo,plazas_disponibles,lugar,categoria1,linferior1,lsuperior1,categoria2, "
					+ "linferior2,lsuperior2,categoria3,linferior3,lsuperior3,categoria4,linferior4,lsuperior4,fecha_apertura_ins2,fecha_final_ins2,precio2,"
					+ " fecha_apertura_ins3,fecha_final_ins3,precio3,fecha_apertura_ins4,fecha_final_ins4,precio4,fecha_apertura_ins5,fecha_final_ins5,precio5 "
					+ "from competicion,organizador where organizador.idorganizador = competicion.idorganizador AND CURRENT_DATE BETWEEN fecha_apertura_ins AND "
					+ " fecha_final_ins or cURRENT_DATE BETWEEN fecha_apertura_ins2 AND fecha_final_ins2 or cURRENT_DATE BETWEEN fecha_apertura_ins3 AND fecha_final_ins3 "
					+ "or cURRENT_DATE BETWEEN fecha_apertura_ins4 AND fecha_final_ins4 or cURRENT_DATE BETWEEN fecha_apertura_ins5 AND fecha_final_ins5 "
					+ " order by fechacompeticion";

			ResultSet rs = st.executeQuery(consulta);
			Categoria[] categorias;
			java.sql.Date apertura = null, finaliza = null;
			Double precio = null;
			Date actual = new Date();
			while (rs.next()) {

				if (rs.getString("categoria4") != null && rs.getInt("linferior4") != 0
						&& rs.getInt("lsuperior4") != 0) {
					Categoria[] c = {
							new Categoria(rs.getInt("linferior1"), rs.getInt("lsuperior1"), rs.getString("categoria1")),
							new Categoria(rs.getInt("linferior2"), rs.getInt("lsuperior2"), rs.getString("categoria2")),
							new Categoria(rs.getInt("linferior3"), rs.getInt("lsuperior3"), rs.getString("categoria3")),
							new Categoria(rs.getInt("linferior4"), rs.getInt("lsuperior4"),
									rs.getString("categoria4")), };
					categorias = c;
				} else {
					Categoria[] c = {
							new Categoria(rs.getInt("linferior1"), rs.getInt("lsuperior1"), rs.getString("categoria1")),
							new Categoria(rs.getInt("linferior2"), rs.getInt("lsuperior2"), rs.getString("categoria2")),
							new Categoria(rs.getInt("linferior3"), rs.getInt("lsuperior3"),
									rs.getString("categoria3")), };
					categorias = c;
				}
				
				if (rs.getDate("Fecha_apertura_ins") != null && rs.getDate("Fecha_final_ins") != null){
					if (rs.getDate("Fecha_apertura_ins").before(actual)
							&& rs.getDate("Fecha_final_ins").after(actual)) {
						apertura = rs.getDate("Fecha_apertura_ins");
						finaliza = rs.getDate("Fecha_final_ins");
						precio = rs.getDouble("precio");
					}
				}
				if (rs.getDate("Fecha_apertura_ins2") != null && rs.getDate("Fecha_final_ins2") != null){
					if (rs.getDate("Fecha_apertura_ins2").before(actual)
							&& rs.getDate("Fecha_final_ins2").after(actual)) {
						apertura = rs.getDate("Fecha_apertura_ins2");
						finaliza = rs.getDate("Fecha_final_ins2");
						precio = rs.getDouble("precio2");
					}
				}
				if(rs.getDate("Fecha_apertura_ins3") != null && rs.getDate("Fecha_final_ins3") != null){
					if (rs.getDate("Fecha_apertura_ins3").before(actual)
							&& rs.getDate("Fecha_final_ins3").after(actual)) {
						apertura = rs.getDate("Fecha_apertura_ins3");
						finaliza = rs.getDate("Fecha_final_ins3");
						precio = rs.getDouble("precio3");
					}
				}
				if(rs.getDate("Fecha_apertura_ins4") != null && rs.getDate("Fecha_final_ins4") != null){
					if (rs.getDate("Fecha_apertura_ins4").before(actual)
							&& rs.getDate("Fecha_final_ins4").after(actual)) {
						apertura = rs.getDate("Fecha_apertura_ins4");
						finaliza = rs.getDate("Fecha_final_ins4");
						precio = rs.getDouble("precio4");
					}
				}
				if(rs.getDate("Fecha_apertura_ins5") != null && rs.getDate("Fecha_final_ins5") != null) {
					if (rs.getDate("Fecha_apertura_ins5").before(actual)
							&& rs.getDate("Fecha_final_ins5").after(actual)) {
						apertura = rs.getDate("Fecha_apertura_ins5");
						finaliza = rs.getDate("Fecha_final_ins5");
						precio = rs.getDouble("precio5");
					}
				}		


				if (apertura != null && finaliza != null) {
					carreras.add(new Carrera(rs.getString("Nombre_Competicion"), precio,  finaliza,apertura,
							rs.getDate("Fechacompeticion"), rs.getDouble("Distancia"), rs.getString("Tipo"),
							new Organizador(rs.getString("nombreorganizador"), rs.getString("idorganizador")),
							rs.getInt("plazas_disponibles"), rs.getString("idcompeticion"), rs.getString("lugar"),
							categorias));

				}
				else
					JOptionPane.showMessageDialog(null, "Fechas null");
			}

			st.close();
			con.close();
			rs.close();

		} catch (SQLException e) {

			System.err.println("Error al intentar crear el arrayList de carreras ordenadas");
			e.printStackTrace();

		}

	}

	public void crearCarrera1Plazo3categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, String categoria1,
			int linferior1, int lsuperior1, String categoria2, int linferior2, int lsuperior2, String categoria3,
			int linferior3, int lsuperior3) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,CATEGORIA1,LINFERIOR1,LSUPERIOR1"
					+ " , CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3) VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setString(10, categoria1);
			ps.setInt(11, linferior1);
			ps.setInt(12, lsuperior1);

			ps.setString(13, categoria2);
			ps.setInt(14, linferior2);
			ps.setInt(15, lsuperior2);

			ps.setString(16, categoria3);
			ps.setInt(17, linferior3);
			ps.setInt(18, lsuperior3);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}

	}

	public void crearCarrera1Plazo4categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, String categoria1,
			int linferior1, int lsuperior1, String categoria2, int linferior2, int lsuperior2, String categoria3,
			int linferior3, int lsuperior3, String categoria4, int linferior4, int lsuperior4) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,CATEGORIA1,LINFERIOR1,LSUPERIOR1"
					+ " , CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3,CATEGORIA4,LINFERIOR4,LSUPERIOR4) VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setString(10, categoria1);
			ps.setInt(11, linferior1);
			ps.setInt(12, lsuperior1);

			ps.setString(13, categoria2);
			ps.setInt(14, linferior2);
			ps.setInt(15, lsuperior2);

			ps.setString(16, categoria3);
			ps.setInt(17, linferior3);
			ps.setInt(18, lsuperior3);

			ps.setString(19, categoria4);
			ps.setInt(20, linferior4);
			ps.setInt(21, lsuperior4);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}

	}

	public void crearCarrera2Plazo3categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, String categoria1, int linferior1, int lsuperior1, String categoria2,
			int linferior2, int lsuperior2, String categoria3, int linferior3, int lsuperior3) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,"
					+ " CATEGORIA1,LINFERIOR1,LSUPERIOR1,"
					+ " CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3) VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setString(13, categoria1);
			ps.setInt(14, linferior1);
			ps.setInt(15, lsuperior1);

			ps.setString(16, categoria2);
			ps.setInt(17, linferior2);
			ps.setInt(18, lsuperior2);

			ps.setString(19, categoria3);
			ps.setInt(20, linferior3);
			ps.setInt(21, lsuperior3);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}

	}

	public void crearCarrera2Plazo4categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, String categoria1, int linferior1, int lsuperior1, String categoria2,
			int linferior2, int lsuperior2, String categoria3, int linferior3, int lsuperior3, String categoria4,
			int linferior4, int lsuperior4) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,"
					+ " CATEGORIA1,LINFERIOR1,LSUPERIOR1, CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3,"
					+ " CATEGORIA4,LINFERIOR4,LSUPERIOR4) VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setString(13, categoria1);
			ps.setInt(14, linferior1);
			ps.setInt(15, lsuperior1);

			ps.setString(16, categoria2);
			ps.setInt(17, linferior2);
			ps.setInt(18, lsuperior2);

			ps.setString(19, categoria3);
			ps.setInt(20, linferior3);
			ps.setInt(21, lsuperior3);
			ps.setString(22, categoria4);
			ps.setInt(23, linferior4);
			ps.setInt(24, lsuperior4);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}

	}

	public void crearCarrera3Plazo3categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, double precio3, Date fechaEI3, Date fechaFI3, String categoria1,
			int linferior1, int lsuperior1, String categoria2, int linferior2, int lsuperior2, String categoria3,
			int linferior3, int lsuperior3) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,PRECIO3,FECHA_APERTURA_INS3,FECHA_FINAL_INS3,"
					+ " CATEGORIA1,LINFERIOR1,LSUPERIOR1, CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3"
					+ ") VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setDouble(13, precio3);
			ps.setDate(14, new java.sql.Date(fechaEI3.getTime()));
			ps.setDate(15, new java.sql.Date(fechaFI3.getTime()));
			ps.setString(16, categoria1);
			ps.setInt(17, linferior1);
			ps.setInt(18, lsuperior1);

			ps.setString(19, categoria2);
			ps.setInt(20, linferior2);
			ps.setInt(21, lsuperior2);

			ps.setString(22, categoria3);
			ps.setInt(23, linferior3);
			ps.setInt(24, lsuperior3);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}
	}

	public void crearCarrera3Plazo4categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, double precio3, Date fechaEI3, Date fechaFI3, String categoria1,
			int linferior1, int lsuperior1, String categoria2, int linferior2, int lsuperior2, String categoria3,
			int linferior3, int lsuperior3, String categoria4, int linferior4, int lsuperior4) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,PRECIO3,FECHA_APERTURA_INS3,FECHA_FINAL_INS3,"
					+ " CATEGORIA1,LINFERIOR1,LSUPERIOR1, CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3, CATEGORIA4,LINFERIOR4,LSUPERIOR4"
					+ ") VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setDouble(13, precio3);
			ps.setDate(14, new java.sql.Date(fechaEI3.getTime()));
			ps.setDate(15, new java.sql.Date(fechaFI3.getTime()));
			ps.setString(16, categoria1);
			ps.setInt(17, linferior1);
			ps.setInt(18, lsuperior1);

			ps.setString(19, categoria2);
			ps.setInt(20, linferior2);
			ps.setInt(21, lsuperior2);

			ps.setString(22, categoria3);
			ps.setInt(23, linferior3);
			ps.setInt(24, lsuperior3);

			ps.setString(25, categoria4);
			ps.setInt(26, linferior4);
			ps.setInt(27, lsuperior4);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}
	}

	public void crearCarrera4Plazo3categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, double precio3, Date fechaEI3, Date fechaFI3, double precio4, Date fechaEI4,
			Date fechaFI4, String categoria1, int linferior1, int lsuperior1, String categoria2, int linferior2,
			int lsuperior2, String categoria3, int linferior3, int lsuperior3) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,PRECIO3,FECHA_APERTURA_INS3,FECHA_FINAL_INS3, "
					+ " PRECIO4,FECHA_APERTURA_INS4,FECHA_FINAL_INS4, CATEGORIA1,LINFERIOR1,LSUPERIOR1, CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3"
					+ ") VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setDouble(13, precio3);
			ps.setDate(14, new java.sql.Date(fechaEI3.getTime()));
			ps.setDate(15, new java.sql.Date(fechaFI3.getTime()));
			ps.setDouble(16, precio4);
			ps.setDate(17, new java.sql.Date(fechaEI4.getTime()));
			ps.setDate(18, new java.sql.Date(fechaFI4.getTime()));

			ps.setString(19, categoria1);
			ps.setInt(20, linferior1);
			ps.setInt(21, lsuperior1);

			ps.setString(22, categoria2);
			ps.setInt(23, linferior2);
			ps.setInt(24, lsuperior2);

			ps.setString(25, categoria3);
			ps.setInt(26, linferior3);
			ps.setInt(27, lsuperior3);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}
	}

	public void crearCarrera4Plazo4categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, double precio3, Date fechaEI3, Date fechaFI3, double precio4, Date fechaEI4,
			Date fechaFI4, String categoria1, int linferior1, int lsuperior1, String categoria2, int linferior2,
			int lsuperior2, String categoria3, int linferior3, int lsuperior3, String categoria4, int linferior4,
			int lsuperior4) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,PRECIO3,FECHA_APERTURA_INS3,FECHA_FINAL_INS3, "
					+ " PRECIO4,FECHA_APERTURA_INS4,FECHA_FINAL_INS4, CATEGORIA1,LINFERIOR1,LSUPERIOR1, CATEGORIA2,LINFERIOR2,LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3,"
					+ " CATEGORIA4,LINFERIOR4,LSUPERIOR4) VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setDouble(13, precio3);
			ps.setDate(14, new java.sql.Date(fechaEI3.getTime()));
			ps.setDate(15, new java.sql.Date(fechaFI3.getTime()));
			ps.setDouble(16, precio4);
			ps.setDate(17, new java.sql.Date(fechaEI4.getTime()));
			ps.setDate(18, new java.sql.Date(fechaFI4.getTime()));

			ps.setString(19, categoria1);
			ps.setInt(20, linferior1);
			ps.setInt(21, lsuperior1);

			ps.setString(22, categoria2);
			ps.setInt(23, linferior2);
			ps.setInt(24, lsuperior2);

			ps.setString(25, categoria3);
			ps.setInt(26, linferior3);
			ps.setInt(27, lsuperior3);

			ps.setString(28, categoria4);
			ps.setInt(29, linferior4);
			ps.setInt(30, lsuperior4);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}
	}

	public void crearCarrera5Plazo3categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, double precio3, Date fechaEI3, Date fechaFI3, double precio4, Date fechaEI4,
			Date fechaFI4, double precio5, Date fechaEI5, Date fechaFI5, String categoria1, int linferior1,
			int lsuperior1, String categoria2, int linferior2, int lsuperior2, String categoria3, int linferior3,
			int lsuperior3) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,PRECIO3,FECHA_APERTURA_INS3,FECHA_FINAL_INS3, "
					+ " PRECIO4,FECHA_APERTURA_INS4,FECHA_FINAL_INS4, PRECIO5,FECHA_APERTURA_INS5,FECHA_FINAL_INS5,CATEGORIA1,LINFERIOR1,LSUPERIOR1, CATEGORIA2,LINFERIOR2,"
					+ "LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3"
					+ ") VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setDouble(13, precio3);
			ps.setDate(14, new java.sql.Date(fechaEI3.getTime()));
			ps.setDate(15, new java.sql.Date(fechaFI3.getTime()));
			ps.setDouble(16, precio4);
			ps.setDate(17, new java.sql.Date(fechaEI4.getTime()));
			ps.setDate(18, new java.sql.Date(fechaFI4.getTime()));
			ps.setDouble(19, precio5);
			ps.setDate(20, new java.sql.Date(fechaEI5.getTime()));
			ps.setDate(21, new java.sql.Date(fechaFI5.getTime()));

			ps.setString(22, categoria1);
			ps.setInt(23, linferior1);
			ps.setInt(24, lsuperior1);

			ps.setString(25, categoria2);
			ps.setInt(26, linferior2);
			ps.setInt(27, lsuperior2);

			ps.setString(28, categoria3);
			ps.setInt(29, linferior3);
			ps.setInt(30, lsuperior3);
			ps.executeQuery();
		} catch (SQLException e) {

			System.err.println("Error al intentar insertar una carrera creada por el organizador");
			e.printStackTrace();
		}
	}

	public void crearCarrera5Plazo4categorias(String nombre, double precio, Date fechaCompeticion, Date fechaEI,
			Date fechaFI, double distancia, String tipo, int plazasDisponibles, String lugar, double precio2,
			Date fechaEI2, Date fechaFI2, double precio3, Date fechaEI3, Date fechaFI3, double precio4, Date fechaEI4,
			Date fechaFI4, double precio5, Date fechaEI5, Date fechaFI5, String categoria1, int linferior1,
			int lsuperior1, String categoria2, int linferior2, int lsuperior2, String categoria3, int linferior3,
			int lsuperior3, String categoria4, int linferior4, int lsuperior4) {
		try {
			con = getConnection();
			String consulta = "INSERT INTO COMPETICION (IDORGANIZADOR,FECHACOMPETICION, FECHA_APERTURA_INS,FECHA_FINAL_INS, PRECIO,"
					+ " NOMBRE_COMPETICION,DISTANCIA, TIPO,PLAZAS_DISPONIBLES,LUGAR,PRECIO2,FECHA_APERTURA_INS2,FECHA_FINAL_INS2,PRECIO3,FECHA_APERTURA_INS3,FECHA_FINAL_INS3, "
					+ " PRECIO4,FECHA_APERTURA_INS4,FECHA_FINAL_INS4, PRECIO5,FECHA_APERTURA_INS5,FECHA_FINAL_INS5,CATEGORIA1,LINFERIOR1,LSUPERIOR1, CATEGORIA2,LINFERIOR2,"
					+ "LSUPERIOR2,CATEGORIA3,LINFERIOR3,LSUPERIOR3,CATEGORIA4,LINFERIOR4,LSUPERIOR4"
					+ ") VALUES(1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(consulta);

			ps.setDate(1, new java.sql.Date(fechaCompeticion.getTime()));
			ps.setDate(2, new java.sql.Date(fechaEI.getTime()));
			ps.setDate(3, new java.sql.Date(fechaFI.getTime()));
			ps.setDouble(4, precio);
			ps.setString(5, nombre);
			ps.setDouble(6, distancia);
			ps.setString(7, tipo);
			ps.setInt(8, plazasDisponibles);
			ps.setString(9, lugar);
			ps.setDouble(10, precio2);
			ps.setDate(11, new java.sql.Date(fechaEI2.getTime()));
			ps.setDate(12, new java.sql.Date(fechaFI2.getTime()));
			ps.setDouble(13, precio3);
			ps.setDate(14, new java.sql.Date(fechaEI3.getTime()));
			ps.setDate(15, new java.sql.Date(fechaFI3.getTime()));
			ps.setDouble(16, precio4);
			ps.setDate(17, new java.sql.Date(fechaEI4.getTime()));
			ps.setDate(18, new java.sql.Date(fechaFI4.getTime()));
			ps.setDouble(19, precio5);
			ps.setDate(20, new java.sql.Date(fechaEI5.getTime()));
			ps.setDate(21, new java.sql.Date(fechaFI5.getTime()));

			ps.setString(22, categoria1);
			ps.setInt(23, linferior1);
			ps.setInt(24, lsuperior1);

			ps.setString(25, categoria2);
			ps.setInt(26, linferior2);
			ps.setInt(27, lsuperior2);

			ps.setString(28, categoria3);
			ps.setInt(29, linferior3);
			ps.setInt(30, lsuperior3);

			ps.setString(31, categoria4);
			ps.setInt(32, linferior4);
			ps.setInt(33, lsuperior4);
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
