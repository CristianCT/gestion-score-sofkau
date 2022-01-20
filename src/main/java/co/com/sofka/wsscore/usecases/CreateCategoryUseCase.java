package co.com.sofka.wsscore.usecases;

import co.com.sofka.wsscore.domain.category.Category;
import co.com.sofka.wsscore.domain.category.commands.CreateCategoryCommand;
import co.com.sofka.wsscore.domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreateCategoryUseCase implements Function<CreateCategoryCommand, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CreateCategoryCommand createCategoryCommand) {
        var category = new Category(createCategoryCommand.getCategoryId(), createCategoryCommand.getTitle(), createCategoryCommand.getGender(), createCategoryCommand.getDescription());
        return category.getUncommittedChanges();
    }
}
