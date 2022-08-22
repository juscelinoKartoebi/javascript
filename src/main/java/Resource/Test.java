package Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class Test {
    @Path("getText")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText(){
        return "Works";
    }
}