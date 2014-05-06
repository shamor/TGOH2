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
	private int courseCounter = 1;
	private int notCounter = 1;

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
		c.setId(courseCounter++);
		c.setCourse("Introduction to Something");
		c.setTeacher("Babcock");
		courses.add(c);

		registrations = new ArrayList<Registration>();
//		Registration reg = new Registration();
//		reg.setId(registrationCounter++);
//		reg.setUserId(1);
//		//reg.setCourseId(1);
//		reg.setStatus(RegistrationStatus.PENDING);
//		registrations.add(reg);
		
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
		notifications.add(not);
	}

	public void addUser(User user) {
		//add user to the list
		users.add(user);
	}
	
	public User getUser(String username){
		for(User user : users){
			if(user.getUserName().equals(username)){
				return user;
			}
		}
		return null;
	}

	// getting a user
	public User getUserfromRegistration(int Username) {
		for (User user1 : users) {
			if (user1.getId()==Username) {
				// return a copy
				return user1;
			}
		}
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
		course.setId(courseCounter++);
		courses.add(course);
	}

	public void deleteCourse(int Coursename){
		courses.remove(Coursename-1);
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

	public void RemovingUserFromCourse(User user, Courses course){
		Registration reg = findUserForCourse(user, course);
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
	
	public Registration AcceptingUserforCourse(User user, Courses course){
		Registration reg = findUserForCourse(user, course);
		reg.setStatus(RegistrationStatus.APPROVED);
		return reg;
	}

	public List<Courses> getCoursefromUser(int user){
		int count = 0;
		List<Courses> course = new ArrayList<Courses>();
		for(Registration temp : registrations){
			if(temp.getUserId() == user && count < 7){
				course.add(getCourse(temp.getCourseId()));
				count++;
			}
		}
		return course;
	}
	
	public List<User> getPendingUserforCourse(int course){
		int count = 0;
		List<User> user = new ArrayList<User>();
		for(Registration temp : registrations){
			if(temp.getCourseId() == course && count < 30 && temp.getStatus()==RegistrationStatus.APPROVED){
				user.add(getUserfromRegistration(temp.getUserId()));
				count++;
			}
		}
		return user;
	}

	@Override
	public void changePass(String password) {
		//TODO: Implement
	}
	
	public Notification getNotification(int id){
		for(Notification not : notifications){
			if(not.getId() == id){
				return not;
			}
		}
		return null;
	}
	
	public List<Notification> getNotificationForCourse(int courseId){
		List<Notification> not = new ArrayList<Notification>();
		for(Notification temp : notifications){
			if(temp.getCourseId() == courseId){
				not.add(getNotification(temp.getId()));
			}
		}
		return not;
	}
	
	public Notification addNotification(int courseId, String text){
		Notification not = new Notification();
		not.setCourseId(courseId);
		not.setText(text);
		not.setId(notCounter++);
		notifications.add(not);
		return not;
	}
	
	public void removeNotification(int id){
		notifications.remove(id);
	}

	@Override
	public Courses getCourseByName(String coursename) {
		// TODO Auto-generated method stub
		return null;
	}
}
