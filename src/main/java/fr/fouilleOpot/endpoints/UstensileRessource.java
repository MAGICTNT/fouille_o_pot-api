package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.entity.UstensileEntity;
import fr.fouilleOpot.repository.UstensileRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ustensile")
@Tag(name = "Ustensile")
@Produces(MediaType.APPLICATION_JSON)
public class UstensileRessource {
    @Inject
    UstensileRepository ustensileRepository;
    @GET
    public Response ustensiles(){
        List<UstensileEntity> ustensileEntityList =  ustensileRepository.listAll();
        return Response.ok(ustensileEntityList).build();
    }

    @GET
    @Path("{id}")
    public Response ustensileId(@PathParam("id") Integer id){
       return Response.ok(ustensileRepository.findById(id)).build();
    }

    @POST
    @Path("/ajouterUstensile/{nom}")
    @Transactional
    public void addUstensile(@PathParam("nom") String nom){
        UstensileEntity ustensile = new UstensileEntity();
        ustensile.setLabel(nom);
        ustensileRepository.persist(ustensile);
    }

    @PUT
    @Path("/modifierUstensil/{id}/{nom}")
    @Transactional
    public void modifUstensile(@PathParam("id") int id,@PathParam("nom") String nom){
        UstensileEntity ustensile = new UstensileEntity();
        ustensile.setIdUstensile(id);
        ustensile.setLabel(nom);
        ustensileRepository.update("label = '"+ustensile.getLabel()+"' where id_ustensile = "+ustensile.getIdUstensile());

    }

    @DELETE
    @Path("/supprimerUstensile/{id}")
    @Transactional
    public void suppUstensile(@PathParam("id") int id){
        UstensileEntity ustensile = new UstensileEntity();
        ustensile.setIdUstensile(id);
        ustensileRepository.deleteById(ustensile.getIdUstensile());
    }
}
