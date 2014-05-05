package edu.ycp.cs.cs496.TGOH.controller;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class getPendingUserForCourse {
	public List<User> getPendingUserforCourse(int courseId){
		IDatabase db = Database.getInstance();
		return db.getPendingUserforCourse(courseId);
	}
}
