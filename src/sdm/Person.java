package sdm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crypto.abe.api.Client;

import java.sql.Connection;
import databaseAccess.DBConnection;

public class Person extends Client {
	/** Basic info */
	private int id;
	private String name;
	private String[] attrs;
	Connection connection = DBConnection.getConnection();

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void getAttributeListDB() {
		ArrayList<String> attributs = new ArrayList<String>();
		attributs.add(((Integer) id).toString());
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.employments WHERE id_person = " + id);
			while (res.next()) {
				if (!attributs.contains(res.getString("type_job")))
					attributs.add(res.getString("type_job"));
				switch (res.getString("type_company")) {
				case "hospital":
					ResultSet hospitalres = Statement
							.executeQuery("SELECT * FROM sdmproject.hospitals WHERE id = " + res.getInt("id_company"));
					hospitalres.first();
					attributs.add(hospitalres.getString("name"));
					hospitalres.close();
					break;
				case "company":
					ResultSet companyres = Statement
							.executeQuery("SELECT * FROM sdmproject.companies WHERE id = " + res.getInt("id_company"));
					companyres.first();
					attributs.add(companyres.getString("name"));
					companyres.close();
					break;
				case "insurance":
					ResultSet insuranceres = Statement
							.executeQuery("SELECT * FROM sdmproject.insurances WHERE id = " + res.getInt("id_company"));
					insuranceres.first();
					attributs.add(insuranceres.getString("name"));
					insuranceres.close();
					break;
				case "health_club":
					ResultSet healthclubres = Statement.executeQuery(
							"SELECT * FROM sdmproject.health_clubs WHERE id = " + res.getInt("id_company"));
					healthclubres.first();
					attributs.add(healthclubres.getString("name"));
					healthclubres.close();
					break;
				default:
					break;
				}
			}
			res.close();
			Statement.close();
			attrs = new String[attributs.size()];
			for (int i = 0; i < attrs.length; i++) {
				attrs[i] = attributs.get(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> readBasicInfoDB(int uid) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.persons_basic_info WHERE id = " + uid);
			res.first();
			for (int i = 0; i < 8; i++)
				results.add(res.getString(i));
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateBasicInfoDB(int uid, String name, Date birth_date, String birth_place, String gender,
			String nationality, String address, String phone_no) throws SQLException {
		// TODO The values inserted into the database should be the encrypted
		// values according to this person's policy
		ResultSet res = DBConnection.query("SELECT * FROM sdmproject.persons_basic_info WHERE id = " + uid);
		if (res.wasNull())
			DBConnection
					.update("INSERT INTO sdmproject.persons_basic_info (name, birth_date, birth_place, gender, nationality, address, phone_number) VALUES ('"
							+ name + "', '" + birth_date + "', '" + birth_place + "', '" + gender + "', '" + nationality
							+ "', '" + address + "', '" + phone_no + "')");
		else
			DBConnection.update("UPDATE sdmproject.persons_basic_info SET name='" + name + "', birth_date='"
					+ birth_date + "', birth_place='" + birth_place + "', gender='" + gender + "', nationality='"
					+ nationality + "', address='" + address + "', phone_number='" + phone_no + "' WHERE id = " + uid);
	}

	public ArrayList<String> readBasicHealthInfoDB(int uid) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement
					.executeQuery("SELECT * FROM sdmproject.patients_basic_health_info WHERE id_patient = " + uid);
			res.first();
			for (int i = 0; i < 8; i++)
				results.add(res.getString(i));
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateBasicHealthInfoDB(int uid, String blood_type, String weight, String height,
			String emergency_contact, String id_family_doctor) throws SQLException {
		// TODO The values inserted into the database should be the encrypted
		// values according to this person's policy
		ResultSet res = DBConnection
				.query("SELECT * FROM sdmproject.patients_basic_health_info WHERE id_patient = " + uid);
		if (res.wasNull())
			DBConnection
					.update("INSERT INTO sdmproject.patients_basic_health_info (blood_type, weight, height, emergency_contact, id_family_doctor) VALUES ('"
							+ blood_type + "', '" + weight + "', '" + height + "', '" + emergency_contact + "', '"
							+ id_family_doctor + "')");
		else
			DBConnection.update("UPDATE sdmproject.patients_basic_health_info SET blood_type='" + blood_type
					+ "', weight='" + weight + "', height='" + height + "', emergency_contact='" + emergency_contact
					+ "', id_family_doctor='" + id_family_doctor + "' WHERE id_patient = " + uid);
	}

	public ArrayList<ArrayList<String>> readMedicalVisitDB(int uid) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement
					.executeQuery("SELECT * FROM sdmproject.patients_visits WHERE id_patient = " + uid);
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				for (int i = 0; i < 7; i++)
					subresults.add(res.getString(i));
				results.add(subresults);
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addMedicalVisitDB(int uid, String date_start, String date_end, String reason, String results,
			String id_hospital_doctors) {
		DBConnection
				.update("INSERT INTO sdmproject.patients_visits (id_patient, date_start, date_end, reason, results, id_hospital_doctors) VALUES ('"
						+ uid + "', '" + date_start + "', '" + date_end + "', '" + reason + "', '" + results + "', '"
						+ id_hospital_doctors + "')");
	}

	public ArrayList<ArrayList<String>> readMedicineDB(int uid) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery(
					"SELECT * FROM sdmproject.patients_medicines WHERE id_visit=(SELECT id FROM sdmproject.patients_visits WHERE id_patient="
							+ uid + ")");
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				for (int i = 0; i < 5; i++)
					subresults.add(res.getString(i));
				results.add(subresults);
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addMedicineDB(String medicine_name, String dosage, String date_start, String date_end, int id_visit) {
		DBConnection
				.update("INSERT INTO sdmproject.patients_medicines (medicine_name, dosage, date_start, date_end, id_visit) VALUES ('"
						+ medicine_name + "', '" + dosage + "', '" + date_start + "', '" + date_end + "', '" + id_visit
						+ "', '" + date_start + "')");
	}

	public ArrayList<ArrayList<String>> readHealthClubVisitDB(int uid) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery(
					"SELECT * FROM sdmproject.patients_health_clubs_visits WHERE id_patient_healthclub=(SELECT id FROM sdmproject.health_clubs_patients WHERE id_patient="
							+ uid + ")");
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				for (int i = 0; i < 6; i++)
					subresults.add(res.getString(i));
				results.add(subresults);
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addHealthClubVisitsDB(int id_patient_healthclub, String date, String duration, String reasons,
			String results, String comments) {
		DBConnection
				.update("INSERT INTO sdmproject.patients_health_clubs_visits (id_patient_healthclub, date, duration, reasons, results, comments) VALUES ('"
						+ id_patient_healthclub + "', '" + date + "', '" + duration + "', '" + reasons + "', '"
						+ results + "', '" + comments + "')");
	}

}
