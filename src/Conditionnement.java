
public class Conditionnement extends Machine{
	
	private String packaging;
	
	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public Conditionnement() {
		super();
		this.packaging = "";
	}
	
	public Conditionnement(String packaging, int cadence, int changement) {
		super(cadence, changement);
		this.packaging = packaging;
	}
	

}
