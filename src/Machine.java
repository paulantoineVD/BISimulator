
public class Machine {
	protected int id;
	protected int cadence;
	protected int changement;
	
	public int getCadence() {
		return cadence;
	}

	public void setCadence(int cadence) {
		this.cadence = cadence;
	}

	public int getChangement() {
		return changement;
	}

	public void setChangement(int changement) {
		this.changement = changement;
	}

	public int getId() {
		return id;
	}

	public Machine() {
		this.id = 0;
		this.cadence = 0;
		this.changement = 0;
	}
	
	public Machine(int cadence, int changement) {
		this.cadence = cadence;
		this.changement = changement;
	}
	
}
