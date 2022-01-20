package co.com.sofka.wsscore.infra.materialize;

import co.com.sofka.wsscore.domain.category.events.CategoryCreated;
import co.com.sofka.wsscore.domain.category.events.ProductAdded;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class CategoryHandle {
    private final MongoClient mongoClient;

    public CategoryHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofkau.category.categorycreated", blocking = true)
    void consumeCategoryCreated(CategoryCreated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("title", event.getTitle());
        document.put("gender", event.getGender());
        document.put("description", event.getDescription());

        mongoClient.getDatabase("queries")
                .getCollection("category")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofkau.category.productadded", blocking = true)
    void consumeProductAdded(ProductAdded event) {
        BasicDBObject document = new BasicDBObject();
        document.put("product."+event.getProductId()+".name", event.getName());
        document.put("product."+event.getProductId()+".description", event.getDescription());
        document.put("product."+event.getProductId()+".price", event.getPrice());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("category")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }
}