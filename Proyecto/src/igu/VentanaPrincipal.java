package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JPanel panel;
	private JButton btnUsuario;
	private JButton btClasificacion;
	private JButton btnAsignarDorsal;

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
		contentPane.add(getPanel(), BorderLayout.SOUTH);
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
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnUsuario());
			panel.add(getBtnMostrarCarreras());
			panel.add(getBtClasificacion());
			panel.add(getBtnAsignarDorsal());
		}
		return panel;
	}
	private JButton getBtnUsuario() {
		if (btnUsuario == null) {
			btnUsuario = new JButton("Ver Usuario");
			btnUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dni = JOptionPane.showInputDialog("Introduzca dni");
					if(dni!=null && !dni.isEmpty())
						mostrarVentanaUsuario(dni);
				}
			});
		}
		return btnUsuario;
	}
	
	private void mostrarVentanaUsuario(String dni){
		VentanaUsuario vu = new VentanaUsuario(dni);
		vu.setLocationRelativeTo(this);
		//vu.setModal(true);
		vu.setVisible(true);
	}
	private JButton getBtClasificacion() {
		if (btClasificacion == null) {
			btClasificacion = new JButton("Ver Clasificacion");
			btClasificacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String competicion = JOptionPane.showInputDialog("Introduzca id de la Competicion");
					if( competicion!=null && !competicion.isEmpty())
						mostrarVentanaClasificacion(competicion);
				}
			});
		}
		return btClasificacion;
	}
	private void mostrarVentanaClasificacion(String competicion){
		VentanaClasificacion vc = new VentanaClasificacion(competicion);
		vc.setLocationRelativeTo(this);
		vc.setModal(true);
		vc.setVisible(true);
	}
	private JButton getBtnAsignarDorsal() {
		if (btnAsignarDorsal == null) {
			btnAsignarDorsal = new JButton("Asignar dorsal");
			btnAsignarDorsal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String competicion = JOptionPane.showInputDialog("Introduzca id de la competición");
					String organizador = JOptionPane.showInputDialog("Introduzca id del organizador");
					boolean isAsignada = base.getBaseInscripciones().asignarDorsal(competicion, organizador);
					if(isAsignada)					
						JOptionPane.showMessageDialog(null, "Dorsales asignadas");
					else
						JOptionPane.showMessageDialog(null, "No se han podido asignar las dorsales");
				}
			});
		}
		return btnAsignarDorsal;
	}
}
