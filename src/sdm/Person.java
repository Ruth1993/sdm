package sdm;

import java.util.Date;
import java.util.List;

import databaseAccess.DBConnection;

public class Person {
	/** Basic info */
	private int id;
	private String name;
	private Date birth_date;
	private String gender;
	private String blood_type;
	
	/** Roles 
	 * These can be either Patient, Doctor or Employer
	*/
	private List<Role> roles;
	
	public Person(int id, String name, Date birth_date, String gender, String blood_type, List<Role> roles) {
		this.id = id;
		this.name = name;
		this.birth_date = birth_date;
		this.gender = gender;
		this.blood_type = blood_type;
		this.roles = roles;
	}
	
	/**
	 * Add role (patient, doctor or employer) to person
	 * Method only accessible by authorized institutes
	 * @require !this.roles.contains(role.getClass())
	 * @param role
	 */
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	/**
	 * Remove role (patient, doctor or employer) from person
	 * Method only accessible by authorized institutes
	 * @require this.roles.contains(role.getClass())
	 * @param role
	 */
	public void removeRole(Role role) {
		this.roles.remove(role);
	}
	
	/**
	 * Insert or change the name of the emergency contact in the PHR system
	 * @param emergency_contact
	 */
	public void changeEmergencyContactDB(String emergency_contact) {
		
		DBConnection.update("UPDATE sdmproject.patients_basic_health_info set emergency_contact= '"+emergency_contact+"' WHERE id_patient= "+this.id+" limit 1");
		
	}
	
	/**
	 * Insert or change the id of the family doctor in the PHR system
	 * @param id_family_doctor
	 */
	public void changeIdFamilyDoctorDB(int id_family_doctor) {
		
		DBConnection.update("UPDATE sdmproject.patients_basic_health_info set id_family_doctor= '"+id_family_doctor+"' WHERE id_patient= "+this.id+" limit 1");
		
	}
	
	public Patient patient() {
		Patient patient = null;
		
		for(int i=0; i<this.roles.size(); i++) {
			Role curRole = this.roles.get(i);
			if(curRole instanceof Patient) {
				patient = (Patient) curRole;
			}
		}

		return patient;
	}
	
	public Doctor doctor() {
		Doctor doctor = null;
		
		for(int i=0; i<this.roles.size(); i++) {
			Role curRole = this.roles.get(i);
			if(curRole instanceof Doctor) {
				doctor = (Doctor) curRole;
			}
		}

		return doctor;
	}
	
	public Employer employer() {
		Employer employer = null;
		
		for(int i=0; i<this.roles.size(); i++) {
			Role curRole = this.roles.get(i);
			if(curRole instanceof Employer) {
				employer = (Employer) curRole;
			}
		}

		return employer;
	}
}
