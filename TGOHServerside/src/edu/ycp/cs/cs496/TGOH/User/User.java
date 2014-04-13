package edu.ycp.cs.cs496.TGOH.User;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.Classes.Courses;

public class User {
	private String name;
	private String FirstName;
	private String LastName;
	private String Password;
	private Courses Course;
	
	public User(){
		
	}
	
	public User(String name, String FirstName, String LastName, String Password){
		this.name = name;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Password = Password;
		this.Course = null;
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
	
	public Courses getCourse() {
		return Course;
	}
	
	public void setClass(Courses class1) {
		Course = class1;
	}
}
