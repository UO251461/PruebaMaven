package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfirmacionPago extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumCuenta;
	private JTextField txtPrecio;
	private JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaConfirmacionPago dialog = new VentanaConfirmacionPago();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaConfirmacionPago() {
		setBounds(100, 100, 498, 317);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria: ");
		lblCuentaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuentaBancaria.setBounds(26, 32, 147, 27);
		contentPanel.add(lblCuentaBancaria);
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setBackground(Color.WHITE);
		txtNumCuenta.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtNumCuenta.setText("ES-6543232101301030501");
		txtNumCuenta.setEditable(false);
		txtNumCuenta.setBounds(149, 29, 281, 27);
		contentPanel.add(txtNumCuenta);
		txtNumCuenta.setColumns(10);
		
		JLabel lblPrecioInscripcin = new JLabel("Precio inscripci\u00F3n: ");
		lblPrecioInscripcin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecioInscripcin.setBounds(26, 94, 117, 19);
		contentPanel.add(lblPrecioInscripcin);
		
		txtPrecio = new JTextField();
		txtPrecio.setBackground(Color.WHITE);
		txtPrecio.setDisabledTextColor(Color.WHITE);
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(149, 95, 117, 20);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.add(getBtnAceptar());
		}
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		return btnAceptar;
	}
}
