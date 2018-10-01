package sdm;

import java.util.Date;
import java.util.List;

public class Person {
	private int id;
	private String name;
	private List<Role> roles;
	private Date birthdate;
	
	public Person(int id, String name, List<Role> roles, Date birthdate) {
		this.id = id;
		this.name = name;
		this.roles = roles;
	}
}
