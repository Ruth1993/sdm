package sdm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
	private String[] attrs;
	Connection connection = DBConnection.getConnection();

	public Policies policies;

	public Person(int id) {
		this.id = id;
		policies = new Policies(id);
	}

	public int getId() {
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
		String year = "year"+Calendar.getInstance().get(Calendar.YEAR);
		attributs.add(year);
		attributs.add("id"+ id);
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.employments WHERE id_person = " + id);
			while (res.next()) {
				if (!attributs.contains(res.getString("type_job"))){
					attributs.add(res.getString("type_job"));
					attributs.add(res.getString("type_company"));
				}
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
			this.attrs = new String[attributs.size()];
			this.attrs = attributs.toArray(attrs);
			this.setAttrs(this.attrs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateWritingPolicy(String tableName, String policy) {
		Policies p = this.policies;
		
		switch (tableName) {
		case "BasicInfo":
			p.setBIWritingPolicy(policy);
			break;
		case "BasicHealthInfo":
			p.setBHIWritingPolicy(policy);
			break;
		case "MedicalVisit":
			p.setMVWritingPolicy(policy);
			break;
		case "Medicine":
			p.setMWritingPolicy(policy);
			break;
		case "HealthClubVisit":
			p.setHCVWritingPolicy(policy);
			break;
		default:
			break;
		}
	}
	
	public void updateReadingPolicy(String tableName, String policy) {
		Policies p = this.policies;
		
		switch (tableName) {
		case "BasicInfo":
			p.setBIReadingPolicy(policy);
			break;
		case "BasicHealthInfo":
			p.setBHIReadingPolicy(policy);
			break;
		case "MedicalVisit":
			p.setMVReadingPolicy(policy);
			break;
		case "Medicine":
			p.setMReadingPolicy(policy);
			break;
		case "HealthClubVisit":
			p.setHCVReadingPolicy(policy);
			break;
		default:
			break;
		}
	}

	public ArrayList<String> readBasicInfoDB(int uid) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery("SELECT * FROM sdmproject.persons_basic_info WHERE id = " + uid);
			while (res.next()) {
				results.add(res.getString(1));
				for (int i = 2; i <= 8; i++) {
					System.out.println(res.getBytes(i));
					System.out.println(i);
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
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		if (checkWritingPolicy("BasicInfo", uid)) {
			PreparedStatement pstmt;
			String sql = "UPDATE sdmproject.persons_basic_info SET name = ? , birth_date = ? , birth_place = ?, gender = ? , nationality = ? , address = ? , phone_number = ? WHERE id = ?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setBytes(1, this.enc(name, p.getBIReadingPolicy(), ""));
				pstmt.setBytes(2, this.enc(birth_date, p.getBIReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(birth_place, p.getBIReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(gender, p.getBIReadingPolicy(), ""));
				pstmt.setBytes(5, this.enc(nationality, p.getBIReadingPolicy(), ""));
				pstmt.setBytes(6, this.enc(address, p.getBIReadingPolicy(), ""));
				pstmt.setBytes(7, this.enc(phone_no, p.getBIReadingPolicy(), ""));
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
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		if (checkWritingPolicy("BasicHealthInfo", uid)) {
			PreparedStatement pstmt;
			String sql = "UPDATE sdmproject.patients_basic_health_info SET blood_type = ? , weight = ? , height = ? , emergency_contact = ? , id_family_doctor = ? WHERE id_patient = ? ";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setBytes(1, this.enc(blood_type, p.getBHIReadingPolicy(), ""));
				pstmt.setBytes(2, this.enc(weight, p.getBHIReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(height, p.getBHIReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(emergency_contact, p.getBHIReadingPolicy(), ""));
				pstmt.setInt(5, id_family_doctor);
				pstmt.setInt(6, uid);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<ArrayList<String>> readDisplayMedicalVisitDB(int uid) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement
					.executeQuery("SELECT patients_visits.id, patients_visits.id_patient, patients_visits.date_start, patients_visits.date_end, patients_visits.reason, patients_visits.results, patients_visits.id_hospital_doctors, hospitals_doctors.id_doctor, hospitals.name FROM patients_visits INNER JOIN hospitals_doctors ON patients_visits.id_hospital_doctors  = hospitals_doctors.id AND patients_visits.id_patient = "+uid+" INNER JOIN hospitals ON hospitals.id = hospitals_doctors.id_hospital");
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				subresults.add(res.getString(1));
				subresults.add(res.getString(2));
				for (int i = 3; i <= 6; i++) {
					subresults.add(this.dec(res.getBytes(i)));
				}
				subresults.add(res.getString(7));
				subresults.add(res.getString(8));
				subresults.add(res.getString(9));
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
	
	public ArrayList<ArrayList<String>> readUpdateMedicalVisitDB(int uid) {
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

	public void updateMedicalVisitDB(int visitid, int uid, String date_start, String date_end, String reason,
			String results, int id_hospital_doctors) {
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		if (checkWritingPolicy("MedicalVisit", uid)) {
			PreparedStatement pstmt;
			String sql = "UPDATE sdmproject.patients_visits SET id_patient = ?, date_start = ?, date_end = ?, reason = ?, results = ?, id_hospital_doctors = ? WHERE id = ?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, uid);
				pstmt.setBytes(2, this.enc(date_start, p.getMVReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(date_end, p.getMVReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(reason, p.getMVReadingPolicy(), ""));
				pstmt.setBytes(5, this.enc(results, p.getMVReadingPolicy(), ""));
				pstmt.setInt(6, id_hospital_doctors);
				pstmt.setInt(7, visitid);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addMedicalVisitDB(int uid, String date_start, String date_end, String reason, String results,
			int id_hospital_doctors) {
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		if (checkWritingPolicy("MedicalVisit", uid)) {
			PreparedStatement pstmt;
			String sql = "INSERT INTO sdmproject.patients_visits (id_patient, date_start, date_end, reason, results, id_hospital_doctors) VALUES ( ? , ? , ? , ? , ? , ? ) ";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, uid);
				pstmt.setBytes(2, this.enc(date_start, p.getMVReadingPolicy(), ""));
				pstmt.setBytes(3, this.enc(date_end, p.getMVReadingPolicy(), ""));
				pstmt.setBytes(4, this.enc(reason, p.getMVReadingPolicy(), ""));
				pstmt.setBytes(5, this.enc(results, p.getMVReadingPolicy(), ""));
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
				subresults.add(res.getString(1));
				for (int i = 2; i <= 5; i++) {
					subresults.add(this.dec(res.getBytes(i)));
				}
				subresults.add(res.getString(6));
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
	
	public ArrayList<ArrayList<String>> readDisplayMedicineDB(int uid) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery(
					"SELECT patients_medicines.id, patients_medicines.medicine_name, patients_medicines.dosage, patients_medicines.date_start, patients_medicines.date_end, patients_medicines.id_visit, patients_visits.id_patient, hospitals_doctors.id_doctor, hospitals.name FROM patients_medicines INNER JOIN patients_visits ON patients_medicines.id_visit = patients_visits.id AND patients_visits.id_patient = "
							+ uid
							+ " INNER JOIN hospitals_doctors ON patients_visits.id_hospital_doctors  = hospitals_doctors.id INNER JOIN hospitals ON hospitals.id = hospitals_doctors.id_hospital");
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				subresults.add(res.getString(1));
				for (int i = 2; i <= 5; i++) {
					subresults.add(this.dec(res.getBytes(i)));
				}
				subresults.add(res.getString(6));
				subresults.add(res.getString(7));
				subresults.add(res.getString(8));
				subresults.add(res.getString(9));
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

	public void updateMedicineDB(int rowid, int uid, String medicine_name, String dosage, String date_start,
			String date_end, int id_visit) {
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		String str = "SELECT CASE WHEN EXISTS (SELECT id FROM patients_visits WHERE id_patient = " + uid
				+ " AND id = " + id_visit + ") THEN 'TRUE' ELSE 'FALSE' END";
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(str);
			
			while (res.next()) {
				str = res.getString(1);
			}
			res.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (str.toLowerCase().equals("true")) {
			if (checkWritingPolicy("Medicine", uid)) {
				PreparedStatement pstmt;
				String sql = "UPDATE patients_medicines SET medicine_name = ?, dosage = ?, date_start = ?, date_end = ?, id_visit = ? WHERE id = ?";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.setBytes(1, this.enc(medicine_name, p.getMReadingPolicy(),""));
					pstmt.setBytes(2, this.enc(dosage, p.getMReadingPolicy(),""));
					pstmt.setBytes(3, this.enc(date_start, p.getMReadingPolicy(),""));
					pstmt.setBytes(4, this.enc(date_end, p.getMReadingPolicy(),""));
					pstmt.setInt(5, id_visit);
					pstmt.setInt(6, rowid);
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("WRONG (user id, visit id) combination");
		}
	}

	public void addMedicineDB(int uid, String medicine_name, String dosage, String date_start, String date_end,
			int id_visit) {
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		String str = "SELECT CASE WHEN EXISTS (SELECT id FROM patients_visits WHERE id_patient = " + uid
				+ " AND id = " + id_visit + ") THEN 'TRUE' ELSE 'FALSE' END";
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(str);
			while (res.next()) {
				str = res.getString(1);
			}
			res.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (str.toLowerCase().equals("true")) {
			if (checkWritingPolicy("Medicine", uid)) {
				PreparedStatement pstmt;
				String sql = "INSERT INTO sdmproject.patients_medicines (medicine_name, dosage, date_start, date_end, id_visit) VALUES ( ? , ? , ? , ? , ? ) ";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.setBytes(1, this.enc(medicine_name, p.getMReadingPolicy(),""));
					pstmt.setBytes(2, this.enc(dosage, p.getMReadingPolicy(),""));
					pstmt.setBytes(3, this.enc(date_start, p.getMReadingPolicy(),""));
					pstmt.setBytes(4, this.enc(date_end, p.getMReadingPolicy(),""));
					pstmt.setInt(5, id_visit);
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("WRONG (user id, visit id) combination");
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
				subresults.add(res.getString(2));
				for (int i = 3; i <= 7; i++) {
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
	
	public ArrayList<ArrayList<String>> readDisplayHealthClubVisitDB(int uid) {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try {
			Statement Statement = connection.createStatement();
			ResultSet res = Statement.executeQuery(
					"SELECT patients_health_clubs_visits.id, patients_health_clubs_visits.id_patient_healthclub, health_clubs_patients.id_patient, health_clubs.name, patients_health_clubs_visits.date, patients_health_clubs_visits.duration, patients_health_clubs_visits.reasons, patients_health_clubs_visits.results, patients_health_clubs_visits.comments FROM patients_health_clubs_visits INNER JOIN health_clubs_patients ON patients_health_clubs_visits.id_patient_healthclub = health_clubs_patients.id AND health_clubs_patients.id_patient = "
							+ uid
							+ " INNER JOIN health_clubs ON health_clubs_patients.id_health_club  = health_clubs.id");
			while (res.next()) {
				ArrayList<String> subresults = new ArrayList<String>();
				subresults.add(res.getString(1));
				subresults.add(res.getString(2));
				subresults.add(res.getString(3));
				subresults.add(res.getString(4));
				for (int i = 5; i <= 9; i++) {
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

	public void updateHealthClubVisitsDB(int rowid, int uid, int id_patient_healthclub, String date, String duration,
			String reasons, String results, String comments) {
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		String str = "SELECT CASE WHEN EXISTS (SELECT id FROM health_clubs_patients WHERE id_patient = " + uid
				+ " AND id = " + id_patient_healthclub + ") THEN 'TRUE' ELSE 'FALSE' END";
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(str);
			while (res.next()) {
				str = res.getString(1);
			}
			res.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (str.toLowerCase().equals("true")) {
			if (checkWritingPolicy("HealthClubVisit", uid)) {
				PreparedStatement pstmt;
				String sql = "UPDATE patients_health_clubs_visits SET id_patient_healthclub = ?, date = ?, duration = ?, reasons = ?, results = ?, comments = ? WHERE id = ?";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, id_patient_healthclub);
					pstmt.setBytes(2, this.enc(date, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(3, this.enc(duration, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(4, this.enc(reasons, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(5, this.enc(results, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(6, this.enc(comments, p.getHCVReadingPolicy(),""));
					pstmt.setInt(7, rowid);
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("WRONG (user id, visit id) combination");
		}
	}

	public void addHealthClubVisitsDB(int uid, int id_patient_healthclub, String date, String duration, String reasons,
			String results, String comments) {
		Policies p;
		if (this.id != uid) {
			p = new Policies(uid);
		} else {
			p = this.policies;
		}
		String str = "SELECT CASE WHEN EXISTS (SELECT id FROM health_clubs_patients WHERE id_patient = " + uid
				+ " AND id = " + id_patient_healthclub + ") THEN 'TRUE' ELSE 'FALSE' END";
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(str);
			while (res.next()) {
				str = res.getString(1);
			}
			res.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (str.toLowerCase().equals("true")) {
			if (checkWritingPolicy("HealthClubVisit", uid)) {
				PreparedStatement pstmt;
				String sql = "INSERT INTO sdmproject.patients_health_clubs_visits (id_patient_healthclub, date, duration, reasons, results, comments) VALUES ( ? , ? , ? , ? , ? , ? ) ";
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, id_patient_healthclub);
					pstmt.setBytes(2, this.enc(date, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(3, this.enc(duration, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(4, this.enc(reasons, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(5, this.enc(results, p.getHCVReadingPolicy(),""));
					pstmt.setBytes(6, this.enc(comments, p.getHCVReadingPolicy(),""));
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("WRONG (user id, visit id) combination");
		}
	}
	
	public void startGUI() {
		new GUI(connection, this);
	}
}
