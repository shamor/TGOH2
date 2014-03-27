package edu.ycp.cs.cs496.TGOH.pesist;

import edu.ycp.cs.cs496.TGOH.User.User;

public interface IDatabase {
/**
 * Database persistance methods
 */
	public void addUser(User user);
	
	public void deleteUser(User user);
	
	public User getUser(User user); 
}
