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
	public boolean addUser(User user) {
		//add user to the list
		if(users.add(user))
			return true;
		else
			return false;
	}
	
	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		boolean check = false;
		
		for (User user1 : users) {
			if (user1.getName().equals(user.getName())&&user1.getPassword().equals(user.getPassword())) {
				if(users.remove(user1))
					check = true;
				else 
					check = false;
			}
		}	
		return check;
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
