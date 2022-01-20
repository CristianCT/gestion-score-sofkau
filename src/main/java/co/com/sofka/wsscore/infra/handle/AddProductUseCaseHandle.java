package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.category.commands.AddProductCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.AddProductUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddProductUseCaseHandle extends UseCaseHandle {

    private final AddProductUseCase addProductUseCase;

    public AddProductUseCaseHandle(AddProductUseCase addProductUseCase) {
        this.addProductUseCase = addProductUseCase;
    }

    @ConsumeEvent(value = "sofkau.category.addproduct")
    void consumeBlocking(AddProductCommand command) {
        var events = addProductUseCase.apply(command);
        saveCategory(command.getCategoryId(), events);
    }
}
