package sdm;

import java.sql.*;

public class DBConnection {
	private String db_driver = "com.mysql.jdbc.Driver";
	private String db_url = "jdbc:mysql://localhost:3306";
	private String db_username = "root";
	private String db_password = "";
	private String db_dbname = "telnetscans";
	

	public ResultSet selectQuery(int limit) throws SQLException {
		ResultSet res = null;
		
		Connection conn = null;
		try {
			// Connecting to DB
			conn = DriverManager.getConnection(this.db_url + "/" + this.db_dbname, this.db_username, this.db_password);
			
			// Query
			String selectQuery = "SELECT ip FROM scans limit ? ";
			PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
			selectStmt.setInt(1, limit);
			res = selectStmt.executeQuery();
			
//			while (res.next()) {
//				System.out.println(res.getRow());
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (conn != null) {
				conn.close();
			}
		}
		
		return res;
	}
}
