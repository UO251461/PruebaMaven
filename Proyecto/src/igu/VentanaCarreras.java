package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import database.Base;


import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;

public class VentanaCarreras extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelNorte;
	private JLabel lblCarreras;
	private JPanel panelSur;
	private JPanel panelCentro;
	private JButton btnAtras;
	private JButton btnSiguiente;
	
	private Base base;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCarreras frame = new VentanaCarreras();
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
	public VentanaCarreras() {
		
		base = new Base();
		
		setTitle("Carreras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelNorte(), BorderLayout.NORTH);
		contentPane.add(getPanelSur(), BorderLayout.SOUTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
		
		añadirPanelesCarreras();
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
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new GridLayout(1, 0, 0, 0));
			
		}
		return panelCentro;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
		}
		return btnAtras;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
		}
		return btnSiguiente;
	}
	
	private void añadirPanelesCarreras(){
		for(int i=0;i<base.getBaseCarrera().getCarreras().size();i++){
			panelCentro.add(crearPanelCarrera(i));
		}
		
	}
	
	private JLabel crearJLabel(String text) {
		JLabel label = new JLabel("");
		label.setBackground(Color.BLACK);
		label.setText(text);
		return label;
	}
	
	private JPanel crearPanelCarrera(int pos){
		JPanel panelCarrera = new JPanel();
		panelCarrera.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCarrera.setLayout(new BorderLayout(0, 0));
		
		panelCarrera.add(crearJLabel(base.getBaseCarrera().getCarreras().get(pos).getNombre()), BorderLayout.NORTH);
		
		JPanel panelInfo = crearPanelInfo(pos);
		panelCarrera.add(panelInfo,BorderLayout.CENTER);
				
		JPanel panelFecha = crearPanelFecha(pos);
		panelCarrera.add(panelFecha, BorderLayout.SOUTH);
		return panelCarrera;
	}
	
	private JPanel crearPanelInfo(int pos) {
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new LineBorder(new Color(0, 0, 0)));	
		panelInfo.add(crearJLabel(base.getBaseCarrera().getCarreras().get(pos).getTipo()));
		panelInfo.add(crearJLabel(String.valueOf(base.getBaseCarrera().getCarreras().get(pos).getDistancia())));
		panelInfo.add(crearJLabel(String.valueOf(base.getBaseCarrera().getCarreras().get(pos).getPrecio())));
		return panelInfo;
	}
	
	@SuppressWarnings("deprecation")
	private JPanel crearPanelFecha(int pos) {
		JPanel panelFecha = new JPanel();
		panelFecha.setBorder(new LineBorder(new Color(0, 0, 0)));	
		panelFecha.add(crearJLabel(base.getBaseCarrera().getCarreras().get(pos).getFechaCompeticion().toLocaleString()));
		panelFecha.add(crearJLabel(base.getBaseCarrera().getCarreras().get(pos).getFechaFinalizaInscripcion().toLocaleString()));
		return panelFecha;
	}

	
}
