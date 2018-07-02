import java.util.ArrayList;

public class Main 
{
	
	public static void main(String[] args)
	{
		//Initialisation de la chaine de production
		Preparation preparation = new Preparation();
		
		/*
		while(true)
		{
			//récupération des commandes à préparer
			
		}
		*/

		/*
		Produit produit = new Produit("Dragibus", "Sachet");
		Produit produit2 = new Produit("Mnms", "Boite");
		
		Carton carton = new Carton();
		carton.AjouterUnProduit(produit);
		carton.AjouterUnProduit(produit2);
		
		Gare gare1 = new Gare(0);
		Gare gare2 = new Gare(1);
		Gare gare3 = new Gare(2);
		
		carton.ajouterUneGare(gare1);
		carton.ajouterUneGare(gare2);
		carton.ajouterUneGare(gare3);
		
		System.out.println(carton.gares.size());
		
		carton.retirerUneGare();
		carton.retirerUneGare();
		
		System.out.println(carton.gares.get(0).numero);
		
		
		System.out.println(carton.ref_carton);
		System.out.println(carton.espace_utilise);
		*/
		
		ArrayList<Produit> produits = new ArrayList<Produit>();
		int e = 10;
		
		produits.add(new Produit("Acidofolio", "Sachet", 5));
		produits.add(new Produit("Schtroumf", "Sachet", 12));
		produits.add(new Produit("Dragibus", "Sachet", 21));
		produits.add(new Produit("Acidofolio", "Boite", 24));
		produits.add(new Produit("Dragibus", "Boite", 30));

		
		Colis colis = new Colis(produits, e);
		colis.start();
	}
}
