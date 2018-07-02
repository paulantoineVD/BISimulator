import java.util.ArrayList;
import java.util.Date;

public class Colis extends Thread {

	//Variables
	public Long reference;
	public String etat;
	
	public int position_precedente_colis;
	public int position_colis;
	public int position_suivante_colis;
	public int sortie = 41;
	
	public int position;
	
	public ArrayList<Produit> produits;
	
	public int espace_total = 10;
	public int espace_utilise;
	
	//Constructeur(produits, espace_utilise)
	public Colis(ArrayList<Produit> p, int e)
	{
		reference =  new Date().getTime();
		
		etat = "En preparation";
		
		produits = p;
		
		position_precedente_colis = 0;
		position_colis = 0;
		position_suivante_colis = 0;
		
		espace_utilise = e;	
	}
	
	
    @Override 
    public void run() { 
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
		int temps_pause_trajet = 8000;
		
		
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
					this.sleep(8000 * difference);
				} catch (InterruptedException e) {
					System.out.println("Aucune pause lors du déplacement");
				}

				System.out.println("Le colis est arrivé à la gare n°" + position_colis);
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
					this.sleep(8000 * difference);
				} catch (InterruptedException e) {
					System.out.println("Aucune pause lors du déplacement");
				}
				
				System.out.println("Le colis est arrivé à la gare n°" + position_colis);
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
				this.sleep(8000 * difference);
			} catch (InterruptedException e) {
				System.out.println("Aucune pause lors du déplacement");
			}
			
			//Il n'y a plus de référence à ajouter on sort
			etat = "A envoyer";
			System.out.println("Fin de la préparation du colis REF: " + reference);
			return false;
		}
		
		return true;
	}
	
	public void ajoutDeLaReference()
	{
		
		if (position_colis == position_precedente_colis && position_colis == position_suivante_colis)
		{
			produits.get(position - 1).Ajouter();
			System.out.println("Référence ajoutée au colis " + produits.get(position - 1).ref_produit);	
		}
		else if (position_colis == position_precedente_colis && position_colis != position_suivante_colis )
		{
			produits.get(position - 1).Ajouter();
			System.out.println("Référence ajoutée au colis " + produits.get(position - 1).ref_produit);	
		}
		else
		{		
			System.out.println("En attente de(s) référence(s)");

			try {
				this.sleep(16000);
			} catch (InterruptedException e) {
				System.out.println("Aucune pause lors de l'ajout de la référence");
			}
			produits.get(position - 1).Ajouter();
			System.out.println("Référence ajoutée au colis " + produits.get(position - 1).ref_produit);	
		}
	}
	
	
	
	
}
