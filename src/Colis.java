import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Colis extends Thread {

	//Variables
	public String reference;
	public String etat;
	
	public int position_precedente_colis;
	public int position_colis;
	public int position_suivante_colis;
	public int sortie = 41;
	
	public int position;
	
	int temps_pause_trajet = 0;
	int temps_pause_gare = 0;
	
	public ArrayList<Produit> produits;
	
	public double espace_total = 10;
	public double espace_utilise;
	

	public Colis()
	{
		reference =  UUID.randomUUID().toString();

		etat = "En attente";
		
		produits = new ArrayList<Produit>();
		
		position_precedente_colis = 0;
		position_colis = 0;
		position_suivante_colis = 0;
		
		espace_utilise = 0;	
	}
	
	
    @Override 
    public void run() { 
    	//System.out.println("Début de la préparation du colis : " + reference);
    	etat = "En preparation";
    	
        while(seDeplacer())
        {
        	ajoutDeLaReference();
        }       
    } 
    
	//Methodes
	
	//Deplace le colis au prochain point du chemin
	public boolean seDeplacer()
	{
		int difference = 0;

		
		try
		{
			//départ du colis
			if(position_colis == 0)
			{
				position_precedente_colis = position_colis;
				position_colis = produits.get(position).numeroDeGare;
			
				difference = position_colis - position_precedente_colis;
				
			}
			else
			{
				position_precedente_colis = position_colis;
				
				position_colis = produits.get(position).numeroDeGare;
				
				try
				{
					position_suivante_colis = produits.get(position + 1).numeroDeGare;
				}
				catch(Exception ex)
				{
					position_suivante_colis = 0;
				}
				
				difference = position_colis - position_precedente_colis;
			}
			
			if(position_colis != position_precedente_colis && position_colis == position_suivante_colis)
			{	
				try {
					this.sleep(temps_pause_trajet * difference);
				} catch (InterruptedException e) {
					System.out.println("Aucune pause lors du déplacement");
				}

				//System.out.println("Le colis de REF : " + this.reference + " est arrivé à la gare n°" + position_colis);
				position++;
			}
			else if (position_colis == position_precedente_colis && position_colis == position_suivante_colis)
			{
				position++;
			}
			else if (position_colis == position_precedente_colis && position_colis != position_suivante_colis )
			{
				position++;
			}
			else
			{
				try {
					this.sleep(temps_pause_trajet * difference);
				} catch (InterruptedException e) {
					System.out.println("Aucune pause lors du déplacement");
				}
				
				//System.out.println("Le colis est arrivé à la gare n°" + position_colis);
				position++;		
			}
		}
		catch (Exception ex)
		{
			//Boucle de sortie:	
			if(position_precedente_colis <= 20)
			{
				difference = 21 - position_precedente_colis;
			}
			else
			{
				difference = 41 - position_precedente_colis;
			}
			
			try {
				this.sleep(temps_pause_trajet * difference);
			} catch (InterruptedException e) {
				System.out.println("Aucune pause lors du déplacement");
			}
			
			//Il n'y a plus de référence à ajouter on sort
			etat = "A envoyer";
			//System.out.println("Fin de la préparation du colis REF: " + reference);
			return false;
		}
		
		return true;
	}
	
	public void ajoutDeLaReference()
	{
		
		if (position_colis == position_precedente_colis && position_colis == position_suivante_colis)
		{
			produits.get(position - 1).Ajouter();
			//System.out.println("Référence REF " + produits.get(position - 1).ref_produit + "ajoutée au colis REF : " + reference );	
		}
		else if (position_colis == position_precedente_colis && position_colis != position_suivante_colis )
		{
			produits.get(position - 1).Ajouter();
			//System.out.println("Référence REF: " + produits.get(position - 1).ref_produit + "ajoutée au colis REF : " + reference);	
		}
		else
		{		
			//System.out.println("En attente de(s) référence(s)");

			try {
				this.sleep(temps_pause_gare);
			} catch (InterruptedException e) {
				System.out.println("Aucune pause lors de l'ajout de la référence");
			}
			produits.get(position - 1).Ajouter();
			//System.out.println("Référence REF: " + produits.get(position - 1).ref_produit + " ajoutée au colis REF " + reference);	
		}
	}
	
	
	//Ajouter un produit dans le carton
	public void ajouterUnProduit(Produit produit)
	{
		produits.add(produit);
		espace_utilise += arrondi(produit.espace);
	}
	
	public boolean aDeLaPlace(Produit p) 
	{
		if(arrondi(espace_utilise)  + arrondi(p.espace) <= arrondi(espace_total))
		{
			return true;
		} 
		else
		{
			return false;
		}
	}
	
	private Double arrondi(Double d)
	{
		return Math.round(d *100.0)/100.0;
	}
	
	public int getNombreProduits()
	{
		return produits.size();
	}

	
	public void trierColis()
	{
		ArrayList<Produit> produitTrier = new ArrayList<Produit>();
		int indexProduit = 0;
		int numeroGare = 41;
		
		//
		while(produits.size() != 0)
		{			
			for(int i = 0; i < produits.size(); i++)
			{
				if(produits.get(i).numeroDeGare < numeroGare)
				{
					numeroGare = produits.get(i).numeroDeGare;
					indexProduit = i;
				}
			}
			
			numeroGare = 41;
			produitTrier.add(produits.get(indexProduit));
			produits.remove(indexProduit);
		}
		
		
		produits = produitTrier;
	}
	
	public void afficherGares()
	{
		for(Produit p : produits)
		{
			System.out.println(p.numeroDeGare);
		}
	}
	
	
	
	
	
	
	
}
