import java.util.ArrayList;

public class Commande {
	
	public String reference;
	
	public ArrayList<Produit> produits = new ArrayList<Produit>();
	public ArrayList<Colis> colis = new ArrayList<Colis>();

	
	public Commande(String r)
	{
		reference = r;
	}
	
	public void ajouterProduit(Produit p)
	{
		produits.add(p);
	}
	
	public void preparerColis()
	{
		int nbProduits = produits.size();

		
		colis.add(new Colis());
		
		while(nbProduits != 0)
		{				
			if(dernierColis().aDeLaPlace(dernierProduit()))
			{
				dernierColis().ajouterUnProduit(dernierProduit());
				nbProduits--;
			}
			else
			{
				colis.add(new Colis());
			}
		}		
	}
	
	private Colis dernierColis()
	{
		return colis.get(colis.size()-1);
	}
	
	private Produit dernierProduit()
	{
		return produits.get(produits.size()-1);
	}
	
	public int countType(String type)
	{
		int cpt = 0;
		
		for(int i = 0; i < produits.size(); i++)
		{
			if(produits.get(i).getConditionnement().compareToIgnoreCase(type) == 0)
			{
				cpt++;
			}
		}
		
		return cpt;
	}
	
	public int getNombreColis()
	{
		return colis.size();
	}
	
	
	
}
