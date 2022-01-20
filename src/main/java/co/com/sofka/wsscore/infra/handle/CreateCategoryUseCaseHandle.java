package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.category.commands.CreateCategoryCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.CreateCategoryUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateCategoryUseCaseHandle extends UseCaseHandle {
    private final CreateCategoryUseCase createCategoryUseCase;

    public CreateCategoryUseCaseHandle(CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @ConsumeEvent(value = "sofkau.category.createcategory")
    void consumeBlocking(CreateCategoryCommand command) {
        var events = createCategoryUseCase.apply(command);
        saveCategory(command.getCategoryId(), events);
    }
}
