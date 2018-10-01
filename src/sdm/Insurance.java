package sdm;

import java.util.List;

public class Insurance {
	private int id;
	private String name;
	private List<Person> clients;
	
	public Insurance(int id, String name, List<Person> clients) {
		this.id = id;
		this.name = name;
		this.clients = clients;
	}
}
