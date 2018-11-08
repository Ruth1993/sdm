package sdm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import databaseAccess.DBConnection;

public class Policies {

	Connection connection = DBConnection.getConnection();

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
			setDefaultBIWritingPolicy();
		}
		return BIWritingPolicy;
	}

	public void setDefaultBIWritingPolicy() {
		BIWritingPolicy = "id" + uid;
	}

	public void setBIWritingPolicy(String bIWritingPolicy) {
		BIWritingPolicy = bIWritingPolicy;
	}

	public String getBIReadingPolicy() {
		if (BIReadingPolicy.equals(" "))
			setDefaultBIReadingPolicy();
		return BIReadingPolicy;
	}

	public void setDefaultBIReadingPolicy() {
		// (SELECT name FROM hospitals) UNION (SELECT name FROM health_clubs
		// WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE
		// id_patient = 2)) UNION (SELECT name FROM insurances WHERE id IN
		// (SELECT id_insurance FROM insurances_patients WHERE id_patient = 2))
		// (SELECT name FROM companies WHERE id IN (SELECT id_company FROM
		// employments WHERE id_person = 2 AND type_company= 'company')) UNION
		// (SELECT name FROM health_clubs WHERE id IN (SELECT id_company FROM
		// employments WHERE id_person = 2 AND type_company= 'healthclub'))
		// UNION (SELECT name FROM insurances WHERE id IN (SELECT id_company
		// FROM employments WHERE id_person = 2 AND type_company= 'insurance'))
		String policy = "";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM hospitals");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			stmt.close();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
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
							+ " AND type_company= 'healthclub')) UNION (SELECT name FROM insurances WHERE id IN (SELECT id_company FROM employments WHERE id_person = "
							+ uid + " AND type_company= 'insurance'))");
			policy = policy.concat(" ( employer AND ( ");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.concat(" ) ) OR ");
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BIReadingPolicy = policy;
	}

	public void setBIReadingPolicy(String bIReadingPolicy) {
		BIReadingPolicy = bIReadingPolicy;
	}

	public String getBHIWritingPolicy() {
		if(BHIWritingPolicy.equals(" "))
			setDefaultBHIWritingPolicy();
		return BHIWritingPolicy;
	}

	public void setDefaultBHIWritingPolicy() {
		// SELECT id_family_doctor FROM patients_basic_health_info WHERE
		// id_patient = 2
		String policy = "";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id_family_doctor FROM patients_basic_health_info WHERE id_patient = " + uid);
			while (rs.next()) {
				policy = policy.concat("id");
				policy = policy.concat(rs.getString(1));
			}
			policy = policy.concat(" OR id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BHIWritingPolicy = policy;
	}

	public void setBHIWritingPolicy(String bHIWritingPolicy) {
		BHIWritingPolicy = bHIWritingPolicy;
	}

	public String getBHIReadingPolicy() {
		if(BHIReadingPolicy.equals(" "))
				setDefaultBHIReadingPolicy();
		return BHIReadingPolicy;
	}

	public void setDefaultBHIReadingPolicy() {
		// (SELECT name FROM hospitals) UNION (SELECT name FROM health_clubs
		// WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE
		// id_patient = 2)) UNION (SELECT name FROM insurances WHERE id IN
		// (SELECT id_insurance FROM insurances_patients WHERE id_patient = 2))

		String policy = "";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"(SELECT name FROM hospitals) UNION (SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE id_patient = "
							+ uid
							+ ")) UNION (SELECT name FROM insurances WHERE id IN (SELECT id_insurance FROM insurances_patients WHERE id_patient = "
							+ uid + "))");
			while (rs.next()) {
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BHIReadingPolicy = policy;
	}

	public void setBHIReadingPolicy(String bHIReadingPolicy) {
		BHIReadingPolicy = bHIReadingPolicy;
	}

	public String getMVWritingPolicy() {
		if(MVWritingPolicy.equals(" "))
			setDefaultMVWritingPolicy();
		return MVWritingPolicy;
	}

	public void setDefaultMVWritingPolicy() {
		// SELECT CONCAT('id',id_doctor) FROM hospitals_doctors WHERE id IN
		// (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient =
		// 2)
		// SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM
		// hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM
		// patients_visits WHERE id_patient = 2))
		String policy = "";
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
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MVWritingPolicy = policy;
	}

	public void setMVWritingPolicy(String mVWritingPolicy) {
		MVWritingPolicy = mVWritingPolicy;
	}

	public String getMVReadingPolicy() {
		if(MVReadingPolicy.equals(" "))
			setDefaultMVReadingPolicy();
		return MVReadingPolicy;
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
		String policy = "";
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
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MVReadingPolicy = policy;
	}

	public void setMVReadingPolicy(String mVReadingPolicy) {
		MVReadingPolicy = mVReadingPolicy;
	}

	public String getMWritingPolicy() {
		if(MWritingPolicy.equals(" "))
			setDefaultMWritingPolicy();
		return MWritingPolicy;
	}

	public void setDefaultMWritingPolicy() {
		// SELECT id_doctor FROM hospitals_doctors WHERE id IN (SELECT
		// id_hospital_doctors FROM patients_visits WHERE id_patient = 2)
		String policy = "";
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
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MWritingPolicy = policy;
	}

	public void setMWritingPolicy(String mWritingPolicy) {
		MWritingPolicy = mWritingPolicy;
	}

	public String getMReadingPolicy() {
		if(MReadingPolicy.equals(" "))
			setDefaultMReadingPolicy();
		return MReadingPolicy;
	}

	public void setDefaultMReadingPolicy() {
		// SELECT id_hospital, id_doctor FROM hospitals_doctors WHERE id IN
		// (SELECT id_hospital_doctors FROM patients_visits WHERE id_patient =
		// 2)
		// SELECT name FROM hospitals WHERE id IN (SELECT id_hospital FROM
		// hospitals_doctors WHERE id IN (SELECT id_hospital_doctors FROM
		// patients_visits WHERE id_patient = 2))
		String policy = "";
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
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MReadingPolicy = policy;
	}

	public void setMReadingPolicy(String mReadingPolicy) {
		MReadingPolicy = mReadingPolicy;
	}

	public String getHCVWritingPolicy() {
		if(HCVWritingPolicy.equals(" "))
			setDefaultHCVWritingPolicy();
		return HCVWritingPolicy;
	}

	public void setDefaultHCVWritingPolicy() {
		// (SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club
		// FROM health_clubs_patients WHERE id_patient = 2))
		String policy = "";
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
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HCVWritingPolicy = policy;
	}

	public void setHCVWritingPolicy(String hCVWritingPolicy) {
		HCVWritingPolicy = hCVWritingPolicy;
	}

	public String getHCVReadingPolicy() {
		if(HCVReadingPolicy.equals(" ")){
			setDefaultHCVReadingPolicy();
		}
		return HCVReadingPolicy;
	}

	public void setDefaultHCVReadingPolicy() {
		// (SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club
		// FROM health_clubs_patients WHERE id_patient = 2))
		String policy = "";
		Statement stmt;
		try {
			System.out.println("try");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT name FROM health_clubs WHERE id IN (SELECT id_health_club FROM health_clubs_patients WHERE id_patient = "
							+ uid + ")");
			while (rs.next()) {
				System.out.println("found something");
				policy = policy.concat(rs.getString(1));
				policy = policy.concat(" OR ");
			}
			policy = policy.concat("id" + uid);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HCVReadingPolicy = policy;
	}

	public void setHCVReadingPolicy(String hCVReadingPolicy) {
		HCVReadingPolicy = hCVReadingPolicy;
	}

}
