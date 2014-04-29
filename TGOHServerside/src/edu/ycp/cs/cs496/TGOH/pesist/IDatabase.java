package edu.ycp.cs.cs496.TGOH.pesist;

import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.Registration;
import edu.ycp.cs.cs496.TGOH.temp.User;

public interface IDatabase {
/**
 * Database persistence methods
 * @return 
 */
	public void addUser(User user);
	
	public boolean deleteUser(User user);
	
	public User getUser(String Username);
	
	public Courses getCourse(int coursename);
	
	public Courses addCourse(String Coursename);
	
	public void deleteCourse(int Coursename);
	
	public Registration registerUserForCourse(User user, Courses course);

	public void RemovingUserFromCourse(User user, Courses course);
	
	Registration findpendingUserForCourse(User user, Courses course);
	
	Registration findApproveUserForCourse(User user, Courses course);
	
	public Courses[] getCoursefromUser(int user);
}
	/*
	public void addACourse(String username, String course);
	
	public List<String> getAllCourse(String Username);

	public String getCourse(String username, String course);
	
	public void deleteCourse(String Username, String Course);
	*/