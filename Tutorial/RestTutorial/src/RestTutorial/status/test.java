package RestTutorial.status;


import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/test")
public class test {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getCookie(@CookieParam("abc") Cookie cookie) {
		
		String returnString = cookie.getValue();
		
		
		return Response.ok(returnString).build();
	}
}
