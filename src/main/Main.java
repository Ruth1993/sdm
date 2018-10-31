package main;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import databaseAccess.DBConnection;
import sdm.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");		

		// Test DB connection
		Connection connection = DBConnection.getConnection();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.health_clubs");

			while (res.next()) {
				System.out.println(res.getString("id"));
			}
			res.close();
			Statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
