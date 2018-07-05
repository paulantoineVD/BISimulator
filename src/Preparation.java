import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Preparation extends Thread {
	
	
	ArrayList<Boucle> boucles = new ArrayList<Boucle>();
	ArrayList<String> references;
	ArrayList<Commande> commandes = new ArrayList<Commande>();
	
	Entrepot entrepot;
	
	public Preparation()
	{
		entrepot = new Entrepot();
		
		//Génération des références
		references = genererReference();
		
		ArrayList<String> referencesParGare = new ArrayList<String>();
		
		int refX = 0;
		int refMaxParGare = 100;
		
		boucles.add(new Boucle());
		boucles.add(new Boucle());		
		
		System.out.println("Initialisation de la boucle N*1");
		
		//Boucle n*1
		for(int i = 1; i < 20; i++)
		{
			Gare gare = new Gare(i);
			
			//Ajoute les différentes références pour la gare
			for(int position = refX; position < i*refMaxParGare; position++)
			{
				gare.ref_produits.add(references.get(position));
			}
			refX += 100;
			
			boucles.get(0).ajouterGare(gare);
		}
		
		System.out.println("Initialisation de la boucle N*2");
		
		try
		{
			//Boucle n*2
			for(int i = 21; i < 40; i++)
			{
				Gare gare = new Gare(i);
				
				//Ajoute les différentes références pour la gare
				for(int position = refX; position < i*refMaxParGare; position++)
				{
					gare.ref_produits.add(references.get(position));
				}
				refX += 100;
				
				boucles.get(1).ajouterGare(gare);
			}
		}
		catch (Exception ex)
		{
			//Les references sont initialisées
		}
		
		System.out.println("Prêt !");
	}
	
	//THREAD
    @Override 
    public void run() { 

    	boolean enCours = true;
    	
    	while(enCours)
    	{
	    	recupererLesCommandes();
	    	
			for(Commande c : commandes)
			{
				System.out.println("Préparation de la commande : " + c.reference);
				c.start();
				
				try {
					this.sleep(0);
				} catch (InterruptedException e) {
					System.out.println("Aucune pause lors de l'ajout de la référence");
				}
			}
			
	   		retirerCommande();

    	}
    	
		entrepot.fermer();
		System.out.println("Fin");
    } 
	
    public void retirerCommande()
    {
    	int i = 0;
    	for(Commande c : commandes)
    	{
    		if(c.etat == "Envoi" || c.etat == "Envoyee")
    		{
    			commandes.remove(i);
    		}
    		
    		i++;
    	}
    }
	
	//Recupere la gare n*x
	public Gare getGare(int numero)
	{
		if(numero < 21)
		{
			return boucles.get(0).getGareNumero(numero);
		}
		else
		{
			return boucles.get(1).getGareNumero(numero);
		}		
	}
	
	//Recupere le numero d'une gare par rapport à sa ref
	public int getNumeroGare(String ref)
	{
		for(int i = 0; i < boucles.size(); i++)
		{
			for (Gare gare : boucles.get(i).gares)
			{
				if(gare.referenceEstDansLaGare(ref))
				{
					return gare.numero;
				}
			}
		}
		
		return 41;
	}
	
	//Generation des références de chaque produit;
	private ArrayList<String> genererReference()
	{
		ArrayList<String> references = new ArrayList<String>();

		String[] nom = {"Acidofilo", "Bouteille cola", "Brazil pik", "Color Schtroummpf pik", "Langues acides", 
				"London pik", "Miami pik", "Pasta Basta", "Pasta frutta", "Sour snup", "Dragibus", "Carensac", 
				"Fraizibus", "Grain de millet", "Starmint", "Florent violette", "Kimono", "Pain Zan", "Rotella", 
				"Zanoïd", "Fraise tagada", "Croco", "Chamallows", "Polka", "Banane", "Ourson", "Filament"};
		
		
		String[] couleur = {"Rouge", "Orange", "Jaune", "Vert", "Bleu", "Violet", "Noir", "Marron"};
		String[] variante = {"Acide", "Sucre", "Gelifie"};
		String[] texture = {"Mou", "Dur"};
		String[] conditionnement = {"Sachet", "Boite", "Echantillon"};
		

		for(int a = 0; a < nom.length; a++)
		{
			for(int b = 0; b < couleur.length; b++)
			{
				for(int c = 0; c < variante.length; c++)
				{
					for(int d = 0; d < texture.length; d++)
					{
						for(int e = 0; e < conditionnement.length; e++)
						{
							references.add(a  + "-" + b + "-" + c + "-" + d + "-" + e);
						}
					}
				}
			}
		}
		
		return references;
	}
	
	private void recupererLesCommandes()
	{		
		//Connexion à la base de données
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://projet:projetb1@ds227171.mlab.com:27171/projetbi?retryWrites=true"));
		MongoDatabase db = client.getDatabase("projetbi");
		
		
		MongoCollection<Document> collection = db.getCollection("commandes");

		//Recuperation des commandes
		Bson filtre = Filters.eq("etat", "Preparation");
		for (Document commande : collection.find(filtre))
		{			
			//System.out.println("");
			//System.out.println("ref_commande :" + commande.get("_id"));
			ArrayList<Document> lignes = (ArrayList<Document>)commande.get("lignesCommande");
			

			//Recuperation de l'id de la commande
			Object id = commande.get("_id");
			String name_country = ((Document) commande.get("country")).getString("name");
			
			Commande cmd = new Commande(id, name_country, this);
			

			//Insertion des produits dans les commandes
			for (Document ligne : lignes)
			{
				//System.out.println("\n\tProduit N* : ");
				//System.out.println("\tref : " + ligne.get("reference"));
				//System.out.println("\tConditionnement : " + ligne.get("conditionnement") + "\n");
				
				String ref = ligne.getString("reference");
				int quantite = ligne.getInteger("quantity");
				
				for(int x = 0; x < quantite; x++)
				{
					cmd.ajouterProduit(new Produit(ref, getNumeroGare(ref)));
				}
			}
			//System.out.println(cmd.produits.size());
			
			//On prépare le nombre de colis à envoyer
			cmd.preparerColis();
			
			//On ajoute les colis à la liste des comandes
			commandes.add(cmd);
		}
		
		System.out.println(commandes.size() + " commandes a preparer...");	
		client.close();	
		
	}
}
