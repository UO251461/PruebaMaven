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

import javax.swing.JDialog;

import igu.VentanaInformeIncidencias;
import logica.Corredor;
import logica.GestorExtractos;
import logica.Incidencia;
import logica.Inscripcion;
import logica.Plazo;

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
	private void cerrarConexion() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException sq) {
				sq.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException sq) {
				sq.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException sq) {
				sq.printStackTrace();
			}

	}

	public void cambiarEstado(String estado, Inscripcion ins) {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"UPDATE INSCRIPCION SET ESTADO = ? WHERE IDCOMPETICION = ? AND IDORGANIZADOR = ? AND DNI = ?");
			ps.setString(1, estado);
			ps.setString(2, ins.getCarrera().getIdcarrera());
			ps.setString(3, ins.getCarrera().getOrganizador().getIdorganizador());
			ps.setString(4, ins.getCorredor().getDni());
			rs = ps.executeQuery();
			ins.setEstado(estado);
		} catch (SQLException sq) {
			sq.printStackTrace();
		} finally {
			cerrarConexion();
		}

	}

	/**
	 * Si el corredor no esta en la tabla registro lo inserta y rellena sus
	 * datos en el objeto inscripcion, en caso contrariorecupera los datos de la
	 * tabla y los rellena en inscripcion (Samuel)
	 * 
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param fecha
	 * @param sexo
	 * @param inscripcion
	 * @throws SQLException
	 */

	public boolean registrarCorredor(String fecha, Inscripcion inscripcion) throws SQLException {// (String
																									// dni,
																									// String
																									// nombre,
																									// String
																									// aplllido,
																									// String
																									// fecha,
																									// String
																									// sexo)
		boolean inscrito;
		try {
			con = getConnection();

			Corredor c = inscripcion.getCorredor();
			String dni = c.getDni();
			String nombre = c.getNombre();
			String apellido = c.getApellido();
			String sexo = c.getSexo();
			// REGISTRAR E INSERTAR EN LA TABLA "CORREDOR"
			ps = con.prepareStatement("INSERT INTO CORREDOR VALUES(?,?,?,?,?)");
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setString(4, fecha);
			ps.setString(5, sexo);
			rs = ps.executeQuery();

		} catch (SQLException sql) {

		} finally {
			inscrito = inscribirCompeticion(inscripcion);
			cerrarConexion();

		}
		return inscrito;
	}

	/*
	 * Metodo que devuelve
	 */
	public void getInscripcionPorCompeticion(String idCompeticion) {
		Connection con;
		try {

			con = getConnection();
			String consulta = "select i.idcompeticion, i.idorganizador,i.dni,i.estado,i.fecha, "
					+ "i.categoria,nombre from inscripcion i, corredor c where i.dni = c.dni and i.idcompeticion = ? and i.estado = 'INSCRITO' order by fecha";
			PreparedStatement pst = con.prepareStatement(consulta);

			pst.setString(1, idCompeticion);

			ResultSet rs1 = pst.executeQuery();

			while (rs1.next()) {
				inscripcionesCarrera.add(new Inscripcion(rs1.getString("idcompeticion"), rs1.getString("idorganizador"),
						rs1.getString("dni"), rs1.getString("estado"), rs1.getDate("fecha"), rs1.getString("categoria"),
						rs1.getString("nombre")));

			}

			consulta = "select i.idcompeticion, i.idorganizador,i.dni,i.estado,i.fecha, "
					+ "i.categoria,nombre from inscripcion i, corredor c where i.dni = c.dni and i.idcompeticion = ? and i.estado = 'PRE-INSCRITO' order by fecha";
			PreparedStatement pst2 = con.prepareStatement(consulta);

			pst2.setString(1, idCompeticion);

			ResultSet rs2 = pst2.executeQuery();

			while (rs2.next()) {
				inscripcionesCarrera.add(new Inscripcion(rs2.getString("idcompeticion"), rs2.getString("idorganizador"),
						rs2.getString("dni"), rs2.getString("estado"), rs2.getDate("fecha"), rs2.getString("categoria"),
						rs2.getString("nombre")));

			}

			consulta = "select i.idcompeticion, i.idorganizador,i.dni,i.estado,i.fecha, "
					+ "i.categoria,nombre from inscripcion i, corredor c where i.dni = c.dni and i.idcompeticion = ? and i.estado = 'PENDIENTE_DE_PAGO' order by fecha";
			PreparedStatement pst3 = con.prepareStatement(consulta);

			pst3.setString(1, idCompeticion);

			ResultSet rs3 = pst3.executeQuery();

			while (rs3.next()) {
				inscripcionesCarrera.add(new Inscripcion(rs3.getString("idcompeticion"), rs3.getString("idorganizador"),
						rs3.getString("dni"), rs3.getString("estado"), rs3.getDate("fecha"), rs3.getString("categoria"),
						rs3.getString("nombre")));

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

	public boolean inscribirCompeticion(Inscripcion inscripcion) throws SQLException {
		boolean inscrito = false;
		try {
			// CONSULTA ADAPTADA PARA AÑADIRLE LA CATEGORIA
			con = getConnection();
			ps = con.prepareStatement(
					"INSERT INTO INSCRIPCION(IDCOMPETICION,IDORGANIZADOR,DNI,ESTADO,FECHA,CATEGORIA) VALUES(?,?,?,'PRE-INSCRITO',?,?)");
			Date fecha = new Date();
			ps.setString(1, inscripcion.getCarrera().getIdcarrera());
			ps.setString(2, inscripcion.getCarrera().getOrganizador().getIdorganizador());
			ps.setString(3, inscripcion.getCorredor().getDni());
			ps.setString(4, new SimpleDateFormat("dd-MM-yyyy").format(fecha));
			ps.setString(5, inscripcion.getCategoria());

			inscripcion.setFecha(fecha);

			rs = ps.executeQuery();
			if (rs.next()) {
				inscrito = true;
			}
			// si no no esta inscrito(Samuel)
			// inscripcion.setId_competicion(inscripcion.getCarrera());
			// inscripcion.setId_organizador(inscripcion.getId_organizador());
			inscripcion.setEstado("PRE-INSCRITO");
			// inscripcion.setPrecio(inscripcion.getPrecio());

		} catch (SQLException se) {

		}
		cerrarConexion();
		return inscrito;

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

	public ArrayList<String> getNombreCompeticiones() {
		nombres = new ArrayList<String>();
		ids = new HashMap<String, String[]>();
		try {
			Connection con = getConnection();
			ps = con.prepareStatement(
					"SELECT IDCOMPETICION, IDORGANIZADOR, NOMBRE_COMPETICION, PRECIO FROM COMPETICION ");
			rs = ps.executeQuery();
			while (rs.next()) {
				nombres.add(rs.getString(3));
				ids.put(rs.getString(3),
						new String[] { rs.getString(1), rs.getString(2), String.valueOf(rs.getDouble(4)) });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombres;
	}

	/**
	 * Devuelve la informacion del atleta de todas las carreras muestra el
	 * estado de su inscripcion tambien el tiempo y la posicion si dicha carrera
	 * ha finalizado.
	 * 
	 * @param atleta
	 *            identificador del atleta cuyos datos se desean obtener.
	 * @return
	 */
	public ArrayList<Inscripcion> getDatosAtleta(String atleta) {
		ArrayList<Inscripcion> datos = new ArrayList<Inscripcion>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM INSCRIPCION WHERE DNI=? ORDER BY FECHA DESC");
			ps.setString(1, atleta); // se introduce el identificador del atleta
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				datos.add(new Inscripcion(rs.getString("IDCOMPETICION"), rs.getString("IDORGANIZADOR"),
						rs.getString("DNI"), rs.getString("ESTADO"), rs.getDate("FECHA"), rs.getInt("DORSAL"),
						rs.getDouble("TIEMPO"), rs.getString("INCIDENCIA")));
			}

			rs.close();
			ps.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return datos;
	}

	/**
	 * Método que genera las clasificaciones absolutas y por sexo.
	 */
	public ArrayList<Inscripcion> generarClasificaciones(int n, String competicion) {
		ArrayList<Inscripcion> clasificacion = new ArrayList<Inscripcion>();
		try {
			Connection con = getConnection();
			// se crean las clasificaciones absolutas
			if (n == 0) {

				PreparedStatement ps = con.prepareStatement(
						"SELECT i.*, c.SEXO FROM INSCRIPCION i, CORREDOR c WHERE DORSAL>0 AND IDCOMPETICION=? AND i.dni=c.dni  ORDER BY TIEMPO");
				ps.setString(1, competicion);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					clasificacion.add(new Inscripcion(rs.getString("IDCOMPETICION"), rs.getString("IDORGANIZADOR"),
							rs.getString("DNI"), rs.getString("ESTADO"), rs.getDate("FECHA"), rs.getInt("DORSAL"),
							rs.getDouble("TIEMPO"), rs.getString("Categoria"), rs.getString("SEXO")));
				}
				rs.close();
				ps.close();
			} else if (n == 1) {
				// se crean las clasificaciones masculinas
				PreparedStatement ps = con.prepareStatement(
						"select * FROM INSCRIPCION WHERE DNI IN(SELECT DNI FROM CORREDOR WHERE SEXO='HOMBRE') AND IDCOMPETICION=? AND DORSAL>0 ORDER BY TIEMPO");
				ps.setString(1, competicion);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					clasificacion.add(new Inscripcion(rs.getString("IDCOMPETICION"), rs.getString("IDORGANIZADOR"),
							rs.getString("DNI"), rs.getString("ESTADO"), rs.getDate("FECHA"), rs.getInt("DORSAL"),
							rs.getDouble("TIEMPO"), rs.getString("Categoria"), "HOMBRE"));
				}
				rs.close();
				ps.close();
			} else if (n == 2) {
				// se crean las clasificaciones femeninas
				PreparedStatement ps = con.prepareStatement(
						"select * FROM INSCRIPCION WHERE DNI IN(SELECT DNI FROM CORREDOR WHERE SEXO='MUJER') AND IDCOMPETICION=? AND DORSAL>0 ORDER BY TIEMPO");
				ps.setString(1, competicion);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					clasificacion.add(new Inscripcion(rs.getString("IDCOMPETICION"), rs.getString("IDORGANIZADOR"),
							rs.getString("DNI"), rs.getString("ESTADO"), rs.getDate("FECHA"), rs.getInt("DORSAL"),
							rs.getDouble("TIEMPO"), rs.getString("Categoria"), "MUJER"));
				}
				rs.close();
				ps.close();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clasificacion;
	}

	/**
	 * Asigna el campo dorsal de la tabla inscripciones de aquellos que esten en
	 * estado INSCRITO(Samuel)
	 */
	public boolean asignarDorsal(String idcompeticion, String idorganizador) {
		boolean asignada = false;
		try {
			con = getConnection();
			ps = con.prepareStatement("SELECT dni FROM inscripcion WHERE estado = 'INSCRITO' AND idcompeticion = ?"
					+ " AND  idorganizador = ? ORDER BY FECHA ASC");
			ps.setString(1, idcompeticion);
			ps.setString(2, idorganizador);

			PreparedStatement corredorInscrito = con.prepareStatement(
					"UPDATE inscripcion SET dorsal = ? WHERE idcompeticion = ? AND idorganizador = ? AND dni = ?");

			rs = ps.executeQuery();
			// se dejan los 10 primeros (requisito)
			int numeroDorsal = 11;

			while (rs.next()) {
				asignada = true;
				corredorInscrito.setInt(1, numeroDorsal);
				corredorInscrito.setString(2, idcompeticion);
				corredorInscrito.setString(3, idorganizador);
				corredorInscrito.setString(4, rs.getString(1));
				corredorInscrito.executeQuery();
				numeroDorsal++;
			}

			corredorInscrito.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return asignada;
	}

	public Corredor estaRegistrado(String dni) {
		try {
			con = getConnection();
			ps = con.prepareStatement("select DNI, NOMBRE, APELLIDO, SEXO from CORREDOR where dni = ?");
			ps.setString(1, dni);
			rs = ps.executeQuery();
			if (rs.next())
				return new Corredor(rs.getString("DNI"), 0, rs.getString("SEXO"), rs.getString("NOMBRE"),
						rs.getString("APELLIDO"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return null;
	}

	/**
	 * Método que gestiona los estados de las inscripciones según el campo
	 * "incidencias" . Si de una inscripcion el campo "incidencia" está en
	 * "Falta dinero"estado de la inscripción ->ANULADO, si "Devolución" estado
	 * de la inscripción->INSCRITO, si "Fuera de plazo" estado -> ANULADO, si
	 * Pago correcto" estado -> "INSCRITO", si "(BLANCO)" estado -> "ANULADO",
	 * luego se mostrará una ventana con los datos de las incidencias :el número
	 * de pagos menos, no pagados, pagos ok, pago demás, pago fuera de plazo,
	 * cantidad procesados.(SAMUEL)
	 */
	public void gestionarIncidenciasActuales() {
		int cLeidos = 0;
		int nPagoMenos = 0;
		int nPagoMas = 0;
		int nPagadoFueraPlazo = 0;
		int nNoPagados = 0;
		int nPagosOk = 0;
		String campoIncidencia = "";
		try {
			con = getConnection();
			ps = con.prepareStatement("select IDCOMPETICION, IDORGANIZADOR, DNI, INCIDENCIA from INSCRIPCION");
			rs = ps.executeQuery();
			PreparedStatement ps2 = con.prepareStatement(
					"UPDATE INSCRIPCION SET ESTADO = ? WHERE IDCOMPETICION=? AND IDORGANIZADOR=? AND DNI = ?");
			while (rs.next()) {
				campoIncidencia = rs.getString("INCIDENCIA");

				ps2.setString(2, rs.getString("IDCOMPETICION"));
				ps2.setString(3, rs.getString("IDORGANIZADOR"));
				ps2.setString(4, rs.getString("DNI"));

				if (campoIncidencia == null) {
					ps2.setString(1, "ANULADO");
					nNoPagados++;
				} else if (campoIncidencia.equals("PAGO_MENOS")) {
					ps2.setString(1, "ANULADO");
					nPagoMenos++;
				} else if (campoIncidencia.equals("PAGO_MAS")) {
					ps2.setString(1, "INSCRITO");
					nPagoMas++;
				} else if (campoIncidencia.equals("PAGO_FUERA_PLAZO")) {
					ps2.setString(1, "ANULADO");
					nPagadoFueraPlazo++;
				} else if (campoIncidencia.equals("PAGO_CORRECTO")) {
					ps2.setString(1, "INSCRITO");
					nPagosOk++;
				}

				ps2.executeQuery();
				cLeidos++;
			}

			ps2.close();

			VentanaInformeIncidencias vi = new VentanaInformeIncidencias(nPagosOk, nPagoMenos, nPagoMas,
					nPagadoFueraPlazo, nNoPagados, cLeidos);
			vi.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			vi.setVisible(true);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			cerrarConexion();
		}

	}

	public void generarIncidencias(GestorExtractos incidencias) {

		try {
			Connection conexion = getConnection();
			PreparedStatement pst = conexion.prepareStatement(
					"update INSCRIPCION set INCIDENCIA = ?,CANTIDAD= ? WHERE IDCOMPETICION = ? AND IDORGANIZADOR = ? AND DNI= ?");

			for (Incidencia inc : incidencias.getIncidencias()) {
				pst.setString(1, incidencias.comentario(inc));
				float pagoCuota = getPrecioCarrera(
						getInscripcionByIds(inc.getIdCompeti(), inc.getOrganizador(), inc.getDni()).getCarrera()
								.getIdcarrera(),
						getInscripcionByIds(inc.getIdCompeti(), inc.getOrganizador(), inc.getDni()).getCarrera()
								.getOrganizador().getIdorganizador());
				float pagoTotal = (float) (inc.getPago() - pagoCuota);
				pst.setFloat(2, pagoTotal);
				pst.setString(3, inc.getIdCompeti());
				pst.setString(4, inc.getOrganizador());
				pst.setString(5, inc.getDni());
				ResultSet rst = pst.executeQuery();
				rst.close();
			}

			pst.close();
			conexion.close();
		} catch (SQLException e) {

		} finally {

		}
	}

	public Inscripcion getInscripcionByIds(String idCompeticion, String idOrganizacion, String dni) {
		Inscripcion inscripcion;
		try {
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(
					"select estado , fecha , categoria from inscripcion where idCompeticion = ? and idOrganizador = ? and dni = ?");
			pst.setString(1, idCompeticion);
			pst.setString(2, idOrganizacion);
			pst.setString(3, dni);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				inscripcion = new Inscripcion(idCompeticion, idOrganizacion, dni, rst.getString("estado"),
						rst.getDate("fecha"), rst.getString("categoria"));
				return inscripcion;
			}

			rst.close();
			pst.close();
			con.close();
		} catch (SQLException se) {

		}
		return null;

	}

	private float getPrecioCarrera(String idCompeti, String idOrganizador) {
		float precio = 0;
		try {
			Connection con = getConnection();
			PreparedStatement pst = con
					.prepareStatement("select precio from competicion where idCompeticion = ? and idOrganizador = ?");
			pst.setString(1, idCompeti);
			pst.setString(2, idOrganizador);

			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				precio = rst.getFloat("precio");
			}

			rst.close();
			pst.close();
			con.close();
		} catch (SQLException se) {

		}
		return precio;
	}

	public void actualizarDorsales(String idCompeticion, String idOrganizador, int dorsal) {
		try {
			Connection con = getConnection();
			if(dorsal==-1){
			PreparedStatement ps2 = con.prepareStatement(
					"select max(dorsal) max " + "from inscripcion " + "where IDCOMPETICION=? and idorganizador=?");
			ps2.setString(1, idCompeticion);
			ps2.setString(2, idOrganizador);
			ResultSet rs2 = ps2.executeQuery();
			
			if(rs2.next())
				dorsal = rs2.getInt("MAX");
			rs2.close();
			ps2.close();
			}
			PreparedStatement ps3 = con.prepareStatement("select * "
					+ "from inscripcion where estado='INSCRITO' and IDCOMPETICION=? and idorganizador=? and dorsal is null order by fecha asc");
			ps3.setString(1, idCompeticion);
			ps3.setString(2, idOrganizador);
			ResultSet rs3 = ps3.executeQuery();
			dorsal++;
			PreparedStatement ps4 = con.prepareStatement(
					"UPDATE inscripcion SET dorsal = ? WHERE idcompeticion = ? AND idorganizador = ? AND dni = ?");

			while (rs3.next()) {
				ps4.setInt(1, dorsal);
				ps4.setString(2, idCompeticion);
				ps4.setString(3, idOrganizador);
				ps4.setString(4, rs3.getString("dni"));
				ps4.executeQuery();
				dorsal++;
			}
			ps4.close();
			rs3.close();


			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cancelarInscripcion(Inscripcion inscripcion, float precio) throws SQLException {
	
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement("UPDATE inscripcion SET estado = ? and incidencia = ? and cantidad = ? where idcompeticion = ? and idorganizador = ? and dni = ?");
			
			pst.setString(1, "CANCELADA");
			pst.setString(2, "CANCELADO_DEVOLVER_DINERO");
			pst.setFloat(3, precio);
			pst.setString(4, inscripcion.getCarrera().getIdcarrera());
			pst.setString(5, inscripcion.getCarrera().getOrganizador().getIdorganizador());
			pst.setString(6, inscripcion.getCorredor().getDni());
			
			ResultSet rst = pst.executeQuery();
			

			rst.close();
			pst.close();
			con.close();
	
		
		
	}
	
	
	public float cantidadInscripcion(Inscripcion inscripcion) throws SQLException {
		
			float cantidad = -1;
			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement("select cantidad from inscripcion where idcompeticion = ? and idorganizador = ? and dni = ?");
			pst.setString(1, inscripcion.getCarrera().getIdcarrera());
			pst.setString(2, inscripcion.getCarrera().getOrganizador().getIdorganizador());
			pst.setString(3, inscripcion.getCorredor().getDni());
			
			
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				cantidad = rs.getFloat("cantidad");
			}

			rst.close();
			pst.close();
			con.close();
			return cantidad;
		
	}
	
	public float porcentajeCancelacion(Inscripcion insc, Date date) throws SQLException {
		float porcentaje = -1;
		
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select porcentaje from plazo_cancelacion pl where idcompeticion = ? and IDORGANIZADOR = ? and ? between fecha_inicio_can and fecha_final_can");
		pst.setString(1, insc.getCarrera().getIdcarrera());
		pst.setString(2, insc.getCarrera().getOrganizador().getIdorganizador());
		pst.setDate(3, (java.sql.Date) date);
		
		ResultSet rst = pst.executeQuery();
		if (rst.next()) {
			porcentaje = rs.getFloat("porcentaje");
		}

		rst.close();
		pst.close();
		con.close();
		
		return porcentaje;
	}
	
	public int precioInscripcion(Inscripcion inscripcion) throws SQLException {
		int cantidad = -1;
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement("select precio from inscripcion where idcompeticion = ? and idorganizador = ? and dni = ?");
		pst.setString(1, inscripcion.getCarrera().getIdcarrera());
		pst.setString(2, inscripcion.getCarrera().getOrganizador().getIdorganizador());
		pst.setString(3, inscripcion.getCorredor().getDni());
		
		
		ResultSet rst = pst.executeQuery();
		if (rst.next()) {
			cantidad = rs.getInt("cantidad");
		}

		rst.close();
		pst.close();
		con.close();
		return cantidad;
	}
	
	public void aumentarPlazasDisponibles(Inscripcion inscripcion) throws SQLException {
		Connection con = getConnection();
		
		PreparedStatement pst = con.prepareStatement("select plazas_disponibles from competicion where idcompeticion = ? and idorganizador = ?");
		pst.setString(1, inscripcion.getCarrera().getIdcarrera());
		pst.setString(2, inscripcion.getCarrera().getOrganizador().getIdorganizador());
			
		
		ResultSet rst = pst.executeQuery();
		int plazas = rst.getInt("plazas_disponibles");
	
		
		PreparedStatement pst2 = con.prepareStatement("UPDATE competicion SET plazas_disponibles = ? where idcompeticion = ? and idorganizador = ? and dni = ?");
		pst2.setInt(1, plazas+1);
		pst2.setString(2, inscripcion.getCarrera().getIdcarrera());
		pst2.setString(3, inscripcion.getCarrera().getOrganizador().getIdorganizador());
		
		
		
		ResultSet rst2 = pst.executeQuery();
	

		rst2.close();
		pst2.close();
		rst.close();
		pst.close();
		con.close();
	}
}




