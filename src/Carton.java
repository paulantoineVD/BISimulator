import java.util.ArrayList;
import java.util.Date;

public class Carton extends Thread {
	
	public Long ref_carton;
	
	//Référence de la commande
	public String ref_commande;
	
	//Espace
	double espace_total = 10;
	public double espace_utilise;
	
	 //false: vide / true: plein
	public boolean etat;

	//Les emplacements ou sont stockées les produits
	ArrayList<Gare> gares = new ArrayList<Gare>();
	
	//Les produits dans le carton
	ArrayList<Produit> produits = new ArrayList<Produit>();
	
	
	int numero_gare;
	
	public Carton()
	{	
		ref_carton = new Date().getTime();
		etat = false;
		espace_utilise = 0;
	}

	//Ajouter une gare 
	public void ajouterUneGare(Gare gare)
	{
		gares.add(gare);
	}
	
	//Supprime la gare passé
	public void retirerUneGare()
	{
		gares.remove(0);
	}
	
	//Ajouter un produit dans le carton
	public boolean AjouterUnProduit(Produit produit)
	{
		if(espace_utilise  + produit.espace < espace_total)
		{
			produits.add(produit);
			espace_utilise += produit.espace;
		} 
		else
		{
			//Impossible d'ajouter le produit pas assez de place
			return false;
		}
		
		//Si il reste moins de 0.005 le colis est plein
		if(espace_total - espace_utilise < 0.005)
		{
			etat = true;
		}
		
		return true;
	}
	
	public void seDeplacer()
	{
		Gare gare = gares.get(0);
		
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


