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
import edu.ycp.cs.cs496.TGOH.temp.User;

public class CoursesPage {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		String pathInfo1 = req.getPathInfo();
		if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {
			// FIXME: add support for accessing the entire inventory
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("text/plain");
			resp.getWriter().println("Getting entire inventory not supported yet");
			return;
		}
		
		// Get the item name
		if (pathInfo.startsWith("/"))
			pathInfo = pathInfo.substring(1);
			pathInfo1 = pathInfo1.substring(2);
		

		// Use a GetItemByName controller to find the item in the database
		getCourse controller = new getCourse();
		String user = controller.getUser(pathInfo, pathInfo1);
		
		if (user == null) {
			// No such item, so return a NOT FOUND response
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			resp.setContentType("text/plain");
			resp.getWriter().println("No such item: " + pathInfo);
			return;
		}

		// Set status code and content type
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");
		
		// Return the item in JSON format
		JSON.getObjectMapper().writeValue(resp.getWriter(), user);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = JSON.getObjectMapper().readValue(req.getReader(), User.class);
		String course = JSON.getObjectMapper().readValue(req.getReader(), String.class);
			// Use a GetItemByName controller to find the item in the database
		AddingCourses controller = new AddingCourses();
		controller.addCourse(user.getName(), course);
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
