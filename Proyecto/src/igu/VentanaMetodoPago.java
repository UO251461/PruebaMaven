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


	/**
	 * Create the dialog.
	 */
	public VentanaMetodoPago(VentanaInscripcion pre) {
		setTitle("Metodo de Pago");
		try {
			this.vi = pre;
			inscripcion = pre.getInscripcion();
			base = pre.getBase();
			setBounds(300, 300, 605, 127);
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
			panel.setBounds(59, 75, 457, 113);
			panel.setLayout(null);
			panel.add(getLblTipoDeTarjeta());
			panel.add(getLblNumeroDeTarjeta());
			panel.add(getComboBox());
			panel.add(getLblTarjera());
			panel.add(getTxtNumTarjeta());
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
			lblNumeroDeTarjeta.setBounds(21, 64, 149, 26);
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
		setBounds(300, 300, 605, 127);
		panel.setVisible(false);
	}
	
	private void mostrarTarjeta(){
		setBounds(300, 300, 605, 290);
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
	
	
}
