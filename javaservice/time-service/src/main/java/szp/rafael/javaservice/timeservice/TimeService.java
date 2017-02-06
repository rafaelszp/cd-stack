package szp.rafael.javaservice.timeservice;

import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * Created by rafaelszp on 1/25/17.
 */
@Path("/times")
public class TimeService {

    Logger logger = Logger.getLogger(this.getClass());


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response get() throws UnknownHostException {
        LocalDateTime now = LocalDateTime.now();
        String responseString = InetAddress.getLocalHost().getHostAddress()+": "+now.toString();
        logger.infof("I will respond with %s on path %s",responseString,"/times");
        Response response = Response.ok(responseString).build();
        return response;
    }


    @GET
    @Path("/health")
    public Response health(){
        return Response.ok().build();
    }
}
