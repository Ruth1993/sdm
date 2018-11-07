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

	private String defaultBIWritingPolicy = ((Integer) id).toString();
	private String defaultBIReadingPolicy = null;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
		//PRODUCE ERROR
		defaultBIReadingPolicy = "( Company1 AND employer ) OR ( doctor ) OR ( nurse ) OR ( insuranceprovider AND insurance1 ) OR ( healthclub1 AND healthclubemployee ) OR "
				+ id;
	}

	public static void main(String[] args) throws SQLException {
		Person p1 = new Person(1, "Name 1");
		// p1.updateBasicInfoDB(6, "Name", "Birth date", "Birth place",
		// "Gender","Nationality", "Address","number");
		// p1.updateBasicHealthInfoDB(3,
		// "type3","weight3","height3","contact3","doctor3");
		// p1.addMedicalVisitDB(2,"date1","date2","reason","results","hospital");
		// p1.addMedicineDB("med","dos","dat1","dat6",3);
		// p1.addHealthClubVisitsDB(2,"date","dur","reason","result","comment");
		ArrayList<String> bi = p1.readBasicInfoDB(2);
		ArrayList<String> bhi = p1.readBasicHealthInfoDB(2);
		ArrayList<ArrayList<String>> mv = p1.readMedicalVisitDB(2);
		ArrayList<ArrayList<String>> m = p1.readMedicineDB(2);
		ArrayList<ArrayList<String>> hcv = p1.readHealthClubVisitDB(2);
		System.out.println(bi.toString());
		System.out.println(bhi.toString());
		System.out.println(mv.toString());
		System.out.println(m.toString());
		System.out.println(hcv.toString());

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
					Statement Statementhos = connection.createStatement();
					ResultSet hospitalres = Statementhos
							.executeQuery("SELECT * FROM sdmproject.hospitals WHERE id = " + res.getInt("id_company"));
					hospitalres.first();
					attributs.add(hospitalres.getString("name"));
					hospitalres.close();
					Statementhos.close();
					break;
				case "company":
					Statement Statementcom = connection.createStatement();
					ResultSet companyres = Statementcom
							.executeQuery("SELECT * FROM sdmproject.companies WHERE id = " + res.getInt("id_company"));
					companyres.first();
					attributs.add(companyres.getString("name"));
					companyres.close();
					Statementcom.close();
					break;
				case "insurance":
					Statement Statementins = connection.createStatement();
					ResultSet insuranceres = Statementins
							.executeQuery("SELECT * FROM sdmproject.insurances WHERE id = " + res.getInt("id_company"));
					insuranceres.first();
					attributs.add(insuranceres.getString("name"));
					insuranceres.close();
					Statementins.close();
					break;
				case "health_club":
					Statement Statementhc = connection.createStatement();
					ResultSet healthclubres = Statementhc.executeQuery(
							"SELECT * FROM sdmproject.health_clubs WHERE id = " + res.getInt("id_company"));
					healthclubres.first();
					attributs.add(healthclubres.getString("name"));
					healthclubres.close();
					Statementhc.close();
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
			results.add(res.getString(1));
			for (int i = 2; i <= 8; i++)
				results.add(this.dec(res.getBytes(i)));
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateBasicInfoDB(int uid, String name, String birth_date, String birth_place, String gender,
			String nationality, String address, String phone_no) throws SQLException {
		// TODO The values inserted into the database should be the encrypted
		// values according to this person's policy
		System.out.println(defaultBIReadingPolicy);
		ResultSet res = DBConnection.query("SELECT * FROM sdmproject.persons_basic_info WHERE id = " + uid);
		if (res.isClosed())
			DBConnection
					.update("INSERT INTO sdmproject.persons_basic_info (name, birth_date, birth_place, gender, nationality, address, phone_number) VALUES ('"
							+ this.enc(name, defaultBIReadingPolicy) + "', '"
							+ this.enc(birth_date, defaultBIReadingPolicy) + "', '"
							+ this.enc(birth_place, defaultBIReadingPolicy) + "', '"
							+ this.enc(gender, defaultBIReadingPolicy) + "', '"
							+ this.enc(nationality, defaultBIReadingPolicy) + "', '"
							+ this.enc(address, defaultBIReadingPolicy) + "', '"
							+ this.enc(phone_no, defaultBIReadingPolicy) + "')");
		else
			DBConnection
					.update("UPDATE sdmproject.persons_basic_info SET name='" + this.enc(name, defaultBIReadingPolicy)
							+ "', birth_date='" + this.enc(birth_date, defaultBIReadingPolicy) + "', birth_place='"
							+ this.enc(birth_place, defaultBIReadingPolicy) + "', gender='"
							+ this.enc(gender, defaultBIReadingPolicy) + "', nationality='"
							+ this.enc(nationality, defaultBIReadingPolicy) + "', address='"
							+ this.enc(address, defaultBIReadingPolicy) + "', phone_number='"
							+ this.enc(phone_no, defaultBIReadingPolicy) + "' WHERE id = " + uid);
	}

	public ArrayList<String> readBasicHealthInfoDB(int uid) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement
					.executeQuery("SELECT * FROM sdmproject.patients_basic_health_info WHERE id_patient = " + uid);
			res.first();
			for (int i = 1; i <= 6; i++)
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
		if (res.isClosed())
			DBConnection
					.update("INSERT INTO sdmproject.patients_basic_health_info (id_patient, blood_type, weight, height, emergency_contact, id_family_doctor) VALUES ('"
							+ uid + "', '" + blood_type + "', '" + weight + "', '" + height + "', '" + emergency_contact
							+ "', '" + id_family_doctor + "')");
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
				for (int i = 1; i <= 7; i++)
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
					"SELECT * FROM sdmproject.patients_medicines WHERE id_visit IN (SELECT id FROM sdmproject.patients_visits WHERE id_patient="
							+ uid + ")");
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				for (int i = 1; i <= 5; i++)
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
						+ "')");
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
				for (int i = 1; i <= 6; i++)
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
