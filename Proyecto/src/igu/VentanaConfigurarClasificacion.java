package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Carrera;
import logica.Categoria;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class VentanaConfigurarClasificacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblMostrarClasificacionesDe;
	private JLabel lblCategoria;
	private JRadioButton rdbtnTodos;
	private JLabel lblSexo;
	private JRadioButton rdbtnTodos_1;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JPanel pnCategoria;
	private JPanel pnSexo;
	private JLabel lbNombre;
	private Carrera carrera;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the dialog.
	 */
	public VentanaConfigurarClasificacion(Carrera carrera) {
		this.carrera=carrera;
		setBounds(100, 100, 609, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCarrera = new JLabel("Carrera:");
			lblCarrera.setBounds(33, 31, 41, 14);
			contentPanel.add(lblCarrera);
		}
		contentPanel.add(getLblMostrarClasificacionesDe());
		contentPanel.add(getLblCategoria());
		contentPanel.add(getLblSexo());
		contentPanel.add(getPnCategoria());
		contentPanel.add(getPnSexo());
		contentPanel.add(getLbNombre());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						mostrarVentanaClasificacion(carrera);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private JLabel getLblMostrarClasificacionesDe() {
		if (lblMostrarClasificacionesDe == null) {
			lblMostrarClasificacionesDe = new JLabel("Mostrar Clasificaciones de:");
			lblMostrarClasificacionesDe.setBounds(33, 98, 152, 14);
		}
		return lblMostrarClasificacionesDe;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categoria:");
			lblCategoria.setBounds(33, 123, 73, 14);
		}
		return lblCategoria;
	}
	private JRadioButton getRdbtnTodos() {
		if (rdbtnTodos == null) {
			rdbtnTodos = new JRadioButton("Todos");
			rdbtnTodos.setSelected(true);
		}
		return rdbtnTodos;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(249, 123, 46, 14);
		}
		return lblSexo;
	}
	private JRadioButton getRdbtnTodos_1() {
		if (rdbtnTodos_1 == null) {
			rdbtnTodos_1 = new JRadioButton("Todos");
			buttonGroup.add(rdbtnTodos_1);
			rdbtnTodos_1.setSelected(true);
		}
		return rdbtnTodos_1;
	}
	private JRadioButton getRdbtnMasculino() {
		if (rdbtnMasculino == null) {
			rdbtnMasculino = new JRadioButton("Masculino");
			buttonGroup.add(rdbtnMasculino);
		}
		return rdbtnMasculino;
	}
	private JRadioButton getRdbtnFemenino() {
		if (rdbtnFemenino == null) {
			rdbtnFemenino = new JRadioButton("Femenino");
			buttonGroup.add(rdbtnFemenino);
		}
		return rdbtnFemenino;
	}
	private JPanel getPnCategoria() {
		if (pnCategoria == null) {
			pnCategoria = new JPanel();
			pnCategoria.setBounds(43, 148, 152, 114);
			pnCategoria.setLayout(new GridLayout(0, 1, 0, 0));
			pnCategoria.add(getRdbtnTodos());
			generarCategorias();
		}
		return pnCategoria;
	}
	private JPanel getPnSexo() {
		if (pnSexo == null) {
			pnSexo = new JPanel();
			pnSexo.setBounds(259, 148, 135, 111);
			pnSexo.setLayout(new GridLayout(0, 1, 0, 0));
			pnSexo.add(getRdbtnTodos_1());
			pnSexo.add(getRdbtnMasculino());
			pnSexo.add(getRdbtnFemenino());
		}
		return pnSexo;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("");
			lbNombre.setBounds(89, 31, 338, 14);
		}
		return lbNombre;
	}
	
	
	private void generarCategorias(){
		int n=carrera.getCategorias().size();
		ArrayList<Categoria> cat= carrera.getCategorias();
		ArrayList<String> metidas = new ArrayList<String>();
		for(int i=0;i<n;i++){
			String nombre = cat.get(i).getCategoria();
			if(!metidas.contains(nombre)){
				pnCategoria.add(new JRadioButton(nombre));
				metidas.add(nombre);
			}
		}
	}
	
	
	private void mostrarVentanaClasificacion(Carrera competicion){
		VentanaClasificacion vc = new VentanaClasificacion(competicion.getIdcarrera());
		vc.setLocationRelativeTo(this);
		vc.setModal(true);
		vc.setVisible(true);
	}
}