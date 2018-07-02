
public class Fabrication extends Machine{
	private String[] variante;
	
	public String[] getVariante() {
		return variante;
	}

	public void setVariante(String[] variante) {
		this.variante = variante;
	}

	public Fabrication() {
		super();
		this.variante = new String[0];
	}
	
	public Fabrication(String[] variante, int cadence, int changement) {
		super(cadence, changement);
		this.variante = variante;
	}
	
}
