package edu.ycp.cs.cs496.TGOH.pesist;

import edu.ycp.cs.cs496.TGOH.User.User;

public interface IDatabase {
/**
 * Database persistence methods
 * @return 
 */
	public boolean addUser(User user);
	
	public boolean deleteUser(User user);
	
	public User getUser(String Username); 
}
