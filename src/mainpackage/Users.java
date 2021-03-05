package mainpackage;

public class Users {

	String username;
	String name;
	String surname;
	String department;
	int usersCounter;
	static int counter=0;
	
	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
	    this.name = name;
	}
	
	public String getSurname(){
		return this.surname;
	}

	public void setSurname(String surname) {
	    this.surname = surname;
	}
	
	public String getDepartement(){
		return this.department;
	}

	public void setDepartment(String department) {
	    this.department = department;
	}
	
	public Users() {
		 counter++;
	     usersCounter = usersCounter  + counter;
	}
}

