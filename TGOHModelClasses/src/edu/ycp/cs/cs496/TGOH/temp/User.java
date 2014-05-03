package edu.ycp.cs.cs496.TGOH.temp;

public class User {
	private int id;
	private String Username;
	private String FirstName;
	private String LastName;
	private String Password;
	private boolean type;
	
	public User(){
		
	}
	
	public User(String Username, String FirstName, String LastName, String Password, boolean type){
		this.Username = Username;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Password = Password;
		this.type = type;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUserName() {
		return Username;
	}
	
	public void setUserName(String name) {
		this.Username = name;
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
/*	
	public List<String> getCourse() {
		return course;
	}
	
	public void setCourse(List<String> class1) {
		course = class1;
	}
	
	public void addCourse(String usercourse){
		course.add(usercourse);
	}
	
	public String findCourse(String Course){
		String y = null;
		for(String x : course){
			if(x.equals(Course))
				y = x;
		}
		return y;
	}
	
	public boolean removeCourse(String Coursename){
		if(course.remove(Coursename))
			return true;
		else
			return false;
		
	}
*/
}
