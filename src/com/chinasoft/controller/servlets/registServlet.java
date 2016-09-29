package com.chinasoft.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.USERPASSWORD;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;

public class registServlet extends HttpServlet {

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

		String U_account = request.getParameter("msg_account");
		String U_password = request.getParameter("msg_pwd_check");
		String U_safecode = request.getParameter("msg_safepwd");
		String U_phone = request.getParameter("msg_phone");
		
		USERPASSWORD up = new USERPASSWORD();
		
		up.setU_account(U_account);
		up.setU_password(U_password);
		up.setU_safecode(U_safecode);
		up.setU_phone(U_phone);
		
		IMembersService ims = new IMembersServiceImpl();
		
		ims.registMem(up);
		
		Thread thread = new Thread();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("login.jsp");
		
		
		
	}

}









