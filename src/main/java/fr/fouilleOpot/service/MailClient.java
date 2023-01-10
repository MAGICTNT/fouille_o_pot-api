package fr.fouilleOpot.service;



import fr.fouilleOpot.dto.EmailDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@RegisterRestClient
@Path("/email")
public interface MailClient {
    @POST
    @Path("/send")
    Response sendMail(EmailDto email);
}
