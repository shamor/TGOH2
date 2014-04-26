package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;

public class DeleteCoursesController {
	public void deleteCourse(String Username, String Course){
		IDatabase db = Database.getInstance();
		db.deleteCourse(Username, Course);
	}

}
