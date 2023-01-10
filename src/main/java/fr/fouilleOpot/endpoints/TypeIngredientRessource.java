package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.entity.TypeIngredientEntity;
import fr.fouilleOpot.repository.TypeIngredientRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/typeingredient")
@Tag(name = "Type d'ingredient")
@Produces(MediaType.APPLICATION_JSON)
public class TypeIngredientRessource {
    @Inject
    TypeIngredientRepository typeIngredientRepository;
    @GET
    public Response typeIngredients(){
        List<TypeIngredientEntity> listTypeIngredient = typeIngredientRepository.listAll();
        return Response.ok(listTypeIngredient).build();
    }
}
