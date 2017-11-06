package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class VentanaCrearCarrera extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

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
	private JPanel panel;
	private JLabel lblFechaComienzoInscripcion;
	private JDateChooser dateComienzoInscripcion;
	private JDateChooser dateFinalInscripcion;
	private JLabel lblFechaFinalInscripcion;
	private JLabel lblPrecio;
	private JTextField textPrecio;
	private JLabel lblLugar;
	private JTextField textLugar;

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
		setBounds(100, 100, 1013, 399);
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
		contentPane.add(getPanel());
		contentPane.add(getDateCompeticion());
		contentPane.add(getLblLugar());
		contentPane.add(getTextLugar());
	}

	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (compruebaCampos()) {
						vp.getBase().getBaseCarrera().crearCarrera(getTextNombreCarrera().getText(),
								Double.parseDouble(getTextPrecio().getText()), getDateCompeticion().getDate(),
								getDateComienzoInscripcion().getDate(), getDateFinalInscripcion().getDate(),
								Double.parseDouble(getTextDistancia().getText()), getTextTipo().getText(),
								Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText());
						JOptionPane.showMessageDialog(null, "Su carrera ha sido creada satisfactoriamente");
						dispose();
					}
				}

			});
			btnCrear.setBounds(897, 336, 86, 23);
		}
		return btnCrear;
	}

	private boolean compruebaCampos() {
		if (stringNoVacio(getTextNombreCarrera().getText()))
			if (esNumericoYNoVacio(getTextPrecio().getText()) && Double.parseDouble(getTextPrecio().getText())>0)
				if (compruebaFechas())
					if (esNumericoYNoVacio(getTextDistancia().getText()) && Double.parseDouble(getTextDistancia().getText())>0)
						if (stringNoVacio(getTextTipo().getText()))
							if (esNumericoYNoVacio(getTextPlazas().getText())  && Integer.parseInt(getTextDistancia().getText())>0)
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
							"Las fechas no son concordantes entre ellas, la primera ha de ser la fecha de inscripcion,"
									+ " despues la fecha final de la inscripcion y por ultimo la fecha de competicion");
					return false;
				}
			else {
				JOptionPane.showMessageDialog(this, "El precio de la carrera NO ha sido introducido correctamente");
				return false;
			}
		else {
			JOptionPane.showMessageDialog(this, "El nombre de la carrera NO ha sido introducido correctamente");
			return false;
		}
	}

	private boolean compruebaFechas() {
		// new Date() es la fecha actual
		if(getDateCompeticion().getDate()!=null && getDateFinalInscripcion().getDate()!=null && getDateComienzoInscripcion().getDate()!=null)
			if (getDateCompeticion().getDate().after(getDateFinalInscripcion().getDate())
				&& getDateCompeticion().getDate().after(getDateComienzoInscripcion().getDate()))
				if (getDateFinalInscripcion().getDate().after(getDateComienzoInscripcion().getDate()))
					if (getDateComienzoInscripcion().getDate().after(new Date()))
						return true;
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
			btnAtras.setBounds(768, 336, 96, 23);
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
			lblDistancia.setBounds(625, 24, 124, 19);
			lblDistancia.setLabelFor(getTextDistancia());
			lblDistancia.setDisplayedMnemonic('d');
		}
		return lblDistancia;
	}

	private JTextField getTextDistancia() {
		if (textDistancia == null) {
			textDistancia = new JTextField();
			textDistancia.setBounds(724, 26, 115, 20);
			textDistancia.setColumns(10);
		}
		return textDistancia;
	}

	private JDateChooser getDateCompeticion() {
		if (fechaCompeticion == null) {
			fechaCompeticion = new JDateChooser();
			fechaCompeticion.setBounds(153, 57, 87, 20);
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
			lblFechaCompeticion.setBounds(10, 59, 133, 20);
		}
		return lblFechaCompeticion;
	}

	private JLabel getLblNumeroDePlazas() {
		if (lblNumeroDePlazas == null) {
			lblNumeroDePlazas = new JLabel("Numero de plazas:");
			lblNumeroDePlazas.setLabelFor(getTextPlazas());
			lblNumeroDePlazas.setDisplayedMnemonic('n');
			lblNumeroDePlazas.setBounds(309, 59, 115, 20);
		}
		return lblNumeroDePlazas;
	}

	private JTextField getTextPlazas() {
		if (textPlazas == null) {
			textPlazas = new JTextField();
			textPlazas.setBounds(434, 57, 124, 20);
			textPlazas.setColumns(10);
		}
		return textPlazas;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(23, 90, 454, 239);
			panel.setLayout(null);
			panel.add(getLblFechaComienzoInscripcion());
			panel.add(getDateComienzoInscripcion());
			panel.add(getDateFinalInscripcion());
			panel.add(getLblFechaFinalInscripcion());
			panel.add(getLblPrecio());
			panel.add(getTextPrecio());
		}
		return panel;
	}

	private JLabel getLblFechaComienzoInscripcion() {
		if (lblFechaComienzoInscripcion == null) {
			lblFechaComienzoInscripcion = new JLabel("Fecha comienzo inscripcion:");
			lblFechaComienzoInscripcion.setLabelFor(getDateComienzoInscripcion());
			lblFechaComienzoInscripcion.setDisplayedMnemonic('e');
			lblFechaComienzoInscripcion.setBounds(10, 11, 260, 28);
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
			dateComienzoInscripcion.setBounds(293, 11, 128, 20);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {

				fechaCompeticion.setDate(sf.parse("09-11-2017"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateComienzoInscripcion;
	}

	private JDateChooser getDateFinalInscripcion() {
		if (dateFinalInscripcion == null) {
			dateFinalInscripcion = new JDateChooser();
			dateFinalInscripcion.setBounds(293, 42, 128, 20);
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
			lblFechaFinalInscripcion.setBounds(10, 42, 260, 28);
		}
		return lblFechaFinalInscripcion;
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setLabelFor(getTextPrecio());
			lblPrecio.setDisplayedMnemonic('r');
			lblPrecio.setBounds(10, 81, 66, 28);
		}
		return lblPrecio;
	}

	private JTextField getTextPrecio() {
		if (textPrecio == null) {
			textPrecio = new JTextField();
			textPrecio.setBounds(86, 85, 116, 20);
			textPrecio.setColumns(10);
		}
		return textPrecio;
	}

	private JLabel getLblLugar() {
		if (lblLugar == null) {
			lblLugar = new JLabel("Lugar:");
			lblLugar.setBounds(614, 62, 96, 17);
		}
		return lblLugar;
	}

	private JTextField getTextLugar() {
		if (textLugar == null) {
			textLugar = new JTextField();
			textLugar.setBounds(720, 59, 124, 20);
			textLugar.setColumns(10);
		}
		return textLugar;
	}
}
