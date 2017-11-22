package igu;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;
import logica.Carrera;
import logica.GestorExtractos;

import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCarreras extends JDialog {

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
	private JButton btnInscripcionesClub;
	private JButton btnMostrarEstadoInscripciones;
	private boolean organizador;
	private JButton btnGestionarExtractos;
	private JButton btnAsginarDorsales;
	private JButton btnClasificacin;

	/**
	 * Create the frame.
	 */
	public VentanaCarreras(VentanaPrincipal vp, boolean organizador) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		this.organizador=organizador;
		this.vp = vp;
		setTitle("Carreras");
		setBounds(100, 100, 987, 481);
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
			btnSiguiente = new JButton("Inscribirse");
			btnSiguiente.setEnabled(false);
			btnSiguiente.setVisible(!organizador);
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
		dispose();
		VentanaInscripcion vinscripcion = new VentanaInscripcion(this);
		vinscripcion.setLocationRelativeTo(this);		
		vinscripcion.setVisible(true);
		
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
					if(vp.getBase().getBaseCarrera().getCarreraSeleccionada() == null) {
					btnSiguiente.setEnabled(true);
					btnInfoCarrera.setEnabled(true);
					btnInscripcionesClub.setEnabled(true);
					btnMostrarEstadoInscripciones.setEnabled(true);
					btnGestionarExtractos.setEnabled(true);
					btnAsginarDorsales.setEnabled(true);}
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
			panelBotonesCarrera.setLayout(new GridLayout(10, 1, 0, 0));
			panelBotonesCarrera.add(getBtnInfoCarrera());
			panelBotonesCarrera.add(getBtnInscripcionesClub());
			panelBotonesCarrera.add(getBtnMostrarEstadoInscripciones());
			panelBotonesCarrera.add(getBtnGestionarExtractos());
			panelBotonesCarrera.add(getBtnClasificacin());
			panelBotonesCarrera.add(getBtnAsginarDorsales());
		}
		return panelBotonesCarrera;
	}
	
	private JButton getBtnInfoCarrera() {
		if (btnInfoCarrera == null) {
			btnInfoCarrera = new JButton("Mostrar Informacion Carrera");
			btnInfoCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, listCarreras.getSelectedValue().infoCarrera(),listCarreras.getSelectedValue().getNombre(), 1);
					
				}
			});
			btnInfoCarrera.setEnabled(false);
		}
		return btnInfoCarrera;
	}
	private JButton getBtnInscripcionesClub() {
		if (btnInscripcionesClub == null) {
			btnInscripcionesClub = new JButton("Inscripcion de Club");
			btnInscripcionesClub.setEnabled(false);
			btnInscripcionesClub.setVisible(!organizador);
			btnInscripcionesClub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					mostrarInscripcionClub();
				}
			});
		}
		return btnInscripcionesClub;
	}
	
	private void mostrarInscripcionClub() {
		dispose();
		Ventana_Inscripcion_Club vinscripcion = new Ventana_Inscripcion_Club(this);
		vinscripcion.setLocationRelativeTo(null);
		vinscripcion.setVisible(true);
	}
	private JButton getBtnMostrarEstadoInscripciones() {
		if (btnMostrarEstadoInscripciones == null) {
			btnMostrarEstadoInscripciones = new JButton("Mostrar estado inscripciones");
			btnMostrarEstadoInscripciones.setEnabled(false);
			btnMostrarEstadoInscripciones.setVisible(organizador);
			btnMostrarEstadoInscripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					mostrarCorredoresDeCompeticion();
				}
			});
		}
		return btnMostrarEstadoInscripciones;
	}
	
	private void mostrarCorredoresDeCompeticion() {
		dispose();
		VentanaCorredoresDeCompeticion vc = new VentanaCorredoresDeCompeticion(this);
		vc.setLocationRelativeTo(null);
		vc.setVisible(true);
	}
	private JButton getBtnGestionarExtractos() {
		if (btnGestionarExtractos == null) {
			btnGestionarExtractos = new JButton("Gestionar Extractos");
			btnGestionarExtractos.setEnabled(false);
			btnGestionarExtractos.setVisible(organizador);
			btnGestionarExtractos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					generarExtractos();
					vp.getBase().getBaseInscripciones().gestionarIncidenciasActuales();
				}
			});
		}
		return btnGestionarExtractos;
	}
	
	private void generarExtractos() {
		File fichero = new File("documentos/extracto" + vp.getBase().getBaseCarrera().getCarreraSeleccionada().getIdcarrera()+"-" + 
				vp.getBase().getBaseCarrera().getCarreraSeleccionada().getOrganizador().getIdorganizador()+".txt");
		GestorExtractos gestor = new GestorExtractos(this);
		gestor.leerFichero(fichero);
		vp.getBase().getBaseInscripciones().generarIncidencias(gestor);

	}
	private JButton getBtnAsginarDorsales() {
		if (btnAsginarDorsales == null) {
			btnAsginarDorsales = new JButton("Asginar Dorsales");
			btnAsginarDorsales.setEnabled(false);
			btnAsginarDorsales.setVisible(organizador);
			btnAsginarDorsales.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String idCarrera=listCarreras.getSelectedValue().getIdcarrera();
					String idOrganizador=listCarreras.getSelectedValue().getOrganizador().getIdorganizador();
					//int plazas=listCarreras.getSelectedValue().getPlazasDisponibles();
					if(vp.getBase().getBaseCarrera().getDosalesReservados(idCarrera, idOrganizador)==-1){
						String numero=JOptionPane.showInputDialog("Introduce el número de dorsales que desea reservar:");
						int dorsales=-1;
						if(numero!=null && !numero.isEmpty()){
							try{
							dorsales=Integer.parseInt(numero);
							if(dorsales<0)
								JOptionPane.showMessageDialog(null, "Datos incorretos.\nPor favor, vuelve a intentarlo");
							else{ 
								vp.getBase().getBaseInscripciones().actualizarDorsales(idCarrera, idOrganizador, dorsales);
								vp.getBase().getBaseCarrera().setDorsal(idCarrera, idOrganizador,dorsales);
							}
							}catch(NumberFormatException e){
								JOptionPane.showMessageDialog(null, "Datos incorretos.\nPor favor, vuelve a intentarlo");
								//throw new RuntimeException("No se ha introducido un número",e);
								
							}
						}
						
					else{					
						vp.getBase().getBaseInscripciones().actualizarDorsales(idCarrera, idOrganizador,-1);
					}
				}
				}
			});
		}
		return btnAsginarDorsales;
	}
	private JButton getBtnClasificacin() {
		if (btnClasificacin == null) {
			btnClasificacin = new JButton("Clasificaci\u00F3n");
			btnClasificacin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//mostrar la clasificacion
					mostrarVentanaClasificacion(listCarreras.getSelectedValue().getIdcarrera());
				}
			});
			btnClasificacin.setVisible(organizador);
			btnClasificacin.setEnabled(false);
		}
		return btnClasificacin;
	}
	
	private void mostrarVentanaClasificacion(String competicion){
		VentanaClasificacion vc = new VentanaClasificacion(competicion);
		vc.setLocationRelativeTo(this);
		vc.setModal(true);
		vc.setVisible(true);
	}
}
