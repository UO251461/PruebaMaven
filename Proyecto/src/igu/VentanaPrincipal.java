package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAplicacion;
	private JButton btnMostrarCarreras;
	
	private Base base;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		base = new Base();
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblAplicacion(), BorderLayout.NORTH);
		contentPane.add(getBtnMostrarCarreras(), BorderLayout.SOUTH);
	}

	private JLabel getLblAplicacion() {
		if (lblAplicacion == null) {
			lblAplicacion = new JLabel("Aplicacion");
			lblAplicacion.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAplicacion;
	}
	private JButton getBtnMostrarCarreras() {
		if (btnMostrarCarreras == null) {
			btnMostrarCarreras = new JButton("Mostrar Carreras");
			btnMostrarCarreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarCarreras();
				}
			});
			btnMostrarCarreras.setMnemonic('M');
		}
		return btnMostrarCarreras;
	}
	
	private void mostrarCarreras(){
		VentanaCarreras vcarrera = new VentanaCarreras(this);
		vcarrera.setLocationRelativeTo(null);		;
		vcarrera.setVisible(true);
		this.setVisible(false);
	}
	
	public Base getBase(){
		return base;
	}
}
