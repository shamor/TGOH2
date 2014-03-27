package edu.ycp.cs.cs496.TGOH.User;

public class User {
	private String name;
	private String FirstName;
	private String LastName;
	private String Password;
	
	
	public User(){
		
	}
	
	public User(String name, String FirstName, String LastName, String Password){
		this.name = name;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Password = Password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
}
