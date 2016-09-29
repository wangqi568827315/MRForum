package com.chinasoft.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.chinasoft.model.entity.NOTES;
import com.chinasoft.model.entity.Pager;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.INotesService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;
import com.chinasoft.model.services.impl.INotesServiceImpl;

public class msgNotePageServlet extends HttpServlet {

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
		
		response.setCharacterEncoding("utf-8");

		int currentpIndex = Integer.valueOf(request.getParameter("pIndex"));
		int mid = Integer.valueOf(request.getParameter("mid"));
		
		Pager mypage = new Pager();
		mypage.setPageIndex(currentpIndex);
		mypage.setPageSize(20);
		mypage.setPageTotal(0);
		
		List<NOTES> list = new ArrayList<NOTES>();
		INotesService ins = new INotesServiceImpl();
		
		list = ins.selMyNotes(mypage, mid);
		for(int i = 0 ; i < list.size() ; i++)
		{
			//控制标题字数
			if(list.get(i).getN_title().length() > 30)
			{
				list.get(i).setN_title(list.get(i).getN_title().substring(0,29)+"...");
			}
			//控制正文字数
			if(list.get(i).getN_article().length() > 45)
			{
				if(list.get(i).getN_article().contains("img"))
				{
					String article = null;
					article = list.get(i).getN_article().substring(0,44)+list.get(i).getN_article().substring(44).split("/>")[0]+" />...";
					list.get(i).setN_article(article.replace("max-width:600px", "max-width:250px"));
				}else {
					list.get(i).setN_article(list.get(i).getN_article().substring(0,44)+"...");
				}
			}
		}
		
		
		JSONArray arr = new JSONArray();
		arr.addAll(list);
		
		JSONObject obj = new JSONObject();
		obj.accumulate("datalist", arr);
		obj.accumulate("pIndex", currentpIndex);
		obj.accumulate("pTotal", mypage.getPageTotal());
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
		
		
	}

}













