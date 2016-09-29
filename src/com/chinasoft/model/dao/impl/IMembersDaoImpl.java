package com.chinasoft.model.dao.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasoft.model.dao.IMembersDao;
import com.chinasoft.model.dbfactory.DBFactory;
import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.entity.USERPASSWORD;
import com.chinasoft.util.DateTime;

public class IMembersDaoImpl implements IMembersDao {

	private static Connection connection = null;
	
	
	@Override
	//根据id查询Member
	public MEMBERS selMemById(int mid) {
		
		connection = DBFactory.getInstance();
		String sql = "select * from Members where M_id = ?";
		MEMBERS mem = new MEMBERS();
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, mid);
			
			ResultSet res1 = ps1.executeQuery();
			while(res1.next())
			{
				mem.setM_id(res1.getInt("M_id"));
				mem.setP_no(res1.getShort("P_no"));
				mem.setM_nickname(res1.getString("M_nickname"));
				mem.setM_icon(res1.getString("M_icon"));
				mem.setM_exp(res1.getShort("M_exp"));
				mem.setM_regdate(res1.getString("M_regdate"));
				mem.setM_lbantime(res1.getString("M_lbantime"));
			}
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mem;
	}

	@Override
	//查询所有的论坛管理
	public List<MEMBERS> selForumManagers() {
		connection = DBFactory.getInstance();
		String sql = "select top 4 * from Members where P_no = 3 order by M_exp desc";
		List<MEMBERS> list = new ArrayList<MEMBERS>();
		MEMBERS mem = null;
		
		try {
			Statement s1 = connection.createStatement();
			
			ResultSet res1 = s1.executeQuery(sql);
			while(res1.next())
			{
				mem = new MEMBERS();
				mem.setM_id(res1.getInt("M_id"));
				mem.setP_no(res1.getShort("P_no"));
				mem.setM_nickname(res1.getString("M_nickname"));
				mem.setM_icon(res1.getString("M_icon"));
				mem.setM_exp(res1.getShort("M_exp"));
				mem.setM_regdate(res1.getString("M_regdate"));
				mem.setM_lbantime(res1.getString("M_lbantime"));
				list.add(mem);
			}
			
			res1.close();
			s1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	
	

	@Override
	//签到加经验
	public boolean signinExp(int mid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Members set M_exp = M_exp + 5 where M_id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, mid);
			
			ps1.executeUpdate();
			
			ps1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	
	@Override
	//注册
	public boolean registMem(USERPASSWORD up) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		
		String imgStr = "img/DEFAULTICON/defaultMemIcon.png";
		String sql = "insert into Members select 1,?,?,0,?,null";
		String sql1 = "select M_id from Members where M_nickname = ?";
		String sql2 = "insert into UserPassword select ?,?,?,?,?";
		String sql3 = "insert into MembersInfo select ?,null,null,null,null,null,null,null,null,null,null,1";
		String sql4 = "insert into Dailyexp select ?,?,?,?";
		
		int mid = 0;
		
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement ps1 = connection.prepareStatement(sql);
			PreparedStatement ps2 = connection.prepareStatement(sql1);
			PreparedStatement ps3 = connection.prepareStatement(sql2);
			PreparedStatement ps4 = connection.prepareStatement(sql3);
			PreparedStatement ps5 = connection.prepareStatement(sql4);
			ps1.setString(1, up.getU_account());
			ps1.setString(2, imgStr) ;
			ps1.setString(3, DateTime.sqlDateTime());
			ps1.executeUpdate();
			
			
			ps2.setString(1, up.getU_account());
			ResultSet res1 = ps2.executeQuery();
			while(res1.next()){
				mid = res1.getInt("M_id");
			}
			
			
			ps3.setInt(1, mid);
			ps3.setString(2, up.getU_account());
			ps3.setString(3, up.getU_password());
			ps3.setString(4, up.getU_safecode());
			ps3.setString(5, up.getU_phone());
			ps3.executeUpdate();
			
			
			ps4.setInt(1, mid);
			ps4.executeUpdate();
			
			
			ps5.setInt(1, mid);
			ps5.setString(2, "2016-09-25");
			ps5.setInt(3, 0);
			ps5.setString(4, "2016-09-25");
			ps5.executeUpdate();
			
			connection.commit();
			
			
			ps1.close();
			ps2.close();
			res1.close();
			ps3.close();
			ps4.close();
			ps5.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
			
			return false;
		}finally{
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
		
	}

	@Override
	//判断昵称是否存在
	public boolean checkNickname(String nickname) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "select * from Members where M_nickname = ?";
		boolean flag = true;
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, nickname);
			
			ResultSet res1 = ps1.executeQuery();
			
			if(res1.next())
			{
				flag = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	//上传头像修改头像路径
	public boolean uploadIcon(MEMBERS mem) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Members set M_icon = ? where M_id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, mem.getM_icon());
			ps1.setInt(2, mem.getM_id());
			
			ps1.executeUpdate();
			
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	

}















