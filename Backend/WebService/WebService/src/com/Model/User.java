package com.Model;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.JSONObject;
import com.mysql.jdbc.Statement;

public class User {
	
	private int user_id;     
	private String name;         
	private String mobile_num;
	private String national_id;
	private String rate_total;
	private String positive_rate;
	private String image;
	private String email;
	private String password;
	
	public User() {}
	
	public User(String name, String mobile_num, String national_id, String image, String email, String password) {
		super();
		this.name = name;
		this.mobile_num = mobile_num;
		this.national_id = national_id;
		this.rate_total = "0";
		this.positive_rate = "0";
		this.image = image;
		this.email = email;
		this.password = password;
	}

	public static String testConn() {
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from user";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.executeQuery();

			return "OK";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}
	
	public static int signup(User user) {
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into user "
					+ "(`name`,`mobile_num`,`national_id`,`rate_total`,`positive_rate`, `image`, `email`, `password`) "
					+ "VALUES  (?,?,?,?,?,?,?,?)";
		    
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.name);
			stmt.setString(2, user.mobile_num);
			stmt.setString(3, user.national_id);
			stmt.setString(4, user.rate_total);
			stmt.setString(5, user.positive_rate);
			stmt.setString(6, ""/*user.image.toString()*/);
			stmt.setString(7, user.email);
			stmt.setString(8, user.password);
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				user.user_id = rs.getInt(1);				
				return user.user_id;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static User login(String email, String password) {
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from user where email = ? and password = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.user_id = rs.getInt(1);
				user.name = rs.getString("name");
				user.mobile_num = rs.getString("mobile_num");
				user.national_id = rs.getString("national_id");
				user.rate_total = rs.getString("rate_total");
				user.positive_rate = rs.getString("positive_rate");
				user.image = rs.getString("image");
				user.email = rs.getString("email");
				user.password = rs.getString("password");
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	public static User getUserById(int user_id) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from user where user_id = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.user_id = rs.getInt(1);
				user.name = rs.getString("name");
				user.mobile_num = rs.getString("mobile_num");
				user.national_id = rs.getString("national_id");
				user.rate_total = rs.getString("rate_total");
				user.positive_rate = rs.getString("positive_rate");
				user.image = rs.getString("image");
				user.email = rs.getString("email");
				user.password = rs.getString("password");
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject toJsonObject (User user) {
		JSONObject object = new JSONObject();
		object.put("user_id", user.user_id);
		object.put("name", user.name);
		object.put("mobile_num", user.mobile_num);
		object.put("national_id", user.national_id);
		object.put("rate_total", user.rate_total);
		object.put("positive_rate", user.positive_rate);
		object.put("image", user.image);
		object.put("email", user.email);
		object.put("password", user.password);
		
		return object;
	}
}
