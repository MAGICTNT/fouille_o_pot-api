package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.dto.IngredientDto;
import fr.fouilleOpot.entity.IngrediantEntity;
import fr.fouilleOpot.repository.IngredientRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("/ingredient")
@Tag(name = "Ingrédient")
@Produces(MediaType.APPLICATION_JSON)
public class IngredientRessource {
    @Inject
    IngredientRepository ingredientRepository;

    @GET
    public Response allIngredient(@Context UriInfo uriInfo){
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        for(IngrediantEntity ingrediant : ingredientRepository.listAll())
        {
            IngredientDto ingredientDto = new IngredientDto(ingrediant);
            String uri = uriInfo.getRequestUriBuilder().build().toString();
            ingredientDto.addLink("all", uri);
            ingredientDto.addLink("self" , uri + "/" + ingrediant.getIdIngrediant());
            ingredientDtoList.add(ingredientDto);
        }
        return Response.ok(ingredientDtoList).build();
    }

    @GET
    @Path("/{id}")
    public Response ingrediantById(@PathParam("id") int id, @Context UriInfo uriInfo)
    {
        IngrediantEntity ingrediantEntity = ingredientRepository.findById(id);
        List<IngredientDto> ingredientDtoList = new ArrayList<>();

        if(ingrediantEntity != null)
        {
            IngredientDto ingredientDto = new IngredientDto(ingrediantEntity);
            String uri = uriInfo.getRequestUriBuilder().build().toString();
            ingredientDto.addLink("all", uri.replace("/" + ingrediantEntity.getIdIngrediant(), ""));
            ingredientDto.addLink("self", uri);
            ingredientDtoList.add(ingredientDto);
            return Response.ok(ingredientDtoList).build();
        }
        return Response.ok("id non reconnu").build();
    }



    @POST
    @Path("/ajouterIngredient/{nom}/{idtype}/{iduniter}")
    @Transactional
    public void ajoutIngredient(@PathParam("nom")String nom,@PathParam("idtype")int idtype,
                                @PathParam("iduniter")int iduniter){
        IngrediantEntity ingrediant = new IngrediantEntity();
        ingrediant.setLabel(nom);
        ingrediant.setIdTypeIngredient(idtype);
        ingrediant.setIdUniter(iduniter);
        ingredientRepository.persist(ingrediant);
    }
    @POST
    @Path("/ajouterIngredient")
    @Transactional
    public void ajoutIngredient2(IngredientDto ingredientDto){
//        IngredientDto ingredientDto = new IngredientDto(ingrediant);
        IngrediantEntity ingrediant = new IngrediantEntity();
        ingrediant.setLabel(ingredientDto.getLabel());
        ingrediant.setIdTypeIngredient(ingredientDto.getIdIngrediant());
//        ingrediant.setIdUniter(ingredientDto.get);
        ingredientRepository.persist(ingrediant);
    }

    @PUT
    @Path("/modifierIngredient/{id}/{nom}/{idtype}/{iduniter}")
    @Transactional
    public void modifIngredient(@PathParam("id")int id,@PathParam("nom")String nom,
                                @PathParam("idtype")int idtype,@PathParam("iduniter")int iduniter){
        IngrediantEntity ingrediant = new IngrediantEntity();
        ingrediant.setIdIngrediant(id);
        ingrediant.setLabel(nom);
        ingrediant.setIdTypeIngredient(idtype);
        ingrediant.setIdUniter(iduniter);
       ingredientRepository.update( "label = '"+ingrediant.getLabel()+"' where id_ingrediant = "+ingrediant.getIdIngrediant());

    }

    @DELETE
    @Path("/supprimerIngredient/{id}")
    @Transactional
    public void updateIngredient(@PathParam("id")int id){
        IngrediantEntity ingrediant = new IngrediantEntity();
        ingrediant.setIdIngrediant(id);
        ingredientRepository.deleteById(ingrediant.getIdIngrediant());

    }

}
