package sdm;

import java.util.List;

public class HealthClub {
	private int id;
	private String name;
	private List<Person> members;
	
	public HealthClub(int id, String name, List<Person> members) {
		this.id = id;
		this.name = name;
		this.members = members;
	}
	

}
