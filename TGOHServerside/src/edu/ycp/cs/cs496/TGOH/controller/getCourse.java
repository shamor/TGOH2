package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Courses;

public class getCourse {
	public String getCourseName(String Username, String Course){
		IDatabase db = Database.getInstance();
		return db.getCourse(Username, Course).getCourse(0);
	}
}
