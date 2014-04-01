package edu.ycp.cs.cs496.TGOH.pesist;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs.cs496.TGOH.User.User;

public class FakeDatabase implements IDatabase {
	private List<User> users; 
	public FakeDatabase() {
		users = new ArrayList<User>(); 
		
	}
	
	/**
	 * Adds user to the database
	 */
	@Override
	public void addUser(User user) {
		//add user to the list
		users.add(user);
		
	}
	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		for (User user1 : users) {
			if (user1.getName().equals(user.getName())) {
				users.remove(user1);
			}
		}
		
	}
		
	@Override
	public User getUser(User user) {

		for (User user1 : users) {
			if (user1.getName().equals(user.getName())) {
				// return a copy
				return new User(user.getName(), user.getFirstName(), user.getLastName(), user.getPassword());
			}
		}
		
		// no such user in database
		return null;
		
	}

}
