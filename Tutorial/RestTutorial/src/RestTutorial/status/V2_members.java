package RestTutorial.status;


import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import RestTutorial.dao.tube308;

@Path("/v2/members")
public class V2_members {
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMember(String incomingData) {
		String returningString = null;
		JSONObject jobject = new JSONObject();
		JSONArray jarray = new JSONArray();
		tube308 dao = new tube308();
		
		try {
			JSONObject data = new JSONObject(incomingData);
			System.out.println("JSON DATA: " + data.toString());
			int http_code = dao.insertIntoMembers(data.optInt("id"),
												data.optString("fname"),
												data.optString("lname"),
												data.optString("email"),
												data.optString("uname"),
												data.optString("pass"),
												data.optString("date"));
			
			if (http_code == 200) {
				jobject.put("HTTP_CODE", "200");
				jobject.put("MSG", "This member has been stored.");
				returningString = jarray.put(jobject).toString();
				
			} else {
				return Response.status(500).entity("Unable to store this member").build();
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		return Response.ok(returningString).build();
	}
}
