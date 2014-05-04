package edu.ycp.cs.cs496.TGOH.controller;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Notification;

public class getANotification {
	public Notification GetANotification(int id){
		IDatabase db = Database.getInstance();
		return db.getNotification(id);
	}
}
