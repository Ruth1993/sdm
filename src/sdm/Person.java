package sdm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crypto.abe.CPABEImpl;
import crypto.abe.Parser;
import crypto.abe.Policy;
import crypto.abe.api.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;

import databaseAccess.DBConnection;

import gui.GUI;

public class Person extends Client {
	/** Basic info */
	private int id;
	private String name;
	private String[] attrs;
	Connection connection = DBConnection.getConnection();

	public Policies policies;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
		policies = new Policies(id);
	}
	public int getId(){
		return this.id;
	}

	private boolean checkWritingPolicy(String tableName, int uid) {
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		String writingPolicy = "";
		switch (tableName) {
		case "BasicInfo":
			writingPolicy = p.getBIWritingPolicy();
			break;
		case "BasicHealthInfo":
			writingPolicy = p.getBHIWritingPolicy();
			break;
		case "MedicalVisit":
			writingPolicy = p.getMVWritingPolicy();
			break;
		case "Medicine":
			writingPolicy = p.getMWritingPolicy();
			break;
		case "HealthClubVisit":
			writingPolicy = p.getHCVWritingPolicy();
			break;
		default:
			break;
		}
		Parser parser = new Parser();
		Policy pw = parser.parse(writingPolicy);
		CPABEImpl.check_sat(this.getSK(), pw);
		if (pw.satisfiable != 1)
			return false;
		else
			return true;
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
			e.printStackTrace();
		}
	}

	public ArrayList<String> readBasicInfoDB(int uid) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.persons_basic_info WHERE id = " + uid);
			while (res.next()) {
				results.add(res.getString(1));
				results.add(res.getString(2));
				for (int i = 3; i <= 8; i++) {
					System.out.println(res.getBytes(i).toString());
					System.out.println(this.dec(res.getBytes(i)));
					results.add(this.dec(res.getBytes(i)));
				}
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertBasicInfoDB(String name, String birth_date, String birth_place, String gender, String nationality,
			String address, String phone_no) throws SQLException {
		PreparedStatement pstmt;
		String sql = "INSERT INTO sdmproject.persons_basic_info (name, birth_date, birth_place, gender, nationality, address, phone_number) VALUES ( ? , ? , ? , ? , ? , ? , ? )";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setBytes(2, this.enc(birth_date, policies.getBIReadingPolicy(), ""));
			pstmt.setBytes(3, this.enc(birth_place, policies.getBIReadingPolicy(), ""));
			pstmt.setBytes(4, this.enc(gender, policies.getBIReadingPolicy(), ""));
			pstmt.setBytes(5, this.enc(nationality, policies.getBIReadingPolicy(), ""));
			pstmt.setBytes(6, this.enc(address, policies.getBIReadingPolicy(), ""));
			pstmt.setBytes(7, this.enc(phone_no, policies.getBIReadingPolicy(), ""));
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBasicInfoDB(int uid, String name, String birth_date, String birth_place, String gender,
			String nationality, String address, String phone_no) throws SQLException {
		// Connection connection = DBConnection.getConnection();
		if (checkWritingPolicy("BasicInfo", uid)) {
			PreparedStatement pstmt;
			String sql = "UPDATE sdmproject.persons_basic_info SET name = ? , birth_date = ? , birth_place = ?, gender = ? , nationality = ? , address = ? , phone_number = ? WHERE id = ?";
			try {
				pstmt = connection.prepareStatement(sql);
				System.out.println(policies.getBIReadingPolicy());
				pstmt.setBytes(1, this.enc(name, policies.getBIReadingPolicy(), ""));
				pstmt.setBytes(2, this.enc(birth_date, policies.getBIReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(birth_place, policies.getBIReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(gender, policies.getBIReadingPolicy(), ""));
				pstmt.setBytes(5, this.enc(nationality, policies.getBIReadingPolicy(), ""));
				pstmt.setBytes(6, this.enc(address, policies.getBIReadingPolicy(), ""));
				pstmt.setBytes(7, this.enc(phone_no, policies.getBIReadingPolicy(), ""));
				pstmt.setInt(8, uid);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> readBasicHealthInfoDB(int uid) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement
					.executeQuery("SELECT * FROM sdmproject.patients_basic_health_info WHERE id_patient = " + uid);
			while (res.next()) {
				results.add(res.getString(1));
				for (int i = 2; i <= 5; i++) {
					results.add(this.dec(res.getBytes(i)));
				}
				results.add(res.getString(6));
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertBasicHealthInfoDB(int uid, String blood_type, String weight, String height,
			String emergency_contact, int id_family_doctor) throws SQLException {

		PreparedStatement pstmt;
		String sql = "INSERT INTO sdmproject.patients_basic_health_info (id_patient, blood_type, weight, height, emergency_contact, id_family_doctor) VALUES ( ? , ? , ? , ? , ? , ? ) ";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setBytes(2, this.enc(blood_type, policies.getBHIReadingPolicy(), ""));
			pstmt.setBytes(3, this.enc(weight, policies.getBHIReadingPolicy(), ""));
			pstmt.setBytes(4, this.enc(height, policies.getBHIReadingPolicy(), ""));
			pstmt.setBytes(5, this.enc(emergency_contact, policies.getBHIReadingPolicy(), ""));
			pstmt.setInt(6, id_family_doctor);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBasicHealthInfoDB(int uid, String blood_type, String weight, String height,
			String emergency_contact, int id_family_doctor) throws SQLException {
		if (checkWritingPolicy("BasicHealthInfo", uid)) {
			PreparedStatement pstmt;
			String sql = "UPDATE sdmproject.patients_basic_health_info SET blood_type = ? , weight = ? , height = ? , emergency_contact = ? , id_family_doctor = ? WHERE id_patient = ? ";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setBytes(1, this.enc(blood_type, policies.getBHIReadingPolicy(), ""));
				pstmt.setBytes(2, this.enc(weight, policies.getBHIReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(height, policies.getBHIReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(emergency_contact, policies.getBHIReadingPolicy(), ""));
				pstmt.setInt(5, id_family_doctor);
				pstmt.setInt(6, uid);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<ArrayList<String>> readMedicalVisitDB(int uid) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement
					.executeQuery("SELECT * FROM sdmproject.patients_visits WHERE id_patient = " + uid);
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				subresults.add(res.getString(1));
				subresults.add(res.getString(2));
				for (int i = 3; i <= 6; i++) {
					subresults.add(this.dec(res.getBytes(i)));
				}
				subresults.add(res.getString(7));
				results.add(subresults);
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addMedicalVisitDB(int uid, String date_start, String date_end, String reason, String results,
			int id_hospital_doctors) {
		if (checkWritingPolicy("MedicalVisit", uid)) {
			PreparedStatement pstmt;
			String sql = "INSERT INTO sdmproject.patients_visits (id_patient, date_start, date_end, reason, results, id_hospital_doctors) VALUES ( ? , ? , ? , ? , ? , ? ) ";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, uid);
				pstmt.setBytes(2, this.enc(date_start, policies.getMVReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(date_end, policies.getMVReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(reason, policies.getMVReadingPolicy(), ""));
				pstmt.setBytes(5, this.enc(results, policies.getMVReadingPolicy(), ""));
				pstmt.setInt(6, id_hospital_doctors);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
				for (int i = 1; i <= 4; i++) {
					subresults.add(this.dec(res.getBytes(i)));
				}
				subresults.add(res.getString(5));
				results.add(subresults);
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addMedicineDB(int uid, String medicine_name, String dosage, String date_start, String date_end,
			int id_visit) {
		if (checkWritingPolicy("Medicine", uid)) {
			PreparedStatement pstmt;
			String sql = "INSERT INTO sdmproject.patients_medicines (medicine_name, dosage, date_start, date_end, id_visit) VALUES ( ? , ? , ? , ? , ? ) ";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setBytes(1, this.enc(medicine_name, policies.getMReadingPolicy(), ""));
				pstmt.setBytes(2, this.enc(dosage, policies.getMReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(date_start, policies.getMReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(date_end, policies.getMReadingPolicy(), ""));
				pstmt.setInt(5, id_visit);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
				subresults.add(res.getString(1));
				for (int i = 2; i <= 5; i++) {
					subresults.add(this.dec(res.getBytes(i)));
				}
				results.add(subresults);
			}
			res.close();
			Statement.close();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addHealthClubVisitsDB(int uid, int id_patient_healthclub, String date, String duration, String reasons,
			String results, String comments) {
		if (checkWritingPolicy("HealthClubVisit", uid)) {
			PreparedStatement pstmt;
			String sql = "INSERT INTO sdmproject.patients_health_clubs_visits (id_patient_healthclub, date, duration, reasons, results, comments) VALUES ( ? , ? , ? , ? , ? , ? ) ";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, id_patient_healthclub);
				pstmt.setBytes(2, this.enc(date, policies.getHCVReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(duration, policies.getHCVReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(reasons, policies.getHCVReadingPolicy(), ""));
				pstmt.setBytes(5, this.enc(results, policies.getHCVReadingPolicy(), ""));
				pstmt.setBytes(6, this.enc(comments, policies.getHCVReadingPolicy(), ""));
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void startGUI() {
		new GUI(connection, this);
	}
}
