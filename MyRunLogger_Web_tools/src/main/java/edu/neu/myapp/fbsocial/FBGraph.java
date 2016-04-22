package edu.neu.myapp.fbsocial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class FBGraph {
	private String accessToken;

	public FBGraph(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getFBGraph() {
		String graph = null;
		try {

			//String g = "https://graph.facebook.com/me?" + accessToken;
			System.out.println("accessToken  " +accessToken);
			String g = "https://graph.facebook.com/me?fields=name,gender,email,first_name,last_name,picture&"+accessToken;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println("GRaph data :::" + graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public Map<String, String> getGraphData(String fbGraph) {
		Map<String, String> fbProfile = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(fbGraph);
			System.out.println(json);
			fbProfile.put("id", json.getString("id"));
			fbProfile.put("name", json.getString("name"));
			fbProfile.put("first_name", json.getString("first_name"));
			fbProfile.put("last_name", json.getString("last_name"));
			if (json.has("email"))
				fbProfile.put("email", json.getString("email"));
			if (json.has("gender"))
				fbProfile.put("gender", json.getString("gender"));
			if(json.has("picture")){
				JSONObject jsonProfilePicture = (JSONObject)json.get("picture");
				//System.out.println(jsonProfilePicture);
				jsonProfilePicture = (JSONObject)jsonProfilePicture.get("data");
				//System.out.println(jsonProfilePicture);
				
				fbProfile.put("profile",jsonProfilePicture.getString("url"));
				
			}
			System.out.println(fbProfile);
			
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}
}
