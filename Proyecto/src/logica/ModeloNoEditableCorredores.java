package logica;

import java.util.ArrayList;

import javax.swing.AbstractListModel;


public class ModeloNoEditableCorredores extends AbstractListModel{

	ArrayList<Corredor> corredores = new ArrayList<Corredor>() ;
	

	
	@Override
	public int getSize() {
		return corredores.size();
	}

	@Override
	public Object getElementAt(int index) {
		Corredor corredor = corredores.get(index);
		return corredor.getDni();
	}
	
	public void añadirCorredor(Corredor corredor) {
		corredores.add(corredor);
		this.fireIntervalAdded(this, getSize(), getSize()+1);
	}

}
