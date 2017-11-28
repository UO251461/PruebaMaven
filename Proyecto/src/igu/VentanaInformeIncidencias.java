package igu;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInformeIncidencias extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtrPagaronDems;
	private JTextArea txtrPagaronMas;
	private JTextArea txtrPagaronFueraDe;
	private JTextArea txtrNoPagados;
	private JTextArea txtrTotalProcesados;
	private JButton btnAceptar;
	
	private int menos;
	private int mas;
	private int fuera;
	private int noPagado;
	private int total;
	private int ok;
	private JTextArea txtPagosOK;


	/**
	 * Create the dialog.
	 */
	public VentanaInformeIncidencias(int ok,int menos, int mas, int fuera, int noPagado, int total) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		this.ok = ok;
		this.menos = menos;
		this.mas = mas;
		this.fuera = fuera;
		this.noPagado = noPagado;
		this.total = total;
		
		setResizable(false);
		setBounds(100, 100, 450, 368);
		getContentPane().setLayout(null);
		
		getContentPane().add(getTxtrPagaronDems());
		getContentPane().add(getTxtrPagaronMas());
		getContentPane().add(getTxtrPagaronFueraDe());
		getContentPane().add(getTxtrNoPagados());
		getContentPane().add(getTxtrTotalProcesados());
		getContentPane().add(getBtnAceptar());
		getContentPane().add(getTxtPagosOK());
	}
	private JTextArea getTxtrPagaronDems() {
		if (txtrPagaronDems == null) {
			txtrPagaronDems = new JTextArea();
			txtrPagaronDems.setEditable(false);
			txtrPagaronDems.setText("Pagaron de menos:\t"+menos);
			txtrPagaronDems.setBounds(52, 73, 246, 29);
		}
		return txtrPagaronDems;
	}
	private JTextArea getTxtrPagaronMas() {
		if (txtrPagaronMas == null) {
			txtrPagaronMas = new JTextArea();
			txtrPagaronMas.setEditable(false);
			txtrPagaronMas.setText("Pagaron dem\u00E1s:\t"+ mas);
			txtrPagaronMas.setBounds(52, 113, 246, 29);
		}
		return txtrPagaronMas;
	}
	private JTextArea getTxtrPagaronFueraDe() {
		if (txtrPagaronFueraDe == null) {
			txtrPagaronFueraDe = new JTextArea();
			txtrPagaronFueraDe.setEditable(false);
			txtrPagaronFueraDe.setText("Pagaron fuera de plazo:\t"+fuera);
			txtrPagaronFueraDe.setBounds(52, 156, 246, 29);
		}
		return txtrPagaronFueraDe;
	}
	private JTextArea getTxtrNoPagados() {
		if (txtrNoPagados == null) {
			txtrNoPagados = new JTextArea();
			txtrNoPagados.setEditable(false);
			txtrNoPagados.setText("No pagados:\t\t"+ noPagado);
			txtrNoPagados.setBounds(52, 196, 246, 29);
		}
		return txtrNoPagados;
	}
	private JTextArea getTxtrTotalProcesados() {
		if (txtrTotalProcesados == null) {
			txtrTotalProcesados = new JTextArea();
			txtrTotalProcesados.setEditable(false);
			txtrTotalProcesados.setText("Total procesados:\t"+total);
			txtrTotalProcesados.setBounds(52, 236, 246, 29);
		}
		return txtrTotalProcesados;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptar.setBounds(162, 288, 89, 23);
		}
		return btnAceptar;
	}
	private JTextArea getTxtPagosOK() {
		if (txtPagosOK == null) {
			txtPagosOK = new JTextArea();
			txtPagosOK.setText("Pagaron correctos:\t"+ok);
			txtPagosOK.setEditable(false);
			txtPagosOK.setBounds(52, 33, 246, 29);
		}
		return txtPagosOK;
	}
}
