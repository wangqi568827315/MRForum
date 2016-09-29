package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.POA;

import com.chinasoft.model.services.INotesService;
import com.chinasoft.model.services.impl.INotesServiceImpl;

public class editNoteTopServlet extends HttpServlet {

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
		
		int noteid = Integer.valueOf(request.getParameter("noteid"));
		int eindex = Integer.valueOf(request.getParameter("eindex"));
		boolean flag = false;
		String result = null;
		INotesService ins = new INotesServiceImpl();
		
		if(ins.SelTopNoteCount() >= 3 && eindex == 1)
		{
			result = "× 置顶主题不能超过3个!";
		}else{
			if(request.getParameter("noteid")==null)
			{
				response.sendRedirect("404error.jsp");
			}else
			{
				flag = ins.editTopNote(noteid, eindex);
			}
			
			if(flag == true)
			{
				result = "ok";
			}else {
				result = "no";
			}
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		
		out.flush();
		out.close();
		
	}

}









