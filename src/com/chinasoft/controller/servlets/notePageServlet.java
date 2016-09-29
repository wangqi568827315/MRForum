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

public class notePageServlet extends HttpServlet {

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
		
		int pageIndex = Integer.parseInt(request.getParameter("pIndex"));
		int typeIndex = Integer.parseInt(request.getParameter("tIndex"));
		
		INotesService ins = new INotesServiceImpl();
		Pager mypage = new Pager();
		mypage.setPageIndex(pageIndex);
		mypage.setPageSize(30);
		mypage.setPageTotal(0);
		
		List<NOTES> res = new ArrayList<NOTES>();
		if(typeIndex == 0)
		{
			res = ins.selAllNotesByPage(mypage);
		}else if(typeIndex == 1)
		{
			res = ins.selSuperNotes(mypage);
		}
		
	
		
		
		//查出普通帖
		JSONArray arr = new JSONArray();
		for(int i = 0 ; i < res.size() ; i++)
		{
			JSONObject obj = new JSONObject();
			String pubname="";
			int id = res.get(i).getM_id();
			IMembersService ims = new IMembersServiceImpl();
			//设置名字和标题字体颜色
			
			//取出标题内容
			obj.accumulate("notetitle", res.get(i).getN_title());
			//
			
			//处理标题字数(20字)
			String titleString = res.get(i).getN_title().length() > 20? 
					res.get(i).getN_title().substring(0,19)+"...":res.get(i).getN_title();
			res.get(i).setN_title(titleString);
			//处理正文字数(30字)
			String articleString = res.get(i).getN_article();
			if(articleString.contains("img"))
			{
				articleString = res.get(i).getN_article().length() > 30? 
						res.get(i).getN_article().substring(0,29)+res.get(i).getN_article().substring(29).split("/>")[0]+" />"
						+"...":res.get(i).getN_article();
				res.get(i).setN_article(articleString.replace("max-width:600px", "max-width:250px"));
			}else {
				articleString = res.get(i).getN_article().length() > 30? res.get(i).getN_article().substring(0,29)+"...":res.get(i).getN_article();
				res.get(i).setN_article(articleString);
			}
			
			//
			
			switch (ims.selMemById(id).getP_no()) {
				case 1: {
					pubname = "<font style='color:black'>"+ims.selMemById(id).getM_nickname()+"</font>";
					res.get(i).setN_title("<font style='color:black'>"+res.get(i).getN_title()+"</font>");
				}
					break;
				case 2: {
					pubname = "<font style='color:red'>"+ims.selMemById(id).getM_nickname()+"</font>";
					res.get(i).setN_title("<font style='color:red'>"+res.get(i).getN_title()+"</font>");
				}
					break;
				case 3: {
					pubname = "<font style='color: #FF7F24 '>"+ims.selMemById(id).getM_nickname()+"</font>";
					res.get(i).setN_title("<font style='color: #FF7F24 '>"+res.get(i).getN_title()+"</font>");
				
				}
					break;
				case 4: {
					pubname= "<font style='color: #FF00FF '>"+ims.selMemById(id).getM_nickname()+"</font>";
					res.get(i).setN_title("<font style='color: #FF00FF '>"+res.get(i).getN_title()+"</font>");
				}
					break;
				}
				
				//是否加精
				String supornot = res.get(i).getN_statesup()==true?"<img src='img/indexIMG/other/supernote.png' style='position:relative;top:5px'/>":"";
				//是否置顶
				String topornot = res.get(i).getN_statetop()==true?"<img src='img/indexIMG/other/topnote.png' style='position:relative;top:5px'/>":"";
				
				obj.accumulate("topornot", topornot);
				obj.accumulate("supornot", supornot);
				obj.accumulate("objdata", res.get(i));
				obj.accumulate("pname",pubname);
				arr.add(obj);
		}
		
		
		

		
		
		JSONObject obj = new JSONObject();
		obj.accumulate("notelist", arr);
		obj.accumulate("pIndex", pageIndex);
		obj.accumulate("pTotal", mypage.getPageTotal());
		//obj.accumulate("pubName", pubname);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
	}

}









