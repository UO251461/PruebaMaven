package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import logica.Carrera;
import logica.Inscripcion;
import logica.InscripcionesClub;
import oracle.sql.DATE;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Ventana_Inscripcion_Club extends JFrame {

	private JPanel contentPane;
	private JPanel panelBotones;
	private JPanel panelPrincipal;
	private JButton btnCancelar;
	private JButton btnInscribir;
	private JLabel lblAadirArchivoDe;
	private JButton button;
	private JLabel lblTotalProcesados;
	private JTextField txtProcesados;
	private JLabel lblProcesadosCorrectamente;
	private JTextField txtProcesadosOk;
	private JLabel lblProcesadosIncorrectamente;
	private JTextField txtProcesadosKo;
	private JFileChooser jfInscripcion = null;
	private InscripcionesClub inscripcionClub = null;
	private JButton btnContinuar;
	private ArrayList<Inscripcion> inscripciones;
	private VentanaCarreras vc;
	private int contadorOK=0;
	private int contadorKO=0;
	private JLabel lblCarrera;
	private JTextField txtCarrera;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private InscripcionesClub ins;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Ventana_Inscripcion_Club frame = new Ventana_Inscripcion_Club(new VentanaCarreras(new VentanaPrincipal()),new Carrera("", 0.0, null, null, null, "", "", null, "", ""));
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Ventana_Inscripcion_Club(VentanaCarreras vc) {
		this.vc = vc;
		this.ins = new InscripcionesClub(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
		contentPane.add(getPanelPrincipal(), BorderLayout.CENTER);
		txtCarrera.setText(vc.getBase().getBaseCarrera().getCarreraSeleccionada().getNombre());
		txtPrecio.setText(String.valueOf(vc.getBase().getBaseCarrera().getCarreraSeleccionada().getPrecio()));
	}

	public VentanaCarreras getVc() {
		return this.vc;
	}
	
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelBotones.add(getBtnInscribir());
			panelBotones.add(getBtnCancelar());
			panelBotones.add(getBtnContinuar());
		}
		return panelBotones;
	}
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(null);
			panelPrincipal.add(getLblAadirArchivoDe());
			panelPrincipal.add(getButton());
			panelPrincipal.add(getLblTotalProcesados());
			panelPrincipal.add(getTxtProcesados());
			panelPrincipal.add(getLblProcesadosCorrectamente());
			panelPrincipal.add(getTxtProcesadosOk());
			panelPrincipal.add(getLblProcesadosIncorrectamente());
			panelPrincipal.add(getTxtProcesadosKo());
			panelPrincipal.add(getLblCarrera());
			panelPrincipal.add(getTxtCarrera());
			panelPrincipal.add(getLblPrecio());
			panelPrincipal.add(getTxtPrecio());
		}
		return panelPrincipal;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
		}
		return btnCancelar;
	}
	
	private JFileChooser getJFInscripcion() {
		if(jfInscripcion == null) {
			jfInscripcion = new JFileChooser();
			jfInscripcion.setMultiSelectionEnabled(false);
			jfInscripcion.setFileFilter(new FileNameExtensionFilter("Archivos txt", "txt"));
			
			String userDir = System.getProperty("user.home");
			jfInscripcion.setCurrentDirectory(new File(userDir+"/Desktop"));
		}
		return jfInscripcion;
	}
	
	private JButton getBtnInscribir() {
		if (btnInscribir == null) {
			btnInscribir = new JButton("Inscribir");
			btnInscribir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inscripciones = inscripcionClub.getInscripcionesClub();
					for(Inscripcion ins : inscripciones) {
						try {
						
						vc.getBase().getBaseInscripciones().registrarCorredor(DATE.getCurrentDate().stringValue(),ins);
						
						ins.asignarCategoria(ins.getFecha(), vc.getBase().getBaseCarrera().getCarreraSeleccionada());
						contadorOK++;}
						catch(SQLException se) {
						//	JOptionPane.showMessageDialog(null, se.getMessage());
							contadorKO++;
						} finally {
							btnContinuar.setEnabled(true);
						}
						}
					txtProcesados.setText(String.valueOf(inscripciones.size())); 
					txtProcesadosOk.setText(String.valueOf(contadorOK));
					txtProcesadosKo.setText(String.valueOf(contadorKO));
					
				}
			});
		}
		return btnInscribir;
	}
	private JLabel getLblAadirArchivoDe() {
		if (lblAadirArchivoDe == null) {
			lblAadirArchivoDe = new JLabel("A\u00F1adir archivo de corredores: ");
			lblAadirArchivoDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAadirArchivoDe.setBounds(22, 84, 204, 26);
		}
		return lblAadirArchivoDe;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("...");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int respuesta = getJFInscripcion().showOpenDialog(null);
					if(respuesta == getJFInscripcion().APPROVE_OPTION) {
						File textoInscripcion = jfInscripcion.getSelectedFile();
						ins.leerFichero(textoInscripcion);
						inscripciones = ins.getInscripcionesClub();
					}
					
				}
			});
			button.setBounds(236, 88, 33, 23);
		}
		return button;
	}
	private JLabel getLblTotalProcesados() {
		if (lblTotalProcesados == null) {
			lblTotalProcesados = new JLabel("Total procesados:");
			lblTotalProcesados.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblTotalProcesados.setBounds(22, 141, 147, 19);
		}
		return lblTotalProcesados;
	}
	private JTextField getTxtProcesados() {
		if (txtProcesados == null) {
			txtProcesados = new JTextField();
			txtProcesados.setText("0");
			txtProcesados.setHorizontalAlignment(SwingConstants.CENTER);
			txtProcesados.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtProcesados.setEditable(false);
			txtProcesados.setBounds(238, 141, 86, 19);
			txtProcesados.setColumns(10);
		}
		return txtProcesados;
	}
	private JLabel getLblProcesadosCorrectamente() {
		if (lblProcesadosCorrectamente == null) {
			lblProcesadosCorrectamente = new JLabel("Inscritos Correctamente:");
			lblProcesadosCorrectamente.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblProcesadosCorrectamente.setBounds(22, 182, 188, 23);
		}
		return lblProcesadosCorrectamente;
	}
	private JTextField getTxtProcesadosOk() {
		if (txtProcesadosOk == null) {
			txtProcesadosOk = new JTextField();
			txtProcesadosOk.setText("0");
			txtProcesadosOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtProcesadosOk.setHorizontalAlignment(SwingConstants.CENTER);
			txtProcesadosOk.setEditable(false);
			txtProcesadosOk.setBounds(238, 185, 86, 20);
			txtProcesadosOk.setColumns(10);
		}
		return txtProcesadosOk;
	}
	private JLabel getLblProcesadosIncorrectamente() {
		if (lblProcesadosIncorrectamente == null) {
			lblProcesadosIncorrectamente = new JLabel("Inscritos Incorrectamente: ");
			lblProcesadosIncorrectamente.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblProcesadosIncorrectamente.setBounds(22, 233, 204, 26);
		}
		return lblProcesadosIncorrectamente;
	}
	private JTextField getTxtProcesadosKo() {
		if (txtProcesadosKo == null) {
			txtProcesadosKo = new JTextField();
			txtProcesadosKo.setText("0");
			txtProcesadosKo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtProcesadosKo.setHorizontalAlignment(SwingConstants.CENTER);
			txtProcesadosKo.setEditable(false);
			txtProcesadosKo.setBounds(238, 238, 86, 20);
			txtProcesadosKo.setColumns(10);
		}
		return txtProcesadosKo;
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
			btnContinuar.setEnabled(false);
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Recuerde avisar a sus corredores de que paguen el precio de la inscripcion");
					dispose();
				}
			});
		}
		return btnContinuar;
	}
	private JLabel getLblCarrera() {
		if (lblCarrera == null) {
			lblCarrera = new JLabel("Carrera:");
			lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblCarrera.setBounds(22, 22, 66, 26);
		}
		return lblCarrera;
	}
	private JTextField getTxtCarrera() {
		if (txtCarrera == null) {
			txtCarrera = new JTextField();
			txtCarrera.setEditable(false);
			txtCarrera.setBounds(82, 22, 391, 25);
			txtCarrera.setColumns(10);
		}
		return txtCarrera;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPrecio.setBounds(505, 26, 57, 18);
		}
		return lblPrecio;
	}
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setEditable(false);
			txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecio.setBounds(551, 25, 86, 20);
			txtPrecio.setColumns(10);
		}
		return txtPrecio;
	}
}
