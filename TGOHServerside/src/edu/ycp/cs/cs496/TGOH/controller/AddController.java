package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.User.User;
import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;

public class AddController {
	public boolean addUser(User user){
		IDatabase db = Database.getInstance();
		return db.addUser(user);
	}
}
