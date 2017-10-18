package logica;

public class Categoria {
	
	private int limiteInferior;
	private int limiteSuperior;
	private String categoria;
	
	public Categoria(int linferior,int lsuperior,String cat){
		this.limiteInferior = linferior;
		this.limiteSuperior = lsuperior;
		this.categoria = cat;
	}

	public int getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public int getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(int limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
