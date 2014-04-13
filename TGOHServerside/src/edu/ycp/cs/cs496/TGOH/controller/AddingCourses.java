package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;

public class AddingCourses {
	public boolean addCourse(String Username, String Course){
		IDatabase db = Database.getInstance();
		return db.addCourses(Username, Course);
	}
}
