package edu.ycp.cs.cs496.TGOH.pesist;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.temp.User;

public interface IDatabase {
/**
 * Database persistence methods
 * @return 
 */
	public boolean addUser(User user);
	
	public boolean deleteUser(User user);
	
	public User getUser(String Username);

	public boolean addCourses(String username, String course);

	public String getCourse(String Username, String Course);
	
	public List<String> getAllCourses(String Username);

}
