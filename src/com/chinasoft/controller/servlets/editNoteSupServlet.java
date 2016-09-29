package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.services.INotesService;
import com.chinasoft.model.services.impl.INotesServiceImpl;

public class editNoteSupServlet extends HttpServlet {

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
		
		response.setCharacterEncoding("utf-8");
		//==null
		int noteid = Integer.valueOf(request.getParameter("noteid"));
		int eindex = Integer.valueOf(request.getParameter("eindex"));
		boolean flag = false;
		
		if(request.getParameter("noteid")==null)
		{
			response.sendRedirect("404error.jsp");
		}else
		{
			INotesService ins = new INotesServiceImpl();
			flag = ins.editSupNote(noteid, eindex);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(flag == true)
		{
			out.print("ok");
		}else {
			out.print("no");
		}
		
		out.flush();
		out.close();
		
	}

}















