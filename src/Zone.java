import java.sql.Timestamp;
import java.util.ArrayList;

import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Zone extends Thread {
	
	public String pays;
	public String transport;
	
	public int nombre_palette;
	public int nombre_colis;
	
	public int nbMaxColis;
	
	public ArrayList<Commande> commandes = new ArrayList<Commande>();
	
	private boolean estOuvert;
	
	public Zone(String p, String t)
	{
		pays = p;
		transport = t;
		
		switch(transport)
		{
			case "Camion":
				nombre_colis = 40;
				nombre_palette = 33;
				break;
			case "Bateau":
				nombre_colis = 30;
				nombre_palette = 60;
				break;
			case "Avion":
				nombre_colis = 20;
				nombre_palette = 20;
				break;
			default:
		}
		
		nbMaxColis = nombre_colis*nombre_palette;
	}
	
    @Override 
    public void run() { 
    	estOuvert = true;
    	
    	while(estOuvert)
    	{
    		
    		if(envoyer())
    		{
    			System.out.println("Un " + transport + " vient de partir pour la/le " + pays);
    		}
    		else
    		{}
    		
			try {
				this.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("Aucune pause pour les zones");
			}
    	}
    } 
    
    //Envoi les colis
    public boolean envoyer()
    {
    	int nombreColis = 0;
    	int nombreColisCmdSuivante = 0;  	
    	
    	try
    	{
	    	for(int i = 0; i < commandes.size(); i++)
	    	{
	    		nombreColis += commandes.get(i).getNombreColis();
	    		nombreColisCmdSuivante = nombreColis + commandes.get(i+1).getNombreColis();
	    		
				//System.out.println(nombreColis + " <= " + nbMaxColis +  "&&" + nombreColisCmdSuivante + " > " + nbMaxColis);
	
	    		if(nombreColis <= nbMaxColis && nombreColisCmdSuivante > nbMaxColis)
	    		{
	    			for(int x = 0; x < i; x++)
	    			{
	    				commandes.get(x).etat = "Envoyee";
	    				
	    				//MODIFIER COMMANDE MONGODB
	    				
	    				
	    				//Connexion à la base de données
	    				MongoClient client = new MongoClient(new MongoClientURI("mongodb://projet:projetb1@ds227171.mlab.com:27171/projetbi?retryWrites=true"));
	    				MongoDatabase db = client.getDatabase("projetbi");
	    				
	    				
	    				MongoCollection<Document> collection = db.getCollection("commandes");
	    				
	    				Bson filtre = Filters.eq("_id", commandes.get(x).reference);
	    				Bson newValue = Updates.set("etat", "Envoyee");    				    				
	    				collection.updateOne(filtre, newValue);
	
	    				Bson dateEnvoi = Updates.currentDate("dateEnvoiPrevu");
	    				collection.updateOne(filtre, dateEnvoi);
	    				
	    				client.close();
	    				
	    				commandes.remove(x);
	    			}
	    			return true;
	    		} 
	    	}
    	}
    	catch(Exception ex)
    	{
    		
    	}
    	return false;
    }
    
	public void ajouterUneCommande(Commande c)
	{
		commandes.add(c);
	}
	
	public void fermer()
	{
		estOuvert = false;
	}
	
	
}
