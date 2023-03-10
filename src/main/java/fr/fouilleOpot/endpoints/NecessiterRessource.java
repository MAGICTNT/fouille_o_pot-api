package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.repository.NecessiterRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/necessiter")
@Produces(MediaType.APPLICATION_JSON)
public class NecessiterRessource {

    @Inject
    NecessiterRepository necessiterRepository;

    @GET
    public Response allNecessiter(){
        return Response.ok().build();
    }
}
