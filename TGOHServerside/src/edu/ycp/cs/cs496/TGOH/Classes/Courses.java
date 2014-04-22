package edu.ycp.cs.cs496.TGOH.Classes;

import java.util.List;

public class Courses {
	private List<String> Course;
	
	public Courses(){
		
	}
	
	public Courses(List<String> Course){
		this.Course = Course;
	}
	
	public List<String> getAllCourses() {
		return Course;
	}
	
	public String getCourse(int numbers) {
		return Course.get(numbers);
	}
	
	public void setCourse(List<String> course) {
		Course = course;
	}
	
	public boolean addCourse(String course) {
		if(Course.add(course))
			return true;
		else
			return false;
	}
	
	public boolean deleteCourse(String course) {
		if(Course.remove(course))
			return true;
		else 
			return false;
	}
}
