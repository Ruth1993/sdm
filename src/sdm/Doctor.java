package sdm;

import java.util.Date;

public class Doctor extends Role {
	public String test() {
		return "Test doctor";
	}
	
	/**
	 * Insert entry in insertVisitationDB table
	 * @param id_patient
	 * @param date_start
	 * @param date_end
	 * @param reason
	 * @param results
	 * @param id_hospitals_doctors
	 */
	public void insertVisitationDB(int id_patient, Date date_start, Date date_end, String reason, String results, int id_hospitals_doctors) {
		//to be implemented
		//sql query
	}
	
	/**
	 * Insert entry in Persons_medicine table
	 * @param medicine_name
	 * @param dosage
	 * @param date_start
	 * @param date_end
	 * @param id_visit
	 */
	public void insertMedicineDB(String medicine_name, String dosage, Date date_start, Date date_end, int id_visit) {
		//to be implemented
		//sql query
	}
}
