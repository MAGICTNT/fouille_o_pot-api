package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.dto.RecetteDto;
import fr.fouilleOpot.entity.RecetteEntity;
import fr.fouilleOpot.repository.RecetteRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("/recette")
@Tag(name = "Recette")
@Produces(MediaType.APPLICATION_JSON)
public class RecetteRessource {

    @Inject
    RecetteRepository recetteRepository;

    @GET
    public Response allRecette(@Context UriInfo uriInfo)
    {
        List<RecetteDto> recetteDtoList = new ArrayList<>();
        for(RecetteEntity recette : recetteRepository.listAll())
        {
            RecetteDto recetteDto = new RecetteDto(recette);
            String uri = uriInfo.getRequestUriBuilder().build().toString();
            recetteDto.addLink("all", uri);
            recetteDto.addLink("self", uri + "/" + recette.getIdRecette());
            recetteDtoList.add(recetteDto);
        }
        return Response.ok(recetteDtoList).build();
    }

    @GET
    @Path("/{id}")
    public Response recetteById(@PathParam("id") int id , @Context UriInfo uriInfo)
    {
        RecetteEntity recetteEntity = recetteRepository.findById(id);

        List<RecetteDto> recetteDtoList = new ArrayList<>();
        if(recetteEntity != null)
        {
            RecetteDto recetteDto = new RecetteDto(recetteEntity);
            String uri = uriInfo.getRequestUriBuilder().build().toString();
            recetteDto.addLink("all", uri.replace("/" + recetteEntity.getIdRecette(), ""));
            recetteDto.addLink("self", uri);
            recetteDtoList.add(recetteDto);
            return Response.ok(recetteDtoList).build();
        }

        return Response.ok("id non reconnu").build();
    }

}
