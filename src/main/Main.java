package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sdm.Person;
import crypto.abe.api.Server;
import databaseAccess.DBConnection;
import databaseAccess.KBConnection;

public class Main {


	public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");
		String newLine = System.getProperty("line.separator");
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		Server server = new Server();

		server.setMasterKey(KBConnection.getMasterKey());
		server.setPublicKey(KBConnection.getPublicKey());
		String PKJSONString = server.getPublicKeyInString();
		System.out.println(PKJSONString);
		boolean exit = false;
		while (!exit) {
			System.out.print("Insert your id: ");
			int id = Integer.parseInt(bufRead.readLine());
			Person p = new Person(id);
			p.setPK(PKJSONString);
			System.out.print("Insert your secret key: ");
			String sk = bufRead.readLine();
			p.setSK("{\"SK\":\"" + sk + "\"}");
			p.startGUI();

		}
	}
}
