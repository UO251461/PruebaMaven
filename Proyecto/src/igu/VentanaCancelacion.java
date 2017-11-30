package igu;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.Carrera;
import logica.Corredor;
import logica.Inscripcion;

public class VentanaCancelacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbTitulo;
	private JLabel label;
	private JTextField txtDni;
	private JLabel label_1;
	private JTextField txtEstado;
	private JLabel lblImporteDeDevolucin;
	private JLabel lblNewLabel;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private VentanaCarreras vc;
	private JLabel plbPorcentaje;
	private JTextField textPorcentaje;
	private float devolucion ;
	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		try {
			VentanaCancelacion dialog = new VentanaCancelacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 */
	/**
	 * Create the dialog.
	 */
	public VentanaCancelacion(VentanaCarreras vc) {
		setResizable(false);
		this.vc = vc;
		devolucion = vc.getBase().getBaseInscripciones().getPorcent(vc.getBase().getBaseCarrera().getCarreraSeleccionada());
		setModal(true);
		setBounds(100, 100, 588, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLbTitulo());
		contentPanel.add(getLabel());
		contentPanel.add(getTxtDni());
		contentPanel.add(getLabel_1());
		contentPanel.add(getTextField_1());
		contentPanel.add(getLblImporteDeDevolucin());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getBtnAceptar());
		contentPanel.add(getPlbPorcentaje());
		contentPanel.add(getTextPorcentaje());
		
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel();
			lbTitulo.setText(vc.getBase().getBaseCarrera().getCarreraSeleccionada().getNombre());
			
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbTitulo.setBounds(75, 31, 408, 31);
		}
		return lbTitulo;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("DNI :");
			label.setDisplayedMnemonic('d');
			label.setBounds(177, 83, 72, 23);
		}
		return label;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					Inscripcion inscrip= null;
					boolean estaInscrito = false;
					if(campoDniNoVacio(txtDni)){
						if(comprobarDniValido(txtDni)){
							ponerLetraMayuscula(txtDni);
							System.out.println("PASA");
							Corredor corredor = new Corredor(txtDni.getText(), null, null, null, null);
							Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
							inscrip = new Inscripcion(carreraSel, corredor);
							estaInscrito=vc.getBase().getBaseInscripciones().estaInscrito(inscrip);
						}
					}
					
					
					if(estaInscrito && inscrip!=null){
						completarCampos(inscrip);
					}
					else{
						borrarCampos();
					}
					
				}

				
			});
			txtDni.setColumns(10);
			txtDni.setBounds(259, 83, 148, 23);
		}
		return txtDni;
	}
	
	private void borrarCampos() {
		txtEstado.setText("");
		
	}
	private void completarCampos(Inscripcion inscripcion){
		//-1 si no hay dorsal asignado, se añade en baseInscripciones
		txtEstado.setText(inscripcion.getEstado());
		
	}
	
	private void ponerLetraMayuscula(JTextField txtDni) {
		char[] dniUpper = txtDni.getText().toCharArray();
		if(dniUpper[8] >97 && dniUpper[8] <122){
			
			txtDni.setText(txtDni.getText().replace(dniUpper[8], (char) (dniUpper[8]-32)));
		}
		
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
		
	
		
		return true;
	}
	private boolean campoDniNoVacio(JTextField txtDni) {
		if(getTxtDni().getText().equals(""))			
			return false;

		return true;
	}
	
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Estado :");
			label_1.setBounds(177, 136, 72, 23);
		}
		return label_1;
	}
	private JTextField getTextField_1() {
		if (txtEstado == null) {
			txtEstado = new JTextField();
			txtEstado.setText("");
			txtEstado.setEditable(false);
			txtEstado.setColumns(10);
			txtEstado.setBounds(259, 136, 148, 23);
		}
		return txtEstado;
	}
	private JLabel getLblImporteDeDevolucin() {
		if (lblImporteDeDevolucin == null) {
			lblImporteDeDevolucin = new JLabel("Importe de devoluci\u00F3n:");
			lblImporteDeDevolucin.setBounds(155, 229, 143, 31);
		}
		return lblImporteDeDevolucin;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel();			
			lblNewLabel.setText(Math.round((devolucion/100) * vc.getBase().getBaseCarrera().getCarreraSeleccionada().getPrecio()*100.00)/100.00+" €");
			//lblNewLabel.setText(vc.getBase().getBaseCarrera().getCarreraSeleccionada().getPrecio() + " \u20AC");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setBounds(311, 216, 115, 54);
		}
		return lblNewLabel;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(109, 281, 148, 37);
		}
		return btnCancelar;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!esCamposVacios()){
						if(correctoDni1()){
								if(txtEstado.getText().equals("INSCRITO")){
										int resp = mensajeOpcion();
										if(resp == 0)
											cancelarInsc();
								}else errorNoInscrito();													
						}else errorDni1();
					}
					else errorVacio();
				}
			});
			btnAceptar.setBounds(308, 281, 167, 37);
		}
		return btnAceptar;
		
		
	}
	private int mensajeOpcion() {
		return JOptionPane.showConfirmDialog(this, "¿Esta seguro de cancelar su inscripción?", "Alerta!", JOptionPane.YES_NO_OPTION);
	}
	private void errorNoInscrito() {
		JOptionPane.showMessageDialog(this, "Únicamente pueden ceder sus dorsales los corredores cuyo estado sea INSCRITO.");
	}
	private void errorDni1() {
		JOptionPane.showMessageDialog(this, "El primer corredor no está inscrito en esta carrera.");		
	}
	private void errorVacio() {
		JOptionPane.showMessageDialog(this, "Es obligatorio rellenar correctamente todos los campos");
	}
	private boolean correctoDni1() {
		Corredor corredor = new Corredor(txtDni.getText(), null, null, null, null);
		Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
		Inscripcion inscripcion = new Inscripcion(carreraSel, corredor);	
		/*
		if(vc.getBase().getBaseInscripciones().estaInscrito(inscripcion) != null)
			return true;*/
		return vc.getBase().getBaseInscripciones().estaInscrito(inscripcion) ;
	}
	
	
private void cancelarInsc() {
		
		Corredor corredor = new Corredor(txtDni.getText(), null, null, null, null);
		Carrera carreraSel = vc.getBase().getBaseCarrera().getCarreraSeleccionada();
		Inscripcion inscripcion = new Inscripcion(carreraSel, corredor);	
		
		
			vc.getBase().getBaseInscripciones().cancelarInscripcion(inscripcion, devolucion);
		
		
			JOptionPane.showMessageDialog(this, "Inscripción cancelada.");
	}
	private boolean esCamposVacios() {
		return txtDni.getText().equals("") ;
	}
	private Date convertirDate(String fecha) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		return sf.parse(fecha);
	}
	private JLabel getPlbPorcentaje() {
		if (plbPorcentaje == null) {
			plbPorcentaje = new JLabel("Porcentaje de devoluci\u00F3n:");
			plbPorcentaje.setBounds(155, 179, 159, 23);
		}
		return plbPorcentaje;
	}
	private JTextField getTextPorcentaje() {
		if (textPorcentaje == null) {
			textPorcentaje = new JTextField();
			textPorcentaje.setText(devolucion+" %");
			textPorcentaje.setEditable(false);
			textPorcentaje.setColumns(10);
			textPorcentaje.setBounds(321, 179, 86, 23);
		}
		return textPorcentaje;
	}
}
