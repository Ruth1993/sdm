package sdm;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import databaseAccess.DBConnection;

public class Company {
	private String id;
	private String name;

	public Company(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addEmployer(int id) {
		// to be implemented
		// sql query, insert employer id (id of person that is the employer?)
		// and company id into Employments
	}

	Connection connection = DBConnection.getConnection();

	public List<Company> getAll() {
		List<Company> companies = new LinkedList<>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.companies");
			while (res.next()) {
				Company company = new Company(res.getString("id"), res.getString("name"));
				companies.add(company);
			}
			res.close();
			Statement.close();
			return companies;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Company get(String id) {
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.companies WHERE id = " + id);
			res.first();
			Company company = new Company(res.getString("id"), res.getString("name"));
			res.close();
			Statement.close();
			return company;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void update(Company t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate("UPDATE sdmproject.companies SET id='" + t.getId() + "',name='"
					+ t.getName());
			Statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(Company t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate("DELETE FROM sdmproject.companies WHERE id = '" + t.getId() + "'");
			Statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void create(Company t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate("INSERT INTO sdmproject.companies VALUES ('" + t.getId() + "','"
						+ t.getName() + "')");
			Statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
