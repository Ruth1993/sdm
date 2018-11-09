package main;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.pku.ss.crypto.abe.Parser;
import cn.edu.pku.ss.crypto.abe.Policy;
import cn.edu.pku.ss.crypto.abe.apiV2.Server;
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
		
		// Test system
		Server server = new Server();
		Person p1 = new Person(1, "Alice", new String[]{"Patient"});
		Person p2 = new Person(2, "Bob", new String[]{"Doctor"});
		p1.startGUI(connection);
		
		
		//client从server处获取公钥字符串
		String PKJSONString = server.getPublicKeyInString();
		p1.setPK(PKJSONString);
		p2.setPK(PKJSONString);

		//client将自己的属性信息发送给server,并获取私钥字符串
		String SKJSONString = server.generateSecretKey(p1.getAttrs());
		p1.setSK(SKJSONString);
		
		SKJSONString = server.generateSecretKey(p2.getAttrs());
		p2.setSK(SKJSONString);
		
		// TODO we need to change the input file to a String[] with the parameters for the query and outputFileName to a String[] with the encrypted parameters
		String outputFileName = "test.cpabe";
		File in = new File("README.md");
		String policy = "(Student AND Teacher) OR (Student AND PhD)";
		p1.enc(in, policy, outputFileName);
		
		//解密
		in = new File(outputFileName);
//		THUClient.dec(in);
		p2.dec(in);
	}

}
