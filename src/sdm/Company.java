package sdm;

public class Company {
	private int id;
	private String name;
	
	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void addEmployer(int id) {
		//to be implemented
		//sql query, insert employer id (id of person that is the employer?) and company id into Employments
	}
}
