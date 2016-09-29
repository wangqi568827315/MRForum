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
import com.chinasoft.model.entity.MEMBERSINFO;
import com.chinasoft.model.services.IMembersInfoService;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.impl.IMembersInfoServiceImpl;
import com.chinasoft.model.services.impl.IMembersServiceImpl;

public class editMsgServlet extends HttpServlet {

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
		//"nickname":nickname,"QMD":QMD,"sex":sex,"age":age,"birthday":birthday,"location":location,"explain":explain,"mid":mid
		int stateccn = Integer.valueOf(request.getParameter("stateccn"));
		String nickname = request.getParameter("nickname").replace(" ", "");
		String QMD = request.getParameter("QMD");
		String sex = request.getParameter("sex");
		short age = Short.valueOf(request.getParameter("age"));
		String birthday = request.getParameter("birthday");
		String location = request.getParameter("location");
		String explain = request.getParameter("explain");
		int mid = Integer.valueOf(request.getParameter("mid"));
		
		
		MEMBERSINFO meminfo = new MEMBERSINFO();
		IMembersInfoService imis = new IMembersInfoServiceImpl();
		if(imis.selMemInfoById(mid).getMI_stateccn() == true && stateccn == 1){
			meminfo.setMI_stateccn(true);
		}else {
			meminfo.setMI_stateccn(false);
		}
		meminfo.setM_id(mid);
		meminfo.setMI_age(age);
		
		if(birthday.indexOf("-")>1)
		{
			meminfo.setMI_birthday(birthday);
		}
		
		meminfo.setMI_explain(explain);
		if(location.indexOf("-")>1)
		{
			meminfo.setMI_location_sheng(location.split("-")[0]);
			meminfo.setMI_location_shi(location.split("-")[1]);
			meminfo.setMI_location_qu(location.split("-")[2]);
		}
		meminfo.setMI_QMD(QMD);
		meminfo.setMI_sex(sex);
		
		boolean flag = imis.updateMemInfoById(meminfo,nickname);
		
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		
		arr.add(flag);
		obj.accumulate("flag", arr);
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		out.print(obj.toString());
		
		out.flush();
		out.close();
		
	}

}













