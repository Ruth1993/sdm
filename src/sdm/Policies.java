package sdm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import databaseAccess.DBConnection;

public class Policies {

	Connection connection = DBConnection.getConnection();

	String year = "year"+Calendar.getInstance().get(Calendar.YEAR);

	int uid;

	// default person_basic_info policies
	// write: person
	private String BIWritingPolicy = " ";
	// read: employer, all hospitals, all doctors, healthclubs, insurance,
	// person
	private String BIReadingPolicy = " ";

	// default basic_health_info policies
	// write: person, family doctor
	private String BHIWritingPolicy = " ";
	// read: all hospitals, all doctors, healthclubs, insurance, person
	private String BHIReadingPolicy = " ";

	// default medical_visit policies
	// write: doctors, hospitals
	private String MVWritingPolicy = " ";
	// read: doctors, hospitals, insurance, person
	private String MVReadingPolicy = " ";

	// default medicines policies
	// write: doctors
	private String MWritingPolicy = " ";
	// read: doctors, hospital, person
	private String MReadingPolicy = " ";

	// default heatlh_club_visits policies
	// write: healthclubs
	private String HCVWritingPolicy = " ";
	// read: healthclubs, person
	private String HCVReadingPolicy = " ";

	public Policies(int uid) {
		super();
		this.uid = uid;
	}

	public String getBIWritingPolicy() {
		if (BIWritingPolicy.equals(" ")) {
			String policy = getBIWritingPolicyDB();
			if (policy != " ") {
				BIWritingPolicy = policy;
				return policy;
			}
			setDefaultBIWritingPolicy();
		}
		return BIWritingPolicy;
	}

	public String getBIWritingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BIWritingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultBIWritingPolicy() {
		BIWritingPolicy = "id" + uid;
		DBConnection.update("UPDATE policies SET BIWritingPolicy = '" + BIWritingPolicy + "' WHERE id_person = " + uid);
	}

	public void setBIWritingPolicy(String bIWritingPolicy) {
		BIWritingPolicy = bIWritingPolicy;
		DBConnection.update("UPDATE policies SET BIWritingPolicy = '" + BIWritingPolicy + "' WHERE id_person = " + uid);
	}

	public String getBIReadingPolicy() {
		if (BIReadingPolicy.equals(" ")) {
			String policy = getBIReadingPolicyDB();
			if (policy != " ") {
				BIReadingPolicy = policy;
				return policy;
			}
			setDefaultBIReadingPolicy();
		}
		return BIReadingPolicy;
	}

