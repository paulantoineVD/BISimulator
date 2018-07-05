import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Commande extends Thread {
	
	public Object reference;
	
	public ArrayList<Produit> produits = new ArrayList<Produit>();
	public ArrayList<Colis> colis = new ArrayList<Colis>();

	public String etat;
	public String pays; 
	
	private Preparation preparation;
	
	public Commande(Object r, String p, Preparation prep)
	{	
		reference = r;
		pays = p;
		etat = "Preparation";
		
		preparation = prep;
		
		
		//Connexion à la base de données
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://projet:projetb1@ds227171.mlab.com:27171/projetbi?retryWrites=true"));
		MongoDatabase db = client.getDatabase("projetbi");
		
		
		MongoCollection<Document> collection = db.getCollection("commandes");
		
		Bson filtre = Filters.eq("_id", reference);
		Bson newValue = Updates.set("etat", "En preparation");    				    				
		collection.updateOne(filtre, newValue);
		
		client.close();
	}
	
    @Override 
    public void run() { 
    	
    	for(int i = 0; i < colis.size(); i++)
    	{
    		if(colis.get(i).etat == "En attente")
    		{
    			colis.get(i).start();
    		}
    	}
    	
        while(!commandeEstPrete())
        {}          
        
        etat = "Envoi";
        preparation.entrepot.receptionnerCommandes(this);
        
    } 
    
	private boolean commandeEstPrete()
	{
		int nbColisPret = 0;
		
		for(Colis c : colis)
		{
			if(c.etat == "A envoyer")
			{
				nbColisPret++;
			}
		}
		
		if(nbColisPret == colis.size())
		{
			etat = "Envoi";
			return true;
		}
		
		return false;
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
		
		ordonnerLesColisParGare();
	}
	
	private Colis dernierColis()
	{
		return colis.get(colis.size()-1);
	}
	
	private Produit dernierProduit()
	{
		return produits.get(produits.size()-1);
	}
	
	public int getNombreColis()
	{
		return colis.size();
	}
	
	public void ordonnerLesColisParGare()
	{	
		//Pour chaque colis
		for(Colis c : colis)
		{		
			c.trierColis();
		}
	}	
}
