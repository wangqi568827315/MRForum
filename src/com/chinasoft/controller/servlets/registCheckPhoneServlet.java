package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.chinasoft.model.services.IUserpasswordService;
import com.chinasoft.model.services.impl.IUserpasswordServiceImpl;

public class registCheckPhoneServlet extends HttpServlet {

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

		String phone = request.getParameter("phone");
		boolean exist = false;
		
		IUserpasswordService iups = new IUserpasswordServiceImpl();
		exist = iups.uniquePhone(phone);
		
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		
		arr.add(exist);
		obj.accumulate("exist", arr);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
	}

}
