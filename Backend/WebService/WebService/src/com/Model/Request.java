package com.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.mysql.jdbc.Statement;

public class Request {
	private int user_id;
	private int request_id;
	private String from_lat;
	private String from_lng;
	private String to_lat;
	private String to_lng;
	private String description;
	private String state;
	private String data;
	private String time;
	private int user_id2;
	private int user_id3;
	private int user_id4;

	public Request() {
	}

	public Request(int user_id, String from_lat, String from_lng,
			String to_lat, String to_lng, String description, String state,
			String data, String time) {
		super();
		this.user_id = user_id;
		this.from_lat = from_lat;
		this.from_lng = from_lng;
		this.to_lat = to_lat;
		this.to_lng = to_lng;
		this.description = description;
		this.state = state;
		this.data = data;
		this.time = time;
	}

	public static int addRequest(Request request) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into request "
					+ "(`user_id`,`from_lat`,`from_lng`,`to_lat`,`to_lng`, `description`, `state`, `data`, `time`)"
					+ "VALUES  (?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, request.user_id);
			stmt.setString(2, request.from_lat);
			stmt.setString(3, request.from_lng);
			stmt.setString(4, request.to_lat);
			stmt.setString(5, request.to_lng);
			stmt.setString(6, request.description);
			stmt.setString(7, request.state);
			stmt.setString(8, request.data);
			stmt.setString(9, request.time);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				request.request_id = rs.getInt(1);
				return request.request_id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static ArrayList<Request> getRequests(String from_lat,
			String from_lng, String to_lat, String to_lng) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from request;";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Request> res = new ArrayList<Request>();
			while (rs.next()) {
				int i = 1;
				Request request = new Request();
				request.request_id = rs.getInt(i++);
				request.from_lat = rs.getString(i++);
				request.from_lng = rs.getString(i++);
				request.to_lat = rs.getString(i++);
				request.to_lng = rs.getString(i++);
				request.description = rs.getString(i++);
				request.state = rs.getString(i++);
				request.data = rs.getString(i++);
				request.time = rs.getString(i++);
				request.user_id = rs.getInt(i++);
				request.user_id2 = rs.getInt(i++);
				request.user_id3 = rs.getInt(i++);
				request.user_id4 = rs.getInt(i++);

				if (request.user_id2 == 0 || request.user_id3 == 0
						|| request.user_id4 == 0)
					res.add(request);
			}

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Request> getRequests(int user_id) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from request where user_id = ?;";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Request> res = new ArrayList<Request>();
			while (rs.next()) {
				int i = 1;
				Request request = new Request();
				request.request_id = rs.getInt(i++);
				request.from_lat = rs.getString(i++);
				request.from_lng = rs.getString(i++);
				request.to_lat = rs.getString(i++);
				request.to_lng = rs.getString(i++);
				request.description = rs.getString(i++);
				request.state = rs.getString(i++);
				request.data = rs.getString(i++);
				request.time = rs.getString(i++);
				request.user_id = rs.getInt(i++);
				request.user_id2 = rs.getInt(i++);
				request.user_id3 = rs.getInt(i++);
				request.user_id4 = rs.getInt(i++);

				
				res.add(request);
			}

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Request getRequestByid(int request_id) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from request where req_id = ? ;";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, request_id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int i = 1;
				Request request = new Request();
				request.request_id = rs.getInt(i++);
				request.from_lat = rs.getString(i++);
				request.from_lng = rs.getString(i++);
				request.to_lat = rs.getString(i++);
				request.to_lng = rs.getString(i++);
				request.description = rs.getString(i++);
				request.state = rs.getString(i++);
				request.data = rs.getString(i++);
				request.time = rs.getString(i++);
				request.user_id = rs.getInt(i++);
				request.user_id2 = rs.getInt(i++);
				request.user_id3 = rs.getInt(i++);
				request.user_id4 = rs.getInt(i++);

				return request;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addUser(int request_id, int user_id) {

		Request request = getRequestByid(request_id);
		int i;
		if (request.user_id2 != 0 && request.user_id3 != 0
				&& request.user_id4 != 0)
			return false;

		if (request.user_id2 == 0) {
			i = 2;
		} else if (request.user_id3 == 0) {
			i = 3;
		} else {
			i = 4;
		}

		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "UPDATE request SET user_id" + i + "=" + user_id
					+ " WHERE req_id = " + request_id + ";";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				request.request_id = rs.getInt(1);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean cancelRequest() {
		return false;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject toJsonObject(Request request) {
		JSONObject json = new JSONObject();

		json.put("request_id", request.request_id);
		json.put("from_lat", request.from_lat);
		json.put("from_lng", request.from_lng);
		json.put("to_lat", request.to_lat);
		json.put("to_lng", request.to_lng);
		json.put("description", request.description);
		json.put("state", request.state);
		json.put("data", request.data);
		json.put("time", request.time);
		json.put("user_id", request.user_id);
		json.put("user_id2", request.user_id2);
		json.put("user_id3", request.user_id3);
		json.put("user_id4", request.user_id4);

		return json;
	}

	@SuppressWarnings("unused")
	private static ArrayList<Request> sort(int from_lat, int from_lng,
			int to_lat, int to_lng) {
		return null;
	}

}
