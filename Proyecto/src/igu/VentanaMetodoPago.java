package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import database.Base;
import logica.Inscripcion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class VentanaMetodoPago extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField textField;
	private JRadioButton rdbtnTarjeta;
	private JRadioButton rdbtnTransferencia;
	
	private JLabel lblTipoDeTarjeta;
	private JPanel panel;
	
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JLabel lblNumeroDeTarjeta;
	private JComboBox comboBox;
	private JLabel lblTarjera;
	private JTextField txtNumTarjeta;
	private JPanel paneok;
	private JButton btnContinuar;

	private VentanaInscripcion vi;
	private Inscripcion inscripcion;
	private Base base;
	private JLabel lblApellido1;
	private JLabel lblApellido2;
	private JLabel lblFechaCaducidad;
	private JLabel lblCSV;
	private JTextField textApellido1;
	private JTextField textApellido2;
	private JTextField textFechaCaducidad;
	private JTextField textCSV;


	/**
	 * Create the dialog.
	 */
	public VentanaMetodoPago(VentanaInscripcion pre) {
		setTitle("Metodo de Pago");
		try {
			this.vi = pre;
			inscripcion = pre.getInscripcion();
			base = pre.getBase();
			setBounds(300, 300, 605, 140);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			contentPanel.add(getRdbtnTarjeta());
			contentPanel.add(getRdbtnTransferencia());
			
			contentPanel.add(getPanel());
			getContentPane().add(getPaneok(), BorderLayout.SOUTH);
			setLocationRelativeTo(pre);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(59, 75, 457, 322);
			panel.setLayout(null);
			panel.add(getLblTipoDeTarjeta());
			panel.add(getLblNumeroDeTarjeta());
			panel.add(getComboBox());
			panel.add(getLblTarjera());
			panel.add(getTxtNumTarjeta());
			panel.add(getLblApellido1());
			panel.add(getLblApellido2());
			panel.add(getLblFechaCaducidad());
			panel.add(getLblCSV());
			panel.add(getTextApellido1());
			panel.add(getTextApellido2());
			panel.add(getTextFechaCaducidad());
			panel.add(getTextCSV());
			panel.setVisible(false);
		}
		return panel;
	}
	
	private JLabel getLblTipoDeTarjeta() {
		if (lblTipoDeTarjeta == null) {
			lblTipoDeTarjeta = new JLabel("Tipo de tarjeta:");
			lblTipoDeTarjeta.setDisplayedMnemonic('p');
			lblTipoDeTarjeta.setLabelFor(getComboBox());
			lblTipoDeTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTipoDeTarjeta.setBounds(21, 25, 135, 20);
		}
		return lblTipoDeTarjeta;
	}
	
	private JLabel getLblNumeroDeTarjeta() {
		if (lblNumeroDeTarjeta == null) {
			lblNumeroDeTarjeta = new JLabel("Numero de tarjeta:");
			lblNumeroDeTarjeta.setLabelFor(getTxtNumTarjeta());
			lblNumeroDeTarjeta.setDisplayedMnemonic('n');
			lblNumeroDeTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNumeroDeTarjeta.setBounds(21, 63, 149, 26);
		}
		return lblNumeroDeTarjeta;
	}
	
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Otros", "MasterCard", "Visa", "Alimerka"}));
			comboBox.setBounds(180, 21, 143, 28);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ponerImagenTarjeta();
				}
			});
		}
		return comboBox;
	}
	
	private JLabel getLblTarjera() {
		if (lblTarjera == null) {
			lblTarjera = new JLabel("");
			lblTarjera.setLabelFor(getTxtNumTarjeta());
			lblTarjera.setBounds(333, 18, 69, 35);		
		}
		return lblTarjera;
	}
		
	private JTextField getTxtNumTarjeta() {
		if (txtNumTarjeta == null) {
			txtNumTarjeta = new JTextField();
			//txtNumTarjeta.addKeyListener(aV);
			txtNumTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtNumTarjeta.setBounds(180, 62, 204, 30);
			txtNumTarjeta.setColumns(10);
			
		}
		return txtNumTarjeta;
	}
	
	/**
	 * Pone la imagen de la tarjeta seleccionada
	 */
	private void ponerImagenTarjeta() {
		int selec = getComboBox().getSelectedIndex();
		
		if(selec == 0 )
			lblTarjera.setIcon(new ImageIcon());
		if(selec == 1 ){
			lblTarjera.setIcon(new ImageIcon(adaptarImagen("master")));
		}
		else if(selec == 2 )
			lblTarjera.setIcon(new ImageIcon(adaptarImagen("visa")));
		else if(selec == 3)
			lblTarjera.setIcon(new ImageIcon(adaptarImagen("alimerka")));
			
	}
	
	/**
	 * Adapta la imagen
	 * @param tarjeta
	 * @return imagen adaptada
	 */
	private Image adaptarImagen(String tarjeta){
		Image imgUr = new ImageIcon(getClass().getResource("/img/"+tarjeta+".jpg")).getImage();
		Image imgUr2 =imgUr.getScaledInstance(69,35 , Image.SCALE_FAST);
		return imgUr2;
	}
	
	
	
	private JRadioButton getRdbtnTarjeta() {
		if (rdbtnTarjeta == null) {
			rdbtnTarjeta = new JRadioButton("Tarjeta");
			rdbtnTarjeta.setMnemonic('t');
			rdbtnTarjeta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarTarjeta();
				}
			});
			buttonGroup.add(rdbtnTarjeta);
			rdbtnTarjeta.setBounds(59, 28, 109, 23);
			//rdbtnTarjeta.setAction
		}
		return rdbtnTarjeta;
	}
	private JRadioButton getRdbtnTransferencia() {
		if (rdbtnTransferencia == null) {
			rdbtnTransferencia = new JRadioButton("Transferencia");
			rdbtnTransferencia.setMnemonic('r');
			rdbtnTransferencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ocultarTarjeta();
				}
			});
			buttonGroup.add(rdbtnTransferencia);
			rdbtnTransferencia.setBounds(341, 28, 109, 23);
		}
		return rdbtnTransferencia;
	}
	
	private void ocultarTarjeta(){
		setBounds(getX(), getY(), 605, 140);
		panel.setVisible(false);
	}
	
	private void mostrarTarjeta(){
		setBounds(getX(), getY(), 605, 500);
		panel.setVisible(true);		
	}
	private JPanel getPaneok() {
		if (paneok == null) {
			paneok = new JPanel();
			paneok.add(getBtnContinuar());
		}
		return paneok;
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
			btnContinuar.setMnemonic('c');
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getRdbtnTarjeta().isSelected()){
						if(validarNum()){
							continuarTarjeta();
						} else
							error();
					}
					else continuarTransferencia();					
				}
				
			});
		}
		return btnContinuar;
	}
	
	
	private void continuarTarjeta() {
		base.getBaseInscripciones().cambiarEstado("INSCRITO", inscripcion);
		dispose();
	}

	private void continuarTransferencia() {
		new VentanaConfirmacionPago(this);		
	}

	private void error() {
		JOptionPane.showMessageDialog(this, "Número de tarjeta no válido");
	}
	private boolean validarNum() {
		if(getTxtNumTarjeta().getText().equals(""))
			return false;
		else return true;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public Base getBase() {
		return base;
	}
	private JLabel getLblApellido1() {
		if (lblApellido1 == null) {
			lblApellido1 = new JLabel("Primer Apellido:");
			lblApellido1.setLabelFor(getTextApellido1());
			lblApellido1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblApellido1.setDisplayedMnemonic('a');
			lblApellido1.setBounds(21, 104, 149, 26);
		}
		return lblApellido1;
	}
	private JLabel getLblApellido2() {
		if (lblApellido2 == null) {
			lblApellido2 = new JLabel("Segundo apellido:");
			lblApellido2.setLabelFor(getTextApellido2());
			lblApellido2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblApellido2.setDisplayedMnemonic('e');
			lblApellido2.setBounds(21, 147, 149, 26);
		}
		return lblApellido2;
	}
	private JLabel getLblFechaCaducidad() {
		if (lblFechaCaducidad == null) {
			lblFechaCaducidad = new JLabel("Fecha caducidad:");
			lblFechaCaducidad.setLabelFor(getTextFechaCaducidad());
			lblFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblFechaCaducidad.setDisplayedMnemonic('f');
			lblFechaCaducidad.setBounds(21, 194, 149, 26);
		}
		return lblFechaCaducidad;
	}
	private JLabel getLblCSV() {
		if (lblCSV == null) {
			lblCSV = new JLabel("CSV:");
			lblCSV.setLabelFor(getTextCSV());
			lblCSV.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblCSV.setDisplayedMnemonic('c');
			lblCSV.setBounds(21, 234, 149, 26);
		}
		return lblCSV;
	}
	private JTextField getTextApellido1() {
		if (textApellido1 == null) {
			textApellido1 = new JTextField();
			textApellido1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textApellido1.setColumns(10);
			textApellido1.setBounds(180, 103, 204, 30);
		}
		return textApellido1;
	}
	private JTextField getTextApellido2() {
		if (textApellido2 == null) {
			textApellido2 = new JTextField();
			textApellido2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textApellido2.setColumns(10);
			textApellido2.setBounds(180, 143, 204, 30);
		}
		return textApellido2;
	}
	private JTextField getTextFechaCaducidad() {
		if (textFechaCaducidad == null) {
			textFechaCaducidad = new JTextField();
			textFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textFechaCaducidad.setColumns(10);
			textFechaCaducidad.setBounds(180, 189, 204, 30);
		}
		return textFechaCaducidad;
	}
	private JTextField getTextCSV() {
		if (textCSV == null) {
			textCSV = new JTextField();
			textCSV.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textCSV.setColumns(10);
			textCSV.setBounds(180, 230, 204, 30);
		}
		return textCSV;
	}
}
