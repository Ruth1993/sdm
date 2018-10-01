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
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void removeRole(Role role) {
		this.roles.remove(role);
	}
}
