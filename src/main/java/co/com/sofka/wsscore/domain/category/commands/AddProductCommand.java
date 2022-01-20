package co.com.sofka.wsscore.domain.category.commands;

import co.com.sofka.wsscore.domain.generic.Command;

public class AddProductCommand extends Command {

    private String categoryId;
    private String productId;
    private String name;
    private String description;
    private Double price;

    public AddProductCommand(String categoryId, String productId, String name, String description, Double price) {
        this.categoryId = categoryId;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public AddProductCommand() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
}
