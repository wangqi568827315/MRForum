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
import com.chinasoft.model.entity.NOTES;
import com.chinasoft.model.services.IDailyexpService;
import com.chinasoft.model.services.INotesService;
import com.chinasoft.model.services.impl.IDailyexpServiceImpl;
import com.chinasoft.model.services.impl.INotesServiceImpl;

public class pubNoteServlet extends HttpServlet {

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

		String title = request.getParameter("title");
		String article = request.getParameter("article");
		int mid = ((MEMBERS)request.getSession().getAttribute("loginUser")).getM_id();
		
		article = article.replace("/ueditor", "/MRForum/ueditor");
		article = article.replace("src", "style='max-width:600px' src");
		
		NOTES note = new NOTES();
		note.setM_id(mid);
		note.setN_title(title);
		note.setN_article(article);
		
		INotesService ins = new INotesServiceImpl();
		boolean pubed = false;
		
		//是否可以加经验
		IDailyexpService ides = new IDailyexpServiceImpl();
		ides.updateExp(5, mid);
		String canaddexp = null;
		if(ides.selDailyExpById(mid).getD_dsexp() <= 15)
		{
			canaddexp = "canadd";
			pubed = ins.pubNotes(5, note);
		}else {
			canaddexp = "cannotadd";
			pubed = ins.pubNotes(0, note);
		}
		
		
		
		
		JSONArray arr = new JSONArray();
		arr.add(pubed);
		
		JSONObject obj = new JSONObject();
		obj.accumulate("pubed", arr);
		obj.accumulate("canaddexp", canaddexp);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();	
		
	}

}














