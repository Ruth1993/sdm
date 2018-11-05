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
		

		/**
		 * All of the below is for testing purposes
		 */
		
		//Date birth_date = new Date(1993, 10, 1);
		//List<Role> roles = new ArrayList<Role>();
		//roles.add(new Patient());
		//roles.add(new Employer());
		/*
		Person p1 = new Person(1, "Valentine", birth_date, "Female", "A", roles);
		Person p2 = new Person(2, "Metin", birth_date, "Male", "B", null);
		Person p3 = new Person(3, "Ivan", birth_date, "Male", "AB", null);
		Person p4 = new Person(4, "Ruth", birth_date, "Female", "O", null);
	
		System.out.println(p1.patient().test());
		System.out.println(p1.doctor().test());
		System.out.println(p1.employer().test());*/

	}

}
