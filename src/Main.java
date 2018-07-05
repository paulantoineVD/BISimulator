import java.lang.reflect.GenericSignatureFormatError;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Main 
{
	
	public static void main(String[] args)
	{
		
		//Initialisation de la chaine de production
		Preparation preparation = new Preparation();
		preparation.start();
		
		/*
		//Connexion à la base de données
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://projet:projetb1@ds227171.mlab.com:27171/projetbi?retryWrites=true"));
		MongoDatabase db = client.getDatabase("projetbi");
		
		
		MongoCollection<Document> collection = db.getCollection("commandes");
		
		Document doc = collection.find().first();
		System.out.println(doc.toJson());
		
		Object d = doc.get("_id");
		System.out.println(doc.get("_id"));
		System.out.println(doc.get("etat"));
		
		Bson filtre = Filters.eq("_id", doc.get("_id"));
		Bson newValue = Updates.set("etat", "Envoyee");    				    				
		collection.updateOne(filtre, newValue);
		
		Document doc2 = collection.find().first();
		
		System.out.println(doc2.get("_id"));
		System.out.println(doc2.get("etat"));
		/*
		MongoCollection<Document> collection = db.getCollection("commandes");
		
		Bson filtre = Filters.eq("_id", "5b3ce54599c30f3db7852a07");
		Bson dateEnvoi = Updates.currentDate("dateEnvoiPrevu");
		collection.updateOne(filtre, dateEnvoi);
		 */
			//		collection.updateOne(filtre, document);
		
		//client.close();
		
		
	}
	

}
