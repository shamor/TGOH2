package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class GetController {
	public User getUser(String Username){
		IDatabase db = Database.getInstance();
		return db.getUser(Username);
	}
}
