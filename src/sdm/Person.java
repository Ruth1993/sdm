package sdm;

import java.util.Date;
import java.util.List;

public class Person {
	private int id;
	private String name;
	private List<Role> roles;
	private Date birthdate;
	
	public Person(int id, String name, Date birthdate, List<Role> roles) {
		this.id = id;
		this.name = name;
		this.roles = roles;
	}
}
