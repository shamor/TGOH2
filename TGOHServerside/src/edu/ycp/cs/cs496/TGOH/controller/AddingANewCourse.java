package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Courses;

public class AddingANewCourse {
	public void addCourse(Courses course){
		IDatabase db = Database.getInstance();
		db.addCourse(course);
	}
}
