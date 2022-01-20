package co.com.sofka.wsscore.domain.category;

import co.com.sofka.wsscore.domain.category.events.CategoryCreated;
import co.com.sofka.wsscore.domain.category.events.ProductAdded;
import co.com.sofka.wsscore.domain.category.events.ProductAssigned;
import co.com.sofka.wsscore.domain.generic.EventChange;
import java.util.HashSet;

public class CategoryEventChange implements EventChange {
    public CategoryEventChange(Category category) {
        listener((CategoryCreated event)-> {
            category.title = event.getTitle();
            category.gender = event.getGender();
            category.description = event.getDescription();
            category.products = new HashSet<>();
        });
        listener((ProductAdded event) -> {
            category.products.add(
                new Product(
                    event.getProductId(),
                    event.getName(),
                    event.getDescription(),
                    event.getPrice()
                )
            );
        });

        listener((ProductAssigned event) -> {
            category.products.add(
                new Product(
                    event.getProductId(),
                    event.getName(),
                    event.getDescription(),
                    event.getPrice(),
                    event.getLink(),
                    event.getImage()
                )
            );
        });
    }
}
