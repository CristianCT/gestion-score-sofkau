package co.com.sofka.wsscore.usecases;

import co.com.sofka.wsscore.domain.category.Category;
import co.com.sofka.wsscore.domain.category.commands.AddProductCommand;
import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class AddProductUseCase implements Function<AddProductCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public AddProductUseCase(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AddProductCommand addProductCommand) {
        var events = repository.getEventsBy("category", addProductCommand.getCategoryId());
        var category = Category.from(addProductCommand.getCategoryId(), events);

        category.addProduct(addProductCommand.getProductId(), addProductCommand.getName(), addProductCommand.getDescription(), addProductCommand.getPrice());
        return category.getUncommittedChanges();
    }
}
