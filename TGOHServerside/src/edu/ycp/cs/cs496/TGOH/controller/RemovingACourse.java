package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;

public class RemovingACourse {
	public void removingACourse(int CourseId){
		IDatabase db = Database.getInstance();
		db.deleteCourse(CourseId);
	}
}
