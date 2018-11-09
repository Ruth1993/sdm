package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class KBConnection {

	static String driver = "com.mysql.jdbc.Driver";
	static String password = "root";
	static String user = "root";
	private static Connection connection = null;

	private static KBConnection instanta = null;

	public KBConnection() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildConnection();
	}

	public static Connection getConnection() {
		if (instanta == null) {
			instanta = new KBConnection();
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
		String url = "jdbc:mysql://localhost/sdmkeys";
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

	public static String getMasterKey() {
		Statement stmt;
		String MK = "{\"MK\":\"";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MasterKey FROM serverkeys");
			while (rs.next()) {
				MK = MK.concat(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MK = MK.concat("\"}");
		return MK;
	}

	public static String getPublicKey() throws SQLException {
		Statement stmt;
		String PK = "{\"PK\":\"";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT PublicKey FROM serverkeys");
			while (rs.next()) {
				PK = PK.concat(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PK = PK.concat("\"}");
		return PK;
	}

	public static String getSecretKey(int uid) throws SQLException {
		Statement stmt;
		String SK = "{\"SK\":\"";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SecretKey FROM clientskeys WHERE id=" + uid);
			while (rs.next()) {
				SK = SK.concat(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SK = SK.concat("\"}");
		return SK;
	}

	public static void insertSecretKey(int uid, String SKJSONString) {
		String SK = SKJSONString.substring(7, SKJSONString.length() - 2);
		update("INSERT INTO clientskeys (id, SecretKey) VALUES (" + uid + ",'" + SK + "')");
	}

	public static void updateSecretKey(int uid, String SKJSONString) {
		String SK = SKJSONString.substring(7, SKJSONString.length() - 2);
		update("UPDATE clientskeys SET SecretKey = '" + SK + "' WHERE id = " + uid);
	}

	public static void main(String[] args) throws SQLException {
		Connection connection = KBConnection.getConnection();
		System.out.println(getMasterKey());
		System.out.println(getPublicKey());
		System.out.println(getSecretKey(1));
		/*int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(year);*/
	}
}
