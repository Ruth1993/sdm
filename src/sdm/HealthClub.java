package sdm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import databaseAccess.DBConnection;

public class HealthClub {
	private String id;
	private String name;

	public HealthClub(String id, String name) {
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

	public List<Person> getMembers() {
		// to be implemented
		// sql query
		return null;
	}

	public Person getMember(int id) {
		// to be implemented
		// sql query
		return null;
	}

	Connection connection = DBConnection.getConnection();

	public List<HealthClub> getAll() {
		List<HealthClub> healthClubs = new LinkedList<>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.health_clubs");
			while (res.next()) {
				HealthClub healthClub = new HealthClub(res.getString("id"), res.getString("name"));
				healthClubs.add(healthClub);
			}
			res.close();
			Statement.close();
			return healthClubs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public HealthClub get(String id) {
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.health_clubs WHERE id = " + id);
			res.first();
			HealthClub healthClub = new HealthClub(res.getString("id"), res.getString("name"));
			res.close();
			Statement.close();
			return healthClub;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void update(HealthClub t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate("UPDATE sdmproject.health_clubs SET id='" + t.getId() + "',name='" + t.getName());
			Statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(HealthClub t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate("DELETE FROM sdmproject.health_clubs WHERE id = '" + t.getId() + "'");
			Statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void create(HealthClub t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate(
					"INSERT INTO sdmproject.health_clubs VALUES ('" + t.getId() + "','" + t.getName() + "')");
			Statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
