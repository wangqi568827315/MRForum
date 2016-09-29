package com.chinasoft.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;

public class indexRefreshServlet extends HttpServlet {

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
	@Override
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
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MEMBERS loginuser = (MEMBERS) request.getSession().getAttribute("loginUser");
		if(loginuser != null)
		{
			MEMBERS newuser = new MEMBERS();
			
			IMembersService ims = new IMembersServiceImpl();
			newuser = ims.selMemById(loginuser.getM_id());
			
			request.getSession().setAttribute("loginUser", newuser);
		}
		
		response.sendRedirect("index.jsp");
		
	}

}











