package edu.ycp.cs.cs496.TGOH.temp;


import java.util.List;

public class User {
	private String name;
	private String FirstName;
	private String LastName;
	private String Password;
	private List<String> course;
	private boolean type;
	
	public User(){
		
	}
	
	public User(String name, String FirstName, String LastName, String Password, boolean type){
		this.name = name;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Password = Password;
		this.type = type;
		this.course = null;
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
	
	public boolean getType() {
		return type;
	}
	
	public void setType(boolean type) {
		this.type = type;
	}
	
	public List<String> getCourse() {
		return course;
	}
	
	public void setCourse(List<String> class1) {
		course = class1;
	}
	
	public boolean addCourse(String Course){
		if(course.add(Course))
			return true;
		else
			return false;
	}
	
	public String findCourse(String Course){
		String y = null;
		for(String x : course){
			if(x.equals(Course))
				y = x;
		}
		return y;
	}
	
	public boolean removeCourse(String Course){
		if(course.remove(Course))
			return true;
		else
			return false;
		
	}
}
