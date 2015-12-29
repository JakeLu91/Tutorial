package RestTutorial.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Test {
	public static void main(String[] args) throws JSONException {
		String url = "http://localhost:8080/RestTutorial/api/imageURL?id=--3iH7ezDwTGn5lolFMdhg";
		JSONObject j = new JSONObject();
		
		try {
			j.put("a", url);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(j.toString().replaceAll("\\\\", ""));
	
	}
}
