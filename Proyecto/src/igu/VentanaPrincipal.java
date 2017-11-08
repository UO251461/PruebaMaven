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
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
	private JButton btnCrearCarrera;
	private JPanel panelPrincipal;
	private JPanel panelInicial;
	private JButton btOrganizador;
	private JButton btCorredor;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblAcceso;
	private JPanel pnBase;
	private boolean organizador;

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
		organizador=false;
		base = new Base();
		base.getBaseInscripciones().actualizarDorsales();
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 396);
		contentPane = new JPanel();
		setLocationRelativeTo(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnBase());
		
	}

	private JLabel getLblAplicacion() {
		if (lblAplicacion == null) {
			lblAplicacion = new JLabel("Bienvenido");
			lblAplicacion.setFont(new Font("Impact", Font.BOLD, 66));
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
		VentanaCarreras vcarrera = new VentanaCarreras(this, organizador);
		vcarrera.setLocationRelativeTo(null);		;
		vcarrera.setVisible(true);
	}
	
	public Base getBase(){
		return base;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnCrearCarrera());
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
		vu.setModal(true);
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
			btnAsignarDorsal.setVisible(false);
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
	private JButton getBtnCrearCarrera() {
		if (btnCrearCarrera == null) {
			btnCrearCarrera = new JButton("Crear Carrera");
			btnCrearCarrera.setVisible(false);
			btnCrearCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaCrearCarrera();
				}
			});
		}
		return btnCrearCarrera;
	}


	private void mostrarVentanaCrearCarrera(){
		VentanaCrearCarrera vc = new VentanaCrearCarrera(this);
		vc.setLocationRelativeTo(this);
		vc.setVisible(true);
	}
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new BorderLayout(0, 0));
			panelPrincipal.add(getLblAplicacion());
			panelPrincipal.add(getPanel(), BorderLayout.SOUTH);
		}
		return panelPrincipal;
	}
	private JPanel getPanelInicial() {
		if (panelInicial == null) {
			panelInicial = new JPanel();
			panelInicial.setLayout(new BorderLayout(0, 0));
			panelInicial.add(getPanel_2(), BorderLayout.CENTER);
			panelInicial.add(getPanel_1(), BorderLayout.SOUTH);
		}
		return panelInicial;
	}
	private JButton getBtOrganizador() {
		if (btOrganizador == null) {
			btOrganizador = new JButton("Organizador");
			btOrganizador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accesoOrganizador();
					((CardLayout)pnBase.getLayout()).show(pnBase, "panelPrincipal");
				}
			});
		}
		return btOrganizador;
	}
	private JButton getBtCorredor() {
		if (btCorredor == null) {
			btCorredor = new JButton("Corredor");
			btCorredor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout)pnBase.getLayout()).show(pnBase, "panelPrincipal");
				}
			});
		}
		return btCorredor;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Bienvenido");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		}
		return lblNewLabel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 2, 0, 0));
			panel_1.add(getBtOrganizador());
			panel_1.add(getBtCorredor());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(2, 1, 0, 0));
			panel_2.add(getLblNewLabel());
			panel_2.add(getLblAcceso());
		}
		return panel_2;
	}
	private JLabel getLblAcceso() {
		if (lblAcceso == null) {
			lblAcceso = new JLabel("\u00BFC\u00F3mo desea acceder?");
			lblAcceso.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAcceso;
	}
	private JPanel getPnBase() {
		if (pnBase == null) {
			pnBase = new JPanel();
			pnBase.setLayout(new CardLayout(0, 0));
			pnBase.add(getPanelInicial(), "panelInicial");
			pnBase.add(getPanelPrincipal(), "panelPrincipal");
			((CardLayout)pnBase.getLayout()).show(pnBase, "panelInicial");
		}
		return pnBase;
	}
	
	
	private void accesoOrganizador(){
		organizador=true;
		btnCrearCarrera.setVisible(true);
		btnAsignarDorsal.setVisible(true);
		btnUsuario.setVisible(false);
	}
}
