package edu.ycp.cs.cs496.TGOH.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.TGOH.JSON.JSON;
import edu.ycp.cs.cs496.TGOH.controller.AddController;
import edu.ycp.cs.cs496.TGOH.controller.AddingCourses;
import edu.ycp.cs.cs496.TGOH.controller.DeleteUserController;
import edu.ycp.cs.cs496.TGOH.controller.GetController;
import edu.ycp.cs.cs496.TGOH.controller.getCourse;
import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class CoursesPage {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		String UserpathInfo = "";//the user name associated with the request
		String ClasspathInfo = "";//the user name associated with the request
		
		if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {
			//accessing all classes
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("text/plain");
			resp.getWriter().println("Getting entire class list not supported yet");
			return;
		}
		
		// Get the user name
		if (pathInfo.startsWith("/")) {
			UserpathInfo = pathInfo.substring(1);
		}//get class name
		if(UserpathInfo.contains("/")){
			int i = UserpathInfo.indexOf("/");
			ClasspathInfo = UserpathInfo.substring(i+1);
			UserpathInfo = UserpathInfo.substring(0, i);
		}
		if (pathInfo == "courses" || pathInfo.equals("") || pathInfo.equals("/")) {
			//accessing all classes
			
			
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("text/plain");
			resp.getWriter().println("Getting entire class list not supported yet");
			return;
		}

		// Use a GetItemByName controller to find the item in the database
		getCourse controller = new getCourse();
		String coursename = controller.getCourseName(UserpathInfo, ClasspathInfo);
		
		if (coursename == null) {
			// No such item, so return a NOT FOUND response
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			resp.setContentType("text/plain");
			resp.getWriter().println("No such course: " + pathInfo +  " in which you are enrolled");
			return;
		}

		// Set status code and content type
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");
		
		// Return the item in JSON format
		JSON.getObjectMapper().writeValue(resp.getWriter(), coursename);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = JSON.getObjectMapper().readValue(req.getReader(), User.class);
		String course = JSON.getObjectMapper().readValue(req.getReader(), String.class);
			// Use a GetItemByName controller to find the item in the database
		Courses newcourse = new Courses();
		newcourse.addCourse(course);
		//A//ddingCourses controller = new AddingCourses();
		//controller.addCourse(user.getName(), newcourse);
		// Set status code and content type
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");
		
		// writing the operation out.
		JSON.getObjectMapper().writeValue(resp.getWriter(), user);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = JSON.getObjectMapper().readValue(req.getReader(), User.class);
		
		DeleteUserController deleteUser = new DeleteUserController();
		deleteUser.deleteUser(user);
		
		// send response
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");	
		
		GetController getUser = new GetController();
		
		JSON.getObjectMapper().writeValue(resp.getWriter(), getUser.getUser(user.getName()));
	}

}
