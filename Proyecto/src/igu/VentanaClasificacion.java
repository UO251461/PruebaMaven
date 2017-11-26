package igu;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;
import logica.Carrera;
import logica.Categoria;
import logica.Inscripcion;
import logica.ModeloNoEditable;

import javax.swing.JScrollPane;
import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaClasificacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModeloNoEditable modeloTabla;
	private JPanel contentPane;
	private Base base;
	private Carrera carrera;
	private JScrollPane scrollPane;
	private JPanel panelCentral;
	private JLabel lblNombreCarrera;
	private JButton btnGenerarFichero;
	private ArrayList<String> categorias;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaClasificacion frame = new VentanaClasificacion("6");
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
	public VentanaClasificacion(Carrera carrera, ArrayList<String> categorias) {
		setTitle("Clasificacion");
		base=new Base();
		base.inicializar();
		this.carrera=carrera;
		this.categorias=categorias;
		
		setBounds(100, 100, 1042, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane_1(), BorderLayout.CENTER);
		contentPane.add(getLblNombreCarrera(), BorderLayout.NORTH);
		contentPane.add(getBtnGenerarFichero(), BorderLayout.SOUTH);
		generarTablas();
	}
	
	public void añadirFilas(String categoria){
		ArrayList<Inscripcion> datos;
		Object[] nuevaFila = new Object[6];
//		if(false)
//			datos = base.getBaseInscripciones().generarClasificaciones(0, carrera);
//		else if(false)
//			datos = base.getBaseInscripciones().generarClasificaciones(1, carrera);
//		else
			datos = base.getBaseInscripciones().generarClasificaciones(2, carrera.getIdcarrera(), categoria);
		int pos=1;
		for(Inscripcion i:datos){
			if(true){
			nuevaFila[0]=pos++;
			nuevaFila[1]= i.getCorredor().getDni();
			nuevaFila[2]= i.getDorsal();
			if(i.getTiempo()==0)
				nuevaFila[3]="---";
			else
				nuevaFila[3]=i.getTiempo();
			nuevaFila[4]=i.getCorredor().getSexo();
			nuevaFila[5]=i.getCategoria();
			modeloTabla.addRow(nuevaFila);
			}
		}
	}
	
	
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getPanel_3());
		}
		return scrollPane;
	}
	private JPanel getPanel_3() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return panelCentral;
	}
	private JLabel getLblNombreCarrera() {
		if (lblNombreCarrera == null) {
			lblNombreCarrera = new JLabel(carrera.getNombre());
		}
		return lblNombreCarrera;
	}
	private JButton getBtnGenerarFichero() {
		if (btnGenerarFichero == null) {
			btnGenerarFichero = new JButton("Generar Fichero");
		}
		return btnGenerarFichero;
	}
	
	
	private void generarTablas(){
		panelCentral.removeAll();
		if(categorias.contains("Todos")){
			categorias=new ArrayList<String>();
			categorias.add("Generales");
			ArrayList<Categoria> cat = carrera.getCategorias();
			for(Categoria c:cat)
				if(!categorias.contains(c.getCategoria()))
					categorias.add(c.getCategoria());
		}
		for(String categoria: categorias){			
			panelCentral.add(crearPanelTabla(categoria));
		}
	}

	private JPanel crearPanelTabla(String categoria) {
		JPanel pnTabla = new JPanel();
		pnTabla.setLayout(new BorderLayout(0, 0));
		pnTabla.add(crearPanelTitulo(categoria), BorderLayout.NORTH);
		pnTabla.add(crearScroll(categoria), BorderLayout.CENTER);
		return pnTabla;
	}

	private JScrollPane crearScroll(String categoria) {
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(crearTabla(categoria));
		return scroll;
	}

	private JTable crearTabla(String categoria) {
		JTable tablaClasificacion;
		ModeloNoEditable modeloTabla;
		if(categoria.equals("Generales")){
		tablaClasificacion = new JTable();
		String[] nombreColumnas = {"Posicion","dni","Dorsal", "Tiempo","Sexo","Categoria"};
		modeloTabla = new ModeloNoEditable(nombreColumnas,0); //al pasarle bnombre de columnas creara el numero necesario... filas 0 ya que
		//las crearemos en tiempo de ejecucion
		tablaClasificacion.setModel(modeloTabla);
		//Aplica el renderer que marca la fila seleccionada en rojo y negrita
		//tablaInscripciones.setDefaultRenderer(Object.class, new RendererSubstance());
		///subo el alto de la fila
		tablaClasificacion.setRowHeight(30);
		//modifico el ancho de la columna 0
		tablaClasificacion.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaClasificacion.getTableHeader().setReorderingAllowed(false);
		añadirFilas(categoria);		
	}else{
		tablaClasificacion = new JTable();
		String[] nombreColumnas = {"Posicion","dni","Dorsal", "Tiempo","Sexo"};
		modeloTabla = new ModeloNoEditable(nombreColumnas,0); //al pasarle bnombre de columnas creara el numero necesario... filas 0 ya que
		//las crearemos en tiempo de ejecucion
		tablaClasificacion.setModel(modeloTabla);
		//Aplica el renderer que marca la fila seleccionada en rojo y negrita
		//tablaInscripciones.setDefaultRenderer(Object.class, new RendererSubstance());
		///subo el alto de la fila
		tablaClasificacion.setRowHeight(30);
		//modifico el ancho de la columna 0
		tablaClasificacion.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaClasificacion.getTableHeader().setReorderingAllowed(false);
		añadirFilas(categoria);
	}
		return null;
	}

	private JPanel crearPanelTitulo(String categoria) {
		JPanel pnNombre = new JPanel();
		pnNombre.setLayout(new GridLayout(0, 1, 0, 0));
		pnNombre.add(new JLabel(categoria));
		return pnNombre;
	}
}
