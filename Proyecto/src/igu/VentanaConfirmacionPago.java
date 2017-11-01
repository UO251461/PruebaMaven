package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import database.*;
import logica.Inscripcion;

public class VentanaConfirmacionPago extends JDialog {

	
	private VentanaMetodoPago vi;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCuenta;
	private JTextField txtEntidadBancaria;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JButton btnAceptar;
	private JTextField txtAviso;
	private JButton btnCancelar;
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			VentanaConfirmacionPago dialog = new VentanaConfirmacionPago();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VentanaConfirmacionPago (VentanaMetodoPago vi) {
		setTitle("Confirmacion de Pago");
		this.vi = vi;
		setBounds(100, 100, 593, 317);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblCuenta());
		contentPanel.add(getTxtEntidadBancaria());
		contentPanel.add(getLblPrecio());
		contentPanel.add(getTxtPrecio());
		contentPanel.add(getBtnAceptar());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		txtPrecio.setText(String.valueOf(vi.getInscripcion().getPrecio()));
		txtEntidadBancaria.setText("ES89138183901929");
		contentPanel.add(getTxtAviso());
		contentPanel.add(getBtnCancelar());
		setVisible(true);
		
	}
	private JLabel getLblCuenta() {
		if (lblCuenta == null) {
			lblCuenta = new JLabel("Cuenta Bancaria: ");
			lblCuenta.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblCuenta.setBounds(23, 36, 124, 25);
		}
		return lblCuenta;
	}
	private JTextField getTxtEntidadBancaria() {
		if (txtEntidadBancaria == null) {
			txtEntidadBancaria = new JTextField();
			txtEntidadBancaria.setBackground(Color.WHITE);
			txtEntidadBancaria.setEditable(false);
			txtEntidadBancaria.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtEntidadBancaria.setBounds(170, 31, 381, 31);
			txtEntidadBancaria.setColumns(10);
		}
		return txtEntidadBancaria;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio inscripci\u00F3n: ");
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPrecio.setBounds(23, 104, 139, 25);
		}
		return lblPrecio;
	}
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtPrecio.setBounds(170, 99, 69, 31);
			txtPrecio.setColumns(10);
		}
		return txtPrecio;
	}
	
	
	
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					vi.dispose();
					
					//cerrar las demas ventanas que se relacionen con el pago
					pasarAPendientePago();
				}
			});
			btnAceptar.setBackground(Color.WHITE);
			btnAceptar.setBounds(459, 233, 89, 23);
		}
		return btnAceptar;
	}
	
	/**
	 * Metodo que pone a pendiente de pago al pagar por transferencia una inscripcion
	 */
	private void pasarAPendientePago(){
		JOptionPane.showMessageDialog(this, "Recuerde, su inscripción está pendiente de pago");
		vi.getBase().getBaseInscripciones().cambiarEstado("PENDIENTE_DE_PAGO", vi.getInscripcion());
	}
	private JTextField getTxtAviso() {
		if (txtAviso == null) {
			txtAviso = new JTextField();
			txtAviso.setBorder(new EmptyBorder(0, 0, 0, 0));
			txtAviso.setBackground(Color.WHITE);
			txtAviso.setEditable(false);
			txtAviso.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtAviso.setText("Tiene un limite de 48 horas para realizar el pago, si no se anular\u00E1 su inscripci\u00F3n.");
			txtAviso.setBounds(23, 173, 513, 33);
			txtAviso.setColumns(10);
		}
		return txtAviso;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(343, 233, 89, 23);
		}
		return btnCancelar;
	}
}
