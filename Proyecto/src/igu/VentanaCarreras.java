package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;
import logica.Carrera;

import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class VentanaCarreras extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelNorte;
	private JLabel lblCarreras;
	private JPanel panelSur;
	private JButton btnAtras;
	private JButton btnSiguiente;

	
	private JScrollPane scrollCentro;
	private JPanel panelScroll;
	private JList<Carrera> listCarreras;
	private DefaultListModel<Carrera> modeloCarrera = new DefaultListModel<Carrera>();
	
	private VentanaPrincipal vp;
	private JPanel panelBotonesCarrera;
	private JButton btnInfoCarrera;

	/**
	 * Create the frame.
	 */
	public VentanaCarreras(VentanaPrincipal vp) {
		
		this.vp = vp;
		setTitle("Carreras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelNorte(), BorderLayout.NORTH);
		contentPane.add(getPanelSur(), BorderLayout.SOUTH);
		contentPane.add(getScrollCentro(), BorderLayout.CENTER);

		cargarModelo();

	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.add(getLblCarreras());
		}
		return panelNorte;
	}

	private JLabel getLblCarreras() {
		if (lblCarreras == null) {
			lblCarreras = new JLabel("Carreras");
		}
		return lblCarreras;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			panelSur.setLayout(new GridLayout(1, 0, 0, 0));
			panelSur.add(getBtnAtras());
			panelSur.add(getBtnSiguiente());
		}
		return panelSur;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					vp.setVisible(true);
				}
			});
			btnAtras.setMnemonic('A');
		}
		return btnAtras;
	}

	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					mostrarInscripcion();
					
				}
			});
			btnSiguiente.setMnemonic('S');
		}
		return btnSiguiente;
	}
	
	private void mostrarInscripcion(){
		VentanaInscripcion vinscripcion = new VentanaInscripcion(this);
		vinscripcion.setLocationRelativeTo(null);		;
		vinscripcion.setVisible(true);
		this.setVisible(false);
	}

	private JScrollPane getScrollCentro() {
		if (scrollCentro == null) {
			scrollCentro = new JScrollPane();
			scrollCentro.setViewportView(getPanelScroll());
		}
		return scrollCentro;
	}

	private JPanel getPanelScroll() {
		if (panelScroll == null) {
			panelScroll = new JPanel();
			panelScroll.setLayout(new BorderLayout(0, 0));
			panelScroll.add(getListCarreras());
			panelScroll.add(getPanelBotonesCarrera(), BorderLayout.EAST);
		}
		return panelScroll;
	}

	private JList<Carrera> getListCarreras() {
		if (listCarreras == null) {
			listCarreras = new JList<Carrera>();
			listCarreras.setValueIsAdjusting(true);
			listCarreras = new JList<Carrera>(modeloCarrera);
			listCarreras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					btnSiguiente.setEnabled(true);
					btnInfoCarrera.setEnabled(true);
				}
			});

		}
		return listCarreras;
	}



	private void cargarModelo() {
		modeloCarrera.clear();
		for (int i = 0; i < vp.getBase().getBaseCarrera().getCarreras().size(); i++) {
			if(vp.getBase().getBaseCarrera().getCarreras().get(i).getPlazasDisponibles()>0)
				modeloCarrera.addElement(vp.getBase().getBaseCarrera().getCarreras().get(i));
		}
	}
	
	public Base getBase(){
		return vp.getBase();
	}
	private JPanel getPanelBotonesCarrera() {
		if (panelBotonesCarrera == null) {
			panelBotonesCarrera = new JPanel();
			panelBotonesCarrera.setLayout(new GridLayout(1, 0, 0, 0));
			panelBotonesCarrera.add(getBtnInfoCarrera());
		}
		return panelBotonesCarrera;
	}
	private JButton getBtnInfoCarrera() {
		if (btnInfoCarrera == null) {
			btnInfoCarrera = new JButton("Mostrar Informacion Carrera");
			btnInfoCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Prueba para mostrar nombre de carrera seleccionada: " + listCarreras.getSelectedValue().getNombre(), "Informacion de la carrera " 
							+ listCarreras.getSelectedValue().getNombre(),1);
					
				}
			});
			btnInfoCarrera.setEnabled(false);
		}
		return btnInfoCarrera;
	}
}
