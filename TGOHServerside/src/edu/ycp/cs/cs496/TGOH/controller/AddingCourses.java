package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Courses;

public class AddingCourses {
	public void addCourse(String Username, String Course){
		IDatabase db = Database.getInstance();
		db.addACourse(Username, Course);
	}
}
