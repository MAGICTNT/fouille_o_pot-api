package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.entity.UniterMessureEntity;
import fr.fouilleOpot.repository.UniterMessureRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/unitermessure")
@Tag(name = "Uniter de messure")
@Produces(MediaType.APPLICATION_JSON)
public class UniterMessureRessource {
    @Inject
    UniterMessureRepository uniterMessureRepository;
    @GET
    public Response messure(){
        List<UniterMessureEntity> messureList = uniterMessureRepository.listAll();
        return Response.ok(messureList).build();
    }

    @GET
    @Path("{id}")
    public Response messureId(@PathParam("id") int id){
        return Response.ok(uniterMessureRepository.findById(id)).build();
    }

}
