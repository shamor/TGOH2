package edu.ycp.cs.cs496.TGOH.pesist;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.User;

public interface IDatabase {
/**
 * Database persistence methods
 * @return 
 */
	public boolean addUser(User user);
	
	public boolean deleteUser(User user);
	
	public User getUser(String Username);

	public boolean addACourse(String username, Courses course);

	public List<Courses> getCourses(String Username, String Course);
	
	public List<Courses> getAllCourse(String Username);

	public String getCourse(String username, String course);

}
