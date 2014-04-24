package edu.ycp.cs.cs496.TGOH.controller;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Courses;

public class GetAllCourses {
	public List<Courses> getAllCourses(String Username){
		IDatabase db = Database.getInstance();
		return db.getUserCourses(Username);
	}
}
