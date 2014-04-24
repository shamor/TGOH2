package edu.ycp.cs.cs496.TGOH.temp;

import java.util.List;

// for the other database

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
	
	public String FindCourse(String course){
		for (String list : Course){
			if(list.equals(course)){
				return list;
			}
		}
		return null;
	}
	
	public String getCourse(int index) {
		return Course.get(index);
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
