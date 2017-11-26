package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;
import logica.Inscripcion;
import logica.ModeloNoEditable;

import javax.swing.JScrollPane;
import javax.swing.JDialog;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JLabel;

public class VentanaClasificacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModeloNoEditable modeloTabla;
	private JPanel contentPane;
	private Base base;
	private String competicion;
	private JScrollPane scrollPane;
	private JPanel panelCentral;
	private JPanel panelTabla;
	private JPanel pnNombre;
	private JScrollPane scroll;
	private JTable tablaClasificacion;
	private JLabel lblNombreCategoria;
	private JLabel lblNombreCarrera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClasificacion frame = new VentanaClasificacion("6");
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
	public VentanaClasificacion(String competicion) {
		setTitle("Clasificacion");
		base=new Base();
		base.inicializar();
		this.competicion=competicion;
		setBounds(100, 100, 691, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane_1(), BorderLayout.CENTER);
		contentPane.add(getLblNombreCarrera(), BorderLayout.NORTH);
	}
	
	public void añadirFilas(){
		ArrayList<Inscripcion> datos;
		Object[] nuevaFila = new Object[6];
		if(false)
			datos = base.getBaseInscripciones().generarClasificaciones(0, competicion);
		else if(false)
			datos = base.getBaseInscripciones().generarClasificaciones(1, competicion);
		else
			datos = base.getBaseInscripciones().generarClasificaciones(2, competicion);
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
	
	
	private  void actualizarTabla(){
		//vacio el modelo de la tabla
		modeloTabla.getDataVector().clear();
		//lo vuelvo a rellenar
		añadirFilas();
		//necesario para repintar la tabla(especialmente cuando se queda vacia)
		modeloTabla.fireTableDataChanged();
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
			panelCentral.add(getPanel_1_1());
		}
		return panelCentral;
	}
	private JPanel getPanel_1_1() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setLayout(new BorderLayout(0, 0));
			panelTabla.add(getPanel_4(), BorderLayout.NORTH);
			panelTabla.add(getScrollPane_1_1());
		}
		return panelTabla;
	}
	private JPanel getPanel_4() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			pnNombre.setLayout(new GridLayout(0, 1, 0, 0));
			pnNombre.add(getLblNombreCategoria());
		}
		return pnNombre;
	}
	private JScrollPane getScrollPane_1_1() {
		if (scroll == null) {
			scroll = new JScrollPane();
			scroll.setViewportView(getTable());
			
		}
		return scroll;
	}
	private JTable getTable() {
		if (tablaClasificacion == null) {
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
			añadirFilas();
		}
		return tablaClasificacion;
	}
	private JLabel getLblNombreCategoria() {
		if (lblNombreCategoria == null) {
			lblNombreCategoria = new JLabel("Nombre Categoria");
		}
		return lblNombreCategoria;
	}
	private JLabel getLblNombreCarrera() {
		if (lblNombreCarrera == null) {
			lblNombreCarrera = new JLabel("Nombre Carrera");
		}
		return lblNombreCarrera;
	}
}
