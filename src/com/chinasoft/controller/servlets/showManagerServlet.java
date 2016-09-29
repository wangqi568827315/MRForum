package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;

public class showManagerServlet extends HttpServlet {

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
		
		response.setCharacterEncoding("utf-8");

		IMembersService ims = new IMembersServiceImpl();
		
		List<MEMBERS> managerList = ims.selForumManagers();
		
		JSONArray arricon = new JSONArray();
		JSONArray arrname = new JSONArray();
		JSONArray arrmid = new JSONArray();
		for(int i = 0 ; i < managerList.size() ; i++)
		{
			arricon.add(managerList.get(i).getM_icon());
			arrname.add(managerList.get(i).getM_nickname());
			arrmid.add(managerList.get(i).getM_id());
		}
		
		
		JSONObject obj = new JSONObject();
		
		obj.accumulate("micon", arricon);
		obj.accumulate("mname", arrname);
		obj.accumulate("mid", arrmid);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
	}

}












