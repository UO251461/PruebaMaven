package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Herramientas;
import logica.ModeloNoEditable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaConfiguracionCancelacion extends JDialog {

	private static final int numeroMaxPlazos = 3;
	
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPlazoCancelacion;
	private JTable tablePlazoCancelacion;
	private ModeloNoEditable modeloPlazoCancelacion;
	private JDateChooser fechaLimite;
	private JLabel lblFechaLimite;
	private JButton btnAadirFecha;
	private JButton btnBorrarFecha;
	private JPanel panel;
	private JButton btnAceptar;

	private VentanaCrearCarrera vc;
	private JLabel lbPorcentaje;
	private JTextField txtPorcentaje;

	/**
	 * Create the dialog.
	 */
	public VentanaConfiguracionCancelacion(VentanaCrearCarrera vC) {
		this.vc = vC;
		
		setModal(true);
		setTitle("Configurar fechas de cancelacion");
		setBounds(100, 100, 419, 558);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPlazoCancelacion());
		contentPanel.add(getPanel());
		contentPanel.add(getBtnBorrarFecha());
		contentPanel.add(getBtnAceptar());
		
	}

	
	
	private JScrollPane getScrollPlazoCancelacion() {
		if (scrollPlazoCancelacion == null) {
			scrollPlazoCancelacion = new JScrollPane();
			scrollPlazoCancelacion.setBounds(42, 51, 319, 164);
			scrollPlazoCancelacion.setViewportView(getTableCancelacion());
			
		}
		return scrollPlazoCancelacion;
	}
	
	public JTable getTableCancelacion() {
		if (tablePlazoCancelacion == null) {
			tablePlazoCancelacion = new JTable();
			String[] nombreColumnas = { "Fecha límite", "% de devolucion" };
			modeloPlazoCancelacion = new ModeloNoEditable(nombreColumnas, 0);
			
			copiarDeA((ModeloNoEditable) vc.getTableCancelacion().getModel(),modeloPlazoCancelacion);			
			
			tablePlazoCancelacion.setModel(modeloPlazoCancelacion);
			tablePlazoCancelacion.setRowHeight(30);
			tablePlazoCancelacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return tablePlazoCancelacion;
	}
	private void copiarDeA(ModeloNoEditable model, ModeloNoEditable modeloPlazoCancelacion) {
		for(int i=0; i<model.getRowCount(); i++){
			String[] row = new String[model.getColumnCount()];
			for(int j=0; j<model.getColumnCount(); j++){
				row[j] = (String) model.getValueAt(i, j);
			}
			modeloPlazoCancelacion.addRow(row);
		}
		
	}



	private JDateChooser getFechaLimite() {
		if (fechaLimite == null) {
			fechaLimite = new JDateChooser();
			fechaLimite.setBounds(143, 19, 142, 20);
			fechaLimite.setRequestFocusEnabled(false);
			fechaLimite.setDateFormatString("dd/MM/yyyy");
			
			try {
				if(vc.getDateCompeticion().getDate() == null)
					fechaLimite.setDate(convertirDate("30/11/2017"));
				else
					fechaLimite.setDate(vc.getDateCompeticion().getDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return fechaLimite;
	}
	
	private Date convertirDate(String fecha) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		return sf.parse(fecha);
	}
	private JLabel getLblFechaLimite() {
		if (lblFechaLimite == null) {
			lblFechaLimite = new JLabel("Fecha limite:");
			lblFechaLimite.setBounds(31, 19, 98, 28);
		}
		return lblFechaLimite;
	}
	private JButton getBtnAadirFecha() {
		if (btnAadirFecha == null) {
			btnAadirFecha = new JButton("A\u00F1adir fecha");
			if (tablePlazoCancelacion.getRowCount() >= numeroMaxPlazos) {
				btnAadirFecha.setEnabled(false);
			}
			btnAadirFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobarFecha()){
						if(comprobarPorcentaje()){
							anadePlazoTabla();
							ordenarFechas();
							if (tablePlazoCancelacion.getRowCount() >= numeroMaxPlazos) {
								btnAadirFecha.setEnabled(false);
								JOptionPane.showMessageDialog(null,
										"Ha llegado al limite de plazos de cancelacion, para añadir más debe borrar alguno");
							}
						}
						else errorPorcentaje();
					}
					else errorFecha();
				}

			});
			btnAadirFecha.setBounds(59, 113, 181, 35);
		}
		return btnAadirFecha;
	}
	
	private boolean comprobarFecha() {
		if(fechaLimite.getDate() !=null){
			Date fechaCompeticion = vc.getDateCompeticion().getDate();
			//Datefecha2DiasAntes = fechaCompeticion
					
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaCompeticion);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			
			Date fecha2DiasAntes = calendar.getTime();
			if(fechaLimite.getDate().after(new Date()) && fechaLimite.getDate().before(fecha2DiasAntes))			
				return true;
		}
		return false;
	}

	private boolean comprobarPorcentaje() {
		if(!txtPorcentaje.getText().equals(""))
			try{
				float pct = Float.parseFloat(txtPorcentaje.getText());
				if(pct>=0 && pct<=50)
					return true;
			}catch(NumberFormatException n){
				return false;
			}
		return false;
	}
	
	private void errorPorcentaje() {
		JOptionPane.showMessageDialog(null,
				"Porcentaje debe de estar entre 0 y 50%");
	}


	private void errorFecha() {
		JOptionPane.showMessageDialog(null,
				"La fecha como mínimo mañana o 2 días antes de la competición.");
	}

	private JButton getBtnBorrarFecha() {
		if (btnBorrarFecha == null) {
			btnBorrarFecha = new JButton("Borrar fecha");
			btnBorrarFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrarPlazoTabla();
					if (!btnAadirFecha.isEnabled() && tablePlazoCancelacion.getRowCount() < numeroMaxPlazos)
						btnAadirFecha.setEnabled(true);
				}
			});
			btnBorrarFecha.setBounds(100, 226, 178, 35);
		}
		return btnBorrarFecha;
	}
	
	private void borrarPlazoTabla() {
		if (tablePlazoCancelacion.getSelectionModel() != null && tablePlazoCancelacion.getSelectedRow() != -1) {
			modeloPlazoCancelacion.removeRow(tablePlazoCancelacion.getSelectedRow());
		}
	}
	private void borrarDatos(ModeloNoEditable model){
		int tam = model.getRowCount();
		for(int i=tam-1; i>=0; i--){
			model.removeRow(i);
		}
		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(42, 281, 319, 168);
			panel.setLayout(null);
			panel.add(getLblFechaLimite());
			panel.add(getFechaLimite());
			panel.add(getBtnAadirFecha());
			panel.add(getLbPorcentaje());
			panel.add(getTxtPorcentaje());
		}
		return panel;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrarDatos((ModeloNoEditable) vc.getTableCancelacion().getModel());
					copiarDeA(modeloPlazoCancelacion,(ModeloNoEditable) vc.getTableCancelacion().getModel());	
					dispose();
				}
			});
			btnAceptar.setBounds(112, 460, 166, 35);
		}
		return btnAceptar;
	}


	
	public void anadePlazoTabla() {
		Object[] nuevaFila = new Object[4];
		
		java.sql.Date fechaCI = new java.sql.Date(getFechaLimite().getDate().getTime());
		nuevaFila[0] = fechaCI.getDate() + "/" + (fechaCI.getMonth()+1) + "/" + (fechaCI.getYear() + 1900);

		nuevaFila[1] = getTxtPorcentaje().getText();
		modeloPlazoCancelacion.addRow(nuevaFila);
	}



	private JLabel getLbPorcentaje() {
		if (lbPorcentaje == null) {
			lbPorcentaje = new JLabel("Porcentaje devuelto:");
			lbPorcentaje.setBounds(31, 66, 122, 28);
		}
		return lbPorcentaje;
	}
	private JTextField getTxtPorcentaje() {
		if (txtPorcentaje == null) {
			txtPorcentaje = new JTextField();
			txtPorcentaje.setText("50");
			txtPorcentaje.setHorizontalAlignment(SwingConstants.CENTER);
			txtPorcentaje.setBounds(179, 70, 86, 20);
			txtPorcentaje.setColumns(10);
		}
		return txtPorcentaje;
	}
	
	private void ordenarFechas(){
		try{
			for(int i=1; i<modeloPlazoCancelacion.getRowCount(); i++){
				Date fechaMayor = convertirDate((String) modeloPlazoCancelacion.getValueAt(i,0));
				Date fechaMenor = convertirDate((String) modeloPlazoCancelacion.getValueAt(i-1,0));
				int posAux = i;
				while(posAux>=1 && fechaMayor.before(fechaMenor)){
					Object fechaAux = modeloPlazoCancelacion.getValueAt(posAux, 0);
					Object porcAux	= modeloPlazoCancelacion.getValueAt(posAux, 1);	
					modeloPlazoCancelacion.setValueAt(modeloPlazoCancelacion.getValueAt(posAux-1,0), posAux, 0);
					modeloPlazoCancelacion.setValueAt(modeloPlazoCancelacion.getValueAt(posAux-1,1), posAux, 1);
					
					modeloPlazoCancelacion.setValueAt(fechaAux, posAux-1, 0);
					modeloPlazoCancelacion.setValueAt(porcAux, posAux-1, 1);
					posAux--;
				}
			}
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}
}
