package edu.ycp.cs.cs496.TGOH.controller;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class GetCoursefromUser {
	public List<Courses> getCoursefromUser(int user){
		IDatabase db = Database.getInstance();
		return db.getCoursefromUser(user);
	}
}
