package database;

import java.util.ArrayList;

import logica.Carrera;

public class BaseCarreras {
	
	private ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	
	
	public BaseCarreras(){
		inicializar();
	}
	
	public void inicializar(){
		inicializarCarreras();
	}
	
	public void inicializarCarreras(){
		
	}
	
	
	public ArrayList<Carrera> getCarreras(){
		return carreras;
	}

}
