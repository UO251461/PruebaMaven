package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JMonthChooser;

import database.Base;
import logica.Inscripcion;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;

public class VentanaInscripcion extends JFrame {

	private JPanel contentPane;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblSexo;
	private JLabel lblFechaDeNacimiento;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JPanel panel;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser fecha;
	
	private Inscripcion inscripcion;
	private VentanaCarreras vc;
	
	
	//ESTO BORRAR, SOLO PRUEBA
	private Base base;
	private JLabel lbApellidos;
	private JTextField txtApellidos;
	
	protected String sexoSelected = "HOMBRE";
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaInscripcion frame = new VentanaInscripcion();
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
	public VentanaInscripcion(VentanaCarreras vc) {
		this.vc = vc;
		setTitle("Registrarse");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 513);
		base = new Base();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnRegistrar());
		contentPane.add(getPanel());
		
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI :");
			lblDni.setBounds(40, 35, 72, 23);
		}
		return lblDni;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBounds(172, 35, 209, 23);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre :");
			lblNombre.setBounds(40, 93, 79, 23);
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(172, 93, 209, 23);
			txtNombre.setText("");
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo :");
			lblSexo.setBounds(40, 209, 86, 23);
		}
		return lblSexo;
	}
	private JLabel getLblFechaDeNacimiento() {
		if (lblFechaDeNacimiento == null) {
			lblFechaDeNacimiento = new JLabel("Fecha de nacimiento :");
			lblFechaDeNacimiento.setBounds(40, 267, 126, 23);
		}
		return lblFechaDeNacimiento;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					vc.setVisible(true);
				}
			});
			btnCancelar.setBounds(98, 404, 137, 40);
		}
		return btnCancelar;
	}
	private JButton getBtnRegistrar() {
		if (btnRegistrar == null) {
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarCampos()){
						try{							
							registrar();		
							continuar("¡Felicidades! Está registrado");
						}catch(SQLException sql){
							errorSQL(sql);
						}
					}
					else
						error();
						
						
				}
			});
			btnRegistrar.setBounds(330, 404, 137, 40);
		}
		return btnRegistrar;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(Color.BLACK));
			panel.setBounds(65, 37, 436, 325);
			panel.setLayout(null);
			panel.add(getLblDni());
			panel.add(getTxtDni());
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			panel.add(getLbApellidos());
			panel.add(getTxtApellidos());
			panel.add(getLblSexo());
			panel.add(getLblFechaDeNacimiento());
			panel.add(getRdbtnHombre());
			panel.add(getRdbtnMujer());
			panel.add(getDateChooser());
			
		}
		return panel;
	}
	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("HOMBRE");
			rdbtnHombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					sexoSelected = "HOMBRE";
				}
			});
			rdbtnHombre.setSelected(true);
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setBounds(172, 209, 103, 23);
		}
		return rdbtnHombre;
	}
	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("MUJER");
			rdbtnMujer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sexoSelected = "MUJER";
				}
			});
			buttonGroup.add(rdbtnMujer);
			rdbtnMujer.setBounds(277, 209, 104, 23);
		}
		return rdbtnMujer;
	}
	private JDateChooser getDateChooser() {
		if (fecha == null) {
			fecha = new JDateChooser();
			fecha.setDateFormatString("dd/MM/yyyy");
			fecha.setRequestFocusEnabled(false);
			fecha.setBounds(172, 267, 209, 23);
		}
		return fecha;
	}
	private JLabel getLbApellidos() {
		if (lbApellidos == null) {
			lbApellidos = new JLabel("Apellidos");
			lbApellidos.setBounds(40, 151, 79, 23);
		}
		return lbApellidos;
	}
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setText("");
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(172, 151, 209, 23);
		}
		return txtApellidos;
	}
	
	
	
	//METODOS FUNCIONALES
	
	
	/**
	 * Muestra un mejsaje pasado por parametro, crea una ventana "elegir campo" y cierra la ventana actual
	 * @param str, el texto a mostrar
	 */
	private void continuar(String str){
		JOptionPane.showMessageDialog(this, str);
		new VentanaElegirCompeticion(this);
	}
	
	/**
	 * Procesa los errores SQL
	 * @param sql
	 */
	protected void errorSQL(SQLException sql) {
		int code =sql.getErrorCode();
		if(code == -20001)			
			JOptionPane.showMessageDialog(this, sql.getMessage());
		else if(code == 1){
			continuar("Usted ya está registrado, por favor elija la competición");
		}
			
		else 
			sql.printStackTrace();
		
	}

	/**
	 * Registra en la BBDD el usiario registrado, y crea un objeto inscripcion
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param fecha
	 * @param sexo
	 * @throws SQLException, si el usuario con dni ya está registrado lanza una excepción
	 */
	protected void registrar() throws SQLException {
		String date = fecha.getDate().getDate()+ "/" + (fecha.getDate().getMonth()+1) +"/"+(fecha.getDate().getYear()+1900); 
		inscripcion = new Inscripcion(txtDni.getText()); //CAMBIAR EL NOMBRE Y APELLIDO SI EL USUARIO YA ESTA REGISTRADO, LO HACE LA BBDD
		base.getBaseInscripciones().registrarCorredor(txtDni.getText(), txtNombre.getText(),txtApellidos.getText(), date, sexoSelected, inscripcion);		
	}

	/**
	 * Trata los errores de los campos vacios
	 */
	protected void error() {		
		JOptionPane.showMessageDialog(this, "Es obligatorio rellenar todos los campos");		
	}

/**
 * Comprueba que los campos dni, nombre y fecha sean correctos
 * @return boolean
 */
	protected boolean validarCampos() {
		if(validarDni() && validarNombre() && validarFecha()){
			return true;
		}
		return false;
	}

	private boolean validarDni() {
		if(getTxtDni().getText().equals(""))			
			return false;
		return true;
	}

	private boolean validarFecha() {
		if(getDateChooser().getDate() == null)
			return false;
		return true;
	}

	private boolean validarNombre() {
		if(getTxtNombre().getText().equals(""))
			return false;
		return true;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public Base getBase() {
		return base;
	}

	
}
