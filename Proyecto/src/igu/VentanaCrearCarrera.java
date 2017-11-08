package igu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import logica.Categoria;
import logica.ModeloNoEditable;
import logica.Plazo;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaCrearCarrera extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int numeroMaxPlazos = 5;

	private JPanel contentPane;
	private ModeloNoEditable modeloTabla;
	private ModeloNoEditable modeloCategorias;

	private VentanaPrincipal vp;
	private JButton btnCrear;
	private JButton btnAtras;
	private JLabel lblNombreCompeticion;
	private JTextField textNombreCarrera;
	private JLabel lblTipo;
	private JTextField textTipo;
	private JLabel lblDistancia;
	private JTextField textDistancia;

	private JDateChooser fechaCompeticion;
	private JLabel lblFechaCompeticion;
	private JLabel lblNumeroDePlazas;
	private JTextField textPlazas;
	private JPanel panelPlazos;
	private JLabel lblFechaComienzoInscripcion;
	private JDateChooser dateComienzoInscripcion;
	private JDateChooser dateFinalInscripcion;
	private JLabel lblFechaFinalInscripcion;
	private JLabel lblPrecio;
	private JTextField textPrecio;
	private JLabel lblLugar;
	private JTextField textLugar;
	private JButton btnAñadirPlazoInscripcion;
	private JTable tablePlazos;
	private JButton btnBorrarPlazoInscripcion;
	private JLabel lblPlazos;

	private Plazo[] plazos = new Plazo[5];
	private int numeroPlazos;
	private JLabel lblCategorias;
	private JTable tableCategorias;
	private JScrollPane scrollPlazos;
	private JScrollPane scrollCategorias;
	private JButton btnAñadirCategoria;
	private JButton btnEliminarCategoria;
	
	
	private JPanel panelCategoria;
	private JLabel lblNombreCategoria;
	private JLabel lblLimiteInferiorEdad;
	private JLabel lblLimiteSuperiorEdad;
	private JTextField textNombreCategoria;
	private JTextField textLimiteInferiorEdad;
	private JTextField textLimiteSuperiorEdad;
	
	private Categoria[] categorias = new Categoria[4];
	private int numeroCategorias = 3;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaCrearCarrera(VentanaPrincipal vp) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		this.vp = vp;
		setTitle("Crear Carrera");
		setBounds(100, 100, 1013, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNombreCompeticion());
		contentPane.add(getTextNombreCarrera());
		contentPane.add(getLblTipo());
		contentPane.add(getTextTipo());
		contentPane.add(getLblDistancia());
		contentPane.add(getTextDistancia());
		contentPane.add(getBtnAtras());
		contentPane.add(getBtnCrear());
		contentPane.add(getLblFechaCompeticion());
		contentPane.add(getLblNumeroDePlazas());
		contentPane.add(getTextPlazas());
		contentPane.add(getPanelPlazos());
		contentPane.add(getDateCompeticion());
		contentPane.add(getLblLugar());
		contentPane.add(getTextLugar());
		contentPane.add(getBtnBorrarPlazoInscripcion());
		contentPane.add(getLblPlazos());
		contentPane.add(getLblCategorias());
		contentPane.add(getScrollPlazos());
		contentPane.add(getScrollCategorias());
		contentPane.add(getBtnEliminarCategoria());
		contentPane.add(getPanelCategoria());
	}

	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (compruebaCampos()) {
						creaCarrera();
						dispose();
						vp.setVisible(true);
					}
				}

			});
			btnCrear.setBounds(911, 583, 86, 23);
		}
		return btnCrear;
	}

	private void creaCarrera() {
		if (this.numeroPlazos == 1 && this.numeroCategorias == 3) {
			vp.getBase().getBaseCarrera().crearCarrera1Plazo3categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}
		
		if (this.numeroPlazos == 1 && this.numeroCategorias == 4) {
			vp.getBase().getBaseCarrera().crearCarrera1Plazo4categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior(),
					categorias[3].getCategoria(),categorias[3].getLimiteInferior(),categorias[3].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}

		if (this.numeroPlazos == 2 && numeroCategorias == 3) {
			vp.getBase().getBaseCarrera().crearCarrera2Plazo3categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}
		
		if (this.numeroPlazos == 2 && numeroCategorias == 4) {
			vp.getBase().getBaseCarrera().crearCarrera2Plazo4categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior(),
					categorias[3].getCategoria(),categorias[3].getLimiteInferior(),categorias[3].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}

		if (this.numeroPlazos == 3 && numeroCategorias == 3) {
			vp.getBase().getBaseCarrera().crearCarrera3Plazo3categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(), categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}
		
		if (this.numeroPlazos == 3 && numeroCategorias == 4) {
			vp.getBase().getBaseCarrera().crearCarrera3Plazo4categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(), categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior(),
					categorias[3].getCategoria(),categorias[3].getLimiteInferior(),categorias[3].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}
		if (this.numeroPlazos == 4 && numeroCategorias == 3) {
			vp.getBase().getBaseCarrera().crearCarrera4Plazo3categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(),
					plazos[3].getPrecio(), plazos[3].getFechaEmpiezaInscripcion(),
					plazos[3].getFechaFinalizaInscripcion(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}
		
		if (this.numeroPlazos == 4 && numeroCategorias == 4) {
			vp.getBase().getBaseCarrera().crearCarrera4Plazo4categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(),
					plazos[3].getPrecio(), plazos[3].getFechaEmpiezaInscripcion(),
					plazos[3].getFechaFinalizaInscripcion(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior(),
					categorias[3].getCategoria(),categorias[3].getLimiteInferior(),categorias[3].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}
		if (this.numeroPlazos == 5 && numeroCategorias == 3) {
			vp.getBase().getBaseCarrera().crearCarrera5Plazo3categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(),
					plazos[3].getPrecio(), plazos[3].getFechaEmpiezaInscripcion(),
					plazos[3].getFechaFinalizaInscripcion(), 
					plazos[4].getPrecio(), plazos[4].getFechaEmpiezaInscripcion(),
					plazos[4].getFechaFinalizaInscripcion(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior());
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}
		
		if (this.numeroPlazos == 5 && numeroCategorias == 4) {
			vp.getBase().getBaseCarrera().crearCarrera5Plazo4categorias(getTextNombreCarrera().getText(), plazos[0].getPrecio(),
					getDateCompeticion().getDate(), plazos[0].getFechaEmpiezaInscripcion(),
					plazos[0].getFechaFinalizaInscripcion(), Double.parseDouble(getTextDistancia().getText()),
					getTextTipo().getText(), Integer.parseInt(getTextPlazas().getText()), getTextLugar().getText(),
					plazos[1].getPrecio(), plazos[1].getFechaEmpiezaInscripcion(),
					plazos[1].getFechaFinalizaInscripcion(), plazos[2].getPrecio(),
					plazos[2].getFechaEmpiezaInscripcion(), plazos[2].getFechaFinalizaInscripcion(),
					plazos[3].getPrecio(), plazos[3].getFechaEmpiezaInscripcion(),
					plazos[3].getFechaFinalizaInscripcion(), 
					plazos[4].getPrecio(), plazos[4].getFechaEmpiezaInscripcion(),
					plazos[4].getFechaFinalizaInscripcion(),categorias[0].getCategoria(),
					categorias[0].getLimiteInferior(),categorias[0].getLimiteSuperior(),categorias[1].getCategoria(),categorias[1].getLimiteInferior(),
					categorias[1].getLimiteSuperior(),categorias[2].getCategoria(),categorias[2].getLimiteInferior(),categorias[2].getLimiteSuperior(),
					categorias[3].getCategoria(),categorias[3].getLimiteInferior(),categorias[3].getLimiteSuperior());
			
			JOptionPane.showMessageDialog(this, "Su carrera ha sido creada satisfactoriamente");
		}

	}

	private boolean compruebaCampos() {
		if(compruebaCategorias())
			if (stringNoVacio(getTextNombreCarrera().getText()))
				if (compruebaFechas())
					if (esNumericoYNoVacio(getTextDistancia().getText())
							&& Double.parseDouble(getTextDistancia().getText()) > 0)
						if (stringNoVacio(getTextTipo().getText()))
							if (esNumericoYNoVacio(getTextPlazas().getText())
									&& Integer.parseInt(getTextPlazas().getText()) > 0)
								if (stringNoVacio(getTextLugar().getText()))
									return true;
								else {
									JOptionPane.showMessageDialog(this,
											"El lugar de la carrera NO ha sido introducido correctamente");
									return false;
								}
							else {
								JOptionPane.showMessageDialog(this,
										"El numero de plazas de la carrera NO ha sido introducido correctamente");
								return false;
							}
						else {
							JOptionPane.showMessageDialog(this,
									"El tipo de la carrera NO ha sido introducido correctamente");
							return false;
						}
					else {
						JOptionPane.showMessageDialog(this,
								"La distancia de la carrera NO ha sido introducido correctamente");
						return false;
					}
				else {
					JOptionPane.showMessageDialog(this,
							"Las fechas no son concoordantes entre ellas, no se ha de solapar ningun plazo de inscripcion "
									+ "y todos deben ser antes de la fecha de competicion. Ademas la fecha inicial de inscripcion debe ser "
									+ "antes que la fecha final de inscripcion de cada plazo.");
					return false;
				}
			else {
				JOptionPane.showMessageDialog(this, "El nombre de la carrera NO ha sido introducido correctamente");
				return false;
			}
		else{
			JOptionPane.showMessageDialog(this, "Categorias introducidas ERRONEAMENTE (se solapan categorias o algunos de sus nombres coinciden)");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void creaPlazos() {
		this.plazos = new Plazo[5];
		Vector<Object> contenido = modeloTabla.getDataVector();

		for (int i = 0; i < modeloTabla.getRowCount(); i++) {

			Vector<Object> fila = (Vector<Object>) contenido.get(i);

			String fecha1 = (String) fila.elementAt(1);
			java.sql.Date fechaei = convertirStringFecha(fecha1);

			String fecha2 = (String) fila.elementAt(2);
			java.sql.Date fechafi = convertirStringFecha(fecha2);

			String pre = (String) fila.elementAt(3);
			Double precio = Double.parseDouble(pre);

			this.plazos[i] = new Plazo(fechaei, fechafi, precio);

		}
	}
	
	@SuppressWarnings("unchecked")
	private void creaCategorias(){
		this.categorias = new Categoria[4];
		Vector<Object> contenido = modeloCategorias.getDataVector();

		for (int i = 0; i < modeloCategorias.getRowCount(); i++) {

			Vector<Object> fila = (Vector<Object>) contenido.get(i);

			String nombrec = (String) fila.elementAt(0);		

			String linferior = (String) fila.elementAt(1);
			int limiteInferior = Integer.parseInt(linferior);

			String lsuperior = (String) fila.elementAt(2);
			int limiteSuperior = Integer.parseInt(lsuperior);

			this.categorias[i] = new Categoria(limiteInferior, limiteSuperior, nombrec);

		}
	}
	
	private boolean compruebaCategorias(){
		creaCategorias();		
		for(int i=0;i<numeroCategorias;i++)
			for(int j=0;j<numeroCategorias;j++)
				if(i!=j){
					if(!noSolapaCategorias(categorias[i],categorias[j]))
						return false;
				}
		return true;
			
	}
	
	private boolean noSolapaCategorias(Categoria cat1,Categoria cat2){
		if(cat1.getCategoria().equals(cat2.getCategoria())){
			return false;
		}
		if(cat1.getLimiteInferior() == cat2.getLimiteInferior()){
			return false;
		}
		if(cat1.getLimiteSuperior() == cat2.getLimiteSuperior()){
			return false;
		}
		if(cat1.getLimiteInferior()<cat2.getLimiteInferior() && cat1.getLimiteSuperior()>cat2.getLimiteInferior()){
			return false;
		}
		if(cat1.getLimiteInferior()<cat2.getLimiteSuperior() && cat1.getLimiteSuperior() > cat2.getLimiteSuperior()){
			return false;
		}
		return true;
	

	}

	private boolean compruebaFechas() {
		creaPlazos();	
		if(this.numeroPlazos == 0)
			return false;
		for (int i = 0; i < this.numeroPlazos; i++) {			
			for (int j = 0; j < this.numeroPlazos; j++) {
				if (i != j) {
					if (plazoEntrePlazo(plazos[i], plazos[j]))
						return false;
				}
			}
		}
		return true;
	}

	/*Si la fecha que vas a comparar es anterior al argumento date,
	 *  el método devolverá un valor menor a cero. Si la fecha que vas
	 *   a comparar es posterior al argumento date, el método devolverá
	 *    un valor mayor a cero. Si las fechas son iguales, el método 
	 *    devolverá un cero
	 *    	 */
	
	private boolean plazoEntrePlazo(Plazo plazo1, Plazo plazo2) {
		if(plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())==0 && 
				plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaFinalizaInscripcion())==0) {
			return true;
		}
		if(plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())==0){
		return true;
		}
		

		if (plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())<0
				&& plazo1.getFechaFinalizaInscripcion().compareTo(plazo2.getFechaEmpiezaInscripcion())>0){	

			return true;
		}
		if (plazo1.getFechaEmpiezaInscripcion().compareTo(plazo2.getFechaFinalizaInscripcion())<0
				&& plazo1.getFechaFinalizaInscripcion().after(plazo2.getFechaFinalizaInscripcion())){

			return true;
		}
		return false;
	}

	private boolean stringNoVacio(String cadena) {
		return cadena.isEmpty() ? false : true;
	}

	private boolean esNumericoYNoVacio(String cadena) {
		if (!cadena.isEmpty()) {
			for (int i = 0; i < cadena.length(); ++i) {
				char caracter = cadena.charAt(i);
				if (Character.isDigit(caracter)) {
					return true;
				}
			}
		}
		return false;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnAtras.setBounds(785, 583, 96, 23);
		}
		return btnAtras;
	}

	private JLabel getLblNombreCompeticion() {
		if (lblNombreCompeticion == null) {
			lblNombreCompeticion = new JLabel("Nombre Competicion:");
			lblNombreCompeticion.setBounds(10, 29, 133, 14);
			lblNombreCompeticion.setDisplayedMnemonic('n');
			lblNombreCompeticion.setLabelFor(getTextNombreCarrera());
		}
		return lblNombreCompeticion;
	}

	private JTextField getTextNombreCarrera() {
		if (textNombreCarrera == null) {
			textNombreCarrera = new JTextField();
			textNombreCarrera.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					if(textNombreCarrera.getText().length()>20){
						JOptionPane.showMessageDialog(null, "El texto del nombre de la carrera es demasiado largo");
						textNombreCarrera.setText("");
					}
				}
			});
			textNombreCarrera.setBounds(153, 26, 194, 20);
			textNombreCarrera.setColumns(10);
		}
		return textNombreCarrera;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(357, 29, 67, 14);
			lblTipo.setLabelFor(getTextTipo());
			lblTipo.setDisplayedMnemonic('t');
		}
		return lblTipo;
	}

	private JTextField getTextTipo() {
		if (textTipo == null) {
			textTipo = new JTextField();
			textTipo.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(textTipo.getText().length()>20){
						JOptionPane.showMessageDialog(null, "El texto del tipo de la carrera es demasiado largo");
						textTipo.setText("");
					}
				}
			});
			textTipo.setBounds(434, 26, 124, 20);
			textTipo.setColumns(10);
		}
		return textTipo;
	}

	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia(en km):");
			lblDistancia.setBounds(616, 27, 124, 19);
			lblDistancia.setLabelFor(getTextDistancia());
			lblDistancia.setDisplayedMnemonic('d');
		}
		return lblDistancia;
	}

	private JTextField getTextDistancia() {
		if (textDistancia == null) {
			textDistancia = new JTextField();
			textDistancia.setBounds(715, 26, 115, 20);
			textDistancia.setColumns(10);
		}
		return textDistancia;
	}

	private JDateChooser getDateCompeticion() {
		if (fechaCompeticion == null) {
			fechaCompeticion = new JDateChooser();
			fechaCompeticion.setBounds(153, 86, 124, 20);
			fechaCompeticion.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			fechaCompeticion.setDateFormatString("dd/MM/yyyy");
			fechaCompeticion.setRequestFocusEnabled(false);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {

				fechaCompeticion.setDate(sf.parse("01-12-2017"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return fechaCompeticion;
	}

	private JLabel getLblFechaCompeticion() {
		if (lblFechaCompeticion == null) {
			lblFechaCompeticion = new JLabel("Fecha Competicion:");
			lblFechaCompeticion.setDisplayedMnemonic('f');
			lblFechaCompeticion.setLabelFor(getDateCompeticion());
			lblFechaCompeticion.setBounds(10, 86, 133, 20);
		}
		return lblFechaCompeticion;
	}

	private JLabel getLblNumeroDePlazas() {
		if (lblNumeroDePlazas == null) {
			lblNumeroDePlazas = new JLabel("Numero de plazas:");
			lblNumeroDePlazas.setLabelFor(getTextPlazas());
			lblNumeroDePlazas.setDisplayedMnemonic('n');
			lblNumeroDePlazas.setBounds(309, 86, 115, 20);
		}
		return lblNumeroDePlazas;
	}

	private JTextField getTextPlazas() {
		if (textPlazas == null) {
			textPlazas = new JTextField();
			textPlazas.setBounds(434, 86, 124, 20);
			textPlazas.setColumns(10);
		}
		return textPlazas;
	}

	private JPanel getPanelPlazos() {
		if (panelPlazos == null) {
			panelPlazos = new JPanel();
			panelPlazos.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelPlazos.setBounds(10, 422, 454, 184);
			panelPlazos.setLayout(null);
			panelPlazos.add(getLblFechaComienzoInscripcion());
			panelPlazos.add(getDateComienzoInscripcion());
			panelPlazos.add(getDateFinalInscripcion());
			panelPlazos.add(getLblFechaFinalInscripcion());
			panelPlazos.add(getLblPrecio());
			panelPlazos.add(getTextPrecio());
			panelPlazos.add(getBtnAñadirPlazoInscripcion());
		}
		return panelPlazos;
	}

	private JLabel getLblFechaComienzoInscripcion() {
		if (lblFechaComienzoInscripcion == null) {
			lblFechaComienzoInscripcion = new JLabel("Fecha comienzo inscripcion:");
			lblFechaComienzoInscripcion.setLabelFor(getDateComienzoInscripcion());
			lblFechaComienzoInscripcion.setDisplayedMnemonic('e');
			lblFechaComienzoInscripcion.setBounds(10, 11, 199, 28);
		}
		return lblFechaComienzoInscripcion;
	}

	private JDateChooser getDateComienzoInscripcion() {
		if (dateComienzoInscripcion == null) {
			dateComienzoInscripcion = new JDateChooser();
			fechaCompeticion.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			dateComienzoInscripcion.setRequestFocusEnabled(false);
			dateComienzoInscripcion.setDateFormatString("dd/MM/yyyy");
			dateComienzoInscripcion.setBounds(294, 11, 128, 20);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {

				dateComienzoInscripcion.setDate(sf.parse("09-11-2017"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateComienzoInscripcion;
	}

	private JDateChooser getDateFinalInscripcion() {
		if (dateFinalInscripcion == null) {
			dateFinalInscripcion = new JDateChooser();
			dateFinalInscripcion.setBounds(294, 52, 128, 20);
			dateFinalInscripcion.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			dateFinalInscripcion.setDateFormatString("dd/MM/yyyy");
			dateFinalInscripcion.setRequestFocusEnabled(false);
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				dateFinalInscripcion.setDate(sf.parse("30-11-2017"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateFinalInscripcion;
	}

	private JLabel getLblFechaFinalInscripcion() {
		if (lblFechaFinalInscripcion == null) {
			lblFechaFinalInscripcion = new JLabel("Fecha final inscripcion:");
			lblFechaFinalInscripcion.setDisplayedMnemonic('h');
			lblFechaFinalInscripcion.setLabelFor(getDateFinalInscripcion());
			lblFechaFinalInscripcion.setBounds(10, 50, 260, 28);
		}
		return lblFechaFinalInscripcion;
	}

	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setLabelFor(getTextPrecio());
			lblPrecio.setDisplayedMnemonic('r');
			lblPrecio.setBounds(10, 89, 66, 28);
		}
		return lblPrecio;
	}

	private JTextField getTextPrecio() {
		if (textPrecio == null) {
			textPrecio = new JTextField();
			textPrecio.setBounds(294, 93, 128, 20);
			textPrecio.setColumns(10);
		}
		return textPrecio;
	}

	private JLabel getLblLugar() {
		if (lblLugar == null) {
			lblLugar = new JLabel("Lugar:");
			lblLugar.setBounds(616, 88, 96, 17);
		}
		return lblLugar;
	}

	private JTextField getTextLugar() {
		if (textLugar == null) {
			textLugar = new JTextField();
			textLugar.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(textLugar.getText().length()>20){
						JOptionPane.showMessageDialog(null, "El texto del lugar de la carrera es demasiado largo");
						textLugar.setText("");
					}
				}
			});
			textLugar.setBounds(715, 86, 124, 20);
			textLugar.setColumns(10);
		}
		return textLugar;
	}

	private JButton getBtnAñadirPlazoInscripcion() {
		if (btnAñadirPlazoInscripcion == null) {
			btnAñadirPlazoInscripcion = new JButton("A\u00F1adir Plazo Inscripcion");
			btnAñadirPlazoInscripcion.setMnemonic('a');
			btnAñadirPlazoInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					añadePlazoTabla();
					if (tablePlazos.getRowCount() == numeroMaxPlazos) {
						btnAñadirPlazoInscripcion.setEnabled(false);
						JOptionPane.showMessageDialog(null,
								"Ha llegado al limite de plazos de inscripcion, para añadir mas debe borrar alguno");
					}
				}
			});
			btnAñadirPlazoInscripcion.setBounds(10, 124, 428, 52);
		}
		return btnAñadirPlazoInscripcion;
	}

	private JTable getTablePlazos() {
		if (tablePlazos == null) {
			tablePlazos = new JTable();
			String[] nombreColumnas = { "Plazo", "Fecha Apertura", "Fecha Finalizacion", "Precio" };
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tablePlazos.setModel(modeloTabla);
			tablePlazos.setRowHeight(30);
			tablePlazos.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return tablePlazos;
	}

	@SuppressWarnings("deprecation")
	public void añadirFilas() {
		Object[] nuevaFila = new Object[4];
		nuevaFila[0] = "Plazo " + (tablePlazos.getRowCount() + 1);

		java.sql.Date fechaCI = new java.sql.Date(getDateComienzoInscripcion().getDate().getTime());
		nuevaFila[1] = fechaCI.getDate() + "/" + fechaCI.getMonth() + "/" + (fechaCI.getYear() + 1900);

		java.sql.Date fechaFI = new java.sql.Date(getDateFinalInscripcion().getDate().getTime());
		nuevaFila[2] = fechaFI.getDate() + "/" + fechaFI.getMonth() + "/" + (fechaFI.getYear() + 1900);
		nuevaFila[3] = getTextPrecio().getText();
		modeloTabla.addRow(nuevaFila);
	}

	private void añadePlazoTabla() {
		if (getDateComienzoInscripcion().getDate().before(getDateFinalInscripcion().getDate()) && getDateComienzoInscripcion().getDate().after(new Date()) &&
				getDateComienzoInscripcion().getDate().compareTo(getDateFinalInscripcion().getDate())!=0 &&
				getDateFinalInscripcion().getDate().before(getDateCompeticion().getDate())){
						if (esNumericoYNoVacio(getTextPrecio().getText()) && Double.parseDouble(getTextPrecio().getText()) > 0){
							añadirFilas();
							this.numeroPlazos++;
						}
						else
							JOptionPane.showMessageDialog(this,
									"El precio del plazo es incorrecto, revise que no se encuentre vacio o haya introducido un numero o este sea negativo");			
		}			
		else
			JOptionPane.showMessageDialog(this, "Las fechas del plazo son incorrectas ya que no concuerdan, reviselas");

	}

	private java.sql.Date convertirStringFecha(String cadena) {
		SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy");
		Date parsed = null;
		try {
			parsed = format.parse(cadena);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (parsed != null) {
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
			return sql;
		}
		return null;
	}

	private void borrarPlazoTabla() {
		if (tablePlazos.getSelectionModel() != null && tablePlazos.getSelectedRow() != -1) {
			modeloTabla.removeRow(tablePlazos.getSelectedRow());
			this.numeroPlazos--;
		}
	}

	private JButton getBtnBorrarPlazoInscripcion() {
		if (btnBorrarPlazoInscripcion == null) {
			btnBorrarPlazoInscripcion = new JButton("Borrar Plazo Inscripcion");
			btnBorrarPlazoInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					borrarPlazoTabla();
					if (!btnAñadirPlazoInscripcion.isEnabled() && tablePlazos.getRowCount() < numeroMaxPlazos)
						btnAñadirPlazoInscripcion.setEnabled(true);
				}
			});
			btnBorrarPlazoInscripcion.setBounds(10, 373, 454, 38);
		}
		return btnBorrarPlazoInscripcion;
	}

	private JLabel getLblPlazos() {
		if (lblPlazos == null) {
			lblPlazos = new JLabel("PLAZOS");
			lblPlazos.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblPlazos.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlazos.setBounds(10, 134, 454, 29);
		}
		return lblPlazos;
	}
	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("CATEGORIAS");
			lblCategorias.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCategorias.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategorias.setBounds(583, 137, 331, 23);
		}
		return lblCategorias;
	}
	private JTable getTableCategorias() {
		if (tableCategorias == null) {
			tableCategorias = new JTable();
			tableCategorias.setRowHeight(20);
			String[] nombreColumnas = { "Nombre Categoria", "Limite Inferior de Edad", "Limite Superior de Edad"};
			modeloCategorias = new ModeloNoEditable(nombreColumnas, 0);
			cargaModelo();
			tableCategorias.setModel(modeloCategorias);
			tableCategorias.getColumnModel().getColumn(0).setPreferredWidth(188);
			tableCategorias.getColumnModel().getColumn(0).setMinWidth(30);
			tableCategorias.getColumnModel().getColumn(0).setMaxWidth(2147483644);
			tableCategorias.getColumnModel().getColumn(1).setPreferredWidth(174);
			tableCategorias.getColumnModel().getColumn(2).setPreferredWidth(205);
			tableCategorias.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return tableCategorias;
	}
	
	private void cargaModelo(){
		Object[] nuevaFila = new Object[3];
		nuevaFila[0] = "Senior" ;
		nuevaFila[1] = "18" ;
		nuevaFila[2] = "35" ;
		modeloCategorias.addRow(nuevaFila);
		
		nuevaFila = new Object[3];
		nuevaFila[0] = "Veterano A" ;
		nuevaFila[1] = "35" ;
		nuevaFila[2] = "40" ;
		modeloCategorias.addRow(nuevaFila);
		
		nuevaFila = new Object[3];
		nuevaFila[0] = "Veterano B" ;
		nuevaFila[1] = "40" ;
		nuevaFila[2] = "200" ;
		modeloCategorias.addRow(nuevaFila);
	}
	
	public void añadirFilaCategoria() {
		Object[] nuevaFila = new Object[3];
		nuevaFila[0] = getTextNombreCategoria().getText() ;
		nuevaFila[1] = getTextLimiteInferiorEdad().getText() ;
		nuevaFila[2] = getTextLimiteSuperiorEdad().getText() ;
		modeloCategorias.addRow(nuevaFila);
	}

	private void añadeCategoriaTabla() {
		if( esNumericoYNoVacio(getTextLimiteInferiorEdad().getText()) && esNumericoYNoVacio(getTextLimiteSuperiorEdad().getText()) && !getTextNombreCategoria().getText().isEmpty() )
			if(Integer.parseInt(getTextLimiteInferiorEdad().getText())<Integer.parseInt(getTextLimiteSuperiorEdad().getText()) && Integer.parseInt(getTextLimiteInferiorEdad().getText())>17){
				añadirFilaCategoria();
				numeroCategorias++;
				btnEliminarCategoria.setEnabled(true);
				btnAñadirCategoria.setEnabled(false);
			}
			else
				JOptionPane.showMessageDialog(this, "El limite inferior de edad no es menor que el superior, o permite inscribirse a los menores de edad");
			
		else 
			JOptionPane.showMessageDialog(this, "Los campos de los limites estan vacios o no son numericos o el campo del nombre se encuentra vacio");
			

	}
	
	private void borrarCategoriaTabla() {
		if (tableCategorias.getSelectionModel() != null && tableCategorias.getSelectedRow() != -1) {
			numeroCategorias--;
			btnEliminarCategoria.setEnabled(false);
			btnAñadirCategoria.setEnabled(true);
			modeloCategorias.removeRow(tableCategorias.getSelectedRow());	
		}
	}
	
	private JScrollPane getScrollPlazos() {
		if (scrollPlazos == null) {
			scrollPlazos = new JScrollPane();
			scrollPlazos.setBounds(10, 174, 454, 172);
			scrollPlazos.setViewportView(getTablePlazos());
		}
		return scrollPlazos;
	}
	private JScrollPane getScrollCategorias() {
		if (scrollCategorias == null) {
			scrollCategorias = new JScrollPane();
			scrollCategorias.setBounds(494, 171, 503, 106);
			scrollCategorias.setViewportView(getTableCategorias());
		}
		return scrollCategorias;
	}
	private JButton getBtnAñadirCategoria() {
		if (btnAñadirCategoria == null) {
			btnAñadirCategoria = new JButton("Añadir Categoria");
			btnAñadirCategoria.setBounds(10, 204, 483, 23);
			btnAñadirCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					añadeCategoriaTabla();
				}
			});
		}
		return btnAñadirCategoria;
	}
	private JButton getBtnEliminarCategoria() {
		if (btnEliminarCategoria == null) {
			btnEliminarCategoria = new JButton("Eliminar Categoria");
			btnEliminarCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrarCategoriaTabla();
				}
			});
			btnEliminarCategoria.setBounds(526, 288, 436, 38);
			btnEliminarCategoria.setEnabled(false);
		}
		return btnEliminarCategoria;
	}
	private JPanel getPanelCategoria() {
		if (panelCategoria == null) {
			panelCategoria = new JPanel();
			panelCategoria.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelCategoria.setBounds(494, 337, 503, 238);
			panelCategoria.setLayout(null);
			panelCategoria.add(getBtnAñadirCategoria());
			panelCategoria.add(getLblNombreCategoria());
			panelCategoria.add(getLblLimiteInferiorEdad());
			panelCategoria.add(getLblLimiteSuperiorEdad());
			panelCategoria.add(getTextNombreCategoria());
			panelCategoria.add(getTextLimiteInferiorEdad());
			panelCategoria.add(getTextLimiteSuperiorEdad());
		}
		return panelCategoria;
	}
	private JLabel getLblNombreCategoria() {
		if (lblNombreCategoria == null) {
			lblNombreCategoria = new JLabel("Nombre categoria:");
			lblNombreCategoria.setDisplayedMnemonic('c');
			lblNombreCategoria.setLabelFor(getTextNombreCategoria());
			lblNombreCategoria.setBounds(10, 27, 171, 28);
		}
		return lblNombreCategoria;
	}
	private JLabel getLblLimiteInferiorEdad() {
		if (lblLimiteInferiorEdad == null) {
			lblLimiteInferiorEdad = new JLabel("Limite inferior de edad:");
			lblLimiteInferiorEdad.setLabelFor(getTextLimiteInferiorEdad());
			lblLimiteInferiorEdad.setDisplayedMnemonic('i');
			lblLimiteInferiorEdad.setBounds(10, 79, 171, 28);
		}
		return lblLimiteInferiorEdad;
	}
	private JLabel getLblLimiteSuperiorEdad() {
		if (lblLimiteSuperiorEdad == null) {
			lblLimiteSuperiorEdad = new JLabel("Limite superior de edad:");
			lblLimiteSuperiorEdad.setDisplayedMnemonic('s');
			lblLimiteSuperiorEdad.setLabelFor(getTextLimiteSuperiorEdad());
			lblLimiteSuperiorEdad.setBounds(10, 122, 171, 28);
		}
		return lblLimiteSuperiorEdad;
	}
	private JTextField getTextNombreCategoria() {
		if (textNombreCategoria == null) {
			textNombreCategoria = new JTextField();
			textNombreCategoria.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(textNombreCategoria.getText().length()>20){
						JOptionPane.showMessageDialog(null, "El texto del nombre de la categoria es demasiado largo");
						textNombreCategoria.setText("");
					}
				}
			});
			textNombreCategoria.setBounds(191, 31, 291, 24);
			textNombreCategoria.setColumns(10);
		}
		return textNombreCategoria;
	}
	private JTextField getTextLimiteInferiorEdad() {
		if (textLimiteInferiorEdad == null) {
			textLimiteInferiorEdad = new JTextField();
			textLimiteInferiorEdad.setBounds(191, 83, 291, 24);
			textLimiteInferiorEdad.setColumns(10);
		}
		return textLimiteInferiorEdad;
	}
	private JTextField getTextLimiteSuperiorEdad() {
		if (textLimiteSuperiorEdad == null) {
			textLimiteSuperiorEdad = new JTextField();
			textLimiteSuperiorEdad.setBounds(191, 126, 291, 24);
			textLimiteSuperiorEdad.setColumns(10);
		}
		return textLimiteSuperiorEdad;
	}
}
