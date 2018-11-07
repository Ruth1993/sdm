package main;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
		Person p1 = new Person(1, "Alice", new String[]{"Doctor"}); // create new persons with ID and roles
		Person p2 = new Person(2, "Bob", new String[]{"Patient"});
		
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
		
		
//		// ENCRYPTION: file-based
		String outputFileName = "test.cpabe";
		File in = new File("README.md");
		p1.enc(in, policy, outputFileName);		
		
		// ENCRYPTION: string-based
		String plaintext = "I am healthy.";
		String ciphertext_p1 = p1.enc_string(plaintext, policy);
		System.out.println(ciphertext_p1);
		
//		// DECRYPTION: file-based
//		File in = new File("test.cpabe");
//		p1.dec(in);
//		
//		// DECRYPTION: string-based
//		byte[] cipherbytes;
//		try {
//			cipherbytes = Files.readAllBytes(Paths.get(in.getAbsolutePath(), ""));
//			String cipher_string = new String(cipherbytes);
//			p1.dec_string(cipher_string);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}