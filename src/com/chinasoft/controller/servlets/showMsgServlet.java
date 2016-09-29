package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.entity.MEMBERSINFO;
import com.chinasoft.model.services.IMembersInfoService;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.impl.IMembersInfoServiceImpl;
import com.chinasoft.model.services.impl.IMembersServiceImpl;

public class showMsgServlet extends HttpServlet {

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

		int mid = Integer.valueOf(request.getParameter("mid"));
		
		IMembersInfoService imis = new IMembersInfoServiceImpl();
		MEMBERSINFO meminfo = imis.selMemInfoById(mid);
		IMembersService ims = new IMembersServiceImpl();
		MEMBERS mem = ims.selMemById(mid);
		
		
		request.setAttribute("showMeminfo", meminfo);
		request.setAttribute("showMem", mem);
		request.getRequestDispatcher("showMsg.jsp").forward(request, response);
		
	}

}












