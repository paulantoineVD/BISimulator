import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;

import java.util.Arrays;


public class Main {
    public static void main (String[] args){

        MongoClient client = new MongoClient(new MongoClientURI("mongodb://projet:projetb1@ds227171.mlab.com:27171/projetbi?retryWrites=true"));
        MongoDatabase db = client.getDatabase("projetbi");

        MongoCollection<Document> collection = db.getCollection("commandes");

        AggregateIterable<Document> countries = collection.aggregate(
            Arrays.asList(
                new Document("$unwind", "$lignesCommande"),
                new Document("$group",
                    new Document("_id", "$country.name")
                        .append("count", new Document("$sum", 1))
                        .append("prix",  new Document("$sum",
                                new Document("$multiply", Arrays.asList("$lignesCommande.quantity", "$lignesCommande.prix"))
                            )
                        )
                        .append("avgPrice", new Document("$avg",
                                new Document("$multiply", Arrays.asList("$lignesCommande.quantity", "$lignesCommande.prix"))
                            )
                        )
                ),
                new Document("$sort",
                    new Document("count", -1)
                )
            )
        );

        for (Document country : countries) {
            System.out.println("--------------");
            System.out.println(country);
            AggregateIterable<Document> bonbons = collection.aggregate(
                Arrays.asList(
                    new Document("$unwind", "$lignesCommande"),
                    new Document("$match",
                            new Document("country.name", country.get("_id"))),

                    new Document("$group",
                            new Document("_id", "$lignesCommande.product")
                                .append("count", new Document("$sum", 1))
                    ),
                    new Document("$sort",
                        new Document("count", -1)
                    )
                )
            );

            for (Document bonbon : bonbons) {
                System.out.println(bonbon);
            }

            AggregateIterable<Document> colors = collection.aggregate(
                    Arrays.asList(
                            new Document("$unwind", "$lignesCommande"),
                            new Document("$match",
                                    new Document("country.name", country.get("_id"))),

                            new Document("$group",
                                    new Document("_id", "$lignesCommande.color")
                                            .append("count", new Document("$sum", 1))
                            ),
                            new Document("$sort",
                                new Document("count", -1)
                            )
                    )
            );
            for (Document color : colors) {
                System.out.println(color);
            }
        }
    }
}
