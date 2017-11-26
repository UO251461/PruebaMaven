package igu;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.ComponentOrientation;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class VentanaInscripcion extends JDialog {

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
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.vc = vc;
		setTitle("Registrarse");
		setResizable(false);
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
					Corredor corr= null;
						if(campoDniNoVacio()){
							if(comprobarDniValido()){
								ponerLetraMayuscula();
								corr=vc.getBase().getBaseInscripciones().estaRegistrado(txtDni.getText());
							}
						}
					if(corr!=null){
						completarCampos(corr);
					}
					else{
						activarCampos();
					}
				}

				
			});
			txtDni.setBounds(172, 35, 209, 23);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	
	private void activarCampos() {
		txtNombre.setEditable(true);
		txtApellidos.setEditable(true);
		rdbtnHombre.setEnabled(true);
		rdbtnMujer.setEnabled(true);
		fecha.setEnabled(true);
		
	}
	private void completarCampos(Corredor corr){
		
		txtNombre.setText(corr.getNombre());
		txtApellidos.setText(corr.getApellido());
		if(corr.getSexo()!= null && corr.getSexo().equals("HOMBRE"))
			rdbtnHombre.setSelected(true);
		else
			rdbtnMujer.setSelected(true);
	
		;
		fecha.setDate(corr.getFechaNacimiento());
		
		desactivarCampos();
	}
	
	private void desactivarCampos(){
		txtNombre.setEditable(false);
		txtApellidos.setEditable(false);
		rdbtnHombre.setEnabled(false);
		rdbtnMujer.setEnabled(false);
		fecha.setEnabled(false);
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
					if(campoDniNoVacio() && campoNombreNoVacio() && validarFecha()){
						if(comprobarDniValido()){
							try{			
								ponerLetraMayuscula();
								registrar();							
							}catch(SQLException sql){
								errorSQL(sql);
							}
						}
						else
							errorDni();
					}
					else
						errorCampos();
						
						
				}
			});
			btnPreinscribir.setBounds(330, 483, 137, 40);
		}
		return btnPreinscribir;
	}
	
	private void ponerLetraMayuscula() {
		char[] dniUpper = txtDni.getText().toCharArray();
		if(dniUpper[8] >97 && dniUpper[8] <122){
			
			txtDni.setText(txtDni.getText().replace(dniUpper[8], (char) (dniUpper[8]-32)));
		}
		
	}
	private void errorCampos() {
		JOptionPane.showMessageDialog(this, "Es obligatorio rellenar todos los campos");
		
	}
	
	private void errorDni() {
		JOptionPane.showMessageDialog(this, "DNI no válido.");
		
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
		String date = (new SimpleDateFormat("dd/MM/yyyy").format(fecha.getDate())); //Conversion de la fecha de nacimiento
		Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
		
		Corredor corredor = new Corredor(txtDni.getText(), fecha.getDate(), sexoSelected, txtNombre.getText(), txtApellidos.getText());
		
		inscripcion = new Inscripcion(carreraSel, corredor); 
		//inscripcion.asignarCategoria(fecha.getDate(),carreraSel);//AQUI ES DONDE SE LE ASIGNA LA CATEGORIA A LA INSCRIPCION DEL CORREDOR
		//SE COMPRUEBA QUE NO TENGA CATEGORIA
		if(inscripcion.getCategoria().equals("Sin categoria"))
			JOptionPane.showMessageDialog(this, "Usted es menor de edad o no tiene una categoria asignada y no puede inscribirse en la carrera");
		else{
			
			
			
			Corredor corr=vc.getBase().getBaseInscripciones().estaRegistrado(txtDni.getText());
			// SI EL CORREDOR ESTA REGISTRADO corr SERÁ UN OBJETO, en caso contrario null
			if(corr == null){
				getBase().getBaseInscripciones().registrarCorredor(date, inscripcion);
				JOptionPane.showMessageDialog(this, "¡Felicidades! Está Pre-inscrito");
			}
			else
				JOptionPane.showMessageDialog(this, "Usted ya está Pre-inscrito, por favor proceda a elegir un método de pago.");			
			new VentanaJustificante(this);
			
		}		
	}





	private boolean campoDniNoVacio() {
		if(getTxtDni().getText().equals(""))			
			return false;

		return true;
	}

	private boolean comprobarDniValido() {
		char[] dniArray = txtDni.getText().toCharArray();
		if(dniArray.length!=9)
			return false;
		
		//comprobacion de numeros
		for(int i=0 ; i<dniArray.length-1; i++)
			if(dniArray[i] <48 || dniArray[i] >57)
				return false;
			
		
		//comprobacion de letra	
		if((dniArray[8] <65 || dniArray[8] >90) && (dniArray[8] <97 || dniArray[8] >122))
			return false;
		
		int num = 0;
	
		
		return true;
	}
	private boolean validarFecha() {
		if(getDateChooser().getDate() == null)
			return false;
		return true;
	}

	private boolean campoNombreNoVacio() {
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
