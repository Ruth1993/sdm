package sdm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import databaseAccess.DBConnection;

public class Hospital {
	private String id;
	private String name;

	public Hospital(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<Doctor> getDoctors() {
		// to be implemented
		// sql query
		return null;
	}

	public Doctor getDoctor(int id) {
		// to be implemented
		// sql query
		return null;
	}*/

	public void addDoctor(int id_doctor) {
		// to be implemented
		// sql query, insert this.id and id_doctor into Hospitals_doctors

	}

	Connection connection = DBConnection.getConnection();

	public List<Hospital> getAll() {
		List<Hospital> hospitals = new LinkedList<>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.hospitals");
			while (res.next()) {
				Hospital hospital = new Hospital(res.getString("id"), res.getString("name"));
				hospitals.add(hospital);
			}
			res.close();
			Statement.close();
			return hospitals;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Hospital get(String id) {
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.hospitals WHERE id = " + id);
			res.first();
			Hospital hospital = new Hospital(res.getString("id"), res.getString("name"));
			res.close();
			Statement.close();
			return hospital;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void update(Hospital t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate("UPDATE sdmproject.hospitals SET id='" + t.getId() + "',name='" + t.getName());
			Statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(Hospital t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate("DELETE FROM sdmproject.hospitals WHERE id = '" + t.getId() + "'");
			Statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void create(Hospital t) {
		try {
			Statement Statement = connection.createStatement();
			Statement.executeUpdate(
					"INSERT INTO sdmproject.hospitals VALUES ('" + t.getId() + "','" + t.getName() + "')");
			Statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
