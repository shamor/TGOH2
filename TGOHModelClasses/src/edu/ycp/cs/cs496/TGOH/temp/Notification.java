package edu.ycp.cs.cs496.TGOH.temp;

public class Notification {
	private int id;
	private int courseId;
	private String text;

	public Notification() {

	}
	
	public Notification(String text) {
		this.text = text;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
