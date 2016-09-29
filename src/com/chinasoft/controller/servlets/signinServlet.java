package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.chinasoft.model.entity.DAILYEXP;
import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.services.IDailyexpService;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.impl.IDailyexpServiceImpl;
import com.chinasoft.model.services.impl.IMembersServiceImpl;
import com.chinasoft.util.DateTime;

public class signinServlet extends HttpServlet {

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

		int mid = Integer.valueOf(request.getParameter("mid"));
		
		IMembersService ims = new IMembersServiceImpl();
	
		//签到更改最后签到时间
		IDailyexpService ides = new IDailyexpServiceImpl();
		ides.updateSigned(mid);
		
		//根据id查询dailyexp
		String flag = null;
		
		if(ims.signinExp(mid) == true)
		{
			flag = "ok";
		}else {
			flag = "no";
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print(flag);
		
		out.flush();
		out.close();
	}

}














