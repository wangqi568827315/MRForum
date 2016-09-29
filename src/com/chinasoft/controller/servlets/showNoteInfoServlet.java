package com.chinasoft.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.entity.NOTES;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.INotesService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;
import com.chinasoft.model.services.impl.INotesServiceImpl;

public class showNoteInfoServlet extends HttpServlet {

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

		int noteid = Integer.valueOf(request.getParameter("noteid"));
		INotesService ins = new INotesServiceImpl();
		NOTES note = new NOTES();
		note = ins.selNoteById(noteid);
		if(note.getN_statedel() == false || note == null)
		{
			IMembersService ims = new IMembersServiceImpl();
			MEMBERS mem = null;
			mem = ims.selMemById(note.getM_id());
			
			note.setN_pubtime(note.getN_pubtime().substring(0,16));
			
			String pubname = "";
				switch (mem.getP_no()) {
					case 1: pubname = "<font style='color:black'>"+mem.getM_nickname()+"</font>";
						break;
					case 2: pubname = "<font style='color:red'>"+mem.getM_nickname()+"</font>";
						break;
					case 3: pubname = "<font style='color: #FF7F24 '>"+mem.getM_nickname()+"</font>";
						break;
					case 4: pubname = "<font style='color: #FF00FF '>"+mem.getM_nickname()+"</font>";	
						break;
					}
				
			String pubtitle = "";
			if(ins.selNoteById(noteid).getN_statesup()==true)
			{
				pubtitle = "<img src='img/indexIMG/other/supernote.png' style='position:relative;top:5px'/>"+ ins.selNoteById(noteid).getN_title();
				if(ins.selNoteById(noteid).getN_statetop()==true)
				{
					pubtitle = "<img src='img/indexIMG/other/topnote.png' style='position:relative;top:5px'/>"+pubtitle;
				}
			}else if(ins.selNoteById(noteid).getN_statetop()==true)
			{
				pubtitle = "<img src='img/indexIMG/other/topnote.png' style='position:relative;top:5px'/>"+ ins.selNoteById(noteid).getN_title();
			}else
			{
				pubtitle = ins.selNoteById(noteid).getN_title();
			}
			
			
			
			request.setAttribute("pubtitle", pubtitle);
			request.setAttribute("pubMem", mem);
			request.setAttribute("showNote", note);
			request.setAttribute("pubname", pubname);
			request.getRequestDispatcher("showNote.jsp").forward(request, response);
		}else 
		{
			response.sendRedirect("404error.jsp");
		}
		
		
		
	}

}










