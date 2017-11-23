package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;
import logica.Carrera;
import logica.Inscripcion;
import logica.ModeloNoEditable;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCorredoresDeCompeticion extends JFrame {

	private JPanel contentPane;
	private JPanel pnlCarrera;
	private JPanel pnlBotones;
	private JScrollPane scrollPane;
	private JLabel lblCarrera;
	private JTextField txtCarrera;
	private JButton btnAtras;
	private Base base ;
	private Carrera carrera;
	private JTable tabla;
	private ModeloNoEditable modeloTabla;
	private VentanaCarreras vc;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					VentanaCorredoresDeCompeticion frame = new VentanaCorredoresDeCompeticion("6");
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
	public VentanaCorredoresDeCompeticion(VentanaCarreras competicion) {
		this.vc = competicion;
		this.carrera = competicion.getBase().getBaseCarrera().getCarreraSeleccionada();
		base = competicion.getBase();
		base.getBaseInscripciones().getInscripcionPorCompeticion(carrera.getIdcarrera());
		
		setTitle("Corredores Competicion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlCarrera(), BorderLayout.NORTH);
		contentPane.add(getPnlBotones(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);

		setTextCarrera(base.getBaseCarrera().getNombreCarrera(carrera.getIdcarrera(), carrera.getOrganizador().getIdorganizador()));
		
		actualizarTabla();
	}

	/*
	 * Metodo que cambia el area de texto y pone el de la carrera que recibe
	 */
	private void setTextCarrera(String carrera) {
		txtCarrera.setText(carrera);
	}

	private JPanel getPnlCarrera() {
		if (pnlCarrera == null) {
			pnlCarrera = new JPanel();
			pnlCarrera.setLayout(new GridLayout(0, 2, 0, 0));
			pnlCarrera.add(getLblCarrera());
			pnlCarrera.add(getTxtCarrera());
		}
		return pnlCarrera;
	}

	private JPanel getPnlBotones() {
		if (pnlBotones == null) {
			pnlBotones = new JPanel();
			pnlBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnlBotones.add(getBtnAtras());
		}
		return pnlBotones;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JLabel getLblCarrera() {
		if (lblCarrera == null) {
			lblCarrera = new JLabel("Carrera:");
			lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 24));
		}
		return lblCarrera;
	}

	private JTextField getTxtCarrera() {
		if (txtCarrera == null) {
			txtCarrera = new JTextField();
			txtCarrera.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtCarrera.setEditable(false);
			txtCarrera.setColumns(10);
		}
		return txtCarrera;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();

					vc.setVisible(true);

				}
			});
		}
		return btnAtras;
	}

//	private void cargarModelo() {
//		modeloCorredores.clear();
//		base.getBaseInscripciones().getInscripcionPorCompeticion(carrera);
//		Inscripcion insc;
//		for (int i = 0; i < base.getBaseInscripciones().getInscripcionesCarrera().size(); i++) {
//			insc = base.getBaseInscripciones().getInscripcionesCarrera().get(i);
//			modeloCorredores.addElement(insc);
//		}
//	}

	private JTable getTable() {
		if (tabla== null) {
			tabla= new JTable();
			String[] nombreColumnas = {"DNI","Nombre","Categoria","Fecha Inscripcion"};
			modeloTabla = new ModeloNoEditable(nombreColumnas,0); //al pasarle bnombre de columnas creara el numero necesario... filas 0 ya que
			//las crearemos en tiempo de ejecucion
			tabla.setModel(modeloTabla);
			//Aplica el renderer que marca la fila seleccionada en rojo y negrita
			//tablaInscripciones.setDefaultRenderer(Object.class, new RendererSubstance());
			///subo el alto de la fila
			tabla.setRowHeight(30);
			//modifico el ancho de la columna 0
			tabla.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
			tabla.getTableHeader().setReorderingAllowed(false);
			addFilas();
		}
		return tabla;
	}
	
	
	@SuppressWarnings("deprecation")
	public void addFilas(){
		ArrayList<Inscripcion> datos = base.getBaseInscripciones().getInscripcionesCarrera();

		Object[] nuevaFila = new Object[4];

		//Object[] nuevaFila = new Object[5];

		for(Inscripcion ins : datos) {
			nuevaFila[0] = ins.getCorredor().getDni();
			nuevaFila[1] = ins.getCorredor().getNombre();
			nuevaFila[2] = ins.getCategoria();
			Date fecha = ins.getFecha();

			nuevaFila[3] = fecha.getDate() +"/" +(fecha.getMonth()+1) + "/"+(fecha.getYear() +1900);
			modeloTabla.addRow(nuevaFila); 

			nuevaFila[3] = fecha.getDay() +"/" +fecha.getMonth() + "/"+(fecha.getYear() +1900);

		}
		
	}
	
	private  void actualizarTabla(){
		//vacio el modelo de la tabla
		modeloTabla.getDataVector().clear();
		//lo vuelvo a rellenar
		addFilas();
		//necesario para repintar la tabla(especialmente cuando se queda vacia)
		modeloTabla.fireTableDataChanged();
	}
	
}
