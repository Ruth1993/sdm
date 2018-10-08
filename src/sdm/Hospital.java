package sdm;

import java.util.List;

public class Hospital {
	private int id;
	private String name;
	
	public Hospital(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public List<Doctor> getDoctors() {
		//to be implemented
		//sql query
		return null;
	}
	
	public Doctor getDoctor(int id) {
		//to be implemented
		//sql query
		return null;
	}
	
	public void addDoctor(int id_doctor) {
		//to be implemented
		//sql query, insert this.id and id_doctor into Hospitals_doctors
	}
}
