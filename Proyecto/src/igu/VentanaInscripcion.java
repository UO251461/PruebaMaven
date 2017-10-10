package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInscripcion extends JFrame {

	private JPanel contentPane;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblSexo;
	private JLabel lblFechaDeNacimiento;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JPanel panel;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemeino;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInscripcion frame = new VentanaInscripcion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInscripcion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnRegistrar());
		contentPane.add(getPanel());
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI :");
			lblDni.setBounds(40, 41, 72, 23);
		}
		return lblDni;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBounds(172, 41, 151, 23);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre :");
			lblNombre.setBounds(40, 105, 79, 23);
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(172, 105, 151, 23);
			txtNombre.setText("");
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo :");
			lblSexo.setBounds(40, 169, 86, 23);
		}
		return lblSexo;
	}
	private JLabel getLblFechaDeNacimiento() {
		if (lblFechaDeNacimiento == null) {
			lblFechaDeNacimiento = new JLabel("Fecha de nacimiento :");
			lblFechaDeNacimiento.setBounds(40, 233, 126, 23);
		}
		return lblFechaDeNacimiento;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(64, 380, 137, 40);
		}
		return btnCancelar;
	}
	private JButton getBtnRegistrar() {
		if (btnRegistrar == null) {
			btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarCampos()){
						registrar();
						JOptionPane.showMessageDialog(null, "¡Felicidades! Estás registrado.");
					}
						
				}
			});
			btnRegistrar.setBounds(265, 380, 137, 40);
		}
		return btnRegistrar;
	}
	
	protected void registrar() {
		// TODO Auto-generated method stub
		
	}

	protected boolean validarCampos() {
		// TODO Auto-generated method stub
		return true;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(Color.BLACK));
			panel.setBounds(38, 37, 393, 298);
			panel.setLayout(null);
			panel.add(getLblDni());
			panel.add(getTxtDni());
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			panel.add(getLblSexo());
			panel.add(getLblFechaDeNacimiento());
			panel.add(getRdbtnMasculino());
			panel.add(getRdbtnFemeino());
			panel.add(getDateChooser());
		}
		return panel;
	}
	private JRadioButton getRdbtnMasculino() {
		if (rdbtnMasculino == null) {
			rdbtnMasculino = new JRadioButton("Masculino");
			buttonGroup.add(rdbtnMasculino);
			rdbtnMasculino.setBounds(154, 169, 103, 23);
		}
		return rdbtnMasculino;
	}
	private JRadioButton getRdbtnFemeino() {
		if (rdbtnFemeino == null) {
			rdbtnFemeino = new JRadioButton("Femenino");
			buttonGroup.add(rdbtnFemeino);
			rdbtnFemeino.setBounds(259, 169, 96, 23);
		}
		return rdbtnFemeino;
	}
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setBounds(172, 233, 151, 23);
		}
		return dateChooser;
	}
}
