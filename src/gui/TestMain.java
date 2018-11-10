package gui;

import java.sql.Connection;

import databaseAccess.DBConnection;
import sdm.Person;

public class TestMain {
	public static void main(String[] args) {
		Connection connection = DBConnection.getConnection();
		Person p = new Person(25, "George Bush");
		new GUI(connection, p);
	}
}
