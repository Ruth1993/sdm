package sdm;

public class Patient extends Role {
	private HealthRecord hr;
	
	public Patient(HealthRecord hr) {
		this.hr = hr;
	}

	public HealthRecord getHealthRecord() {
		return this.hr;
	}
}
