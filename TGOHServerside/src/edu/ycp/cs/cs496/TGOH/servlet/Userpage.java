package edu.ycp.cs.cs496.TGOH.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs.cs496.TGOH.User.User;

public class Userpage {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
			req.setAttribute("action", action);
		}
		String itemName = getItemName(req);
		showUI(req, resp, itemName);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String itemName = getItemName(req);
		String action = req.getParameter("action");
		System.out.println(action);
		if (action == null || action.trim().equals("")) {
			showUI(req, resp, itemName);
			return;
		}   if (action.equals("add")) {
			//PostInventory controller = new PostInventory();
			String submittedItemName = req.getParameter("itemName");
			int submittedItemQuantity = Integer.parseInt(req.getParameter("itemQuantity"));
			//Item itemToInsert = new Item(submittedItemName, submittedItemQuantity);
			//if(controller.addItem(itemToInsert))
				req.setAttribute("result", "Item added successfully");
			//else
				req.setAttribute("result", "Item can't be added");
			//req.setAttribute("Item", itemToInsert);
			//showUI(req, resp, itemToInsert.getName());
			return;
		} 	if (action.equals("edit")) {
			//Replace controller = new Replace();
			int submittedItemQuantity = Integer.parseInt(req.getParameter("itemQuantity"));
			//Item itemToInsert =new Item(itemName, submittedItemQuantity);
			//controller.replace(itemToInsert);
			req.setAttribute("result", "Item added successfully");
			//req.setAttribute("Item", itemToInsert);
			//showUI(req, resp, itemToInsert.getName());
			return;
		} 	if (action.equals("delete")) {
			req.setAttribute("action", action);
			//DeleteInventory controller = new DeleteInventory();
			//controller.DeleteInventory(itemName);
			//req.setAttribute("result", "Item deleted successfully");
			req.setAttribute("Item", null);
			showUI(req, resp, itemName);
			return;
		} 
		
		throw new ServletException("Unknown action: " + action);
	}

	public String getItemName(HttpServletRequest req) {
		String itemName = null;
		String pathInfo = req.getPathInfo();
		if (pathInfo != null && !pathInfo.equals("") && !pathInfo.equals("/")) {
			itemName = pathInfo;
			if (itemName.startsWith("/")) {
				itemName = itemName.substring(1);
			}
		}
		return itemName;
	}

	private void showUI(HttpServletRequest req, HttpServletResponse resp,
			String itemName) throws ServletException, IOException {
		if (itemName == null) {
			//req.setAttribute("Inventory", inventory);
			req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
		} else {
			//req.setAttribute("Item", item);
			req.getRequestDispatcher("/_view/item.jsp").forward(req, resp);
		}
	}
	
	private void addItem(HttpServletRequest req, HttpServletResponse resp,
			User user) throws ServletException, IOException {
		//if (item.getName() != null) {
			//PostInventory controller = new PostInventory();
			//controller.addItem(item);
			//req.setAttribute("Item", item);
			req.getRequestDispatcher("/_view/item.jsp").forward(req, resp);
		}
}
