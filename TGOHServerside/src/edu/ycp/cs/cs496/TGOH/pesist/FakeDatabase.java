package edu.ycp.cs.cs496.TGOH.pesist;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.Notification;
import edu.ycp.cs.cs496.TGOH.temp.Registration;
import edu.ycp.cs.cs496.TGOH.temp.RegistrationStatus;
import edu.ycp.cs.cs496.TGOH.temp.User;


public class FakeDatabase implements IDatabase {
	private List<User> users; 
	private List<Courses> courses;
	private List<Registration> registrations;
	private List<Notification> notifications;

	private int registrationCounter = 1;

	public FakeDatabase() {
		users = new ArrayList<User>();
		User user = new User("d","d","d","d",true);
		user.setId(1);
		User user1 = new User("c","c","c","c",false);
		user1.setId(2);
		users.add(user1);
		users.add(user);

		courses = new ArrayList<Courses>();
		Courses c = new Courses();
		c.setId(1);
		c.setCourse("Introduction to Something");
		c.setTeacher("Babcock");
		courses.add(c);

		registrations = new ArrayList<Registration>();
		Registration reg = new Registration();
		reg.setId(registrationCounter++);
		reg.setUserId(1);
		reg.setCourseId(1);
		reg.setStatus(RegistrationStatus.PENDING);
		registrations.add(reg);
		
		Registration reg2 = new Registration();
		reg2.setId(registrationCounter++);
		reg2.setUserId(2);
		reg2.setCourseId(1);
		reg2.setStatus(RegistrationStatus.APPROVED);
		registrations.add(reg2);

		notifications = new ArrayList<Notification>();
		Notification not = new Notification();
		not.setText("going for a hike");
		not.setId(1);
		not.setCourseId(1);
	}

	public void addUser(User user) {
		//add user to the list
		users.add(user);
	}

	// getting a user
	public User getUser(String Username) {
		System.out.println("Looking for user: " + Username);
		for (User user1 : users) {
			if (user1.getUserName().equals(Username)) {
				// return a copy
				System.out.println("Found it, yay");
				return user1;
			}
		}
		// no such user in database
		System.out.println("Nope, not there");
		return null;
	}

	// deleting a user
	public boolean deleteUser(User user) {
		if(users.remove(getUser(user.getUserName())))
			return true;
		else 
			return false;
	}

	public Courses getCourse(int coursename){
		for(Courses x : courses){
			if(x.getId() == coursename){
				return x;
			}
		}
		return null;
	}
	
	public List<Courses> getAllCourse(){
		return new ArrayList<Courses>(courses);
	}

	public void addCourse(Courses course){
		courses.add(course);
	}

	public void deleteCourse(int Coursename){
		courses.remove(Coursename);
	}

	public Registration registerUserForCourse(int user, int course) {
		Registration reg = new Registration();
		reg.setId(registrationCounter++);
		reg.setUserId(user);
		reg.setCourseId(course);
		reg.setStatus(RegistrationStatus.PENDING);
		registrations.add(reg);
		return reg;
	}

	public void RemovingUserFromCourse(int user, int course){
		Registration reg = new Registration(user, course);
		registrations.remove(reg.getId());
	}

	public Registration findUserForCourse(User user, Courses course) {
		for(Registration temp : registrations){
			if(temp.getCourseId()==course.getId() && temp.getUserId()==user.getId()){
				return temp;
			}
		}
		return null;
	}

	public Courses[] getCoursefromUser(int user){
		int count = 0;
		Courses[] course = new Courses[7];
		for(Registration temp : registrations){
			if(temp.getUserId() == user && count < 7){
				course[count] = getCourse(temp.getCourseId());
				count++;
			}
		}
		return course;
	}

	@Override
	public Registration registerUserForCourse(String userId, String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void RemovingUserFromCourse(String userId, String courseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Registration findUserForCourse(String userId, String courseId) {
		// TODO Auto-generated method stub
		return null;
	}
}
