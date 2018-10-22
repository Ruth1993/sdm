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
import databaseAccess.DBConnection2;
import sdm.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");
		

//		// Test DB connection
//		Connection connection = DBConnection.getConnection();
//		try {
//			Statement Statement = connection.createStatement();
//			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.health_clubs");
//
//			while (res.next()) {
//				System.out.println(res.getString("id"));
//			}
//			
//			// Test Update
//			int testUpdate = connection
//			res.close();
//			Statement.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// Test DB connection2
		DBConnection2 DBConn2 = new DBConnection2();
		try {
			// Selecting from DB
			String selectQuery = "SELECT * FROM sdmproject.health_clubs";
			ResultSet res = DBConn2.query(selectQuery);
			while (res.next()) {
				System.out.println(res.getString("id"));
			}
			
			// Updating DB
			String updateQuery = "UPDATE sdmproject.persons_basic_info SET name = 'Sophie Martin 1' WHERE id='PE01' LIMIT 1;";
			int testUpdate = DBConn2.update(updateQuery);
			System.out.println("Update: "+testUpdate);
			
			// Close the ResultSet
			if (res != null) {
				res.close();
			}
			
			// Close the db connection
			DBConn2.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		/**
//		 * All of the below is for testing purposes
//		 */
//		
//		Date birth_date = new Date(1993, 10, 1);
//		List<Role> roles = new ArrayList<Role>();
//		roles.add(new Patient());
//		roles.add(new Employer());
//		
//		Person p1 = new Person(1, "Valentine", birth_date, "Female", "A", roles);
//		Person p2 = new Person(2, "Metin", birth_date, "Male", "B", null);
//		Person p3 = new Person(3, "Ivan", birth_date, "Male", "AB", null);
//		Person p4 = new Person(4, "Ruth", birth_date, "Female", "O", null);
		
		/*
		 * 
		 * p3 = new Doctor()
		 * 
		 * Doctor change to person
		 * p3.transfertoperson
		 */
		
//	
//		System.out.println(p1.patient().test());
//		System.out.println(p1.doctor().test());
//		System.out.println(p1.employer().test());

	}

}
