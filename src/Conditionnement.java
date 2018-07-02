
public class Conditionnement extends Machine{
	
	private String packaging;
	
	public Conditionnement() {
		super();
		this.packaging = "";
	}
	
	public Conditionnement(String packaging, int cadence, int changement) {
		super(cadence, changement);
		this.packaging = packaging;
	}
	

}
