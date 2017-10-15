package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Base;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaElegirCompeticion extends JDialog {
	//ESTA VENTANA ES AUXILIAR PARA ELEGIR LA COMPETICION
	/**
	 * Launch the application.
	 */
	
	private JComboBox cbCompeticion;
	private JLabel lbCompeticion;
	private Base base;
	private JPanel pnBtn;
	private JButton btnContinuar;
	
	private double precio;
	private String dni;

	/**
	 * Create the dialog.
	 */
	public VentanaElegirCompeticion(double precio, String dni) {
		setTitle("Elegir competici\u00F3n");
		base = new Base();
		this.precio = precio;
		this.dni = dni;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 460, 157);
		getContentPane().setLayout(new BorderLayout());
		
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setAutoscrolls(true);
			panel.setFocusTraversalPolicyProvider(true);
			panel.setLayout(null);			
			panel.add(getLbCompeticion());
			panel.add(getCbCompeticion());
		}
		getContentPane().add(getPnBtn(), BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}		
	private JLabel getLbCompeticion() {
		if (lbCompeticion == null) {
			lbCompeticion = new JLabel("Competici\u00F3n :");
			lbCompeticion.setBounds(50, 34, 97, 27);
		}
		return lbCompeticion;
	}
	
	private JComboBox getCbCompeticion() {
		if (cbCompeticion == null) {
			cbCompeticion = new JComboBox();
			cbCompeticion.setModel(new DefaultComboBoxModel(base.getBaseInscripciones().getNombreCompeticiones().toArray()));
			cbCompeticion.setBounds(168, 34, 255, 27);
		}
		return cbCompeticion;
	}
	private JPanel getPnBtn() {
		if (pnBtn == null) {
			pnBtn = new JPanel();
			pnBtn.add(getBtnContinuar());
		}
		return pnBtn;
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{						
						confirmar();
					}catch (SQLException e1) {
						errorSQL(e1);
					}
				}
			});
		}
		return btnContinuar;
	}
	
	
	
	/**
	 * Registra una inscripcion en una  competicion en la BBDD
	 * @throws SQLException, lanza una excepcion si ya está inscrito en esa competición
	 */
	private void confirmar() throws SQLException{
		
		base.getBaseInscripciones().inscribirCompeticion(cbCompeticion.getSelectedItem().toString(), dni);
		new VentanaMetodoPago(precio);
		dispose();
	}
	
	/**
	 * Trata los errores SQL
	 * @param e
	 */
	private void errorSQL(SQLException e){
		int code = e.getErrorCode();
		if(code== 20000)
			JOptionPane.showMessageDialog(this, "Lo sentimos, no quedan plazas disponibles");
		else if(code == 1)
			JOptionPane.showMessageDialog(this, "Usted ya esta inscrito en esta competición, elija otra opción");
		else 
			e.printStackTrace();
	}
	
}
