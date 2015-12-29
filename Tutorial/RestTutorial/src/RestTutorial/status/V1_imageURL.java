package RestTutorial.status;

import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;

import RestTutorial.dao.tube308;

@Path("/imageURL")
public class V1_imageURL {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(tube308.class.getName());
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnImageURL(@QueryParam("id") String id) {
		String returnString = null;
		JSONArray json = new JSONArray();
		tube308 dao = new tube308();
		try {
			
			if (id == null) {
				return Response.status(400).entity("Error: please specify id for this search").build();
			}
			
			json = dao.queryImageURLByID(id);
			returnString = json.toString().replaceAll("\\\\", "");
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		} finally {
			dao.closeConnection();
		}
		
		return Response.ok(returnString).build();
	}
	
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnImageURLInPathWay(@PathParam("id") String id) {
		String returnString = null;
		JSONArray json = new JSONArray();
		tube308 dao = new tube308();
		try {
			
			if (id == null) {
				return Response.status(400).entity("Error: please specify id for this search").build();
			}
			
			json = dao.queryImageURLByID(id);
			returnString = json.toString().replaceAll("\\\\", "");
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		} finally {
			dao.closeConnection();
		}
		
		return Response.ok(returnString).build();
	}
	
	
	
	
}