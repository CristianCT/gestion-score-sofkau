package co.com.sofka.wsscore.domain.category;

import co.com.sofka.wsscore.domain.category.events.CategoryCreated;
import co.com.sofka.wsscore.domain.category.events.ProductAdded;
import co.com.sofka.wsscore.domain.category.events.ProductAssigned;
import co.com.sofka.wsscore.domain.generic.AggregateRoot;
import co.com.sofka.wsscore.domain.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Category extends AggregateRoot {

    protected String title;
    protected String gender;
    protected String description;
    protected Set<Product> products;

    public Category(String id, String title, String gender, String description) {
        super(id);
        subscribe(new CategoryEventChange(this));
        appendChange(new CategoryCreated(title, gender, description));
    }

    private Category(String id){
        super(id);
        subscribe(new CategoryEventChange(this));
    }

    public static Category from(String categoryId, List<DomainEvent> events) {
        var category = new Category(categoryId);
        events.forEach(category::applyEvent);
        return category;
    }

    public void addProduct(String productId, String name, String description, Double price){
        appendChange(new ProductAdded(productId, name, description, price));
    }

    public void assignProduct(String productId, String name, String description, Double price, String link, String image){
        appendChange(new ProductAssigned(productId, name, description, price, link, image));
    }

    public void updateProduct(){
        // TODO: implement this method
    }

    public String title() {
        return title;
    }

    public String gender() {
        return gender;
    }

    public String description() {
        return description;
    }

    public Set<Product> products() {
        return products;
    }
}
