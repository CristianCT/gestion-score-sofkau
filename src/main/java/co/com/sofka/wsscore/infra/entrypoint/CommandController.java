package co.com.sofka.wsscore.infra.entrypoint;


import co.com.sofka.wsscore.domain.category.commands.AddProductCommand;
import co.com.sofka.wsscore.domain.category.commands.CreateCategoryCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createCategory")
    public Response executor(CreateCategoryCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addProduct")
    public Response executor(AddProductCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }
}