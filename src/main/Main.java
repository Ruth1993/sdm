package main;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

import cn.edu.pku.ss.crypto.abe.apiV2.Server;
import databaseAccess.DBConnection;
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
//			res.close();
//			Statement.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// Test system
		Server server = new Server(); // Initialize public and master key
		Person p1 = new Person(1, "Alice", new String[]{"Patient"}); // create new persons with ID and roles
		Person p2 = new Person(2, "Bob", new String[]{"Doctor"});
		
		String policy = "Doctor OR HealthClub";
		
		// Distribute public encryption key
		String PKJSONString = server.getPublicKeyInString();
		p1.setPK(PKJSONString);
		p2.setPK(PKJSONString);

		// Set individual secret keys based on attributes
		String SKJSONString = server.generateSecretKey(p1.getAttrs());
		p1.setSK(SKJSONString);
		
		SKJSONString = server.generateSecretKey(p2.getAttrs());
		p2.setSK(SKJSONString);
		
		
		// TODO we need to change the input file to a String[] with the parameters for the query and outputFileName to a String[] with the encrypted parameters
		String outputFileName = "test.cpabe";
		File in = new File("README.md");
		p1.enc(in, policy, outputFileName);
		
		String plaintext = "I am healthy.";
		String ciphertext = p1.enc_string(plaintext, policy);
		System.out.println(ciphertext);
//		[B@1165b38
		
		//
		in = new File(outputFileName);
//		THUClient.dec(in);
		p2.dec(in);
	}

}