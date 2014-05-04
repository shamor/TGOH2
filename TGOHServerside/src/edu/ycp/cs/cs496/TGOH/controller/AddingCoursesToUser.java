package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Registration;

public class AddingCoursesToUser {
	public Registration addingRegistrationToUser(int user, int course){
		IDatabase db = Database.getInstance();
		return db.registerUserForCourse(user, course);
	}
}
