package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import sdm.DBConnection;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");
		
		// Test DB connection
		DBConnection db = new DBConnection();
		try {
			ResultSet res = db.selectQuery(5);
			while (res.next()) {
				System.out.println(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting to DB");
		}
		
	}

}
