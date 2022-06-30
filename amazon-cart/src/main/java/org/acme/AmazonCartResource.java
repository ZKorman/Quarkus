package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


// @SecurityScheme(
//         scheme = "bearer",
//         type = SecuritySchemeType.HTTP,
//         bearerFormat = "JWT"
// )
@Path("/api/cart")
@ApplicationScoped
public class AmazonCartResource {

    List<AmazonItem> items = new ArrayList<>();

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(){
        return Response.ok(items).build();
    }

    @POST
    @RolesAllowed({"writer"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(AmazonItem amazonItem){
        items.add(amazonItem);
        return Response.ok(items).build();
    }

    @Path("{id}")
    @DELETE
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id")Long id){
        items.stream().filter(item -> item.getId().equals(id))
                                .findFirst()
                                .ifPresent(item -> items.remove(item));
        return Response.noContent().build();
       
    }
    
}
