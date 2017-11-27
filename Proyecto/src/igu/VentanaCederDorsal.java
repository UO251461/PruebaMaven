package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import logica.Carrera;
import logica.Corredor;
import logica.Inscripcion;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCederDorsal extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JLabel lnDni;
	private JTextField txtDni;
	private JLabel lbEstado;
	private JLabel lb_Dorsal;
	private JTextField txtEstado;
	private JTextField txtDorsal;
	private JPanel panel2;
	private JLabel lbDni2;
	private JTextField txtDni2;
	private JLabel lbNombre2;
	private JTextField txtNombre2;
	private JLabel lbApellidos2;
	private JTextField txtApellidos2;
	private JLabel label;
	private JDateChooser fecha2;
	private JRadioButton rdbtnHombre2;
	private JRadioButton rdbtnMujer2;
	private JLabel label_1;
	private JLabel lblCorredor1;
	private JLabel lbCorredor2;
	private JLabel lblNombreLargoDe;
	private JLabel label_2;
	protected String sexoSelected = "HOMBRE";
	
	
	private VentanaCarreras vc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnCancelar;
	private JButton btnCeder;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			VentanaCederDorsal dialog = new VentanaCederDorsal(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
**/
	/**
	 * Create the dialog.
	 */
	public VentanaCederDorsal(VentanaCarreras vc) {
		this.vc = vc;
		setResizable(false);
		setLocationRelativeTo(vc);
		setModal(true);
		setBounds(100, 100, 941, 518);
		
		getContentPane().setLayout(null);
		getContentPane().add(getPanel1());
		getContentPane().add(getPanel2());
		getContentPane().add(getLblCorredor1());
		getContentPane().add(getLbCorredor2());
		getContentPane().add(getLblNombreLargoDe());
		getContentPane().add(getLabel_2());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getBtnCeder());
	}
	private JPanel getPanel1() {
		if (panel1 == null) {
			panel1 = new JPanel();
			panel1.setLayout(null);
			panel1.setBorder(new LineBorder(Color.BLACK));
			panel1.setBounds(53, 130, 306, 265);
			panel1.add(getLnDni());
			panel1.add(getTxtDni());
			panel1.add(getLbEstado());
			panel1.add(getLb_Dorsal());
			panel1.add(getTxtEstado());
			panel1.add(getTxtDorsal());
		}
		return panel1;
	}
	private JLabel getLnDni() {
		if (lnDni == null) {
			lnDni = new JLabel("DNI :");
			lnDni.setDisplayedMnemonic('d');
			lnDni.setBounds(40, 49, 72, 23);
		}
		return lnDni;
	}
	public JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					Inscripcion inscrip= null;
					if(campoDniNoVacio(txtDni)){
						if(comprobarDniValido(txtDni)){
							ponerLetraMayuscula(txtDni);
							
							Corredor corredor = new Corredor(txtDni.getText(), null, null, null, null);
							Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
							Inscripcion inscripcion = new Inscripcion(carreraSel, corredor);
							inscrip=vc.getBase().getBaseInscripciones().estaInscrito(inscripcion);
						}
					}
					
					
					if(inscrip!=null){
						completarCampos(inscrip);
					}
					else{
						borrarCampos();
					}
					
				}

				
			});
			txtDni.setColumns(10);
			txtDni.setBounds(122, 49, 148, 23);
		}
		return txtDni;
	}
	private void borrarCampos() {
		txtDorsal.setText("");
		txtEstado.setText("");
		
	}
	private void completarCampos(Inscripcion inscripcion){
		
		txtDorsal.setText(inscripcion.getDorsal()+"");
		txtEstado.setText(inscripcion.getEstado());
		
	}
	private void ponerLetraMayuscula(JTextField txtDni) {
		char[] dniUpper = txtDni.getText().toCharArray();
		if(dniUpper[8] >97 && dniUpper[8] <122){
			
			txtDni.setText(txtDni.getText().replace(dniUpper[8], (char) (dniUpper[8]-32)));
		}
		
	}
	
	private boolean campoDniNoVacio(JTextField txtDni) {
		if(getTxtDni().getText().equals(""))			
			return false;

		return true;
	}
	
	private boolean comprobarDniValido(JTextField txtDni) {
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
	private JLabel getLbEstado() {
		if (lbEstado == null) {
			lbEstado = new JLabel("Estado :");
			lbEstado.setBounds(40, 193, 72, 23);
		}
		return lbEstado;
	}
	public JLabel getLb_Dorsal() {
		if (lb_Dorsal == null) {
			lb_Dorsal = new JLabel("Dorsal :");
			lb_Dorsal.setDisplayedMnemonic('f');
			lb_Dorsal.setBounds(40, 121, 72, 23);
		}
		return lb_Dorsal;
	}
	private JTextField getTxtEstado() {
		if (txtEstado == null) {
			txtEstado = new JTextField();
			txtEstado.setText("");
			txtEstado.setEditable(false);
			txtEstado.setColumns(10);
			txtEstado.setBounds(122, 193, 148, 23);
		}
		return txtEstado;
	}
	public JTextField getTxtDorsal() {
		if (txtDorsal == null) {
			txtDorsal = new JTextField();
			txtDorsal.setText("");
			txtDorsal.setEditable(false);
			txtDorsal.setColumns(10);
			txtDorsal.setBounds(122, 121, 148, 23);
		}
		return txtDorsal;
	}
	private JPanel getPanel2() {
		if (panel2 == null) {
			panel2 = new JPanel();
			panel2.setLayout(null);
			panel2.setBorder(new LineBorder(Color.BLACK));
			panel2.setBounds(562, 129, 317, 265);
			panel2.add(getLbDni2());
			panel2.add(getTxtDni2());
			panel2.add(getLbNombre2());
			panel2.add(getTxtNombre2());
			panel2.add(getLbApellidos2());
			panel2.add(getTxtApellidos2());
			panel2.add(getLabel());
			panel2.add(getFecha2());
			panel2.add(getRdbtnHombre2());
			panel2.add(getRdbtnMujer2());
			panel2.add(getLabel_1());
		}
		return panel2;
	}
	private JLabel getLbDni2() {
		if (lbDni2 == null) {
			lbDni2 = new JLabel("DNI :");
			lbDni2.setDisplayedMnemonic('d');
			lbDni2.setBounds(40, 25, 72, 23);
		}
		return lbDni2;
	}
	public JTextField getTxtDni2() {
		if (txtDni2 == null) {
			txtDni2 = new JTextField();
			txtDni2.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					Corredor corr= null;
					if(campoDniNoVacio(txtDni2)){
						if(comprobarDniValido(txtDni2)){
							ponerLetraMayuscula(txtDni2);
							corr=vc.getBase().getBaseInscripciones().estaRegistrado(txtDni2.getText());
						}
					}
					if(corr!=null){
						completarCampos2(corr);
					}
					else{
						activarCampos();
					}
				}

				
			});
			txtDni2.setColumns(10);
			txtDni2.setBounds(122, 25, 148, 23);
		}
		return txtDni2;
	}
	
	private void completarCampos2(Corredor corr){
		
		txtNombre2.setText(corr.getNombre());
		txtApellidos2.setText(corr.getApellido());
		if(corr.getSexo()!= null && corr.getSexo().equals("HOMBRE"))
			rdbtnHombre2.setSelected(true);
		else
			rdbtnMujer2.setSelected(true);
	
		fecha2.setDate(corr.getFechaNacimiento());
		
		desactivarCampos();
	}
	
	private void desactivarCampos(){
		txtNombre2.setEditable(false);
		txtApellidos2.setEditable(false);
		rdbtnHombre2.setEnabled(false);
		rdbtnMujer2.setEnabled(false);
		fecha2.setEnabled(false);
	}
	
	private void activarCampos() {
		txtNombre2.setEditable(true);
		txtApellidos2.setEditable(true);
		rdbtnHombre2.setEnabled(true);
		rdbtnMujer2.setEnabled(true);
		fecha2.setEnabled(true);
		
	}
	
	private JLabel getLbNombre2() {
		if (lbNombre2 == null) {
			lbNombre2 = new JLabel("Nombre :");
			lbNombre2.setDisplayedMnemonic('n');
			lbNombre2.setBounds(40, 73, 72, 23);
		}
		return lbNombre2;
	}
	public JTextField getTxtNombre2() {
		if (txtNombre2 == null) {
			txtNombre2 = new JTextField();
			txtNombre2.setText("");
			txtNombre2.setColumns(10);
			txtNombre2.setBounds(122, 73, 148, 23);
		}
		return txtNombre2;
	}
	private JLabel getLbApellidos2() {
		if (lbApellidos2 == null) {
			lbApellidos2 = new JLabel("Apellidos");
			lbApellidos2.setDisplayedMnemonic('a');
			lbApellidos2.setBounds(40, 121, 72, 23);
		}
		return lbApellidos2;
	}
	public JTextField getTxtApellidos2() {
		if (txtApellidos2 == null) {
			txtApellidos2 = new JTextField();
			txtApellidos2.setText("");
			txtApellidos2.setColumns(10);
			txtApellidos2.setBounds(122, 121, 148, 23);
		}
		return txtApellidos2;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Fecha de nacimiento :");
			label.setDisplayedMnemonic('f');
			label.setBounds(25, 220, 126, 23);
		}
		return label;
	}
	private JDateChooser getFecha2() {
		if (fecha2 == null) {
			fecha2 = new JDateChooser();
			fecha2.setRequestFocusEnabled(false);
			fecha2.setDateFormatString("dd/MM/yyyy");
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				fecha2.setDate(sf.parse("01-01-1990"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fecha2.setBounds(151, 220, 119, 23);
		}
		return fecha2;
	}
	private JRadioButton getRdbtnHombre2() {
		if (rdbtnHombre2 == null) {
			rdbtnHombre2 = new JRadioButton("HOMBRE");
			rdbtnHombre2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sexoSelected = "HOMBRE";
				}
			});
			buttonGroup.add(rdbtnHombre2);
			rdbtnHombre2.setSelected(true);
			rdbtnHombre2.setMnemonic('h');
			rdbtnHombre2.setBounds(122, 173, 84, 23);
		}
		return rdbtnHombre2;
	}
	private JRadioButton getRdbtnMujer2() {
		if (rdbtnMujer2 == null) {
			rdbtnMujer2 = new JRadioButton("MUJER");
			rdbtnMujer2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sexoSelected = "MUJER";
				}
			});
			buttonGroup.add(rdbtnMujer2);
			rdbtnMujer2.setMnemonic('m');
			rdbtnMujer2.setBounds(208, 173, 84, 23);
		}
		return rdbtnMujer2;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Sexo :");
			label_1.setBounds(40, 173, 63, 23);
		}
		return label_1;
	}
	private JLabel getLblCorredor1() {
		if (lblCorredor1 == null) {
			lblCorredor1 = new JLabel("Corredor 1");
			lblCorredor1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblCorredor1.setHorizontalAlignment(SwingConstants.CENTER);
			lblCorredor1.setBounds(95, 87, 223, 31);
		}
		return lblCorredor1;
	}
	private JLabel getLbCorredor2() {
		if (lbCorredor2 == null) {
			lbCorredor2 = new JLabel("Corredor 2");
			lbCorredor2.setHorizontalAlignment(SwingConstants.CENTER);
			lbCorredor2.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbCorredor2.setBounds(609, 87, 223, 31);
		}
		return lbCorredor2;
	}
	public JLabel getLblNombreLargoDe() {
		if (lblNombreLargoDe == null) {
			lblNombreLargoDe = new JLabel();
			lblNombreLargoDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombreLargoDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNombreLargoDe.setBounds(95, 27, 708, 31);
			lblNombreLargoDe.setText(vc.getBase().getBaseCarrera().getCarreraSeleccionada().getNombre());
		}
		return lblNombreLargoDe;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
			Image imgUr = new ImageIcon(getClass().getResource("/img/CEDER A.png")).getImage();
			Image imgUr2 =imgUr.getScaledInstance(183,152 , Image.SCALE_FAST);
			
			label_2.setIcon(new ImageIcon(imgUr2));
			label_2.setBounds(369, 183, 183, 152);
		}
		return label_2;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(299, 423, 140, 41);
		}
		return btnCancelar;
	}
	private JButton getBtnCeder() {
		if (btnCeder == null) {
			btnCeder = new JButton("Ceder");
			btnCeder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!esCamposVacios()){
						if(correctoDni1()){
							if(correctoDni2()){
								if(txtEstado.getText().equals("INSCRITO")){
									int resp = mensajeOpcion();
									if(resp == 0)
										ceder();									
								}else errorNoInscrito();
								
							}else errorDni2();						
						}else errorDni1();						
					}
					else errorVacio();
				}

				

				

				
				
			});
			btnCeder.setBounds(510, 423, 140, 41);
		}
		return btnCeder;
	}
	private void ceder() {
		
		Corredor corredor = new Corredor(txtDni2.getText(), fecha2.getDate(), sexoSelected, txtNombre2.getText(), txtApellidos2.getText());
		Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
		Inscripcion inscripcion = new Inscripcion(carreraSel, corredor);	
		inscripcion.setDorsal(Integer.valueOf(txtDorsal.getText()));
		if(inscripcion.getCategoria().equals("Sin categoria"))
			JOptionPane.showMessageDialog(this, "El dorsal no puede ser cedido a un menor de edad.");
		else{
			vc.getBase().getBaseInscripciones().cederDorsal(txtDni.getText(), inscripcion );
		
			VentanaMensajeCederDorsal vcD = new VentanaMensajeCederDorsal(this);
			vcD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			vcD.setVisible(true);
		}
	}
	
	private boolean correctoDni1() {
		Corredor corredor = new Corredor(txtDni.getText(), null, null, null, null);
		Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
		Inscripcion inscripcion = new Inscripcion(carreraSel, corredor);	
		if(vc.getBase().getBaseInscripciones().estaInscrito(inscripcion) != null)
			return true;
		return false;
	}
	private boolean correctoDni2() {
		char[] dniArray = txtDni2.getText().toCharArray();
		if(dniArray.length!=9)
			return false;
		
		//comprobacion de numeros
		for(int i=0 ; i<dniArray.length-1; i++)
			if(dniArray[i] <48 || dniArray[i] >57)
				return false;
			
		
		//comprobacion de letra	
		if((dniArray[8] <65 || dniArray[8] >90) && (dniArray[8] <97 || dniArray[8] >122))
			return false;
		return true;
	}

	
	private boolean esCamposVacios() {
		return (txtDni.getText().equals("") || txtDni2.getText().equals("") || txtNombre2.getText().equals("") ||txtApellidos2.getText().equals("") || fecha2.getDate()==null);
	}

	

	private void errorNoInscrito() {
		JOptionPane.showMessageDialog(this, "Únicamente pueden ceder sus dorsales los corredores cuyo estado sea INSCRITO.");
	}

	private void errorDni1() {
		JOptionPane.showMessageDialog(this, "El primer corredor no está inscrito en esta carrera.");		
	}
	private void errorDni2() {
		JOptionPane.showMessageDialog(this, "El DNI del segundo corredor no es válido.");	
		
	}
	private void errorVacio() {
		JOptionPane.showMessageDialog(this, "Es obligatorio rellenar correctamente todos los campos");
	}
	
	private int mensajeOpcion() {
		return JOptionPane.showConfirmDialog(this, "¿Esta seguro de ceder tu dorsal?", "Alerta!", JOptionPane.YES_NO_OPTION);
	}
}
