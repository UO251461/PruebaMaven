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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaClasificación extends JFrame {

	private ModeloNoEditable modeloTabla;
	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable tablaClasificacion;
	private JLabel lbCategoria;
	private JRadioButton rbAbsoluto;
	private JRadioButton rbMasculino;
	private JRadioButton rbFemenino;
	private Base base;
	private String competicion;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClasificación frame = new VentanaClasificación();
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
	public VentanaClasificación() {
		base=new Base();
		base.inicializar();
		competicion="6";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLbCategoria());
			panel.add(getRbAbsoluto());
			panel.add(getRbMasculino());
			panel.add(getRbFemenino());
		}
		return panel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaClasificacion());
		}
		return scrollPane;
	}
	private JTable getTablaClasificacion() {
		if (tablaClasificacion == null) {
			tablaClasificacion = new JTable();
			String[] nombreColumnas = {"posicion","DNI","Dorsal","Tiempo","Categoria"};
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
			tablaClasificacion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//programar el doble click
					if(arg0.getClickCount()==2){
						//si se hace doble click, que muestre la clasificacion de esa carrera
					}
				}
			});
			añadirFilas(); //aqui o antes de pasarle el modelo
		}
		return tablaClasificacion;
	}
	private JLabel getLbCategoria() {
		if (lbCategoria == null) {
			lbCategoria = new JLabel("Categorias:");
		}
		return lbCategoria;
	}
	private JRadioButton getRbAbsoluto() {
		if (rbAbsoluto == null) {
			rbAbsoluto = new JRadioButton("Absoluto");
			rbAbsoluto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actualizarTabla();
				}
			});
			buttonGroup.add(rbAbsoluto);
			rbAbsoluto.setSelected(true);
		}
		return rbAbsoluto;
	}
	private JRadioButton getRbMasculino() {
		if (rbMasculino == null) {
			rbMasculino = new JRadioButton("Masculino");
			rbMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actualizarTabla();
				}
			});
			buttonGroup.add(rbMasculino);
		}
		return rbMasculino;
	}
	private JRadioButton getRbFemenino() {
		if (rbFemenino == null) {
			rbFemenino = new JRadioButton("Femenino");
			rbFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actualizarTabla();
				}
			});
			buttonGroup.add(rbFemenino);
		}
		return rbFemenino;
	}
	
	public void añadirFilas(){
		ArrayList<Inscripcion> datos;
		Object[] nuevaFila = new Object[5];
		if(rbAbsoluto.isSelected())
			datos = base.getBaseInscripciones().generarClasificaciones(0, competicion);
		else if(rbMasculino.isSelected())
			datos = base.getBaseInscripciones().generarClasificaciones(1, competicion);
		else
			datos = base.getBaseInscripciones().generarClasificaciones(2, competicion);
		int pos=1;
		for(Inscripcion i:datos){
			nuevaFila[0]=pos++;
			nuevaFila[1]= i.getDni();
			nuevaFila[2]= i.getDorsal();
			if(i.getTiempo()==0)
				nuevaFila[3]="---";
			else
				nuevaFila[3]=i.getTiempo();
			nuevaFila[4]=i.getCategoria();
			modeloTabla.addRow(nuevaFila);
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
}
