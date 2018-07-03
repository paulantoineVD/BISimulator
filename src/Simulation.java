
public class Simulation {
	
	public static void main(String[] args) {
		
		String[] F1 = {"Acide"};
		String[] F2 = {"Sucré"};
		String[] F3 = {"Gélifié"};
		String[] F4 = {"Sucré", "Gelifié"};
		
		String C1 = "Sachet";
		String C2 = "Boite";
		String C3 = "Echantillon";
		
		Fabrication MF1 = new Fabrication(F1, 750, 25);
		Fabrication MF2 = new Fabrication(F2, 1230, 45);
		Fabrication MF3 = new Fabrication(F3, 625, 25);
		Fabrication MF4 = new Fabrication(F4, 1230, 25);
		
		Conditionnement MC1 = new Conditionnement(C1, 500, 15);
		Conditionnement MC2 = new Conditionnement(C1, 500, 15);
		Conditionnement MC3 = new Conditionnement(C1, 750, 25);
		Conditionnement MC4 = new Conditionnement(C2, 200, 10);
		Conditionnement MC5 = new Conditionnement(C2, 200, 10);
		Conditionnement MC6 = new Conditionnement(C3, 150, 15);
	}
}
