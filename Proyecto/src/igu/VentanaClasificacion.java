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
import logica.Carrera;
import logica.Inscripcion;
import logica.ModeloNoEditable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class VentanaClasificacion extends JDialog {

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
	private JPanel panel_1;
	private JComboBox<String> comboBox;
	private JPanel panel_2;
	private String[] categorias={"Generales","Menor de edad","Senior","Veterano A","Veterano B"};

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
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			panel.add(getPanel_2());
			panel.add(getPanel_1());
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
			String[] nombreColumnas = {"Posicion","DNI","Dorsal","Tiempo","Sexo","Categoria"};
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
			lbCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
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
		Object[] nuevaFila = new Object[6];
		if(rbAbsoluto.isSelected())
			datos = base.getBaseInscripciones().generarClasificaciones(0, competicion);
		else if(rbMasculino.isSelected())
			datos = base.getBaseInscripciones().generarClasificaciones(1, competicion);
		else
			datos = base.getBaseInscripciones().generarClasificaciones(2, competicion);
		int pos=1;
		for(Inscripcion i:datos){
			if(comboBox.getSelectedIndex()==0 || i.getCategoria().equals(categorias[comboBox.getSelectedIndex()])){
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
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getRbAbsoluto());
			panel_1.add(getRbMasculino());
			panel_1.add(getRbFemenino());
		}
		return panel_1;
	}
	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					actualizarTabla();
				}
			});		
			
			comboBox.setModel(new DefaultComboBoxModel<String>(categorias));
			comboBox.setSelectedIndex(0);
		}
		return comboBox;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(0, 2, 0, 0));
			panel_2.add(getLbCategoria());
			panel_2.add(getComboBox());
		}
		return panel_2;
	}
}
