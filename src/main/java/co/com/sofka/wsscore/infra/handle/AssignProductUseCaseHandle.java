package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.category.commands.AssignProductCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.ExtractProductUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssignProductUseCaseHandle extends UseCaseHandle {
    private final ExtractProductUseCase extractProductUseCase;

    public AssignProductUseCaseHandle(ExtractProductUseCase extractProductUseCase) {
        this.extractProductUseCase = extractProductUseCase;
    }

    @ConsumeEvent(value = "sofkau.category.assignproduct")
    void consumeBlocking(AssignProductCommand command) {
        var events = extractProductUseCase.apply(command);
        saveCategory(command.getCategoryId(), events);
    }
}