	public String getBIReadingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BIReadingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultBIReadingPolicy() {
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
				policy = policy.concat("hospital OR doctor OR ");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE id_patient = "
							+ uid + ")");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			stmt.close();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT name FROM insurances WHERE id IN (SELECT id_insurance FROM insurances_patients WHERE id_patient = "
							+ uid + ")");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			stmt.close();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"(SELECT name FROM companies WHERE id IN (SELECT id_company FROM employments WHERE id_person = "
							+ uid
							+ " AND type_company= 'company')) UNION (SELECT name FROM health_clubs WHERE id IN (SELECT id_company FROM employments WHERE id_person = "
							+ uid
							+ " AND type_company= 'health_club')) UNION (SELECT name FROM insurances WHERE id IN (SELECT id_company FROM employments WHERE id_person = "
							+ uid
							+ " AND type_company= 'insurance')) UNION (SELECT name FROM hospitals WHERE id IN (SELECT id_company FROM employments WHERE id_person = "
							+ uid + " AND type_company= 'hospital'))");
			policy = policy.concat("( employer AND ( ");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BIReadingPolicy = policy;
		DBConnection.update("UPDATE policies SET BIReadingPolicy = '" + BIReadingPolicy + "' WHERE id_person = " + uid);
	}

	public void setBIReadingPolicy(String bIReadingPolicy) {
		BIReadingPolicy = bIReadingPolicy;
		DBConnection.update("UPDATE policies SET BIReadingPolicy = '" + BIReadingPolicy + "' WHERE id_person = " + uid);
	}

	public String getBHIWritingPolicy() {
		if (BHIWritingPolicy.equals(" ")) {
			String policy = getBHIWritingPolicyDB();
			if (policy != " ") {
				BHIWritingPolicy = policy;
				return policy;
			}
			setDefaultBHIWritingPolicy();
		}
		return BHIWritingPolicy;
	}

	public String getBHIWritingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BHIWritingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultBHIWritingPolicy() {
		// SELECT id_family_doctor FROM patients_basic_health_info WHERE
		// id_patient = 2
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id_family_doctor FROM patients_basic_health_info WHERE id_patient = " + uid);
			while (rs.next()) {
				policy = policy.concat("id");
				policy = policy.concat(rs.getString(1));
			}
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BHIWritingPolicy = policy;
		DBConnection
				.update("UPDATE policies SET BHIWritingPolicy = '" + BHIWritingPolicy + "' WHERE id_person = " + uid);
	}

	public void setBHIWritingPolicy(String bHIWritingPolicy) {
		BHIWritingPolicy = bHIWritingPolicy;
		DBConnection
				.update("UPDATE policies SET BHIWritingPolicy = '" + BHIWritingPolicy + "' WHERE id_person = " + uid);
	}

	public String getBHIReadingPolicy() {
		if (BHIReadingPolicy.equals(" ")) {
			String policy = getBHIReadingPolicyDB();
			if (policy != " ") {
				BHIReadingPolicy = policy;
				return policy;
			}
			setDefaultBHIReadingPolicy();
		}
		return BHIReadingPolicy;
	}

	public String getBHIReadingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BHIReadingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultBHIReadingPolicy() {

		String policy = "( "+year + " AND ( hospital OR doctor OR ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"(SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE id_patient = "
							+ uid
							+ ")) UNION (SELECT name FROM insurances WHERE id IN (SELECT id_insurance FROM insurances_patients WHERE id_patient = "
							+ uid + "))");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BHIReadingPolicy = policy;
		DBConnection
				.update("UPDATE policies SET BHIReadingPolicy = '" + BHIReadingPolicy + "' WHERE id_person = " + uid);
	}

	public void setBHIReadingPolicy(String bHIReadingPolicy) {
		BHIReadingPolicy = bHIReadingPolicy;
		DBConnection
				.update("UPDATE policies SET BHIReadingPolicy = '" + BHIReadingPolicy + "' WHERE id_person = " + uid);
	}

	public String getMVWritingPolicy() {
		if (MVWritingPolicy.equals(" ")) {
			String policy = getMVWritingPolicyDB();
			if (policy != " ") {
				MVWritingPolicy = policy;
				return policy;
			}
			setDefaultMVWritingPolicy();
		}
		return MVWritingPolicy;
	}

	public String getMVWritingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MVWritingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultMVWritingPolicy() {
		// SELECT CONCAT('id',id_doctor) FROM hospitals_doctors WHERE id IN
		// (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient =
		// 2)
		// SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM
		// hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM
		// patients_visits WHERE id_patient = 2))
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"(SELECT CONCAT('id',id_doctor) FROM hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient = "
							+ uid
							+ ")) UNION (SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient = "
							+ uid + ")))");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MVWritingPolicy = policy;
		DBConnection.update("UPDATE policies SET MVWritingPolicy = '" + MVWritingPolicy + "' WHERE id_person = " + uid);
	}

	public void setMVWritingPolicy(String mVWritingPolicy) {
		MVWritingPolicy = mVWritingPolicy;
		DBConnection.update("UPDATE policies SET MVWritingPolicy = '" + MVWritingPolicy + "' WHERE id_person = " + uid);
	}

	public String getMVReadingPolicy() {
		if (MVReadingPolicy.equals(" ")) {
			String policy = getMVReadingPolicyDB();
			if (policy != " ") {
				MVReadingPolicy = policy;
				return policy;
			}
			setDefaultMVReadingPolicy();
		}
		return MVReadingPolicy;
	}

	public String getMVReadingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MVReadingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultMVReadingPolicy() {
		// SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM
		// hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM
		// patients_visits WHERE id_patient = 2))
		// SELECT id_hospital, id_doctor FROM hospitals_doctors WHERE id IN
		// (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient =
		// 2)
		// SELECT name FROM insurances WHERE id IN (SELECT id_insurance FROM
		// insurances_patients WHERE id_patient = 2)
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"(SELECT name FROM insurances WHERE id IN (SELECT id_insurance FROM insurances_patients WHERE id_patient = "
							+ uid
							+ ")) UNION (SELECT CONCAT('id',id_doctor) FROM hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient = "
							+ uid
							+ ")) UNION (SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient = "
							+ uid + ")))");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MVReadingPolicy = policy;
		DBConnection.update("UPDATE policies SET MVReadingPolicy = '" + MVReadingPolicy + "' WHERE id_person = " + uid);
	}

	public void setMVReadingPolicy(String mVReadingPolicy) {
		MVReadingPolicy = mVReadingPolicy;
		DBConnection.update("UPDATE policies SET MVReadingPolicy = '" + MVReadingPolicy + "' WHERE id_person = " + uid);
	}

	public String getMWritingPolicy() {
		if (MWritingPolicy.equals(" ")) {
			String policy = getMWritingPolicyDB();
			if (policy != " ") {
				MWritingPolicy = policy;
				return policy;
			}
			setDefaultMWritingPolicy();
		}
		return MWritingPolicy;
	}

	public String getMWritingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MWritingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultMWritingPolicy() {
		// SELECT id_doctor FROM hospitals_doctors WHERE id IN (SELECT
		// id_hospital_doctors FROM patients_visits WHERE id_patient = 2)
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT CONCAT('id',id_doctor) FROM hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient = "
							+ uid + ")");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MWritingPolicy = policy;
		DBConnection.update("UPDATE policies SET MWritingPolicy = '" + MWritingPolicy + "' WHERE id_person = " + uid);
	}

	public void setMWritingPolicy(String mWritingPolicy) {
		MWritingPolicy = mWritingPolicy;
		DBConnection.update("UPDATE policies SET MWritingPolicy = '" + MWritingPolicy + "' WHERE id_person = " + uid);
	}

	public String getMReadingPolicy() {
		if (MReadingPolicy.equals(" ")) {
			String policy = getMReadingPolicyDB();
			if (policy != " ") {
				MReadingPolicy = policy;
				return policy;
			}
			setDefaultMReadingPolicy();
		}
		return MReadingPolicy;
	}

	public String getMReadingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MReadingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultMReadingPolicy() {
		// SELECT id_hospital, id_doctor FROM hospitals_doctors WHERE id IN
		// (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient =
		// 2)
		// SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM
		// hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM
		// patients_visits WHERE id_patient = 2))
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"(SELECT CONCAT('id',id_doctor) FROM hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient = "
							+ uid
							+ ")) UNION (SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient = "
							+ uid + ")))");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MReadingPolicy = policy;
		DBConnection.update("UPDATE policies SET MReadingPolicy = '" + MReadingPolicy + "' WHERE id_person = " + uid);
	}

	public void setMReadingPolicy(String mReadingPolicy) {
		MReadingPolicy = mReadingPolicy;

		DBConnection.update("UPDATE policies SET MReadingPolicy = '" + MReadingPolicy + "' WHERE id_person = " + uid);
	}

	public String getHCVWritingPolicy() {
		if (HCVWritingPolicy.equals(" ")) {
			String policy = getHCVWritingPolicyDB();
			System.out.println(policy);
			if (policy != " ") {
				HCVWritingPolicy = policy;
				return policy;
			}
			setDefaultHCVWritingPolicy();
		}
		return HCVWritingPolicy;
	}

	public String getHCVWritingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT HCVWritingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultHCVWritingPolicy() {
		// (SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club
		// FROM health_clubs_patients WHERE id_patient = 2))
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE id_patient = "
							+ uid + ")");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HCVWritingPolicy = policy;
		DBConnection
				.update("UPDATE policies SET HCVWritingPolicy = '" + HCVWritingPolicy + "' WHERE id_person = " + uid);
	}

	public void setHCVWritingPolicy(String hCVWritingPolicy) {
		HCVWritingPolicy = hCVWritingPolicy;
		DBConnection
				.update("UPDATE policies SET HCVWritingPolicy = '" + HCVWritingPolicy + "' WHERE id_person = " + uid);
	}

	public String getHCVReadingPolicy() {
		if (HCVReadingPolicy.equals(" ")) {
			String policy = getHCVReadingPolicyDB();
			if (policy != " ") {
				HCVReadingPolicy = policy;
				return policy;
			}
			setDefaultHCVReadingPolicy();
		}
		return HCVReadingPolicy;
	}

	public String getHCVReadingPolicyDB() {
		String policy = " ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT HCVReadingPolicy FROM policies WHERE id_person = " + uid);
			while (rs.next()) {
				policy = rs.getString(1);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policy;
	}

	public void setDefaultHCVReadingPolicy() {
		// (SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club
		// FROM health_clubs_patients WHERE id_patient = 2))
		String policy = "( "+year + " AND ( ";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE id_patient = "
							+ uid + ")");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.substring(0, policy.length() - 4);
			policy = policy.concat(" ) ) OR id"+uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HCVReadingPolicy = policy;
		DBConnection
				.update("UPDATE policies SET HCVReadingPolicy = '" + HCVReadingPolicy + "' WHERE id_person = " + uid);
	}

	public void setHCVReadingPolicy(String hCVReadingPolicy) {
		HCVReadingPolicy = hCVReadingPolicy;
		DBConnection
				.update("UPDATE policies SET HCVReadingPolicy = '" + HCVReadingPolicy + "' WHERE id_person = " + uid);
	}

}
