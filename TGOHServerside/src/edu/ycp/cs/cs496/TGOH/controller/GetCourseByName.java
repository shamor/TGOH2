package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Courses;

public class GetCourseByName {
	public Courses getCourseByName(String coursename){
		IDatabase db = Database.getInstance();
		return db.getCourseByName(coursename);
	}
}
