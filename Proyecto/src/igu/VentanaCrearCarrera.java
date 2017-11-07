package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import logica.Inscripcion;
import logica.ModeloNoEditable;
import logica.Plazo;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaCrearCarrera extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int numeroMaxPlazos = 5;

	private JPanel contentPane;
	private ModeloNoEditable modeloTabla;

	private VentanaPrincipal vp;
	private JButton btnCrear;
	private JButton btnAtras;
	private JLabel lblNombreCompeticion;
	private JTextField textNombreCarrera;
	private JLabel lblTipo;
	private JTextField textTipo;
	private JLabel lblDistancia;
	private JTextField textDistancia;

	private JDateChooser fechaCompeticion;
	private JLabel lblFechaCompeticion;
	private JLabel lblNumeroDePlazas;
	private JTextField textPlazas;
	private JPanel panelPlazos;
	private JLabel lblFechaComienzoInscripcion;
	private JDateChooser dateComienzoInscripcion;
	private JDateChooser dateFinalInscripcion;
	private JLabel lblFechaFinalInscripcion;
	private JLabel lblPrecio;
	private JTextField textPrecio;
	private JLabel lblLugar;
	private JTextField textLugar;
	private JButton btnAñadirPlazoInscripcion;
	private JTable tablePlazos;
	private JButton btnBorrarPlazoInscripcion;
	private JLabel lblPlazos;

	private Plazo[] plazos = new Plazo[5];
	private int numeroPlazos;
	private JLabel lblCategorias;
	private JTable tableCategorias;
	private JButton btnBorrarCategoria;
	private JPanel panelCategorias;
	private JLabel lblNombreCategoria;
	private JLabel lblLimiteInferior;
	private JLabel lblLimiteSuperior;
	private JButton btnAadirCategoria;
	private JTextField textNombreCategoria;
	private JTextField textLimiteInferior;
	private JTextField textLimiteSuperior;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaCrearCarrera(VentanaPrincipal vp) {
		setResizable(false);
		this.vp = vp;
		setTitle("Crear Carrera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNombreCompeticion());
		contentPane.add(getTextNombreCarrera());
		contentPane.add(getLblTipo());
		contentPane.add(getTextTipo());
		contentPane.add(getLblDistancia());
		contentPane.add(getTextDistancia());
		contentPane.add(getBtnAtras());
		contentPane.add(getBtnCrear());
		contentPane.add(getLblFechaCompeticion());
		contentPane.add(getLblNumeroDePlazas());
		contentPane.add(getTextPlazas());
		contentPane.add(getPanelPlazos());
		contentPane.add(getDateCompeticion());
		contentPane.add(getLblLugar());
		contentPane.add(getTextLugar());
		contentPane.add(getTablePlazos());
		contentPane.add(getBtnBorrarPlazoInscripcion());
		contentPane.add(getLblPlazos());
		contentPane.add(getLblCategorias());
		contentPane.add(getTableCategorias());
		contentPane.add(getBtnBorrarCategoria());
		contentPane.add(getPanelCategorias());
	}

	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (compruebaCampos()) {
						creaCarrera();
						dispose();
					}
				}

			});
			btnCrear.setBounds(911, 583, 86, 23);
		}
		return btnCrear;
	}

	private void creaCarrera() {
		if (this.numeroPlazos == 1) {
			vp.getBase().getBaseCarrera().crearCarrera1Plazo(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText());
			JOptionPane.showMessageDialog(null, "Su carrera ha sido creada satisfactoriamente");
		}

		if (this.numeroPlazos == 2) {
			vp.getBase().getBaseCarrera().crearCarrera2Plazo(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion());
			JOptionPane.showMessageDialog(null, "Su carrera ha sido creada satisfactoriamente");
		}

		if (this.numeroPlazos == 3) {
			vp.getBase().getBaseCarrera().crearCarrera3Plazo(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion());
			JOptionPane.showMessageDialog(null, "Su carrera ha sido creada satisfactoriamente");
		}
		if (this.numeroPlazos == 4) {
			vp.getBase().getBaseCarrera().crearCarrera4Plazo(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(),
					plazos[3].getPrecio(), plazos[3].getFechaEmpiezaInscripcion(),
					plazos[3].getFechaFinalizaInscripcion());
			JOptionPane.showMessageDialog(null, "Su carrera ha sido creada satisfactoriamente");
		}
		if (this.numeroPlazos == 5) {
			vp.getBase().getBaseCarrera().crearCarrera5Plazo(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(),
					plazos[3].getPrecio(), plazos[3].getFechaEmpiezaInscripcion(),
					plazos[3].getFechaFinalizaInscripcion(), 
					plazos[4].getPrecio(), plazos[4].getFechaEmpiezaInscripcion(),
					plazos[4].getFechaFinalizaInscripcion());
			JOptionPane.showMessageDialog(null, "Su carrera ha sido creada satisfactoriamente");
		}

	}

	private boolean compruebaCampos() {
		if (stringNoVacio(getTextNombreCarrera().getText()))
			if (compruebaFechas())
				if (esNumericoYNoVacio(getTextDistancia().getText())
						&& Double.parseDouble(getTextDistancia().getText()) > 0)
					if (stringNoVacio(getTextTipo().getText()))
						if (esNumericoYNoVacio(getTextPlazas().getText())
								&& Integer.parseInt(getTextPlazas().getText()) > 0)
							if (stringNoVacio(getTextLugar().getText()))
								return true;
							else {
								JOptionPane.showMessageDialog(this,
										"El lugar de la carrera NO ha sido introducido correctamente");
								return false;
							}
						else {
							JOptionPane.showMessageDialog(this,
									"El numero de plazas de la carrera NO ha sido introducido correctamente");
							return false;
						}
					else {
						JOptionPane.showMessageDialog(this,
								"El tipo de la carrera NO ha sido introducido correctamente");
						return false;
					}
				else {
					JOptionPane.showMessageDialog(this,
							"La distancia de la carrera NO ha sido introducido correctamente");
					return false;
				}
			else {
				JOptionPane.showMessageDialog(this,
						"Las fechas no son concoordantes entre ellas, no se ha de solapar ningun plazo de inscripcion "
								+ "y todos deben ser antes de la fecha de competicion. Ademas la fecha inicial de inscripcion debe ser "
								+ "antes que la fecha final de inscripcion de cada plazo.");
				return false;
			}
		else {
			JOptionPane.showMessageDialog(this, "El nombre de la carrera NO ha sido introducido correctamente");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void creaPlazos() {
		this.plazos = new Plazo[5];
		Vector<Object> contenido = modeloTabla.getDataVector();

		for (int i = 0; i < modeloTabla.getRowCount(); i++) {

			Vector<Object> fila = (Vector<Object>) contenido.get(i);

			String fecha1 = (String) fila.elementAt(1);
			java.sql.Date fechaei = convertirStringFecha(fecha1);

			String fecha2 = (String) fila.elementAt(2);
			java.sql.Date fechafi = convertirStringFecha(fecha2);

			String pre = (String) fila.elementAt(3);
			Double precio = Double.parseDouble(pre);

			this.plazos[i] = new Plazo(fechaei, fechafi, precio);

		}
	}

	private boolean compruebaFechas() {
		creaPlazos();		
		for (int i = 0; i < this.numeroPlazos; i++) {			
			for (int j = 0; j < this.numeroPlazos; j++) {
				if (i != j) {
					System.out.println("i: " + i + " j: " + j);
					if (plazoEntrePlazo(plazos[i], plazos[j]))
						return false;
				}
			}
		}
		return true;
	}

	/*Si la fecha que vas a comparar es anterior al argumento date,
	 *  el método devolverá un valor menor a cero. Si la fecha que vas
	 *   a comparar es posterior al argumento date, el método devolverá
	 *    un valor mayor a cero. Si las fechas son iguales, el método 
	 *    devolverá un cero
	 *    	 */
	
	private boolean plazoEntrePlazo(Plazo plazo1, Plazo plazo2) {
		if(plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())==0 && 
				plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaFinalizaInscripcion())==0) 
				{
			System.out.println("Son iguales");
			return true;
		}
		if(plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())==0){
			System.out.println("Empieza el dia que aun sigue otro");
			return true;
		}
		

		if (plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())<0
				&& plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())>0){	
			System.out.println("Se solapa la fecha empieza");
			return true;
		}
		if (plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaFinalizaInscripcion())<0
				&& plazo1.getFechaFinalizaInscripcion().after(plazo2.getFechaFinalizaInscripcion())){
			System.out.println("Se solapa la fecha finaliza");
			return true;
		}
		return false;
	}

	private boolean stringNoVacio(String cadena) {
		return cadena.isEmpty() ? false : true;
	}

	private boolean esNumericoYNoVacio(String cadena) {
		if (!cadena.isEmpty()) {
			for (int i = 0; i < cadena.length(); ++i) {
				char caracter = cadena.charAt(i);
				if (Character.isDigit(caracter)) {
					return true;
				}
			}
		}
		return false;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnAtras.setBounds(785, 583, 96, 23);
		}
		return btnAtras;
	}

	private JLabel getLblNombreCompeticion() {
		if (lblNombreCompeticion == null) {
			lblNombreCompeticion = new JLabel("Nombre Competicion:");
			lblNombreCompeticion.setBounds(10, 29, 133, 14);
			lblNombreCompeticion.setDisplayedMnemonic('n');
			lblNombreCompeticion.setLabelFor(getTextNombreCarrera());
		}
		return lblNombreCompeticion;
	}

	private JTextField getTextNombreCarrera() {
		if (textNombreCarrera == null) {
			textNombreCarrera = new JTextField();
			textNombreCarrera.setBounds(153, 26, 194, 20);
			textNombreCarrera.setColumns(10);
		}
		return textNombreCarrera;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(357, 29, 67, 14);
			lblTipo.setLabelFor(getTextTipo());
			lblTipo.setDisplayedMnemonic('t');
		}
		return lblTipo;
	}

	private JTextField getTextTipo() {
		if (textTipo == null) {
			textTipo = new JTextField();
			textTipo.setBounds(434, 26, 124, 20);
			textTipo.setColumns(10);
		}
		return textTipo;
	}

	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia(en km):");
			lblDistancia.setBounds(616, 27, 124, 19);
			lblDistancia.setLabelFor(getTextDistancia());
			lblDistancia.setDisplayedMnemonic('d');
		}
		return lblDistancia;
	}

	private JTextField getTextDistancia() {
		if (textDistancia == null) {
			textDistancia = new JTextField();
			textDistancia.setBounds(715, 26, 115, 20);
			textDistancia.setColumns(10);
		}
		return textDistancia;
	}

	private JDateChooser getDateCompeticion() {
		if (fechaCompeticion == null) {
			fechaCompeticion = new JDateChooser();
			fechaCompeticion.setBounds(153, 86, 124, 20);
			fechaCompeticion.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			fechaCompeticion.setDateFormatString("dd/MM/yyyy");
			fechaCompeticion.setRequestFocusEnabled(false);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {

				fechaCompeticion.setDate(sf.parse("01-12-2017"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return fechaCompeticion;
	}

	private JLabel getLblFechaCompeticion() {
		if (lblFechaCompeticion == null) {
			lblFechaCompeticion = new JLabel("Fecha Competicion:");
			lblFechaCompeticion.setDisplayedMnemonic('f');
			lblFechaCompeticion.setLabelFor(getDateCompeticion());
			lblFechaCompeticion.setBounds(10, 86, 133, 20);
		}
		return lblFechaCompeticion;
	}

	private JLabel getLblNumeroDePlazas() {
		if (lblNumeroDePlazas == null) {
			lblNumeroDePlazas = new JLabel("Numero de plazas:");
			lblNumeroDePlazas.setLabelFor(getTextPlazas());
			lblNumeroDePlazas.setDisplayedMnemonic('n');
			lblNumeroDePlazas.setBounds(309, 86, 115, 20);
		}
		return lblNumeroDePlazas;
	}

	private JTextField getTextPlazas() {
		if (textPlazas == null) {
			textPlazas = new JTextField();
			textPlazas.setBounds(434, 86, 124, 20);
			textPlazas.setColumns(10);
		}
		return textPlazas;
	}

	private JPanel getPanelPlazos() {
		if (panelPlazos == null) {
			panelPlazos = new JPanel();
			panelPlazos.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelPlazos.setBounds(10, 387, 454, 184);
			panelPlazos.setLayout(null);
			panelPlazos.add(getLblFechaComienzoInscripcion());
			panelPlazos.add(getDateComienzoInscripcion());
			panelPlazos.add(getDateFinalInscripcion());
			panelPlazos.add(getLblFechaFinalInscripcion());
			panelPlazos.add(getLblPrecio());
			panelPlazos.add(getTextPrecio());
			panelPlazos.add(getBtnAñadirPlazoInscripcion());
		}
		return panelPlazos;
	}

	private JLabel getLblFechaComienzoInscripcion() {
		if (lblFechaComienzoInscripcion == null) {
			lblFechaComienzoInscripcion = new JLabel("Fecha comienzo inscripcion:");
			lblFechaComienzoInscripcion.setLabelFor(getDateComienzoInscripcion());
			lblFechaComienzoInscripcion.setDisplayedMnemonic('e');
			lblFechaComienzoInscripcion.setBounds(10, 11, 199, 28);
		}
		return lblFechaComienzoInscripcion;
	}

	private JDateChooser getDateComienzoInscripcion() {
		if (dateComienzoInscripcion == null) {
			dateComienzoInscripcion = new JDateChooser();
			fechaCompeticion.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			dateComienzoInscripcion.setRequestFocusEnabled(false);
			dateComienzoInscripcion.setDateFormatString("dd/MM/yyyy");
			dateComienzoInscripcion.setBounds(294, 11, 128, 20);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {

				dateComienzoInscripcion.setDate(sf.parse("09-11-2017"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateComienzoInscripcion;
	}

	private JDateChooser getDateFinalInscripcion() {
		if (dateFinalInscripcion == null) {
			dateFinalInscripcion = new JDateChooser();
			dateFinalInscripcion.setBounds(294, 52, 128, 20);
			dateFinalInscripcion.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			dateFinalInscripcion.setDateFormatString("dd/MM/yyyy");
			dateFinalInscripcion.setRequestFocusEnabled(false);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				dateFinalInscripcion.setDate(sf.parse("30-11-2017"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateFinalInscripcion;
	}

	private JLabel getLblFechaFinalInscripcion() {
		if (lblFechaFinalInscripcion == null) {
			lblFechaFinalInscripcion = new JLabel("Fecha final inscripcion:");
			lblFechaFinalInscripcion.setDisplayedMnemonic('h');
			lblFechaFinalInscripcion.setLabelFor(getDateFinalInscripcion());
			lblFechaFinalInscripcion.setBounds(10, 50, 260, 28);
		}
		return lblFechaFinalInscripcion;
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setLabelFor(getTextPrecio());
			lblPrecio.setDisplayedMnemonic('r');
			lblPrecio.setBounds(10, 89, 66, 28);
		}
		return lblPrecio;
	}

	private JTextField getTextPrecio() {
		if (textPrecio == null) {
			textPrecio = new JTextField();
			textPrecio.setBounds(294, 93, 128, 20);
			textPrecio.setColumns(10);
		}
		return textPrecio;
	}

	private JLabel getLblLugar() {
		if (lblLugar == null) {
			lblLugar = new JLabel("Lugar:");
			lblLugar.setBounds(616, 88, 96, 17);
		}
		return lblLugar;
	}

	private JTextField getTextLugar() {
		if (textLugar == null) {
			textLugar = new JTextField();
			textLugar.setBounds(715, 86, 124, 20);
			textLugar.setColumns(10);
		}
		return textLugar;
	}

	private JButton getBtnAñadirPlazoInscripcion() {
		if (btnAñadirPlazoInscripcion == null) {
			btnAñadirPlazoInscripcion = new JButton("A\u00F1adir Plazo Inscripcion");
			btnAñadirPlazoInscripcion.setMnemonic('a');
			btnAñadirPlazoInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					añadePlazoTabla();
					if (tablePlazos.getRowCount() == numeroMaxPlazos) {
						btnAñadirPlazoInscripcion.setEnabled(false);
						JOptionPane.showMessageDialog(null,
								"Ha llegado al limite de plazos de inscripcion, para añadir mas debe borrar alguno");
					}
				}
			});
			btnAñadirPlazoInscripcion.setBounds(10, 124, 428, 52);
		}
		return btnAñadirPlazoInscripcion;
	}

	private JTable getTablePlazos() {
		if (tablePlazos == null) {
			tablePlazos = new JTable();
			String[] nombreColumnas = { "Plazo", "Fecha Apertura", "Fecha Finalizacion", "Precio" };
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tablePlazos.setModel(modeloTabla);
			tablePlazos.setRowHeight(30);
			tablePlazos.setBorder(new LineBorder(new Color(0, 0, 0)));
			tablePlazos.setBounds(10, 177, 454, 150);
		}
		return tablePlazos;
	}

	@SuppressWarnings("deprecation")
	public void añadirFilas() {
		Object[] nuevaFila = new Object[4];
		nuevaFila[0] = "Plazo " + (tablePlazos.getRowCount() + 1);

		java.sql.Date fechaCI = new java.sql.Date(getDateComienzoInscripcion().getDate().getTime());
		nuevaFila[1] = fechaCI.getDate() + "/" + fechaCI.getMonth() + "/" + (fechaCI.getYear() + 1900);

		java.sql.Date fechaFI = new java.sql.Date(getDateFinalInscripcion().getDate().getTime());
		nuevaFila[2] = fechaFI.getDate() + "/" + fechaFI.getMonth() + "/" + (fechaFI.getYear() + 1900);
		nuevaFila[3] = getTextPrecio().getText();
		modeloTabla.addRow(nuevaFila);
	}

	private void añadePlazoTabla() {
		if (getDateComienzoInscripcion().getDate().before(getDateFinalInscripcion().getDate()) && getDateComienzoInscripcion().getDate().after(new Date()) &&
				getDateComienzoInscripcion().getDate().compareTo(getDateFinalInscripcion().getDate())!=0 &&
				getDateFinalInscripcion().getDate().before(getDateCompeticion().getDate())){
						if (esNumericoYNoVacio(getTextPrecio().getText()) && Double.parseDouble(getTextPrecio().getText()) > 0){
							añadirFilas();
							this.numeroPlazos++;
						}
						else
							JOptionPane.showMessageDialog(this,
									"El precio del plazo es incorrecto, revise que no se encuentre vacio o haya introducido un numero o este sea negativo");			
		}			
		else
			JOptionPane.showMessageDialog(this, "Las fechas del plazo son incorrectas ya que no concuerdan, reviselas");

	}

	private java.sql.Date convertirStringFecha(String cadena) {
		SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy");
		Date parsed = null;
		try {
			parsed = format.parse(cadena);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (parsed != null) {
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
			return sql;
		}
		return null;
	}

	private void borrarPlazoTabla() {
		if (tablePlazos.getSelectionModel() != null && tablePlazos.getSelectedRow() != -1) {
			modeloTabla.removeRow(tablePlazos.getSelectedRow());
			this.numeroPlazos--;
		}
	}

	private JButton getBtnBorrarPlazoInscripcion() {
		if (btnBorrarPlazoInscripcion == null) {
			btnBorrarPlazoInscripcion = new JButton("Borrar Plazo Inscripcion");
			btnBorrarPlazoInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					borrarPlazoTabla();
					if (!btnAñadirPlazoInscripcion.isEnabled() && tablePlazos.getRowCount() < numeroMaxPlazos)
						btnAñadirPlazoInscripcion.setEnabled(true);
				}
			});
			btnBorrarPlazoInscripcion.setBounds(10, 338, 454, 38);
		}
		return btnBorrarPlazoInscripcion;
	}

	private JLabel getLblPlazos() {
		if (lblPlazos == null) {
			lblPlazos = new JLabel("PLAZOS");
			lblPlazos.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPlazos.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlazos.setBounds(10, 137, 454, 29);
		}
		return lblPlazos;
	}
	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("CATEGORIAS");
			lblCategorias.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCategorias.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategorias.setBounds(583, 137, 331, 23);
		}
		return lblCategorias;
	}
	private JTable getTableCategorias() {
		if (tableCategorias == null) {
			tableCategorias = new JTable();
			tableCategorias.setBorder(new LineBorder(new Color(0, 0, 0)));
			tableCategorias.setBounds(531, 177, 454, 150);
		}
		return tableCategorias;
	}
	private JButton getBtnBorrarCategoria() {
		if (btnBorrarCategoria == null) {
			btnBorrarCategoria = new JButton("Borrar Categoria");
			btnBorrarCategoria.setBounds(531, 338, 466, 38);
		}
		return btnBorrarCategoria;
	}
	private JPanel getPanelCategorias() {
		if (panelCategorias == null) {
			panelCategorias = new JPanel();
			panelCategorias.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelCategorias.setBounds(531, 387, 466, 184);
			panelCategorias.setLayout(null);
			panelCategorias.add(getLblNombreCategoria());
			panelCategorias.add(getLblLimiteInferior());
			panelCategorias.add(getLblLimiteSuperior());
			panelCategorias.add(getBtnAadirCategoria());
			panelCategorias.add(getTextNombreCategoria());
			panelCategorias.add(getTextLimiteInferior());
			panelCategorias.add(getTextLimiteSuperior());
		}
		return panelCategorias;
	}
	private JLabel getLblNombreCategoria() {
		if (lblNombreCategoria == null) {
			lblNombreCategoria = new JLabel("Nombre de la categoria:");
			lblNombreCategoria.setBounds(21, 11, 143, 31);
		}
		return lblNombreCategoria;
	}
	private JLabel getLblLimiteInferior() {
		if (lblLimiteInferior == null) {
			lblLimiteInferior = new JLabel("Limite de edad inferior :");
			lblLimiteInferior.setBounds(21, 54, 143, 31);
		}
		return lblLimiteInferior;
	}
	private JLabel getLblLimiteSuperior() {
		if (lblLimiteSuperior == null) {
			lblLimiteSuperior = new JLabel("Limite de edad superior:");
			lblLimiteSuperior.setBounds(21, 83, 143, 25);
		}
		return lblLimiteSuperior;
	}
	private JButton getBtnAadirCategoria() {
		if (btnAadirCategoria == null) {
			btnAadirCategoria = new JButton("A\u00F1adir Categoria");
			btnAadirCategoria.setMnemonic('c');
			btnAadirCategoria.setBounds(10, 121, 435, 52);
		}
		return btnAadirCategoria;
	}
	private JTextField getTextNombreCategoria() {
		if (textNombreCategoria == null) {
			textNombreCategoria = new JTextField();
			textNombreCategoria.setBounds(229, 16, 216, 20);
			textNombreCategoria.setColumns(10);
		}
		return textNombreCategoria;
	}
	private JTextField getTextLimiteInferior() {
		if (textLimiteInferior == null) {
			textLimiteInferior = new JTextField();
			textLimiteInferior.setBounds(229, 59, 216, 20);
			textLimiteInferior.setColumns(10);
		}
		return textLimiteInferior;
	}
	private JTextField getTextLimiteSuperior() {
		if (textLimiteSuperior == null) {
			textLimiteSuperior = new JTextField();
			textLimiteSuperior.setBounds(229, 84, 216, 20);
			textLimiteSuperior.setColumns(10);
		}
		return textLimiteSuperior;
	}
}
