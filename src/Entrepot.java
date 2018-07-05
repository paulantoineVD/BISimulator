import java.util.ArrayList;

public class Entrepot {
	
	ArrayList<Zone> zones = new ArrayList<Zone>();
	
	
	public Entrepot()
	{
		String[] pays = {"Allemagne", "Autriche", "Belgique", "Bulgarie", "Chypre", "Croatie", "Danemark", 
				"Espagne", "Estonie", "Finlande", "France", "Grèce", "Hongie", "Irelande", "Italie",
				"Lettonie", "Lituanie", "Luxembourg", "Malte", "Pays-Bas", "Pologne", "Portugal",
				"République tchèque", "Roumanie", "Royaume-Uni", "Slovaquie", "Slovénie", "Suède",
				"USA", "Canada", "Mexique", "Japon", "Chine", "Afrique du sud"};
		String[] transport = {
				"Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion",
				"Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion",
				"Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion", "Camion",
				"Camion", "Avion", "Avion", "Avion", "Bateau", "Avion", "Bateau"};
		
		for(int i = 0; i < pays.length; i++)
		{
			Zone zone = new Zone(pays[i], transport[i]);
			zone.start();
			
			zones.add(zone);
		}
	}
	
	public void receptionnerCommandes(Commande c)
	{
		for(Zone z : zones)
		{
			if(z.pays.compareToIgnoreCase(c.pays) == 0)
			{
				System.out.println("Ajout dans l'entrepot la commande ref : " + c.reference);
				z.ajouterUneCommande(c);
			}
		}
	}
	
	public void fermer()
	{
		for(Zone zone : zones)
		{
			zone.fermer();
		}
	}
}
