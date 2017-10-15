package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Inscripcion;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaJustificante extends JDialog {
	private JTextArea txtrIporte;
	private JLabel lblJusticicanteDeLa;
	private VentanaElegirCompeticion ve;
	private JButton btnContinuar;

	private Inscripcion ins;

	/**
	 * Create the dialog.
	 */
	public VentanaJustificante(VentanaElegirCompeticion ve) {
		this.ve = ve;
		ins = ve.getInscripcion();
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 294);
		getContentPane().setLayout(null);
		{
			JTextArea txtrJustificanteDeInscripcinn = new JTextArea();
			txtrJustificanteDeInscripcinn.setEditable(false);
			txtrJustificanteDeInscripcinn.setBounds(20, 71, 359, 70);
			txtrJustificanteDeInscripcinn.setText("Fecha de inscripción : " + ins.getFecha()+"\nNombre : " + ins.getNombreCorredor()+
					"\nNombre de la competici\u00F3n : " + ins.getNombreCompeticion());
			getContentPane().add(txtrJustificanteDeInscripcinn);
		}
		getContentPane().add(getTxtrIporte());
		getContentPane().add(getLblJusticicanteDeLa());
		getContentPane().add(getBtnContinuar());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	private JTextArea getTxtrIporte() {
		if (txtrIporte == null) {
			txtrIporte = new JTextArea();
			txtrIporte.setFont(new Font("Monospaced", Font.BOLD, 18));
			txtrIporte.setText("IMPORTE :" + ins.getPrecio());
			txtrIporte.setBounds(117, 168, 210, 34);
		}
		return txtrIporte;
	}
	private JLabel getLblJusticicanteDeLa() {
		if (lblJusticicanteDeLa == null) {
			lblJusticicanteDeLa = new JLabel("Justicicante de la inscripci\u00F3n");
			lblJusticicanteDeLa.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblJusticicanteDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblJusticicanteDeLa.setBounds(71, 11, 292, 34);
		}
		return lblJusticicanteDeLa;
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					ve.cerrarVentanas();
					dispose();
				}
			});
			btnContinuar.setBounds(268, 228, 151, 27);
		}
		return btnContinuar;
	}
}
