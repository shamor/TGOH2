package edu.ycp.cs.cs496.TGOH.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.TGOH.JSON.JSON;
import edu.ycp.cs.cs496.TGOH.controller.AddUserController;
import edu.ycp.cs.cs496.TGOH.controller.AddingCourses;
import edu.ycp.cs.cs496.TGOH.controller.DeleteUserController;
import edu.ycp.cs.cs496.TGOH.controller.GetUserController;
import edu.ycp.cs.cs496.TGOH.controller.getCourseController;
import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class CoursesPage {
private static final long serialVersionUID = 1L;
	
	/*protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();

		if (pathInfo == null || pathInfo.equals("") || pathInfo.equals("/")) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("text/plain");
			resp.getWriter().println("Getting entire UserList not supported yet");
			return;
		}
		
		// Get the item name
		if (pathInfo.startsWith("/")) 
			pathInfo = pathInfo.substring(1);

		// Use a GetItemByName controller to find the item in the database
		getCourseController controller = new getCourseController();
		Courses course = controller.(pathInfo);
		
		if (user == null) {
			// No such item, so return a NOT FOUND response
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			resp.setContentType("text/plain");
			resp.getWriter().println("No such user: " + pathInfo);
			return;
		//}

		// Set status code and content type
		//resp.setStatus(HttpServletResponse.SC_OK);
		//resp.setContentType("application/json");
		
		// Return the item in JSON format
		JSON.getObjectMapper().writeValue(resp.getWriter(), user);
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
		
		GetUserController getUser = new GetUserController();
		
		JSON.getObjectMapper().writeValue(resp.getWriter(), getUser.getUser(user.getName()));
	}*/

}
