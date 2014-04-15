package edu.ycp.cs.cs496.TGOH.Classes;

import edu.ycp.cs.cs496.TGOH.User.Student;

public class ListCourses {
	
		private Courses course;
		private Student student;
		
		public ListCourses(){
			
		}
		
		public ListCourses(Courses Course, Student student){
			this.course = Course;
			this.student = student;
		}
		
		public Courses getAllCourses() {
			return course;
		}
		
		public Student getStudent() {
			return student;
		}
		
		public void setStudent(Student student) {
			this.student = student;
		}
	}
