package databaseAccess;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection2 {
	private final String driver = "com.mysql.jdbc.Driver";
	private final String password = "root";
	private final String user = "root";
	private final String dburl = "jdbc:mysql://localhost";
	private final String dbname = "sdmproject";
	
	private Connection connection = null;

	/**
	 * Constructor for DBConnection
	 */
	public DBConnection2() {
		this.initiateConnection();
	}
	
	/**
	 * Initiate the DB Connection and store the session into this.connection
	 * This should only be accessed within DBConnection2 class.
	 */
	private void initiateConnection() {
		try {
			Class.forName(driver);
			
			String url = this.dburl+"/"+this.dbname;
			this.connection = DriverManager.getConnection(url, this.user, this.password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (!connection.isClosed())
					System.out.println("JDBC is successfully connected.");
			} catch (SQLException e) {
				displaySQLExceptions(e);
			}
		}
	}

	/**
	 * get the connection
	 * @return this.connection
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	/**
	 * Close the DBConnection
	 */
	public void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
	        } catch (SQLException e) { /* ignored */}
		}
	}

	/**
	 * Display the SQL Exceptions
	 * @param ex: the SQLException object
	 */
	public void displaySQLExceptions(SQLException ex) {
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

	/**
	 * Select something from the DB.
	 * @param selectQuery: the string of the select query
	 * @return ResultSet object, or null
	 */
	public ResultSet query(String selectQuery) {
		Statement stmt = null;
		try {
			if (this.connection == null) {
				initiateConnection();
			}
			stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

			return rs;
		} catch (SQLException e) {
			displaySQLExceptions(e);
		}
		
		if (stmt != null) {
	        try {
	        	stmt.close();
	        } catch (SQLException e) { /* ignored */}
	    }
		return null;
	}

	/**
	 * Update or insert something to or into the database.
	 * @param update: the string of the update query
	 * @return 1 if the update is successful, 0 otherwise
	 */
	public int update(String updateQuery) {
		int output = 0;
		Statement stmt = null;
		try {
			if (this.connection == null) {
				initiateConnection();
			}
			
			stmt = this.connection.createStatement();
			output = stmt.executeUpdate(updateQuery);
			
		} catch (SQLException e) {
			displaySQLExceptions(e);
		}
		
		if (stmt != null) {
	        try {
	        	stmt.close();
	        } catch (SQLException e) { /* ignored */}
	    }
		
		return output;
	}
}
