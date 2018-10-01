package sdm;

import java.util.Date;

public class Patient extends Role {
	private HealthRecord hr;
	
	public Patient(HealthRecord hr) {
		this.hr = hr;
	}

	public HealthRecord getHealthRecord() {
		return this.hr;
	}
	
	public void setEntry(Date date, String descript) {
		this.hr.setEntry(date, descript);
	}
}
