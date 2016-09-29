package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.IUserpasswordService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;
import com.chinasoft.model.services.impl.IUserpasswordServiceImpl;

public class checkNicknameServlet extends HttpServlet {

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

		String nickname = request.getParameter("nickname");
		
		IMembersService ims = new IMembersServiceImpl();
		
		boolean flag = ims.checkNickname(nickname);
		
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		
		arr.add(flag);
		obj.accumulate("judgeSC", arr);
		
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
	}

}









