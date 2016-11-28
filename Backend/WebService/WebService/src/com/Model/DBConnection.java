package com.Model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;
	public static Connection getActiveConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager
					.getConnection(Constants.dpUrl + "?"
							+ "user=" + Constants.dpUser + "&password="+ Constants.dpPassword);
			
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}