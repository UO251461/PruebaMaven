package igu;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;
import logica.Carrera;
import logica.GestorExtractos;

import logica.Incidencia;

import logica.TratarRegistroTiempo;
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
import java.util.ArrayList;
import java.util.Date;
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
	private JButton btnCederDorsal;
	private JButton btnAadirTiempos;
	private JButton btnCancelarInscripcipn;

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
				public void mousePressed(MouseEvent arg0) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					if(vp.getBase().getBaseCarrera().getCarreraSeleccionada() != null) {
						btnSiguiente.setEnabled(true);
						btnInfoCarrera.setEnabled(true);
						btnInscripcionesClub.setEnabled(true);
						btnMostrarEstadoInscripciones.setEnabled(true);
						btnGestionarExtractos.setEnabled(true);
						btnAsginarDorsales.setEnabled(true);
						btnClasificacin.setEnabled(true);
						btnCederDorsal.setEnabled(true);
						btnAadirTiempos.setEnabled(true);
						btnCancelarInscripcipn.setEnabled(true);
					}
				}
			});

		}
		return listCarreras;
	}
	
	private boolean habilitarRegistro() {
		java.util.Date fecha = new Date();
		Date fechaComp = vp.getBase().getBaseCarrera().getCarreraSeleccionada().getFechaCompeticion();
		Long tiempoF = fecha.getTime();
		Long tiempoC = fechaComp.getTime();
		if(tiempoF >= tiempoC) {
			return true;
		}
		return false;
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
			panelBotonesCarrera.add(getBtnCederDorsal());
			panelBotonesCarrera.add(getBtnCancelarInscripcipn());
			panelBotonesCarrera.add(getBtnMostrarEstadoInscripciones());
			panelBotonesCarrera.add(getBtnGestionarExtractos());
			panelBotonesCarrera.add(getBtnClasificacin());
			panelBotonesCarrera.add(getBtnAsginarDorsales());
			panelBotonesCarrera.add(getBtnAadirTiempos());
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
		
		ArrayList<Incidencia> incidencias = gestor.getIncidencias();		
		if(incidencias.size()>0)	
			vp.getBase().getBaseInscripciones().gestionarIncidenciasActuales(incidencias.get(0).getIdCompeti(), incidencias.get(0).getOrganizador());
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
					if(listCarreras.getSelectedValue().getFechaCompeticion().compareTo( new Date())<0)
						mostrarVentanaConfiguracionClasificacion(listCarreras.getSelectedValue());
					else
						JOptionPane.showMessageDialog(null, "No se pueden generar las Clasificaciones\nLa carrera aún no ha finalizado");
				}
			});
			btnClasificacin.setVisible(organizador);
			btnClasificacin.setEnabled(false);
		}
		return btnClasificacin;
	}
	
	private void mostrarVentanaConfiguracionClasificacion(Carrera competicion){
		VentanaConfigurarClasificacion vc = new VentanaConfigurarClasificacion(competicion);
		vc.setLocationRelativeTo(this);
		vc.setModal(true);
		vc.setVisible(true);
	}

	private JButton getBtnCederDorsal() {
		if (btnCederDorsal == null) {
			btnCederDorsal = new JButton("Ceder dorsal");
			btnCederDorsal.setEnabled(false);
			btnCederDorsal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					crearVentanaCederDorsal();
				}
			});
		}
		return btnCederDorsal;
	}
	/**
	 * Crea una ventana ceder dorsal (Samuel)
	 */
	private void crearVentanaCederDorsal(){
		VentanaCederDorsal vce = new VentanaCederDorsal(this);
		vce.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		vce.setVisible(true);
	}
	private JButton getBtnAadirTiempos() {
		if (btnAadirTiempos == null) {
			btnAadirTiempos = new JButton("A\u00F1adir Tiempos");
			btnAadirTiempos.setEnabled(false);
			btnAadirTiempos.setVisible(organizador);
			btnAadirTiempos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					if(habilitarRegistro())
						gestionarRegistroTiempo();
					else {
						JOptionPane.showMessageDialog(null, "La competicion aun no ha acabado y no hay registro de tiempo");
					}
				}
			});
		}
		return btnAadirTiempos;
	}
	
	private void gestionarRegistroTiempo() {
		File fichero = new File("documentos/registrotiempo" + vp.getBase().getBaseCarrera().getCarreraSeleccionada().getIdcarrera()+"-" + 
				vp.getBase().getBaseCarrera().getCarreraSeleccionada().getOrganizador().getIdorganizador()+".txt");
		TratarRegistroTiempo trt = new TratarRegistroTiempo(this);
		trt.leerFichero(fichero);
	}
	private JButton getBtnCancelarInscripcipn() {
		if (btnCancelarInscripcipn == null) {
			btnCancelarInscripcipn = new JButton("Cancelar inscripci\u00F3n");
			btnCancelarInscripcipn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					vp.getBase().getBaseCarrera().setCarreraSeleccionada(listCarreras.getSelectedValue());
					mostrarCancelar();
				}

				
			});
			btnCancelarInscripcipn.setEnabled(false);
		}
		return btnCancelarInscripcipn;
	}
	private void mostrarCancelar() {
		VentanaCancelacion vc = new VentanaCancelacion(this);
		vc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		vc.setVisible(true);
		
	}
}

 

