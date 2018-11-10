package databaseAccess;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	static String driver = "com.mysql.jdbc.Driver";
	static String password = "root";
	static String user = "root";
	private static Connection connection = null;

	private static DBConnection instanta = null;

	public DBConnection() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		buildConnection();
	}

	public static Connection getConnection() {
		if (instanta == null) {
			instanta = new DBConnection();
		}
		return connection;
	}

	public static void dispaySQLExceptions(SQLException ex) {
		while (ex != null) {
			System.out.println("SQL State:" + ex.getSQLState());
			System.out.println("Error Code:" + ex.getErrorCode());
			System.out.println("Message:" + ex.getMessage());
			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause:" + t);
				t = t.getCause();
			}
			ex = ex.getNextException();
		}
	}

	public static void buildConnection() {
		String url = "jdbc:mysql://localhost/sdmproject";
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!connection.isClosed())
					System.out.println("JDBC Successfully connected.");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static ResultSet query(String query) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			stmt.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void update(String update) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(update);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
