package edu.ycp.cs.cs496.TGOH.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.TGOH.JSON.JSON;

public class TeachersClasses extends HttpServlet{
/*		private static final long serialVersionUID = 1L;
		
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String pathInfo = req.getPathInfo();
			
			// Get the item name
			if (pathInfo.startsWith("/")) 
				pathInfo = pathInfo.substring(1);

			// Use a GetItemByName controller to find the item in the database
			getTeachersClass controller = new getTeachersClass();
			String list = controller.getCourses(pathInfo);
			
			
			if (list == null) {
				// No such item, so return a NOT FOUND response
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				resp.getWriter().println("No such item: " + pathInfo);
				return;
			}

			// Set status code and content type
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
			
			// Return the item in JSON format
			JSON.getObjectMapper().writeValue(resp.getWriter(), list);
		}
		
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			ListCourses list = JSON.getObjectMapper().readValue(req.getReader(), ListCourses.class);
			
			// using the controller
			TeachersAddCourse controller = new TeachersAddCourse();
			controller.addCourses(list.getAllCourses().FindCourse(req.getPathInfo()));
			
			// Set status code and content type
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
			
			// writing the operation out.
			JSON.getObjectMapper().writeValue(resp.getWriter(), list);
		}
		
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			ListCourses list = JSON.getObjectMapper().readValue(req.getReader(), ListCourses.class);
			
			//DeleteUserController deleteUser = new DeleteUserController();
			//deleteUser.deleteUser(user);
			
			// send response
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");	
			
			//GetController getUser = new GetController();
			
			//JSON.getObjectMapper().writeValue(resp.getWriter(), getUser.getUser(user.getName()));
		}*/
	}