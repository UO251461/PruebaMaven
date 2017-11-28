package igu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.csvreader.CsvWriter;

import database.Base;
import logica.Carrera;
import logica.Categoria;
import logica.Inscripcion;
import logica.ModeloNoEditable;

public class VentanaClasificacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Base base;
	private Carrera carrera;
	private JScrollPane scrollPane;
	private JPanel panelCentral;
	private JLabel lblNombreCarrera;
	private JButton btnGenerarFichero;
	private ArrayList<String> categorias;
	private int sexo;


	/**
	 * Create the frame.
	 */
	public VentanaClasificacion(Carrera carrera, ArrayList<String> categorias, int sexo) {
		setTitle("Clasificacion");
		base = new Base();
		base.inicializar();
		this.carrera = carrera;
		this.categorias = categorias;
		this.sexo = sexo;
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

	public void añadirFilas(String categoria, ModeloNoEditable modeloTabla, int tiempos) {
		ArrayList<Inscripcion> datos;
		Object[] nuevaFila = new Object[tiempos + 6];
		datos = base.getBaseInscripciones().generarClasificaciones(sexo, carrera.getIdcarrera(), categoria);
		int pos = 1;
		int primero = 0;
		if (categoria.equals("Generales")) {
			nuevaFila = new Object[tiempos + 6];
			for (Inscripcion i : datos) {
				if (primero == 0) {
					nuevaFila[0] = pos++;
					nuevaFila[1] = i.getDorsal();
					int[] time = base.getBaseInscripciones().getTiempo(carrera, i.getCorredor());
					for (int j = 2; j < time.length - tiempos; j++) {
						if (time[j] == 0)
							nuevaFila[j] = "---";
						else
							nuevaFila[j] = time[j];
						;

					}
					if (time[tiempos+2] == 0)
						nuevaFila[tiempos+2] = "---";
					else
						nuevaFila[tiempos + 2] = time[1];
					nuevaFila[tiempos + 3] = "";
					nuevaFila[tiempos + 4] = i.getCorredor().getSexo();
					nuevaFila[tiempos + 5] = i.getCategoria();
					modeloTabla.addRow(nuevaFila);
					primero = time[1];
				} else {
					nuevaFila[0] = pos++;
					nuevaFila[1] = i.getDorsal();
					int[] time = base.getBaseInscripciones().getTiempo(carrera, i.getCorredor());
					for (int j = 2; j < time.length - tiempos; j++) {
						if (time[j] == 0)
							nuevaFila[j] = "---";
						else
							nuevaFila[j] = time[j];
						;

					}
					if (time[tiempos+2] == 0)
						nuevaFila[tiempos+2] = "---";
					else
						nuevaFila[tiempos + 2] = time[1];
					nuevaFila[tiempos + 3] = "+" + (primero - time[1]);
					nuevaFila[tiempos + 4] = i.getCorredor().getSexo();
					nuevaFila[tiempos + 5] = i.getCategoria();
					modeloTabla.addRow(nuevaFila);
				}
			}
		} else {
			nuevaFila = new Object[tiempos + 5];
			for (Inscripcion i : datos) {
				if (primero == 0) {
					nuevaFila[0] = pos++;
					nuevaFila[1] = i.getDorsal();
					int[] time = base.getBaseInscripciones().getTiempo(carrera, i.getCorredor());
					for (int j = 2; j < time.length - tiempos; j++) {
						if (time[j] == 0)
							nuevaFila[j] = "---";
						else
							nuevaFila[j] = time[j];
						;

					}
					if (time[tiempos+2] == 0)
						nuevaFila[tiempos+2] = "---";
					else
						nuevaFila[tiempos + 2] = time[1];
					nuevaFila[tiempos + 3] = "";
					nuevaFila[tiempos + 4] = i.getCorredor().getSexo();
					modeloTabla.addRow(nuevaFila);
					primero = time[1];
				} else {
					nuevaFila[0] = pos++;
					nuevaFila[1] = i.getDorsal();
					int[] time = base.getBaseInscripciones().getTiempo(carrera, i.getCorredor());
					for (int j = 2; j < time.length - tiempos; j++) {
						if (time[j] == 0)
							nuevaFila[j] = "---";
						else
							nuevaFila[j] = time[j];
						;

					}
					if (time[tiempos+2] == 0)
						nuevaFila[tiempos+2] = "---";
					else
						nuevaFila[tiempos + 2] = time[1];
					
					nuevaFila[tiempos + 3] = "+" + (primero - time[1]);
					nuevaFila[tiempos + 4] = i.getCorredor().getSexo();
					modeloTabla.addRow(nuevaFila);
				}
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
			btnGenerarFichero.setEnabled(true);
			btnGenerarFichero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String nombre = JOptionPane.showInputDialog("Introduzca nombre del fichero a generar");
					if (nombre == null || nombre.isEmpty()) {
						JOptionPane.showConfirmDialog(null, "Datos Incorrectos");
					} else {
						generarCSV(nombre);
						btnGenerarFichero.setEnabled(false);
					}
				}
			});
		}
		return btnGenerarFichero;
	}

	private void generarTablas() {
		panelCentral.removeAll();
		if (categorias.contains("Todos")) {
			categorias = new ArrayList<String>();
			categorias.add("Generales");
			ArrayList<Categoria> cat = carrera.getCategorias();
			for (Categoria c : cat)
				if (!categorias.contains(c.getCategoria()))
					categorias.add(c.getCategoria());
		}
		for (String categoria : categorias) {
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
		if (categoria.equals("Generales")) {
			tablaClasificacion = new JTable();
			int n = base.getBaseInscripciones().getNumeroTiempos(carrera);
			String[] nombreColumnas = new String[n + 6];
			nombreColumnas[0] = "Posicion";
			nombreColumnas[1] = "Dorsal";
			for (int i = 0; i < n; i++)
				nombreColumnas[2 + i] = "Tiempo " + (i + 1);
			nombreColumnas[n + 2] = "Tiempo Final";
			nombreColumnas[n + 3] = "Diferencia";
			nombreColumnas[n + 4] = "Sexo";
			nombreColumnas[n + 5] = "Categoria";
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			// al pasarle bnombre de columnas creara el numero necesario...
			// filas 0 ya que
			// las crearemos en tiempo de ejecucion
			tablaClasificacion.setModel(modeloTabla);
			/// subo el alto de la fila
			tablaClasificacion.setRowHeight(30);
			// modifico el ancho de la columna 0
			tablaClasificacion.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
			tablaClasificacion.getTableHeader().setReorderingAllowed(false);
			añadirFilas(categoria, modeloTabla, n);
		} else {
			tablaClasificacion = new JTable();
			int n = base.getBaseInscripciones().getNumeroTiempos(carrera);
			String[] nombreColumnas = new String[n + 5];
			nombreColumnas[0] = "Posicion";
			nombreColumnas[1] = "Dorsal";
			for (int i = 0; i < n; i++)
				nombreColumnas[2 + i] = "Tiempo " + (i + 1);
			nombreColumnas[n + 2] = "Tiempo Final";
			nombreColumnas[n + 3] = "Diferencia";
			nombreColumnas[n + 4] = "Sexo";
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			// al pasarle bnombre de columnas creara el numero necesario...
			// filas 0 ya que
			// las crearemos en tiempo de ejecucion
			tablaClasificacion.setModel(modeloTabla);
			/// subo el alto de la fila
			tablaClasificacion.setRowHeight(30);
			// modifico el ancho de la columna 0
			tablaClasificacion.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(50);
			tablaClasificacion.getTableHeader().setReorderingAllowed(false);
			añadirFilas(categoria, modeloTabla, n);
		}
		return tablaClasificacion;
	}

	private JPanel crearPanelTitulo(String categoria) {
		JPanel pnNombre = new JPanel();
		pnNombre.setLayout(new GridLayout(0, 1, 0, 0));
		pnNombre.add(new JLabel(categoria));
		return pnNombre;
	}

	private void generarCSV(String nombre) {
		try {

			CsvWriter csvOutput = new CsvWriter(new FileWriter(nombre, true), ';');

			csvOutput.write("Clasificacon");
			csvOutput.write(carrera.getNombre());
			csvOutput.endRecord();

			Component[] paneles = panelCentral.getComponents();
			for (Component panel : paneles) {
				csvOutput.write(((JLabel) ((JPanel) ((JPanel) panel).getComponent(0)).getComponent(0)).getText());
				csvOutput.endRecord();
				JTable tabla = (JTable) ((JScrollPane) ((JPanel) panel).getComponent(1)).getViewport().getView();
				for (int i = 0; i < tabla.getModel().getColumnCount(); i++)
					csvOutput.write(tabla.getModel().getColumnName(i));
				csvOutput.endRecord();
				for (int i = 0; i < tabla.getModel().getRowCount(); i++) {
					for (int j = 0; j < tabla.getModel().getColumnCount(); j++) {
						csvOutput.write((String) tabla.getModel().getValueAt(i, j));
					}
					csvOutput.endRecord();
				}
			}
			csvOutput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
