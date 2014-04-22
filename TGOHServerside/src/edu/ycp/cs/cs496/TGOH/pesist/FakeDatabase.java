package edu.ycp.cs.cs496.TGOH.pesist;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs.cs496.TGOH.temp.User;

public class FakeDatabase implements IDatabase {
	private List<User> users; 
	
	public FakeDatabase() {
		users = new ArrayList<User>(); 
	}
	
	// adding the user
	public boolean addUser(User user) {
		//add user to the list
		if(users.add(user))
			return true;
		else
			return false;
	}
	
	// deleting a user
	public boolean deleteUser(User user) {
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
	
	// getting a user
	public User getUser(String Username) {
		for (User user1 : users) {
			if (user1.getName().equals(Username)) {
				// return a copy
				return user1;
			}
		}
		// no such user in database
		return null;
	}
	
	public boolean addCourses(String username, String course){
		for(User user1 : users){
			if (user1.getName().equals(username)) {
				if(user1.addCourse(course))
					return true;
				else
					return false;
			}
		}
		return false;
	}
}
