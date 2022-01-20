package co.com.sofka.wsscore.domain.category.events;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

public class ProductAdded extends DomainEvent {

    private final String productId;
    private final String name;
    private final String description;
    private final Double price;

    public ProductAdded(String productId, String name, String description, Double price) {
        super("sofkau.category.productadded");
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
