package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class AddUserController {
	public void addUser(User user){
		IDatabase db = Database.getInstance();
		db.addUser(user);
	}
}
