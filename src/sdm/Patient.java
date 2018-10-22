package sdm;

import java.util.Date;

import databaseAccess.DBConnection;

public class Patient extends Role {
	private int patient_id;
	
	public Patient(int patient_id) {
		this.patient_id = patient_id;
	}
	
	public void changeHealthRecordDB(String emergency_contact, int id_family_doctor) {
		//to be implemented
		//sql query
	}
	
	/**
	 * Insert or change the name of the emergency contact in the PHR system
	 * @param emergency_contact
	 */
	public void changeEmergencyContactDB(String emergency_contact) {
		
		DBConnection.update("UPDATE sdmproject.patients_basic_health_info set emergency_contact= '"+emergency_contact+"' WHERE id_patient= "+this.patient_id+" limit 1");
		
	}
	
	/**
	 * Insert or change the id of the family doctor in the PHR system
	 * @param id_family_doctor
	 */
	public void changeIdFamilyDoctorDB(int id_family_doctor) {
		
		DBConnection.update("UPDATE sdmproject.patients_basic_health_info set id_family_doctor= '"+
		id_family_doctor+
		"' WHERE id_patient= "+this.patient_id+" limit 1");
		
	}
}
