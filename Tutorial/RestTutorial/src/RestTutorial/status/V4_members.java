package RestTutorial.status;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import RestTutorial.dao.tube308;

@Path("/v4/members")

public class V4_members {
	
	@Path("/{uname}")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("uname") String uname,
								String incomingData) throws Exception {
		
		String id;
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		tube308 dao = new tube308();
		System.out.println(incomingData);
		try {
			JSONObject data = new JSONObject(incomingData);
			id = data.optString("id");
			
			http_code = dao.deleteMember(Integer.parseInt(id));
			
			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully");
				
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
		
	}
}
