package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.entity.REPLIES;
import com.chinasoft.model.services.IDailyexpService;
import com.chinasoft.model.services.IRepliesService;
import com.chinasoft.model.services.impl.IDailyexpServiceImpl;
import com.chinasoft.model.services.impl.IRepliesServiceImpl;

public class replyNoteServlet extends HttpServlet {

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

		String replyContent = request.getParameter("content");
		int noteid = Integer.valueOf(request.getParameter("noteid"));
		int currentfloor = Integer.valueOf(request.getParameter("lastfloor"))+1;
		int memid = ((MEMBERS)(request.getSession().getAttribute("loginUser"))).getM_id();
		int pfloor = Integer.valueOf(request.getParameter("pfloor"));
		
		//处理图片
	
		replyContent = replyContent.replace("/ueditor", "/MRForum/ueditor");
		replyContent = replyContent.replace("src", "style='max-width:600px' src");
		
		REPLIES reply = new REPLIES();
		reply.setM_id(memid);
		reply.setN_id(noteid);
		reply.setR_floor(currentfloor);
		reply.setR_content(replyContent);
		reply.setR_pfloor(pfloor);
		reply.setR_statedel(false);
		
		IRepliesService irs = new IRepliesServiceImpl();
		
		boolean i = false;
		
		String canaddexp = null;
		if(pfloor == 1)
		{
			//是否可以加经验
			IDailyexpService ides = new IDailyexpServiceImpl();
			ides.updateExp(2, memid);
			if(ides.selDailyExpById(memid).getD_dsexp() <= 15)
			{
				canaddexp = "canadd";
				i = irs.pubReply(2,reply);
			}else {
				canaddexp = "cannotadd";
				i = irs.pubReply(0,reply);
			}
			
		}else {
			i = irs.pubChildReply(reply);
		}
		
		
		
		
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		
		arr.add(i);
		obj.accumulate("repjudge", arr);
		obj.accumulate("canaddexp", canaddexp);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
		
	}

}















