package ltweb_weekly_proj.pc.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/ltweb_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "25092004chien";

	public static Connection getConnection(){
		try {
			Class.forName(DATABASE_DRIVER);
			return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
