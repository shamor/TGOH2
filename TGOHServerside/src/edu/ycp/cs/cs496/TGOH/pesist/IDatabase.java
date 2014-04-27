package edu.ycp.cs.cs496.TGOH.pesist;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.User;

public interface IDatabase {
/**
 * Database persistence methods
 * @return 
 */
	public void addUser(User user);
	
	public boolean deleteUser(User user);
	
	public User getUser(String Username);

	public void addACourse(String username, String course);
	
	public List<String> getAllCourse(String Username);

	public String getCourse(String username, String course);
	
	public void deleteCourse(String Username, String Course);

}
