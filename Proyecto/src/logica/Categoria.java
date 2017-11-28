package logica;

public class Categoria implements Comparable<Categoria>{

	private int limiteInferior;
	private int limiteSuperior;
	private String categoria;
	private String sexo;

	public Categoria(int linferior, int lsuperior, String cat, String sexo) {
		this.limiteInferior = linferior;
		this.limiteSuperior = lsuperior;
		this.categoria = cat;
		this.sexo = sexo;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
    @Override
    public int compareTo(Categoria c) {
        if (limiteSuperior < c.limiteSuperior) {
            return -1;
        }
        if (limiteSuperior > c.limiteSuperior) {
            return 1;
        }
        return 0;
    }

}
