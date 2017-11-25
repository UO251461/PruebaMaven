package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;
import logica.Inscripcion;
import logica.ModeloNoEditable;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaUsuario extends JDialog {

	private ModeloNoEditable modeloTabla;//declaro el modelo de la tabla
	private JPanel contentPane;
	private JTable tablaInscripciones;
	private static Base base;
	private String atleta;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btPagar;
	private ArrayList<Inscripcion> datos;
	private JPanel panel_1;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaUsuario frame = new VentanaUsuario();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaUsuario(String atleta) {
		setTitle("Ventana Usuario");
		base=new Base();
		base.inicializar();
		this.atleta=atleta;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane());
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		contentPane.add(getPanel_1(), BorderLayout.NORTH);
	}

	private JTable getTablaInscripciones() {
		if (tablaInscripciones == null) {
			tablaInscripciones = new JTable();
			String[] nombreColumnas = {"Competicion","Estado","Fecha", "Dorsal","Tiempo","Comentario"};
			modeloTabla = new ModeloNoEditable(nombreColumnas,0); //al pasarle bnombre de columnas creara el numero necesario... filas 0 ya que
			//las crearemos en tiempo de ejecucion
			tablaInscripciones.setModel(modeloTabla);
			//Aplica el renderer que marca la fila seleccionada en rojo y negrita
			//tablaInscripciones.setDefaultRenderer(Object.class, new RendererSubstance());
			///subo el alto de la fila
			tablaInscripciones.setRowHeight(30);
			//modifico el ancho de la columna 0
			tablaInscripciones.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
			tablaInscripciones.getTableHeader().setReorderingAllowed(false);
			tablaInscripciones.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//programar el doble click
					if(tablaInscripciones.getValueAt(tablaInscripciones.getSelectedRow(), 1).equals("PRE-INSCRITO"))
						btPagar.setEnabled(true);
					else 
						btPagar.setEnabled(false);
					if(arg0.getClickCount()==2 && tablaInscripciones.getValueAt(tablaInscripciones.getSelectedRow(), 1).equals("INSCRITO")){
						//si se hace doble click, que muestre la clasificacion de esa carrera
						mostrarVentanaClasificacion((String)tablaInscripciones.getValueAt(tablaInscripciones.getSelectedRow(), 0));
					}
				}
			});
			añadirFilas(); //aqui o antes de pasarle el modelo
			
		}
		
		return tablaInscripciones;
	}
	
	
	
	
	
	public void añadirFilas(){
		Object[] nuevaFila = new Object[6];
		datos = base.getBaseInscripciones().getDatosAtleta(atleta);
		for(Inscripcion i:datos){
			nuevaFila[0]= i.getCarrera().getIdcarrera();
			//nuevaFila[]= i.getId_organizador();
			nuevaFila[1]= i.getEstado();
			nuevaFila[2]= i.getFecha();
			if(i.getDorsal()<=0)
				nuevaFila[3]="---";
			else
				nuevaFila[3]=i.getDorsal();
			if(i.getTiempo()==0)
				nuevaFila[4]="---";
			else
				nuevaFila[4]=i.getTiempo();
			
			nuevaFila[5]=i.getComentario();
			
			modeloTabla.addRow(nuevaFila);
		}
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaInscripciones());
		}
		return scrollPane;
	}
	
	private void mostrarVentanaClasificacion(String competicion){
		VentanaClasificacion vc = new VentanaClasificacion(competicion);
		vc.setLocationRelativeTo(this);
		vc.setModal(true);
		vc.setVisible(true);
	}
	
	private void mostrarVentanaPago(Inscripcion inscripcion){
		VentanaMetodoPago vp = new VentanaMetodoPago(inscripcion);
		vp.setLocationRelativeTo(this);
		vp.setModal(true);
		vp.setVisible(true);
		this.dispose();
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtPagar());
		}
		return panel;
	}
	private JButton getBtPagar() {
		if (btPagar == null) {
			btPagar = new JButton("Pagar");
			btPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//all hacer click lleva al usuario a la ventana para pagar
					mostrarVentanaPago(datos.get(tablaInscripciones.getSelectedRow()));
					btPagar.setEnabled(false);
				}
			});
			btPagar.setEnabled(false);
		}
		return btPagar;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}
	
	public Base getBase() {
		return base;
	}
}



