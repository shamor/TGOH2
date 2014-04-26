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

	public boolean addACourse(String username, Courses course);

	public Courses getCourse(String Username, String course);
	
	public List<Courses> getAllCourses(String Username);

}
