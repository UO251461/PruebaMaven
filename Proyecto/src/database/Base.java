package database;

public class Base {
	private static final String USERNAME="IPS_2018_5";
	private static final String PASSWORD="hola2018";
	
	private BaseCarreras baseCarrera;
	
	
	
	
	public Base(){
		inicializar();
	}
	
	public void inicializar(){
		baseCarrera = new BaseCarreras();
	}	
	
	public BaseCarreras getBaseCarrera(){
		return baseCarrera;
	}
	
}
