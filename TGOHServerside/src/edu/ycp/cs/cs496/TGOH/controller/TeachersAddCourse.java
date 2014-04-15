package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;

public class TeachersAddCourse {
	public boolean addCourses(String Course){
		IDatabase db = Database.getInstance();
		return db.addCourse(Course);
	}
}
