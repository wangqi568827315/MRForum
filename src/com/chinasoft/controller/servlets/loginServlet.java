package com.chinasoft.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.IUserpasswordService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;
import com.chinasoft.model.services.impl.IUserpasswordServiceImpl;

public class loginServlet extends HttpServlet {

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
		
		String account = request.getParameter("username");
		String password = request.getParameter("password");
		
		IUserpasswordService iups = new IUserpasswordServiceImpl();
		MEMBERS loginUser = null;
		
		int mid = iups.selAccount(account, password);
		
		if(mid != 0)
		{
			IMembersService ims = new IMembersServiceImpl();
			loginUser = ims.selMemById(mid);
			
			request.getSession().setAttribute("loginUser", loginUser);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else
		{
			response.sendRedirect("login.jsp?msg=账号/密码错误,请重新输入!");
		}
		
	}

}
























