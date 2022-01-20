package co.com.sofka.wsscore.domain.category.events;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

public class ProductAssigned extends DomainEvent {

    private String productId;
    private String name;
    private String description;
    private Double price;
    private String link;
    private String image;

    public ProductAssigned(String productId, String name, String description, Double price, String link, String image) {
        super("sofkau.category.productassigned");
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
        this.image = image;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
