package igu;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


import database.Base;
import logica.Carrera;
import logica.Corredor;
import logica.Inscripcion;


import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.ComponentOrientation;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class VentanaInscripcion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblSexo;
	private JLabel lblFechaDeNacimiento;
	private JButton btnCancelar;
	private JButton btnPreinscribir;
	private JPanel panel;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser fecha;
	private Inscripcion inscripcion;
	private VentanaCarreras vc;
	
	
	//ESTO BORRAR, SOLO PRUEBA
	private JLabel lbApellidos;
	private JTextField txtApellidos;
	
	protected String sexoSelected = "HOMBRE";
	private JTextPane txtTitulo;
	
	/**
	 * Create the frame.
	 */
	public VentanaInscripcion(VentanaCarreras vc) {
		this.vc = vc;
		setTitle("Registrarse");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnPreinscribir());
		contentPane.add(getPanel());
		contentPane.add(getTxtTitulo());
		
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI :");
			lblDni.setDisplayedMnemonic('d');
			lblDni.setLabelFor(getTxtDni());
			lblDni.setBounds(40, 35, 72, 23);
		}
		return lblDni;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					Corredor corr=vc.getBase().getBaseInscripciones().estaRegistrado(txtDni.getText());
					if(corr!=null){
						txtNombre.setText(corr.getNombre());
						txtApellidos.setText(corr.getApellido());
						if(corr.getSexo()!= null && corr.getSexo().equals("HOMBRE"))
							rdbtnHombre.setSelected(true);
						else
							rdbtnMujer.setSelected(true);
						//FALTA SELECCIONAR FECHA NACIMIENTO
					}
				}
			});
			txtDni.setBounds(172, 35, 209, 23);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre :");
			lblNombre.setLabelFor(getTxtNombre());
			lblNombre.setDisplayedMnemonic('n');
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
			lblFechaDeNacimiento.setLabelFor(getDateChooser());
			lblFechaDeNacimiento.setDisplayedMnemonic('f');
			lblFechaDeNacimiento.setBounds(40, 267, 126, 23);
		}
		return lblFechaDeNacimiento;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('c');
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cancelar();
				}

				
			});
			btnCancelar.setBounds(98, 483, 137, 40);
		}
		return btnCancelar;
	}
	private JButton getBtnPreinscribir() {
		if (btnPreinscribir == null) {
			btnPreinscribir = new JButton("Pre-inscribir");
			btnPreinscribir.setMnemonic('r');
			btnPreinscribir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarCampos()){
						try{							
							registrar();							
						}catch(SQLException sql){
							errorSQL(sql);
						}
					}
					else
						error();
						
						
				}
			});
			btnPreinscribir.setBounds(330, 483, 137, 40);
		}
		return btnPreinscribir;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(Color.BLACK));
			panel.setBounds(65, 116, 436, 325);
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
			rdbtnHombre.setMnemonic('h');
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
			rdbtnMujer.setMnemonic('m');
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
			fecha.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			fecha.setDateFormatString("dd/MM/yyyy");
			fecha.setRequestFocusEnabled(false);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				fecha.setDate(sf.parse("01-01-1990"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fecha.setBounds(172, 267, 209, 23);
		}
		return fecha;
	}
	private JLabel getLbApellidos() {
		if (lbApellidos == null) {
			lbApellidos = new JLabel("Apellidos");
			lbApellidos.setDisplayedMnemonic('a');
			lbApellidos.setLabelFor(getTxtApellidos());
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
	private void continuar(){
		
		JOptionPane.showMessageDialog(this, "¡Felicidades! Está Pre-inscrito");
		
		new VentanaJustificante(this);
	}
	
	/**
	 * Procesa los errores SQL
	 * @param sql
	 */
	protected void errorSQL(SQLException sql) {
		if(sql.getErrorCode() == 1){
			JOptionPane.showMessageDialog(this,"Usted ya está registrado, por favor proceda a facturar.");
			new VentanaJustificante(this);
		}
			
		else 
			sql.printStackTrace();
		
	}

	/**
	 * Registra en la BBDD el usiario registrado, y crea un objeto inscripcion (Samuel)
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param fecha
	 * @param sexo
	 * @throws SQLException, si el usuario con dni ya está registrado lanza una excepción
	 */
	protected void registrar() throws SQLException {
		String date = fecha.getDate().getDate()+ "/" + (fecha.getDate().getMonth()+1) +"/"+(fecha.getDate().getYear()+1900); //Conversion de la fecha de nacimiento a lo guarro
		Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
		
		inscripcion = new Inscripcion(carreraSel.getIdcarrera(), carreraSel.getOrganizador().getIdorganizador() ,txtDni.getText(), carreraSel.getPrecio()); //CAMBIAR EL NOMBRE Y APELLIDO SI EL USUARIO YA ESTA REGISTRADO, LO HACE LA BBDD
		inscripcion.asignarCategoria(fecha.getDate(),carreraSel);//AQUI ES DONDE SE LE ASIGNA LA CATEGORIA A LA INSCRIPCION DEL CORREDOR
		//SE COMPRUEBA QUE NO TENGA CATEGORIA
		if(inscripcion.getCategoria().equals("Sin categoria"))
			JOptionPane.showMessageDialog(this, "Usted es menor de edad o no tiene una categoria asignada y no puede inscribirse en la carrera");
		else{
			Corredor c = inscripcion.getCorredor();
			c.setDni(txtDni.getText());
			c.setNombre(txtNombre.getText());
			c.setApellido(txtApellidos.getText());
			c.setSexo(sexoSelected);
			getBase().getBaseInscripciones().registrarCorredor(date, inscripcion);
			continuar();
		}		
	}

	/**
	 * Trata los errores de los campos vacios(Samuel)
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
		return vc.getBase();
	}

	public void cerrarVentanas(){
		new  VentanaMetodoPago(this);
		vc.dispose();
		dispose();
	}
	
	private void cancelar() {
		dispose();
		vc.setVisible(true);
	}
	private JTextPane getTxtTitulo() {
		if (txtTitulo == null) {
			txtTitulo = new JTextPane();
			txtTitulo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			txtTitulo.setRequestFocusEnabled(false);
			txtTitulo.setOpaque(false);
			txtTitulo.setFocusable(false);
			txtTitulo.setEditable(false);
			txtTitulo.setFont(new Font("Arial Black", Font.BOLD, 16));
			txtTitulo.setText(vc.getBase().getBaseCarrera().getCarreraSeleccionada().getNombre());			
			//txtTitulo.setText("CARERRA PRUEBA CON UN TITULO LARGOOOOOOOOOOOOOOOO LAAAAAARGGGGGGGGGGO");
			txtTitulo.setBounds(75, 24, 426, 81);
		}
		return txtTitulo;
	}
}
