package edu.ycp.cs.cs496.TGOH.pesist;
import java.util.ArrayList;
import java.util.List;


import edu.ycp.cs.cs496.TGOH.temp.Courses;

import edu.ycp.cs.cs496.TGOH.temp.User;


public class FakeDatabase implements IDatabase {
	private List<User> users; 

	public FakeDatabase() {
		users = new ArrayList<User>();
		User user = new User("d","d","d","d",false);
		User user1 = new User("c","c","c","c",false);
		Courses usercourse = new Courses();
		
		usercourse.addCourse("CS101");
		usercourse.addCourse("CS191");
		usercourse.addCourse("CS108");
		usercourse.addCourse("CS107");
		user.addCourse(usercourse);
		users.add(user1);
		users.add(user);
	}
	
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
				return new User(user1.getName(), user1.getFirstName(), user1.getLastName(), user1.getPassword(), true);
			}
		}
		// no such user in database
		return null;
	}
	

	public List<Courses> getCourses(String username, String course){
		//Courses class1 = new Courses();
		for(User user1 : users){
			if (user1.getName().equals(username)) {
			//	 class1 = user1.getCourse();
					return user1.getCourse();
			}
		}
		//user not found
		return null;
		//return class1;
	}
	

	public boolean addACourse(String username, Courses course){
		for(User user1 : users){
			if (user1.getName().equals(username)) {
				if(user1.addCourse(course))
					return true;
			//	else
					//return false;
			}
		}
		return false;
	}
	
	public Courses getCourse(String Username, String Course){
		return getUser(Username).findCourse(Course);
	}
	
	public List<Courses> getAllCourse(String Username){
		return getUser(Username).getCourse();
	}
}
