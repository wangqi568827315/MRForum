package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.DAILYEXP;
import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.services.IDailyexpService;
import com.chinasoft.model.services.impl.IDailyexpServiceImpl;
import com.chinasoft.util.DateTime;

public class showIndexServlet extends HttpServlet {

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

		String flag = null;
		
		if(request.getSession().getAttribute("loginUser") != null)
		{
			int mid = ((MEMBERS)request.getSession().getAttribute("loginUser")).getM_id();
			IDailyexpService ides = new IDailyexpServiceImpl();
			DAILYEXP dailyexp = new DAILYEXP();
			dailyexp = ides.selDailyExpById(mid);
			
			String currentdate = DateTime.getDate("-");
			//更新每日获得经验
			if(DateTime.diffDay(dailyexp.getD_ledate(), currentdate) >= 1)
			{
				ides.clearDailyExp(mid);
			}
			
			//判断能否签到
			String lsdate = dailyexp.getD_lsdate();
			if(DateTime.diffDay(lsdate, currentdate) >= 1)
			{
				flag = "cansignin";
			}else {
				flag = "cannontsign";
			}
			}else{
				flag = "unlogin";
			}
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
		
			out.print(flag);
		
			out.flush();
			out.close();
	}

}



















