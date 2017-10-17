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
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTextArea textCarreras;
	
	private VentanaPrincipal vp;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaCarreras frame = new VentanaCarreras();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
			panelScroll.setLayout(new GridLayout(0, 2, 0, 0));
			panelScroll.add(getListCarreras());
			panelScroll.add(getTextCarreras());
		}
		return panelScroll;
	}

	private JList<Carrera> getListCarreras() {
		if (listCarreras == null) {
			listCarreras = new JList<Carrera>();
			listCarreras = new JList<Carrera>(modeloCarrera);
			listCarreras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					btnSiguiente.setEnabled(true);
					textCarreras.setText(mostrarCarrera(listCarreras.getSelectedValue()));
				}
			});

		}
		return listCarreras;
	}

	private String mostrarCarrera(Carrera carrera) {
		String cadena = "Tipo: " + carrera.getTipo() + "\nDistancia: " + carrera.getDistancia() + "\nPrecio: "
				+ carrera.getPrecio() + "\nFecha Competicion: " + carrera.getFechaCompeticion()
				+ "\nFecha Final Inscripcion: " + carrera.getFechaFinalizaInscripcion();
		return cadena;
	}

	private JTextArea getTextCarreras() {
		if (textCarreras == null) {
			textCarreras = new JTextArea();
			textCarreras.setBackground(Color.WHITE);
			textCarreras.setLineWrap(true);
		}
		return textCarreras;
	}

	private void cargarModelo() {
		modeloCarrera.clear();
		for (int i = 0; i < vp.getBase().getBaseCarrera().getCarreras().size(); i++) {
			modeloCarrera.addElement(vp.getBase().getBaseCarrera().getCarreras().get(i));
		}
	}
	
	public Base getBase(){
		return vp.getBase();
	}
}
