package edu.ycp.cs.cs496.TGOH.pesist;

import java.util.List;

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
	
	public void addCourse(Courses course);
	
	public void deleteCourse(int Coursename);
	
	public Registration registerUserForCourse(String userId, String courseId);

	public void RemovingUserFromCourse(String userId, String courseId);
	
	Registration findUserForCourse(String userId, String courseId);
	
	public Courses[] getCoursefromUser(int user);
	
	public List<Courses> getAllCourse();
}