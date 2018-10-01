package sdm;

import java.util.Date;

public class Entry {
	private int id;
	private Date date;
	private String description;
	
	public Entry(int id, Date date, String discript) {
		this.id = id;
		this.date = date;
		this.description = discript;
	}
}
