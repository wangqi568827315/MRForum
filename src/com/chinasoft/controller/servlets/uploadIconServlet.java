package com.chinasoft.controller.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.services.IMembersService;
import com.chinasoft.model.services.impl.IMembersServiceImpl;
import com.chinasoft.util.DateTime;

import sun.misc.BASE64Decoder;

public class uploadIconServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String imgdata = request.getParameter("imgdata");
		int mid = ((MEMBERS) request.getSession().getAttribute("loginUser"))
				.getM_id();

		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (CreateImages(imgdata, mid)) {
			out.print("ok");
		} else {
			out.print("error");
		}

		out.flush();
		out.close();
	}

	public boolean CreateImages(String imagedata, int loginmid) {
		// String[] param = imagedata.split(";");
		byte[] img = getImgData(imagedata);

		if (img == null) {
			return false;
		}

		// 取出本来的路径
		IMembersService ims = new IMembersServiceImpl();
		String oldpath = ims.selMemById(loginmid).getM_icon();

		// 存图片的path
		String url = null;
		String sqliconurl = null;
		String filepath = getServletContext().getRealPath("uploads");
		if (oldpath.equals("img/DEFAULTICON/defaultMemIcon.png")) {
			
			String pathtime = DateTime.getAccDateTime("");
			String pathradomint = String.valueOf(Math.random()).substring(2, 5);
			
			url = filepath + "/" + pathtime + pathradomint + ".png";
			sqliconurl = "/MRForum/uploads/"+pathtime + pathradomint+".png";
		}else {
			url = filepath+oldpath.replace("/MRForum/uploads", "");
			sqliconurl = oldpath;
		}

		// 
		File file = new File(url);

		if (file.exists()) {
			file.delete();
		}else{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(img);
			fos.flush();
			
			//将新路径更新到数据库
			MEMBERS mem = new MEMBERS();
			mem.setM_id(loginmid);
			mem.setM_icon(sqliconurl);
			ims.uploadIcon(mem);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

	}

	public byte[] getImgData(String imagedata) {
		int start = imagedata.indexOf(",");

		String temp = imagedata.substring(start + 1);
		BASE64Decoder base64 = new BASE64Decoder();

		try {
			// Base64解码
			byte[] b = base64.decodeBuffer(temp);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			return b;
		} catch (Exception ex) {
			return null;
		}

	}

}
