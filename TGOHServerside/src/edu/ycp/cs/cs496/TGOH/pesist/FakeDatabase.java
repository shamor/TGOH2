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
		User user = new User("d","d","d","d",false);
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
	
	public Courses getCourse(int coursename){
		for(Courses x : courses){
			if(x.getId() == coursename){
				return x;
			}
		}
		return null;
	}
	
	public Courses addCourse(String Coursename){
		Courses course = new Courses(Coursename);
		courses.add(course);
		return course;
	}
	
	public void deleteCourse(int Coursename){
		courses.remove(Coursename);
	}

	public Registration registerUserForCourse(User user, Courses course) {
		Registration reg = new Registration();
		reg.setId(registrationCounter++);
		reg.setUserId(user.getId());
		reg.setCourseId(course.getId());
		reg.setStatus(RegistrationStatus.PENDING);
		registrations.add(reg);
		return reg;
	}
	
	public void RemovingUserFromCourse(User user, Courses course){
		Registration reg = new Registration(user.getId(), course.getId());
		registrations.remove(reg.getId());
	}
	
	public Registration findpendingUserForCourse(User user, Courses course) {
		Registration reg = new Registration(user.getId(), course.getId());
		for(Registration temp : registrations){
			if(temp.getId()==reg.getId()&&temp.getStatus()==RegistrationStatus.PENDING){
				return reg;
			}
		}
		return null;
	}
	
	public Registration findApproveUserForCourse(User user, Courses course) {
		Registration reg = new Registration(user.getId(), course.getId());
		for(Registration temp : registrations){
			if(temp.getId()==reg.getId()&&temp.getStatus()==RegistrationStatus.APPROVED){
				return reg;
			}
		}
		return null;
	}
	
	public Courses[] getCoursefromUser(int user){
		Courses[] course = new Courses[7];
		int count = 0;
		for(Registration temp : registrations){
			if(temp.getUserId() == user){
				course[count] = getCourse(temp.getCourseId());
			}
		}
		return course;
	}
}