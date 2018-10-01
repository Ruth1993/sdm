package sdm;

import java.util.Date;
import java.util.List;

public class HealthRecord {
	private List<Entry> entries;
	
	public HealthRecord(List<Entry> entries) {
		this.entries = entries;
	}
	
	public List<Entry> getEntries() {
		return this.entries;
	}
	
	public void setEntry(Date date, String descript) {
		this.entries.add(new Entry(date, descript));
	}
}
