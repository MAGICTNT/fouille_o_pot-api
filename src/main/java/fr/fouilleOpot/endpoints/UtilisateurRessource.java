package fr.fouilleOpot.endpoints;

import fr.fouilleOpot.dto.EmailDto;
import fr.fouilleOpot.dto.UtilisateurDto;
import fr.fouilleOpot.entity.RoleEntity;
import fr.fouilleOpot.entity.UtilisateurEntity;
import fr.fouilleOpot.repository.UtilisateurRepository;
import fr.fouilleOpot.security.EncryptDecryptURL;
import fr.fouilleOpot.security.SecurityTools;
import fr.fouilleOpot.service.JwtService;
import fr.fouilleOpot.service.MailClient;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.RolesAllowed;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Path("utilisateur")
@Tag(name = "utilisateur")
//@PermitAll
@Produces(MediaType.APPLICATION_JSON)
public class UtilisateurRessource {

    @Inject
    @RestClient
    MailClient mailClient;
    @Inject
    UtilisateurRepository utilisateurRepository;

    @Inject
    JwtService jwtService;

    @Inject
    JsonWebToken jwt;




    @GET
    @RolesAllowed({"admin"})
    public Response allUser(@Context UriInfo uriInfo) {
        List<UtilisateurDto> utilisateurDtoList = new ArrayList<>();
        for(UtilisateurEntity utilisateur : utilisateurRepository.listAll())
        {
            UtilisateurDto utilisateurDto = new UtilisateurDto(utilisateur);
            String uri = uriInfo.getRequestUriBuilder().build().toString();
            utilisateurDto.addLink("self", uri + "/" + utilisateur.getId());
            utilisateurDtoList.add(utilisateurDto);
        }
        return Response.ok(utilisateurDtoList).build();
    }
    @GET
    @Path("{id}")
    @RolesAllowed({"admin"})
    public Response selfUser(@PathParam("id")int id,@Context UriInfo uriInfo) {
        UtilisateurEntity utilisateurEntity = utilisateurRepository.findById(id);
        return Response.ok((utilisateurEntity == null) ? "id incorrect" : utilisateurEntity).build();

    }

    @POST
    @Path("/token/{email}")
    public Response askToken(@PathParam("email") String email){

        for(UtilisateurEntity utilisateur : utilisateurRepository.listAll())
        {
            if(email.matches(utilisateur.getMail())){
                return Response.ok(SecurityTools.getToken(utilisateur)).build();
            }
        }
        return Response.ok("email innconu, Créé un compte").build();
    }


    @POST
    @Path("/register/mail={mail}/pseudo={pseudo}/mdp={mdp}")
    @Transactional
    public Response register(@PathParam("mail") String mail, @PathParam("pseudo") String pseudo,@PathParam("mdp") String mdp) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        boolean flag = false;
        for(UtilisateurEntity utilisateur : utilisateurRepository.listAll())
        {
            if(mail.matches(utilisateur.getMail())){
                flag = true;
                break;
            }
        }
        if (!flag) {
            UtilisateurEntity utilisateurEntity = new UtilisateurEntity(pseudo, mail, mdp);
            UtilisateurDto compte = new UtilisateurDto(utilisateurEntity);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 10);
            Date expiration = calendar.getTime();
            String cryptedInfos = String.format("e=%s&ps=%s&sp=%s&exp=%s&sum=%s"
                    , compte.getMail()
                    , compte.getMdp()
                    , compte.getPseudo()
                    , new SimpleDateFormat("dd-MM-yy-HH:mm:ss").format(expiration)
                    , EncryptDecryptURL.checksum(compte.getMail() + compte.getMdp()));
            String urlEncode = EncryptDecryptURL.encrypt(cryptedInfos);
            System.out.println("urlEncode -> " + urlEncode);
            String url = "http://localhost:8888/utilisateur/confirm/" + urlEncode;

            mailClient.sendMail(new EmailDto(compte.getMail(), "Creation de votre compte", url));
            return Response.ok("Un email de confirmation vient de vous être envoyé").build();
        }
        else {
            return Response.ok("cette email est déjà utiliser").build();
        }
    }




    /**
     * @author Maximus
     * @param code le code est le code récupéré par mail et permet d'entré lutilisateur en BDD
     * @info     map.get("sp") => mdp
     * @info    map.get("ps") => pseudo
     * @info    map.get("e") => mail
     */
    @GET
    @Transactional
    @Path("/confirm/{code}")
    public Response confirmation(@PathParam("code") String code) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        code.replace("%3D", "=");
        String decodedParams = EncryptDecryptURL.decrypt(code);
        Map<String, String> map = EncryptDecryptURL.buildMap(decodedParams);
        RoleEntity role = new RoleEntity();
        role.setId_role(1);

        try {
            Date expireAt = new SimpleDateFormat("dd-MM-yy-HH:mm:ss").parse(map.get("exp"));
            if (expireAt.before(Calendar.getInstance().getTime()))
                return Response.ok("Le lien n'est plus valide").status(400, "status 400").build();
        } catch (ParseException e) {
            return Response.ok("Le lien n'est pas valide").status(400, "status 400").build();
        }
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity( map.get("sp"),  map.get("e"), EncryptDecryptURL.encrypt(map.get("ps")), role);
        System.out.println(  utilisateurEntity.getPseudo() + " " + EncryptDecryptURL.decrypt(utilisateurEntity.getMdp()) + " " + utilisateurEntity.getMail());
        System.out.println("decodedParams --> " + decodedParams );
        utilisateurRepository.persist(utilisateurEntity);
//return null;
        return Response.ok(decodedParams).build();
    }

}
