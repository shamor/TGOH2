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
		String usercourse = "A";
		user.addCourse(usercourse);
		user.addCourse("B");
		users.add(user1);
		users.add(user);
	}

	public void addUser(User user) {
		//add user to the list
		users.add(user);
	}

	// getting a user
	public User getUser(String Username) {
		for (User user1 : users) {
			if (user1.getUserName().equals(Username)) {
				// return a copy
				return user1;
			}
		}
		// no such user in database
		return null;
	}

	// deleting a user
	public boolean deleteUser(User user) {
		if(users.remove(getUser(user.getUserName())))
			return true;
		else 
			return false;
	}

	public void addACourse(String username, String course){
		getUser(username).addCourse(course);
	}

	public String getCourse(String Username, String Course){
		return getUser(Username).findCourse(Course);
	}

	public List<String> getAllCourse(String Username){
		return getUser(Username).getCourse();
	}

	public void deleteCourse(String Username, String Course){
		getUser(Username).removeCourse(Course);
	}
}
