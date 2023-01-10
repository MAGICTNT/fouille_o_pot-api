package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.entity.ThemeEntity;
import fr.fouilleOpot.repository.ThemeRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/theme")
@Tag(name = "Théme")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeRessource {
    @Inject
    ThemeRepository themeRepository;
    @GET
    public Response themes(){
        List<ThemeEntity> themeList = themeRepository.listAll();
        return Response.ok(themeList).build();
    }

    @GET
    @Path("{id}")
    public Response themeId(@PathParam("id") int id){
        return Response.ok(themeRepository.findById(id)).build();
    }
}
