package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logoutServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("logoutindex") == null)
		{
			response.sendRedirect("404error.jsp");
		}else
		{
			int logoutindex = Integer.valueOf(request.getParameter("logoutindex"));
			if(logoutindex == 1)
			{
				request.getSession().invalidate();
				response.sendRedirect("login.jsp");
			}else if(logoutindex == 2)
			{
				request.getSession().invalidate();
				response.sendRedirect("index.jsp");
			}else 
			{
				response.sendRedirect("404error.jsp");
			}
			
			
		}
		
		
	}

}








