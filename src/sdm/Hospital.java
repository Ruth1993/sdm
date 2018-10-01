package sdm;

import java.util.List;

public class Hospital {
	private int id;
	private String name;
	private List<Doctor> doctors;
	
	public Hospital(int id, String name, List<Doctor> doctors) {
		this.id = id;
		this.name = name;
		this.doctors = doctors;
		
	}
}
