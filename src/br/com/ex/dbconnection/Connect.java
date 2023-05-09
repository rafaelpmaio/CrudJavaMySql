package br.com.ex.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	private static String dbUrl = "jdbc:mysql://localhost:8080/database";
	private static String dbUser = "root";
	private static String dbPassword = "";

	public static Connection connect() {
		
		try {
			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			return connection;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
