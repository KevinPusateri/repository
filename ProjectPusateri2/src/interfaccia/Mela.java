package interfaccia;

public class Mela implements Comparable<Mela> {
	private int grammi;
	private String tipo;

	public Mela(String tipo, int grammi) {
		this.tipo = tipo;
		this.grammi = grammi;
	}

	public int compareTo(Mela mela) throws Exception {
		int result = 0;
		boolean b = this.tipo.equalsIgnoreCase(mela.tipo);
		
		if(!b) // se le mele sono di tipo diverso
			// lancio un'eccezione
			throw new Exception("non posso confrontore mele di tipo diverso");
		
		int differenzaPeso = grammi - mela.grammi;
		result = differenzaPeso;
		
		return result;
	}
	
	public int compareTo(Pera pera) throws Exception {
		int result = 0;

		int differenzaPeso = grammi - pera.getGrammi();
		result = differenzaPeso;

		return result;
	}
	

	public int getGrammi() {
		return grammi;
	}

	public void setGrammi(int grammi) {
		this.grammi = grammi;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}