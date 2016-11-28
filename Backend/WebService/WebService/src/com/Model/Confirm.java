package com.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.mysql.jdbc.Statement;

public class Confirm {
	private int confirm_id;
	private int user_id;
	private int request_id;
	private int state;
	public final static int send = 0;// 0 -> send
	public final static int accept = 1;// 1 -> accept
	public final static int refused = 2;// 2 -> refused
	private String from_lat;
	private String from_lng;
	private String to_lat;
	private String to_lng;
	
	public Confirm() {
	}

	public Confirm(int user_id, int request_id, int state, String from_lat,
			String from_lng, String to_lat, String to_lng) {
		super();
		this.user_id = user_id;
		this.request_id = request_id;
		this.state = state;
		this.from_lat = from_lat;
		this.from_lng = from_lng;
		this.to_lat = to_lat;
		this.to_lng = to_lng;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getConfirm_id() {
		return confirm_id;
	}

	public void setConfirm_id(int confirm_id) {
		this.confirm_id = confirm_id;
	}

	public static int add(Confirm confirm) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into confirm "
					+ "(`user_id`,`request_id`,`state`,`from_lat`,`from_lng`,`to_lat`,`to_lng`) " 
					+ "VALUES  (?,?,?,?,?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, confirm.user_id);
			stmt.setInt(2, confirm.request_id);
			stmt.setInt(3, confirm.state);
			stmt.setString(4, confirm.from_lat);
			stmt.setString(5, confirm.from_lng);
			stmt.setString(6, confirm.to_lat);
			stmt.setString(7, confirm.to_lng);

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				confirm.confirm_id = rs.getInt(1);
				return confirm.confirm_id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static ArrayList<Confirm> selectByRequest_id(int request_id) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from confirm where request_id = "+ request_id 
					+" and state = '0';";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Confirm> res = new ArrayList<Confirm>();
			while (rs.next()) {
				int i = 1;
				Confirm confirm = new Confirm();
				confirm.confirm_id = rs.getInt(i++);
				confirm.user_id = rs.getInt(i++);
				confirm.request_id = rs.getInt(i++);
				confirm.state = rs.getInt(i++);
				confirm.from_lat = rs.getString(i++);
				confirm.from_lng = rs.getString(i++);
				confirm.to_lat = rs.getString(i++);
				confirm.to_lng = rs.getString(i++);
				res.add(confirm);
			}

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Confirm> selectByUser_id(int user_id) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from confirm where user_id = "+ user_id +";";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Confirm> res = new ArrayList<Confirm>();
			while (rs.next()) {
				int i = 1;
				Confirm confirm = new Confirm();
				confirm.confirm_id = rs.getInt(i++);
				confirm.user_id = rs.getInt(i++);
				confirm.request_id = rs.getInt(i++);
				confirm.state = rs.getInt(i++);
				confirm.from_lat = rs.getString(i++);
				confirm.from_lng = rs.getString(i++);
				confirm.to_lat = rs.getString(i++);
				confirm.to_lng = rs.getString(i++);
				res.add(confirm);
			}

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Confirm selectByRequest_idAndUser_id(int request_id, int user_id) {
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "select * from confirm "
					+ "where user_id = "+ user_id +" and request_id = "+ request_id +";";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int i = 1;
				Confirm confirm = new Confirm();
				confirm.confirm_id = rs.getInt(i++);
				confirm.user_id = rs.getInt(i++);
				confirm.request_id = rs.getInt(i++);
				confirm.state = rs.getInt(i++);
				confirm.from_lat = rs.getString(i++);
				confirm.from_lng = rs.getString(i++);
				confirm.to_lat = rs.getString(i++);
				confirm.to_lng = rs.getString(i++);
				
				return confirm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean delete(int confirm_id) {
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Delete from confirm where `confirm_id` = "+ confirm_id + ";";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject toJsonObject(Confirm confirm) {
		JSONObject object = new JSONObject();
		object.put("confirm_id", confirm.confirm_id);
		object.put("user_id", confirm.user_id);
		object.put("request_id", confirm.request_id);
		object.put("state", confirm.state);
		object.put("from_lat", confirm.from_lat);
		object.put("from_lng", confirm.from_lng);
		object.put("to_lat", confirm.to_lat);
		object.put("to_lng", confirm.to_lng);
		return object;
	}

}
