package igu;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMensajeCederDorsal extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtrDorsalDe;
	private String nombre;
	private String dni;
	private String numero;
	private String apellido;
	private JButton btnAceptar;
	private String nombreCar;
	private VentanaCederDorsal vcD;
	

	/**
	 * Create the dialog.
	 */
	public VentanaMensajeCederDorsal(VentanaCederDorsal vcD) {
		setModal(true);
		this.nombre = vcD.getTxtNombre2().getText();
		this.dni = vcD.getTxtDni2().getText();
		this.numero = vcD.getTxtDorsal().getText();
		this.apellido = vcD.getTxtApellidos2().getText();
		this.nombreCar = vcD.getLblNombreLargoDe().getText();
		this.vcD =vcD;
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 219);
		getContentPane().setLayout(null);
		getContentPane().add(getTxtrDorsalDe());
		getContentPane().add(getBtnAceptar());
		
	}
	private JTextArea getTxtrDorsalDe() {
		if (txtrDorsalDe == null) {
			txtrDorsalDe = new JTextArea();
			txtrDorsalDe.setWrapStyleWord(true);
			txtrDorsalDe.setLineWrap(true);
			txtrDorsalDe.setEditable(false);
			txtrDorsalDe.setText("Dorsal "+ numero +" De la carrera '" + nombreCar+ "' ha sido cedido a:\n Nombre: " + nombre+ " "+apellido +"\n DNI: " + dni );
			txtrDorsalDe.setBounds(65, 31, 320, 86);
		}
		return txtrDorsalDe;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					vcD.dispose();
				}
			});
			btnAceptar.setBounds(153, 128, 150, 35);
		}
		return btnAceptar;
	}
}
