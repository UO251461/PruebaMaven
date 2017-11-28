package igu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import logica.Categoria;
import logica.Herramientas;
import logica.ModeloNoEditable;
import logica.Plazo;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class VentanaCrearCarrera extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int numeroMaxPlazos = 5;
	private static final int MIN_CATEGORIAS = 3;

	private JPanel contentPane;
	private ModeloNoEditable modeloTabla;
//	private ModeloNoEditable modeloCategorias;
	private DefaultTableModel modeloCategoriasMasc;
	private DefaultTableModel modeloCategoriasFem;

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
	private JButton btnAnadirPlazoInscripcion;
	private JTable tablePlazos;
	private JButton btnBorrarPlazoInscripcion;
	private JLabel lblPlazosInscripcion;

	private ArrayList<Plazo> plazos = new ArrayList<Plazo>();
	private JScrollPane scrollPlazos;

	private ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	private JLabel lblCategoriasFemeninas;
	private JScrollPane scrollFemenino;
	private JTable tableFemenino;
	private JLabel lblCategoriasMasculinas;
	private JScrollPane scrollMasculino;
	private JTable tableMasculino;
	private JButton btnRestablecerCategoriasPor;
	private JButton btnComprobarInformacionDe;
	private JPanel panelInfoGeneral;
	private JTextArea txtrSeRecuerdaAl;
	
	private boolean eventoProperty = true;
	private JPanel panelTiemposControl;
	private JLabel lblTiempoFinal;
	private JTextField textTiempoFinal;
	private JLabel lblTiempo;
	private JTextField textTiempo1;
	private JLabel lblTiempo_1;
	private JTextField textTiempo2;
	private JLabel lblTiempo3;
	private JTextField textTiempo3;
	private JLabel lblTiempo4;
	private JTextField textTiempo4;
	private JLabel lblTiempo5;
	private JTextField textTiempo5;
	
	private ArrayList<Integer> tControl = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaCrearCarrera(VentanaPrincipal vp) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		this.vp = vp;
		setTitle("Crear Carrera");
		setBounds(100, 100, 1144, 689);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAtras());
		contentPane.add(getBtnCrear());
		contentPane.add(getPanelPlazos());
		contentPane.add(getBtnBorrarPlazoInscripcion());
		contentPane.add(getLblPlazosInscripcion());
		contentPane.add(getScrollPlazos());
		contentPane.add(getLblCategoriasFemeninas());
		contentPane.add(getScrollFemenino());
		contentPane.add(getLblCategoriasMasculinas());
		contentPane.add(getScrollMasculino());
		contentPane.add(getBtnRestablecerCategoriasPor());
		contentPane.add(getBtnComprobarInformacionDe());
		contentPane.add(getPanelInfoGeneral());
		contentPane.add(getTxtrSeRecuerdaAl());
		contentPane.add(getPanelTiemposControl());
	}

	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (compruebaCampos()) {
						creaCarrera();
						dispose();
						vp.setVisible(true);
					}
				}

			});
			btnCrear.setBounds(994, 609, 86, 23);
		}
		return btnCrear;
	}

	private void creaCarrera() {
		vp.getBase().getBaseCarrera().crearCarrera(getTextNombreCarrera().getText(), getDateCompeticion().getDate(),
				Double.parseDouble(getTextDistancia().getText()), getTextTipo().getText(),
				Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(), plazos, categorias,tControl);
		JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
	}

	private boolean compruebaCampos() {
		if(compruebaTiemposControl())
			if (comprobarCategorias())
				if (Herramientas.stringNoVacio(getTextNombreCarrera().getText()))
					if (compruebaFechas())
						if (Herramientas.esNumericoYNoVacio(getTextDistancia().getText())
								&& Double.parseDouble(getTextDistancia().getText()) > 0)
							if (Herramientas.stringNoVacio(getTextTipo().getText()))
								if (Herramientas.esNumericoYNoVacio(getTextPlazas().getText())
										&& Integer.parseInt(getTextPlazas().getText()) > 0)
									if (Herramientas.stringNoVacio(getTextLugar().getText()))
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
			else {			
				return false;
			}
		else
			return false;
	}
	
	private boolean compruebaTiemposControl(){
		if(creaTiemposControl()){
			if(tControl.get(0) != 0){
				JOptionPane.showMessageDialog(this, "El tiempo de control de la salida es distinto a 0.");
				return false;
			}				
			else
				for(int i=0;i<tControl.size();i++){
					if(i!= tControl.size()-1){
						if(tControl.get(i)> tControl.get(i+1)){
							JOptionPane.showMessageDialog(this, "El tiempo de control con valor " + tControl.get(i) +" y el tiempo de control con valor " + tControl.get(i+1) + " no estan ordenados, reviselos.");
							return false;
						}
							
					}
					else{
						if(tControl.get(i)<tControl.get(i-1)){
							JOptionPane.showMessageDialog(this, "El tiempo de control final no es correcto, reviselo");
							return false;
						}
							
					}							
				}
		}
		else
			return false;		
		return true;
	}
	
	private boolean creaTiemposControl(){
		this.tControl.clear();
		tControl.add(0);		
		if(Herramientas.esNumericoYNoVacio(getTextTiempo1().getText()))
			tControl.add(Integer.parseInt(getTextTiempo1().getText()));		
		if(Herramientas.esNumericoYNoVacio(getTextTiempo2().getText()))
			tControl.add(Integer.parseInt(getTextTiempo2().getText()));	
		if(Herramientas.esNumericoYNoVacio(getTextTiempo3().getText()))
			tControl.add(Integer.parseInt(getTextTiempo3().getText()));	
		if(Herramientas.esNumericoYNoVacio(getTextTiempo4().getText()))
			tControl.add(Integer.parseInt(getTextTiempo4().getText()));	
		if(Herramientas.esNumericoYNoVacio(getTextTiempo5().getText()))
			tControl.add(Integer.parseInt(getTextTiempo5().getText()));	
		if(Herramientas.esNumericoYNoVacio(getTextTiempoFinal().getText())){
			tControl.add(Integer.parseInt(getTextTiempoFinal().getText()));			
			return true;	
		}
		else{
			JOptionPane.showMessageDialog(this, "El tiempo de control final ha de ser un numero, por favor reviselo");
			return false;
		}
					
	}

	@SuppressWarnings("unchecked")
	private void creaPlazos() {
		this.plazos.clear();
		;
		Vector<Object> contenido = modeloTabla.getDataVector();

		for (int i = 0; i < modeloTabla.getRowCount(); i++) {

			Vector<Object> fila = (Vector<Object>) contenido.get(i);

			String fecha1 = (String) fila.elementAt(1);
			String[] x = fecha1.split("/");
			int mes1 = Integer.parseInt(x[1]);
			String fechaFinal1 = x[0]+"/"+mes1+"/"+x[2];			
			java.sql.Date fechaei = Herramientas.convertirStringFecha(fechaFinal1);

			String fecha2 = (String) fila.elementAt(2);
			String[] y = fecha2.split("/");
			int mes2 = Integer.parseInt(y[1]);
			String fechaFinal2 = y[0]+"/"+mes2+"/"+y[2];
			java.sql.Date fechafi = Herramientas.convertirStringFecha(fechaFinal2);

			String pre = (String) fila.elementAt(3);
			Double precio = Double.parseDouble(pre);

			this.plazos.add(new Plazo(fechaei, fechafi, precio));

		}
	}



	private boolean compruebaFechas() {
		creaPlazos();
		if (this.plazos.size() == 0)
			return false;
		for (int i = 0; i < this.plazos.size(); i++) {
			for (int j = 0; j < this.plazos.size(); j++) {
				if (i != j) {
					if (plazoEntrePlazo(plazos.get(i), plazos.get(j)))
						return false;
				}
			}
		}
		return true;
	}

	/*
	 * Si la fecha que vas a comparar es anterior al argumento date, el m�todo
	 * devolver� un valor menor a cero. Si la fecha que vas a comparar es
	 * posterior al argumento date, el m�todo devolver� un valor mayor a cero.
	 * Si las fechas son iguales, el m�todo devolver� un cero
	 */

	private boolean plazoEntrePlazo(Plazo plazo1, Plazo plazo2) {
		if (plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion()) == 0
				&& plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaFinalizaInscripcion()) == 0) {
			return true;
		}
		if (plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion()) == 0) {
			return true;
		}

		if (plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion()) < 0
				&& plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion()) > 0) {

			return true;
		}
		if (plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaFinalizaInscripcion()) < 0
				&& plazo1.getFechaFinalizaInscripcion().after(plazo2.getFechaFinalizaInscripcion())) {

			return true;
		}
		return false;
	}

	

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					eventoProperty = false;
					dispose();
				}
			});
			btnAtras.setBounds(872, 609, 96, 23);
		}
		return btnAtras;
	}

	private JLabel getLblNombreCompeticion() {
		if (lblNombreCompeticion == null) {
			lblNombreCompeticion = new JLabel("Nombre Competicion:");
			lblNombreCompeticion.setBounds(10, 26, 188, 14);
			lblNombreCompeticion.setDisplayedMnemonic('n');
			lblNombreCompeticion.setLabelFor(getTextNombreCarrera());
		}
		return lblNombreCompeticion;
	}

	private JTextField getTextNombreCarrera() {
		if (textNombreCarrera == null) {
			textNombreCarrera = new JTextField();
			textNombreCarrera.setBounds(221, 23, 194, 20);
			textNombreCarrera.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					if (textNombreCarrera.getText().length() > 20) {
						JOptionPane.showMessageDialog(null, "El texto del nombre de la carrera es demasiado largo");
						textNombreCarrera.setText("");
					}
				}
			});
			textNombreCarrera.setColumns(10);
		}
		return textNombreCarrera;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(10, 110, 67, 14);
			lblTipo.setLabelFor(getTextTipo());
			lblTipo.setDisplayedMnemonic('t');
		}
		return lblTipo;
	}

	private JTextField getTextTipo() {
		if (textTipo == null) {
			textTipo = new JTextField();
			textTipo.setBounds(74, 107, 124, 20);
			textTipo.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if (textTipo.getText().length() > 20) {
						JOptionPane.showMessageDialog(null, "El texto del tipo de la carrera es demasiado largo");
						textTipo.setText("");
					}
				}
			});
			textTipo.setColumns(10);
		}
		return textTipo;
	}

	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia(en km):");
			lblDistancia.setBounds(10, 142, 124, 19);
			lblDistancia.setLabelFor(getTextDistancia());
			lblDistancia.setDisplayedMnemonic('d');
		}
		return lblDistancia;
	}

	private JTextField getTextDistancia() {
		if (textDistancia == null) {
			textDistancia = new JTextField();
			textDistancia.setBounds(128, 141, 115, 20);
			textDistancia.setColumns(10);
		}
		return textDistancia;
	}

	private JDateChooser getDateCompeticion() {
		if (fechaCompeticion == null) {
			fechaCompeticion = new JDateChooser();
			fechaCompeticion.setBounds(302, 80, 124, 20);			
			fechaCompeticion.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if(eventoProperty){
						if (fechaCompeticion.getCalendar().getTime().before(new Date())) {

							JOptionPane.showMessageDialog(null,
									"La fecha de competicion es anterior a la fecha actual, REVISELA");
							fechaCompeticion.requestFocusInWindow();
						}
					}
					
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
			lblFechaCompeticion.setBounds(186, 80, 133, 20);
			lblFechaCompeticion.setDisplayedMnemonic('f');
			lblFechaCompeticion.setLabelFor(getDateCompeticion());
		}
		return lblFechaCompeticion;
	}

	private JLabel getLblNumeroDePlazas() {
		if (lblNumeroDePlazas == null) {
			lblNumeroDePlazas = new JLabel("Numero de plazas:");
			lblNumeroDePlazas.setBounds(10, 51, 124, 20);
			lblNumeroDePlazas.setLabelFor(getTextPlazas());
			lblNumeroDePlazas.setDisplayedMnemonic('n');
		}
		return lblNumeroDePlazas;
	}

	private JTextField getTextPlazas() {
		if (textPlazas == null) {
			textPlazas = new JTextField();
			textPlazas.setBounds(151, 49, 124, 20);
			textPlazas.setColumns(10);
		}
		return textPlazas;
	}

	private JPanel getPanelPlazos() {
		if (panelPlazos == null) {
			panelPlazos = new JPanel();
			panelPlazos.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelPlazos.setBounds(10, 448, 454, 184);
			panelPlazos.setLayout(null);
			panelPlazos.add(getLblFechaComienzoInscripcion());
			panelPlazos.add(getDateComienzoInscripcion());
			panelPlazos.add(getDateFinalInscripcion());
			panelPlazos.add(getLblFechaFinalInscripcion());
			panelPlazos.add(getLblPrecio());
			panelPlazos.add(getTextPrecio());
			panelPlazos.add(getBtnAnadirPlazoInscripcion());
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
			dateComienzoInscripcion.getCalendarButton().addActionListener(new ActionListener() {
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
			lblLugar.setBounds(10, 82, 96, 17);
		}
		return lblLugar;
	}

	private JTextField getTextLugar() {
		if (textLugar == null) {
			textLugar = new JTextField();
			textLugar.setBounds(52, 80, 124, 20);
			textLugar.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if (textLugar.getText().length() > 20) {
						JOptionPane.showMessageDialog(null, "El texto del lugar de la carrera es demasiado largo");
						textLugar.setText("");
					}
				}
			});
			textLugar.setColumns(10);
		}
		return textLugar;
	}

	private JButton getBtnAnadirPlazoInscripcion() {
		if (btnAnadirPlazoInscripcion == null) {
			btnAnadirPlazoInscripcion = new JButton("A\u00F1adir Plazo Inscripcion");
			btnAnadirPlazoInscripcion.setMnemonic('a');
			btnAnadirPlazoInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					anadePlazoTabla();
					if (tablePlazos.getRowCount() == numeroMaxPlazos) {
						btnAnadirPlazoInscripcion.setEnabled(false);
						JOptionPane.showMessageDialog(null,
								"Ha llegado al limite de plazos de inscripcion, para a�adir mas debe borrar alguno");
					}
				}
			});
			btnAnadirPlazoInscripcion.setBounds(10, 124, 428, 52);
		}
		return btnAnadirPlazoInscripcion;
	}

	private JTable getTablePlazos() {
		if (tablePlazos == null) {
			tablePlazos = new JTable();
			String[] nombreColumnas = { "Plazo", "Fecha Apertura", "Fecha Finalizacion", "Precio" };
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tablePlazos.setModel(modeloTabla);
			tablePlazos.setRowHeight(30);
			tablePlazos.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return tablePlazos;
	}

	@SuppressWarnings("deprecation")
	public void anadirFilas() {
		Object[] nuevaFila = new Object[4];
		nuevaFila[0] = "Plazo " + (tablePlazos.getRowCount() + 1);

		java.sql.Date fechaCI = new java.sql.Date(getDateComienzoInscripcion().getDate().getTime());
		nuevaFila[1] = fechaCI.getDate() + "/" + (fechaCI.getMonth()+1) + "/" + (fechaCI.getYear() + 1900);

		java.sql.Date fechaFI = new java.sql.Date(getDateFinalInscripcion().getDate().getTime());
		nuevaFila[2] = fechaFI.getDate() + "/" + (fechaFI.getMonth()+1) + "/" + (fechaFI.getYear() + 1900);
		nuevaFila[3] = getTextPrecio().getText();
		modeloTabla.addRow(nuevaFila);
	}

	private void anadePlazoTabla() {
		if (getDateComienzoInscripcion().getDate().before(getDateFinalInscripcion().getDate())
				&& getDateComienzoInscripcion().getDate().after(new Date())
				&& getDateComienzoInscripcion().getDate().compareTo(getDateFinalInscripcion().getDate()) != 0
				&& getDateFinalInscripcion().getDate().before(getDateCompeticion().getDate())) {
			if (Herramientas.esNumericoYNoVacio(getTextPrecio().getText()) && Double.parseDouble(getTextPrecio().getText()) >= 0) {
				anadirFilas();
			} else
				JOptionPane.showMessageDialog(this,
						"El precio del plazo es incorrecto, revise que no se encuentre vacio o haya introducido un numero o este sea negativo");
		} else
			JOptionPane.showMessageDialog(this, "Las fechas del plazo son incorrectas ya que no concuerdan, reviselas");

	}



	private void borrarPlazoTabla() {
		if (tablePlazos.getSelectionModel() != null && tablePlazos.getSelectedRow() != -1) {
			modeloTabla.removeRow(tablePlazos.getSelectedRow());
		}
	}

	private JButton getBtnBorrarPlazoInscripcion() {
		if (btnBorrarPlazoInscripcion == null) {
			btnBorrarPlazoInscripcion = new JButton("Borrar Plazo Inscripcion");
			btnBorrarPlazoInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					borrarPlazoTabla();
					if (!btnAnadirPlazoInscripcion.isEnabled() && tablePlazos.getRowCount() < numeroMaxPlazos)
						btnAnadirPlazoInscripcion.setEnabled(true);
				}
			});
			btnBorrarPlazoInscripcion.setBounds(10, 399, 454, 38);
		}
		return btnBorrarPlazoInscripcion;
	}

	private JLabel getLblPlazos() {
		if (lblPlazos == null) {
			lblPlazos = new JLabel("PLAZOS INSCRIPCION");
			lblPlazos.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPlazos.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlazos.setBounds(10, 188, 454, 29);
		}
		return lblPlazosInscripcion;
	}
	private JScrollPane getScrollPlazos() {
		if (scrollPlazos == null) {
			scrollPlazos = new JScrollPane();
			scrollPlazos.setBounds(10, 216, 454, 172);
			scrollPlazos.setViewportView(getTablePlazos());
		}
		return scrollPlazos;
	}
	private JLabel getLblCategoriasFemeninas() {
		if (lblCategoriasFemeninas == null) {
			lblCategoriasFemeninas = new JLabel("CATEGORIAS FEMENINAS");
			lblCategoriasFemeninas.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCategoriasFemeninas.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategoriasFemeninas.setBounds(484, 268, 596, 31);
		}
		return lblCategoriasFemeninas;
	}
	private JScrollPane getScrollFemenino() {
		if (scrollFemenino == null) {
			scrollFemenino = new JScrollPane();
			scrollFemenino.setBounds(484, 305, 596, 102);
			scrollFemenino.setViewportView(getTableFemenino());
		}
		return scrollFemenino;
	}
	private JTable getTableFemenino() {
		if (tableFemenino == null) {
			tableFemenino = new JTable();
			tableFemenino.setBorder(new LineBorder(new Color(0, 0, 0)));
			String[] nombreColumnas = { "Nombre Categoria", "Limite Inferior de Edad", "Limite Superior de Edad" };
			modeloCategoriasFem = new DefaultTableModel(nombreColumnas, 0);
			cargaModelo(modeloCategoriasFem);
			tableFemenino.setModel(modeloCategoriasFem);
		}
		return tableFemenino;
	}
	private JLabel getLblCategoriasMasculinas() {
		if (lblCategoriasMasculinas == null) {
			lblCategoriasMasculinas = new JLabel("CATEGORIAS MASCULINAS");
			lblCategoriasMasculinas.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCategoriasMasculinas.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategoriasMasculinas.setBounds(484, 399, 596, 38);
		}
		return lblCategoriasMasculinas;
	}
	private JScrollPane getScrollMasculino() {
		if (scrollMasculino == null) {
			scrollMasculino = new JScrollPane();
			scrollMasculino.setBounds(484, 438, 596, 102);
			scrollMasculino.setViewportView(getTableMasculino());
		}
		return scrollMasculino;
	}
	private JTable getTableMasculino() {
		if (tableMasculino == null) {
			tableMasculino = new JTable();
			tableMasculino.setBorder(new LineBorder(new Color(0, 0, 0)));
			String[] nombreColumnas = { "Nombre Categoria", "Limite Inferior de Edad", "Limite Superior de Edad" };
			modeloCategoriasMasc = new DefaultTableModel(nombreColumnas, 0);
			cargaModelo(modeloCategoriasMasc);
			tableMasculino.setModel(modeloCategoriasMasc);
		}
		return tableMasculino;
	}
	private JButton getBtnRestablecerCategoriasPor() {
		if (btnRestablecerCategoriasPor == null) {
			btnRestablecerCategoriasPor = new JButton("Restablecer categorias por defecto");
			btnRestablecerCategoriasPor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					restablecerTabla(modeloCategoriasFem);
					restablecerTabla(modeloCategoriasMasc);					
				}
			});
			btnRestablecerCategoriasPor.setBounds(491, 540, 596, 29);
		}
		return btnRestablecerCategoriasPor;
	}
	
	private void restablecerTabla(DefaultTableModel modelo){
		modelo.getDataVector().clear();
		cargaModelo(modelo);
		modelo.fireTableDataChanged();
	}
	
	private void cargaModelo(DefaultTableModel modelo) {
		Object[] nuevaFila = new Object[3];
		nuevaFila[0] = "Senior";
		nuevaFila[1] = "18";
		nuevaFila[2] = "35";
		modelo.addRow(nuevaFila);

		nuevaFila = new Object[3];
		nuevaFila[0] = "Veterano A";
		nuevaFila[1] = "35";
		nuevaFila[2] = "40";
		modelo.addRow(nuevaFila);

		nuevaFila = new Object[3];
		nuevaFila[0] = "Veterano B";
		nuevaFila[1] = "40";
		nuevaFila[2] = "200";
		modelo.addRow(nuevaFila);
		nuevaFila = new Object[3];
		nuevaFila[0] = "";
		nuevaFila[1] = "";
		nuevaFila[2] = "";
		modelo.addRow(nuevaFila);	
		modelo.addRow(nuevaFila);
	}	
	
	@SuppressWarnings("unchecked")
	private void crearCategorias(){
		categorias.clear();
		Vector<Object> contenidoFem = modeloCategoriasFem.getDataVector();
		Vector<Object> contenidoMasc = modeloCategoriasMasc.getDataVector();

		for (int i = 0; i < modeloCategoriasFem.getRowCount(); i++) {			

			Vector<Object> fila = (Vector<Object>) contenidoFem.get(i);
			String nombrec = (String) fila.elementAt(0);
			String linferior = (String) fila.elementAt(1);
			String lsuperior = (String) fila.elementAt(2);	
			if(!nombrec.isEmpty() && !linferior.isEmpty() && !lsuperior.isEmpty()){
				if(Herramientas.esNumericoYNoVacio(linferior) && Herramientas.esNumericoYNoVacio(lsuperior))
					categorias.add(new Categoria(Integer.parseInt(linferior),Integer.parseInt(lsuperior),nombrec,"Femenino"));
			}			
		}		
		for (int i = 0; i < modeloCategoriasMasc.getRowCount(); i++) {			

			Vector<Object> fila = (Vector<Object>) contenidoMasc.get(i);
			String nombrec = (String) fila.elementAt(0);
			String linferior = (String) fila.elementAt(1);
			String lsuperior = (String) fila.elementAt(2);	
			if(!nombrec.isEmpty() && !linferior.isEmpty() && !lsuperior.isEmpty()){
				if(Herramientas.esNumericoYNoVacio(linferior) && Herramientas.esNumericoYNoVacio(lsuperior))
					categorias.add(new Categoria(Integer.parseInt(linferior),Integer.parseInt(lsuperior),nombrec,"Masculino"));
			}			
		}
		//CATEGORIAS ORDENADAS PRIMERO HOMBRES DE MENOR A MAYOR LIMITE DE EDAD Y DESPUES MUJERES DE MENOR A MAYOR LIMITE DE EDAD
		ordenarCategorias();
	}
	
	private boolean comprobarCategorias(){
		crearCategorias();	
		if(!numeroCorrectoCategorias()){
			JOptionPane.showMessageDialog(this, "El numero minimo de categorias es superior al numero de categorias introducidas, "
					+ "le recordamos que debe introducir tanto en femenino como en masculino al menos 3 categorias");
			return false;
		}
		if(noHuecosCategorias()){
			for(Categoria c1 : categorias){
				
				if(c1.getCategoria().length()>20){
					JOptionPane.showMessageDialog(this, "El texto del nombre de la categoria: " +c1.getCategoria() + " es demasiado largo, modifiquelo");
					return false;
				}
				if(c1.getLimiteInferior() <18){
					JOptionPane.showMessageDialog(this, "El limite inferior de edad de la categoria: " +c1.getCategoria() + " permite inscribirse a menores de edad, modifiquelo");
					return false;
				}
				
				for(Categoria c2: categorias){				

					if(c1.getSexo() == c2.getSexo() && !c1.equals(c2)){
						if(!noSolapaCategorias(c1,c2)){
							JOptionPane.showMessageDialog(this, "Los limites de edad de las categorias " +c1.getCategoria() + "y" + c2.getCategoria() + " se solapan, modifiquelas");
							return false;
						}
						
					}
				}
				
			}
			return true;
		}		
		return false;
		
	}
	
	//CATEGORIAS VAN SIEMPRE DE 18 A 200 A�OS
	private boolean noHuecosCategorias(){
		
		//MASCULINO INFERIOR DEBA SER 18
		if(categorias.get(0).getLimiteInferior() != 18){
			JOptionPane.showMessageDialog(this, "La primera categoria masculina debe empezar con 18 a�os");
			return false;
		}
			for(int i=0;i<categorias.size();i++){
				
				if(i!=categorias.size()-1){
					if(categorias.get(i).getSexo() == categorias.get(i+1).getSexo()){
						//SE COMPARA DEL MISMO SEXO CON LA SIGUIENTE DEL MISMO SEXO
						if(categorias.get(i).getLimiteSuperior() != categorias.get(i+1).getLimiteInferior()){
							JOptionPane.showMessageDialog(this, "No se permite que haya huecos de edad entre categorias, por favor reviselas");
							return false;
						}					
					}
					//SE COMPARA LA ULTIMA CATEGORIA DE MASCULINO DEBA SER 200
					if(categorias.get(i).getSexo() != categorias.get(i+1).getSexo()){
						if(categorias.get(i).getLimiteSuperior() != 200){
							JOptionPane.showMessageDialog(this, "La ultima categoria masculina debe acabar con 200 a�os");
							return false;
						}
					}
				}
				if(i == categorias.size()-1 ){
					//SE COMPARA LA ULTIMA CATEGORIA DEL FEMENINO DEBA SER 200
					if(categorias.get(i).getLimiteSuperior() != 200){
						JOptionPane.showMessageDialog(this, "La ultima categoria femenina debe acabar con 200 a�os");
						return false;
					}
				}
			}
			return true;
	}
	
	private boolean numeroCorrectoCategorias(){
		int masc = 0,fem = 0;
		for(Categoria c1 : categorias){
			if(c1.getSexo() == "Masculino")
				masc++;
			else
				fem++;
		}
		if(masc < MIN_CATEGORIAS || fem < MIN_CATEGORIAS )
			return false;
		return true;
	}
	private boolean noSolapaCategorias(Categoria cat1, Categoria cat2) {
		if (cat1.getCategoria().equals(cat2.getCategoria())) {
			return false;
		}
		if (cat1.getLimiteInferior() == cat2.getLimiteInferior()) {
			return false;
		}
		if (cat1.getLimiteSuperior() == cat2.getLimiteSuperior()) {
			return false;
		}
		if (cat1.getLimiteInferior() < cat2.getLimiteInferior()
				&& cat1.getLimiteSuperior() > cat2.getLimiteInferior()) {
			return false;
		}
		if (cat1.getLimiteInferior() < cat2.getLimiteSuperior()
				&& cat1.getLimiteSuperior() > cat2.getLimiteSuperior()) {
			return false;
		}
		return true;
	}
	private JButton getBtnComprobarInformacionDe() {
		if (btnComprobarInformacionDe == null) {
			btnComprobarInformacionDe = new JButton("Comprobar  informacion de categorias introducidas");
			btnComprobarInformacionDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobarCategorias())
						JOptionPane.showMessageDialog(null, "Sus categorias estan configuradas correctamente");
				}
			});
			btnComprobarInformacionDe.setBounds(491, 569, 596, 29);
		}
		return btnComprobarInformacionDe;
	}
	private JPanel getPanelInfoGeneral() {
		if (panelInfoGeneral == null) {
			panelInfoGeneral = new JPanel();
			panelInfoGeneral.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informacion general de la carrera", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelInfoGeneral.setBounds(10, 11, 439, 172);
			panelInfoGeneral.setLayout(null);
			panelInfoGeneral.add(getLblNombreCompeticion());
			panelInfoGeneral.add(getTextNombreCarrera());
			panelInfoGeneral.add(getLblTipo());
			panelInfoGeneral.add(getTextTipo());
			panelInfoGeneral.add(getLblDistancia());
			panelInfoGeneral.add(getTextDistancia());
			panelInfoGeneral.add(getTextLugar());
			panelInfoGeneral.add(getLblLugar());
			panelInfoGeneral.add(getTextPlazas());
			panelInfoGeneral.add(getLblNumeroDePlazas());
			panelInfoGeneral.add(getDateCompeticion());
			panelInfoGeneral.add(getLblFechaCompeticion());
		}
		return panelInfoGeneral;
	}
	
	public void ordenarCategorias(){
		ArrayList<Categoria> masc = new ArrayList<Categoria>();
		ArrayList<Categoria> fem = new ArrayList<Categoria>();
		
		for(int i=0;i<categorias.size();i++){
			if(categorias.get(i).getSexo().equals("Masculino"))
				masc.add(categorias.get(i));
			else
				fem.add(categorias.get(i));			
		}
		Categoria[] masculino = new Categoria[masc.size()];
		Categoria[] femenino = new Categoria[fem.size()];
		masc.toArray(masculino);
		fem.toArray(femenino);
		Arrays.sort(masculino);
		Arrays.sort(femenino);
		categorias.clear();
		for(int i=0;i<masculino.length;i++){
			categorias.add(masculino[i]);		
		}
		for(int i=0;i<femenino.length;i++){
			categorias.add(femenino[i]);		
		}			
	}
	private JTextArea getTxtrSeRecuerdaAl() {
		if (txtrSeRecuerdaAl == null) {
			txtrSeRecuerdaAl = new JTextArea();
			txtrSeRecuerdaAl.setEditable(false);
			txtrSeRecuerdaAl.setWrapStyleWord(true);
			txtrSeRecuerdaAl.setLineWrap(true);
			txtrSeRecuerdaAl.setText("Se recuerda al usuario que las normas para introducir categorias son: \n-La primera categoria de masculino y femenino debe comenzar con 18 a�os.\n"
			+ "-La ultima categoria tanto de masculino como de femenino debe acabar en 200 anos\n-No se permitira que haya huecos de edades entre categorias para la correcta inscripcion "
					+ "de cualquier usuario de cualquier edad");
			txtrSeRecuerdaAl.setBounds(484, 158, 596, 112);
		}
		return txtrSeRecuerdaAl;
	}
	private JPanel getPanelTiemposControl() {
		if (panelTiemposControl == null) {
			panelTiemposControl = new JPanel();
			panelTiemposControl.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tiempos de control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTiemposControl.setBounds(484, 15, 596, 132);
			panelTiemposControl.setLayout(null);
			panelTiemposControl.add(getLblTiempoFinal());
			panelTiemposControl.add(getTextTiempoFinal());
			panelTiemposControl.add(getLblTiempo());
			panelTiemposControl.add(getTextTiempo1());
			panelTiemposControl.add(getLblTiempo_1());
			panelTiemposControl.add(getTextTiempo2());
			panelTiemposControl.add(getLblTiempo3());
			panelTiemposControl.add(getTextTiempo3());
			panelTiemposControl.add(getLblTiempo4());
			panelTiemposControl.add(getLblTiempo5());
			panelTiemposControl.add(getTextTiempo5());
			panelTiemposControl.add(getTextTiempo4());
		}
		return panelTiemposControl;
	}
	private JLabel getLblTiempoFinal() {
		if (lblTiempoFinal == null) {
			lblTiempoFinal = new JLabel("Tiempo Final:");
			lblTiempoFinal.setBounds(10, 22, 122, 28);
		}
		return lblTiempoFinal;
	}
	private JTextField getTextTiempoFinal() {
		if (textTiempoFinal == null) {
			textTiempoFinal = new JTextField();
			textTiempoFinal.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					if(!Herramientas.esNumericoYNoVacio(textTiempoFinal.getText()) && !textTiempoFinal.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Compruebe que ha introducido un numero en este campo");
				}
			});
			textTiempoFinal.setBounds(102, 26, 130, 24);
			textTiempoFinal.setColumns(10);
		}
		return textTiempoFinal;
	}
	private JLabel getLblTiempo() {
		if (lblTiempo == null) {
			lblTiempo = new JLabel("Tiempo 1:");
			lblTiempo.setBounds(322, 24, 77, 24);
		}
		return lblTiempo;
	}
	private JTextField getTextTiempo1() {
		if (textTiempo1 == null) {
			textTiempo1 = new JTextField();
			textTiempo1.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(!Herramientas.esNumericoYNoVacio(textTiempo1.getText()) && !textTiempo1.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Compruebe que ha introducido un numero en este campo");
				}
			});
			textTiempo1.setBounds(383, 22, 130, 24);
			textTiempo1.setColumns(10);
		}
		return textTiempo1;
	}
	private JLabel getLblTiempo_1() {
		if (lblTiempo_1 == null) {
			lblTiempo_1 = new JLabel("Tiempo 2:");
			lblTiempo_1.setBounds(10, 59, 77, 24);
		}
		return lblTiempo_1;
	}
	private JTextField getTextTiempo2() {
		if (textTiempo2 == null) {
			textTiempo2 = new JTextField();
			textTiempo2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(!Herramientas.esNumericoYNoVacio(textTiempo2.getText()) && !textTiempo2.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Compruebe que ha introducido un numero en este campo");
				}
			});
			textTiempo2.setBounds(102, 61, 130, 20);
			textTiempo2.setColumns(10);
		}
		return textTiempo2;
	}
	private JLabel getLblTiempo3() {
		if (lblTiempo3 == null) {
			lblTiempo3 = new JLabel("Tiempo 3:");
			lblTiempo3.setBounds(322, 59, 77, 28);
		}
		return lblTiempo3;
	}
	private JTextField getTextTiempo3() {
		if (textTiempo3 == null) {
			textTiempo3 = new JTextField();
			textTiempo3.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(!Herramientas.esNumericoYNoVacio(textTiempo3.getText()) && !textTiempo3.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Compruebe que ha introducido un numero en este campo");
				}
			});
			textTiempo3.setColumns(10);
			textTiempo3.setBounds(383, 59, 130, 24);
		}
		return textTiempo3;
	}
	private JLabel getLblTiempo4() {
		if (lblTiempo4 == null) {
			lblTiempo4 = new JLabel("Tiempo 4:");
			lblTiempo4.setBounds(10, 94, 77, 24);
		}
		return lblTiempo4;
	}
	private JTextField getTextTiempo4() {
		if (textTiempo4 == null) {
			textTiempo4 = new JTextField();
			textTiempo4.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(!Herramientas.esNumericoYNoVacio(textTiempo4.getText()) && !textTiempo4.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Compruebe que ha introducido un numero en este campo");
				}
			});
			textTiempo4.setBounds(102, 92, 130, 24);
			textTiempo4.setColumns(10);
		}
		return textTiempo4;
	}
	private JLabel getLblTiempo5() {
		if (lblTiempo5 == null) {
			lblTiempo5 = new JLabel("Tiempo 5:");
			lblTiempo5.setBounds(322, 96, 77, 24);
		}
		return lblTiempo5;
	}
	private JTextField getTextTiempo5() {
		if (textTiempo5 == null) {
			textTiempo5 = new JTextField();
			textTiempo5.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(!Herramientas.esNumericoYNoVacio(textTiempo5.getText()) && !textTiempo5.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Compruebe que ha introducido un numero en este campo");
				}
			});
			textTiempo5.setColumns(10);
			textTiempo5.setBounds(383, 98, 130, 20);
		}
		return textTiempo5;
	}
}
