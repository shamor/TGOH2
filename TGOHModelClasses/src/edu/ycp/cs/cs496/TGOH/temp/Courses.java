package edu.ycp.cs.cs496.TGOH.temp;

import java.util.ArrayList;
import java.util.List;

// FIXME: should be "Course" (singular)
public class Courses {
	private int id;
	private String Coursename;
	private String Teacher; // maybe should be user id of instructor?
	/*
	private List<String> Notification;
	private List<String> AcceptStudent;
	private List<String> PendingStudent;
	*/
	
	public Courses(){
		
	} 
	
	public Courses(String Coursename){
		this.Coursename = Coursename;
//		this.Notification = new ArrayList<String>();
//		this.AcceptStudent = new ArrayList<String>();
//		this.PendingStudent = new ArrayList<String>();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setCourse(String courseName) {
		this.Coursename = courseName;
	}
	
	public String getCourse() {
		return Coursename;
	}
	
	public void setTeacher(String teacher) {
		Teacher = teacher;
	}
	
	public String getTeacher() {
		return Teacher;
	}

	/*
	public String findNotification(String notify){
		for (String list : Notification){
			if(list.equals(notify)){
				return list;
			}
		}
		return null;
	}

	public boolean addNotification(String notify) {
		if(Notification.add(notify))
			return true;
		else
			return false;
	}
	
	public boolean deleteNotification(String notify) {
		if(Notification.remove(notify))
			return true;
		else 
			return false;
	}
	
	public String findAcceptStudent(String Student){
		for (String list : AcceptStudent){
			if(list.equals(Student)){
				return list;
			}
		}
		return null;
	}
	
	public boolean addAcceptStudent(String Student) {
		if(AcceptStudent.add(Student))
			return true;
		else
			return false;
	}
	
	public boolean deleteAcceptStudent(String Student) {
		if(AcceptStudent.remove(Student))
			return true;
		else 
			return false;
	}
	
	public String findPendingStudent(String Student){
		for (String list : PendingStudent){
			if(list.equals(Student)){
				return list;
			}
		}
		return null;
	}
	
	public boolean addPendingStudent(String Student) {
		if(PendingStudent.add(Student))
			return true;
		else
			return false;
	}
	
	public boolean deletePendingStudent(String Student) {
		if(PendingStudent.remove(Student))
			return true;
		else 
			return false;
	}
	*/
}
