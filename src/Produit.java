
public class Produit {
	
	public String ref_produit;
	public String contenant;
	public String etat;
	
	public int numeroDeGare;

	
	public double espace;
	
	
	public Produit(String r, String contenant, int nG)
	{
		ref_produit = r;
		etat = "A ajouter";
		
		if(contenant.compareToIgnoreCase("Sachet") == 0)
		{
			contenant = "Sachet";
			espace = 0.5;
		} 
		else if(contenant.compareToIgnoreCase("Boite") == 0)
		{
			contenant = "Boite";
			espace = 1;

		} 
		else if(contenant.compareToIgnoreCase("Echantillon") == 0)
		{
			contenant = "Echantillon";
			espace = 0.05;
		}
		
		numeroDeGare = nG;
	}
	
	public void Ajouter()
	{
		etat = "Ajoute";
	}
	
	public String getcontenant()
	{
		return contenant;
	}
}
