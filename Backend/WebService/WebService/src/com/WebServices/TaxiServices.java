package com.WebServices;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Model.*;
import com.sun.org.apache.bcel.internal.generic.INEG;

/*
 * This class will be called when url like http://localhost:8080/WebService/rest/TaxiServices is requested
 */
@Path("/TaxiServices")
public class TaxiServices {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String init() {
		String str = User.testConn();
		
		JSONObject object = new JSONObject();
		object.put("init", "Wellcom :D");
		object.put("dpState", str);
		return object.toJSONString();
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("password") String password) {
		
		User user = User.login(email, password);
		if (user == null)
			return new JSONObject().toJSONString();
			
		JSONObject json = User.toJsonObject(user);
		return json.toJSONString();
	}
	
	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signup(@FormParam("name") String name,
			@FormParam("mobile_num") String mobile_num,
			@FormParam("national_id") String national_id,
			@FormParam("image") String image,
			@FormParam("email") String email,
			@FormParam("password") String password) {
		
		User user = new User(name, mobile_num, national_id, null, email, password);
		
		int result = User.signup(user);
		
		JSONObject json = new JSONObject();
		json.put("state", result);
		return json.toJSONString();
	}
	
	@POST
	@Path("/getUserById")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("user_id") String user_id) {
		
		User user = User.getUserById(Integer.parseInt(user_id));
		
		JSONObject json = User.toJsonObject(user);
		return json.toJSONString();
	}
/******************************************************************************************************/	
	@POST
	@Path("/request")
	@Produces(MediaType.TEXT_PLAIN)
	public String request(@FormParam("user_id") int user_id,
			@FormParam("from_lat") String from_lat,
			@FormParam("from_lng") String from_lng,
			@FormParam("to_lat") String to_lat,
			@FormParam("to_lng") String to_lng,
			@FormParam("description") String description,
			@FormParam("state") String state,
			@FormParam("date") String data,
			@FormParam("time") String time) {
		
		Request request = new Request(user_id, from_lat, from_lng, to_lat, to_lng, description, state, data, time);
		int res = Request.addRequest(request);
		JSONObject json = new JSONObject();
		json.put("state", res);
		return json.toJSONString();
	}
	 
	@POST
	@Path("/getRequests")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRequests(@FormParam("from_lat") String from_lat,
			@FormParam("from_lng") String from_lng,
			@FormParam("to_lat") String to_lat,
			@FormParam("to_lng") String to_lng){
		
		ArrayList<Request> requests = Request.getRequests(from_lat, from_lng, to_lat, to_lng);
		
		JSONArray results = new JSONArray();
		for(Request request: requests){
			results.add(Request.toJsonObject(request));
		}
		
		JSONObject res = new JSONObject();
		res.put("result", results);
		
		return res.toJSONString();
	}
	
	@POST
	@Path("/getRequestsByUserId")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRequests(@FormParam("user_id") String user_id){
		
		ArrayList<Request> requests = Request.getRequests(Integer.parseInt(user_id));
		
		JSONArray results = new JSONArray();
		for(Request request: requests){
			results.add(Request.toJsonObject(request));
		}
		
		JSONObject res = new JSONObject();
		res.put("result", results);
		
		return res.toJSONString();
	}
	
	@POST
	@Path("/getRequestById")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRequestById(@FormParam("request_id") String request_id){
		
		Request request = Request.getRequestByid(Integer.parseInt(request_id));
		JSONObject res = Request.toJsonObject(request);		
		return res.toJSONString();
	}
	
	
/*******************************************************************************************************/	
	@POST
	@Path("/confirm")
	@Produces(MediaType.TEXT_PLAIN)
	public String confirm(@FormParam("user_id") int user_id,
			@FormParam("request_id") int request_id,
			@FormParam("state") int state,
			@FormParam("from_lat") String from_lat,
			@FormParam("from_lng") String from_lng,
			@FormParam("to_lat") String to_lat,
			@FormParam("to_lng") String to_lng) {
		
		Confirm confirm = new Confirm(user_id, request_id, state, from_lat, from_lng, to_lat, to_lng);
		int res = Confirm.add(confirm);
		JSONObject json = new JSONObject();
		json.put("state", res);
		return json.toJSONString();
	}
	
	@POST
	@Path("/getConfirmsByRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String getConfirmsByRequest(@FormParam("request_id") String request_id){
		
		int id = Integer.parseInt(request_id);
		ArrayList<Confirm> Confirms = Confirm.selectByRequest_id(id);
				
		JSONArray results = new JSONArray();
		for(Confirm confirm: Confirms){
			results.add(Confirm.toJsonObject(confirm));
		}
		
		JSONObject res = new JSONObject();
		res.put("result", results);
		
		return res.toJSONString();
	}
	
	@POST
	@Path("/getConfirmsByUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String getConfirmsByUser(@FormParam("user_id") String user_id){
		
		int id = Integer.parseInt(user_id);
		ArrayList<Confirm> Confirms = Confirm.selectByUser_id(id);
				
		JSONArray results = new JSONArray();
		for(Confirm confirm: Confirms){
			results.add(Confirm.toJsonObject(confirm));
		}
		
		JSONObject res = new JSONObject();
		res.put("result", results);
		
		return res.toJSONString();
	}
	//refused
	@POST
	@Path("/refusedRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String cancelRequest(@FormParam("request_id") String request_id
			, @FormParam("user_id") String user_id){
		
		int req_id = Integer.parseInt(request_id);
		int us_id = Integer.parseInt(user_id);
		
		Confirm confirm = Confirm.selectByRequest_idAndUser_id(req_id, us_id);
		JSONObject res = new JSONObject();
		
		if (Confirm.delete(confirm.getConfirm_id())){
			confirm.setState(Confirm.refused);
			Confirm.add(confirm);
			res.put("result", "OK");	
			return res.toJSONString();
		}
		res.put("result", "FAIL");	
		return res.toJSONString();
	}
	
	
	//accept
	@POST
	@Path("/acceptRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String acceptRequest(@FormParam("request_id") String request_id
			, @FormParam("user_id") String user_id){
		
		int req_id = Integer.parseInt(request_id);
		int us_id = Integer.parseInt(user_id);
		
		Confirm confirm = Confirm.selectByRequest_idAndUser_id(req_id, us_id);
		JSONObject res = new JSONObject();
		
		if (Confirm.delete(confirm.getConfirm_id())){
			confirm.setState(Confirm.accept);
			Confirm.add(confirm);
			Request.addUser(req_id, us_id);
			
			res.put("result", "OK");	
			return res.toJSONString();
		}
		res.put("result", "FAIL");	
		return res.toJSONString();
	}
	
}
