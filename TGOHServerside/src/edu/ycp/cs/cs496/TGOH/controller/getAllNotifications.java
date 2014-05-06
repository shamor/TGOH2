package edu.ycp.cs.cs496.TGOH.controller;

import java.util.List;

import edu.ycp.cs.cs496.TGOH.pesist.Database;
import edu.ycp.cs.cs496.TGOH.pesist.IDatabase;
import edu.ycp.cs.cs496.TGOH.temp.Notification;

public class getAllNotifications {
	public List<Notification> getAllNotifications(int coursename){
		IDatabase db = Database.getInstance();
		return db.getNotificationForCourse(coursename);
	}
}
