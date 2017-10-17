package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JList;

public class VentanaCorredoresDeCompeticion extends JFrame {

	private JPanel contentPane;
	private JPanel pnlCarrera;
	private JPanel pnlBotones;
	private JScrollPane scrollPane;
	private JLabel lblCarrera;
	private JTextField txtCarrera;
	private JButton btnAtras;
	private JList list;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaCorredoresDeCompeticion frame = new VentanaCorredoresDeCompeticion();
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
	public VentanaCorredoresDeCompeticion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlCarrera(), BorderLayout.NORTH);
		contentPane.add(getPnlBotones(), BorderLayout.SOUTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
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
			pnlBotones.setLayout(new BorderLayout(0, 0));
			pnlBotones.add(getBtnAtras());
		}
		return pnlBotones;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
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
		}
		return btnAtras;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
		}
		return list;
	}
}
