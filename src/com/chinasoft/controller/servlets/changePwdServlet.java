package com.chinasoft.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.USERPASSWORD;
import com.chinasoft.model.services.IUserpasswordService;
import com.chinasoft.model.services.impl.IUserpasswordServiceImpl;

public class changePwdServlet extends HttpServlet {

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

		request.getSession().invalidate();
		
		String account = request.getParameter("changepwd_account");
		String password = request.getParameter("changepwd_renewpwd");
		
		USERPASSWORD up = new USERPASSWORD();
		up.setU_account(account);
		up.setU_password(password);
		up.setU_phone(account);
		
		IUserpasswordService iups = new IUserpasswordServiceImpl();
		boolean i = iups.updatePwd(up);
		
		if(i == true)
		{
			Thread thread = new Thread();
			try {
				request.getSession().invalidate();
				
				Thread.sleep(5000);
				response.sendRedirect("login.jsp");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("changepwd.jsp?msg=<font color:red>系统出错,请稍后再试!.....</font>");
		}
		
		
	}

}









