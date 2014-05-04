package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class RemovingAUserFromCourse {
	public void RemovingUserFromCourse(User i, Courses j){
		IDatabase db = Database.getInstance();
		db.RemovingUserFromCourse(i, j);
	}
}
