package fr.fouilleOpot.endpoints;


import fr.fouilleOpot.dto.EmailDto;
import fr.fouilleOpot.service.MailClient;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/mail")
@Tag(name = "Mail")
public class MailRessource {

    @Inject
    @RestClient
    MailClient mailClient;


    @POST
    @Path("/send")
    public Response send(EmailDto email){
        mailClient.sendMail(email);
        return Response.ok().build();
    }

}
