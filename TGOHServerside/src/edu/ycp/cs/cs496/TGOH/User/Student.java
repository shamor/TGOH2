package edu.ycp.cs.cs496.TGOH.User;

public class Student {

	private String name;
	private boolean accepted;
	
	public Student(){
		
	}
	
	public Student(String name, boolean accepted){
		this.name = name;
		this.accepted = accepted;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getaccepted() {
		return accepted;
	}
	
	public void setaccepted(boolean istrue){
		this.accepted = istrue;
	}
}
