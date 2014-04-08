package edu.ycp.cs.cs496.TGOH.User;

public class User {
	private String name;
	private String Password;
	
	
	public User(){
		
	}
	
	public User(String name, String Password){
		this.name = name;
		this.Password = Password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
}
