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

public class VentanaUsuario extends JFrame {

	private ModeloNoEditable modeloTabla;//declaro el modelo de la tabla
	private JPanel contentPane;
	private JTable tablaInscripciones;
	private static Base base;
	private String atleta;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuario frame = new VentanaUsuario();
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
	public VentanaUsuario() {
		base=new Base();
		base.inicializar();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane());
	}

	private JTable getTablaInscripciones() {
		if (tablaInscripciones == null) {
			tablaInscripciones = new JTable();
			String[] nombreColumnas = {"IdCompeticion", "IdOrganizador","Estado","Fecha", "Dorsal","Tiempo","Categoria"};
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
					if(arg0.getClickCount()==2){
						//si se hace doble click, que muestre la clasificacion de esa carrera
					}
				}
			});
			añadirFilas(); //aqui o antes de pasarle el modelo
			
		}
		
		return tablaInscripciones;
	}
	
	
	
	
	
	public void añadirFilas(){
		Object[] nuevaFila = new Object[7];
		ArrayList<Inscripcion> datos = base.getBaseInscripciones().getDatosAtleta("123");
		for(Inscripcion i:datos){
			nuevaFila[0]= i.getId_competicion();
			nuevaFila[1]= i.getId_organizador();
			nuevaFila[2]= i.getEstado();
			nuevaFila[3]= i.getFecha();
			nuevaFila[4]=i.getDorsal();
			if(i.getTiempo()==0)
				nuevaFila[5]="---";
			else
				nuevaFila[5]=i.getTiempo();
			nuevaFila[6]=i.getCategoria();
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
}



