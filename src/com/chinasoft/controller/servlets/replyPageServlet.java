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

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.entity.NOTES;
import com.chinasoft.model.entity.Pager;
import com.chinasoft.model.entity.REPLIES;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.INotesService;
import com.chinasoft.model.services.IRepliesService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;
import com.chinasoft.model.services.impl.INotesServiceImpl;
import com.chinasoft.model.services.impl.IRepliesServiceImpl;

public class replyPageServlet extends HttpServlet {

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
		
		int nid = Integer.parseInt(request.getParameter("nid"));
		int pIndex = Integer.parseInt(request.getParameter("pIndex"));
		//根据id查出帖子
		INotesService ins = new INotesServiceImpl();
		IMembersService ims = new IMembersServiceImpl();
		NOTES note = ins.selNoteById(nid);
		
		Pager mypage = new Pager();
		mypage.setPageIndex(pIndex);
		mypage.setPageSize(20);
		mypage.setPageTotal(0);
		
		//根据nid查出一级回复列表
		IRepliesService irs = new IRepliesServiceImpl();
		List<REPLIES> list = new ArrayList<REPLIES>();
		list = irs.selAllReplies(nid,mypage);
		//取出最后一条回复的楼层
		int lastfloor = list.size() == 0? 1 : list.get(list.size() - 1).getR_floor();
		//
		
		
		//遍历所有一级回复
		JSONArray arr = new JSONArray();
		for(int i = 0 ; i < list.size() ; i++)
		{
			JSONObject obj1 = new JSONObject();
			//设置发布者昵称,头像,经验
			int pubrank = 0;
			String pubicon = "";
			String pubname = "";
			
			int id = list.get(i).getM_id();
			
			MEMBERS mem = ims.selMemById(id);
			
			//设置昵称颜色
			switch (ims.selMemById(id).getP_no()) {
				case 1: pubname = "<font style='color:black'>"+mem.getM_nickname()+"</font>";
					break;
				case 2: pubname = "<font style='color:red'>"+mem.getM_nickname()+"</font>";
					break;
				case 3: pubname = "<font style='color: #FF7F24 '>"+mem.getM_nickname()+"</font>";
					break;
				case 4: pubname = "<font style='color: #FF00FF '>"+mem.getM_nickname()+"</font>";
					break;
				}
			//发布者头像
			pubicon=mem.getM_icon();
			//发布者等级
			int exp = mem.getM_exp();
			if(exp >=0 && exp < 20) pubrank=1;
			else if(exp >=20 && exp < 50) pubrank=2;
			else if(exp >=50 && exp < 100) pubrank=3;
			else if(exp >=100 && exp < 200) pubrank=4;
			else if(exp >=200 && exp < 500) pubrank=5;
			else if(exp >=500 && exp < 1000) pubrank=6;
			else if(exp >=1000 && exp < 2000) pubrank=7;
			else if(exp >=2000 && exp < 5000) pubrank=8;
			else if(exp >=5000 && exp < 10000) pubrank=9;
			else if(exp >=10000 ) pubrank=10;
			//发布时间
			list.get(i).setR_reptime(list.get(i).getR_reptime().substring(0,16));
			
			//判断回复者是不是楼主本人
			String lzbgi = "";
			if(list.get(i) != null)
			{
				if(list.get(i).getM_id() == note.getM_id())
				{
					lzbgi = "img/showNoteIMG/publisherBgi.png";
				}
			}
			//
			
			//判断发布者是否是登陆者本人,管理员
			MEMBERS loginmem = (MEMBERS)(request.getSession().getAttribute("loginUser"));
			String delclass = null;
			if(loginmem != null)
			{
				if(((MEMBERS)request.getSession().getAttribute("loginUser")).getP_no()>=3)
				{
					delclass = "";
				}else {
					delclass = loginmem.getM_id()==list.get(i).getM_id()? "" : "hideclass";
				}
			}else{
				delclass = "hideclass";
			}
			//
			//判断回复是显示还是隐藏
			String showorhide = null;
			if(list.get(i).getR_statedel() == false)
			{
				showorhide = "showclass";
			}else{
				showorhide = "hideclass";
			}
			
			
			
			//查出所有二级回复(楼中楼)
			List<REPLIES> childlist = new ArrayList<REPLIES>();
			childlist = irs.selAllChildReplies(list.get(i));
			JSONArray arrchild = new JSONArray();
			if(childlist.size() != 0)
			{
				//遍历二级回复
				for(int j = 0 ; j < childlist.size() ; j++)
				{
					//处理字数(90字)
					String content = childlist.get(j).getR_content().length() > 90? 
									childlist.get(j).getR_content().substring(0,89)+"...":childlist.get(j).getR_content();
					childlist.get(j).setR_content(content);
					
					JSONObject obj2 = new JSONObject();
					
					String childpubname = ims.selMemById(childlist.get(j).getM_id()).getM_nickname();
					
					obj2.accumulate("childpubmid", childlist.get(j).getM_id());
					obj2.accumulate("childrep", childlist.get(j));
					obj2.accumulate("childpubname", childpubname);
					arrchild.add(obj2);
				}
			
			}
			
			
			
			obj1.accumulate("showorhide", showorhide);
			obj1.accumulate("childrepdata", arrchild);
			obj1.accumulate("objdata", list.get(i));
			obj1.accumulate("pubRank", pubrank);
			obj1.accumulate("pubIcon", pubicon);
			obj1.accumulate("pubName", pubname);
			obj1.accumulate("delClass", delclass);
			obj1.accumulate("lzbgi", lzbgi);
			
			arr.add(obj1);
		}
		
		
		JSONObject obj = new JSONObject();
		obj.accumulate("list", arr);
		obj.accumulate("pIndex", pIndex);
		obj.accumulate("pTotal", mypage.getPageTotal());
		obj.accumulate("lastfloor", lastfloor);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
	}

}














