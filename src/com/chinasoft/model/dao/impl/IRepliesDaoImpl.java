package com.chinasoft.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasoft.model.dao.IRepliesDao;
import com.chinasoft.model.dbfactory.DBFactory;
import com.chinasoft.model.entity.Pager;
import com.chinasoft.model.entity.REPLIES;
import com.chinasoft.util.DateTime;

public class IRepliesDaoImpl implements IRepliesDao {

	Connection connection = null;
	
	@Override
	//显示某帖子下所有的一级回复
	public List<REPLIES> selAllReplies(int nid, Pager mypage) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "{call [p_selectRepByPage](?,?,?,?)}";
		List<REPLIES> list = new ArrayList<REPLIES>();
		REPLIES rep = null;
		
		try {
			CallableStatement cs1 = connection.prepareCall(sql);
			cs1.setInt(1, nid);
			cs1.setInt(2, mypage.getPageIndex());
			cs1.setInt(3, mypage.getPageSize());
			cs1.registerOutParameter(4, java.sql.Types.INTEGER);
			
			ResultSet res1 = cs1.executeQuery();
			while(res1.next())
			{
				rep = new REPLIES();
				rep.setR_no(res1.getLong("R_no"));
				rep.setR_floor(res1.getInt("R_floor"));
				rep.setR_statedel(res1.getBoolean("R_statedel"));
				rep.setR_pfloor(res1.getInt("R_pfloor"));
				rep.setR_content(res1.getString("R_content"));
				rep.setN_id(res1.getInt("N_id"));
				rep.setM_id(res1.getInt("M_id"));
				rep.setR_reptime(res1.getString("R_reptime"));
				list.add(rep);
			}
			mypage.setPageTotal(cs1.getInt(4));
			
			res1.close();
			cs1.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	

	@Override
	//显示一级回复下面的二级回复
	public List<REPLIES> selAllChildReplies(REPLIES reply) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "select * from Replies where R_pfloor = ? and N_id = ? and R_statedel = 0";
		List<REPLIES> list = new ArrayList<REPLIES>();
		REPLIES rep = null;
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, reply.getR_floor());
			ps1.setInt(2, reply.getN_id());
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next())
			{
				rep = new REPLIES();
				rep.setM_id(res1.getInt("M_id"));
				rep.setN_id(res1.getInt("N_id"));
				rep.setR_content(res1.getString("R_content"));
				rep.setR_floor(res1.getInt("R_floor"));
				rep.setR_no(res1.getInt("R_no"));
				rep.setR_pfloor(res1.getInt("R_pfloor"));
				rep.setR_reptime(res1.getString("R_reptime"));
				rep.setR_statedel(res1.getBoolean("R_statedel"));
				list.add(rep);
			}
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	@Override
	//发表一级回复
	public boolean pubReply(int count, REPLIES reply) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql1 = "insert into Replies select ?,?,?,?,?,?,?";
		String sql2 = "update Members set M_exp = M_exp + ? where M_id = ?";
		String sql3 = "update Notes set N_repcount = N_repcount + 1 where N_id = ?";
		String sql4 = "update Notes set N_reptime = ? where N_id = ?";
		
		try {
			//设置事务非自动提交
			connection.setAutoCommit(false);
			
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			PreparedStatement ps3 = connection.prepareStatement(sql3);
			PreparedStatement ps4 = connection.prepareStatement(sql4);
			ps1.setInt(1, reply.getR_floor());
			ps1.setBoolean(2, reply.getR_statedel());
			ps1.setInt(3, reply.getR_pfloor());
			ps1.setString(4, reply.getR_content());  
			ps1.setInt(5, reply.getN_id());
			ps1.setInt(6, reply.getM_id());
			ps1.setString(7, DateTime.sqlDateTime());
			ps1.executeUpdate();
			
			ps2.setInt(1, count);
			ps2.setInt(2, reply.getM_id());
			ps2.executeUpdate();
			
			ps3.setInt(1, reply.getN_id());
			ps3.executeUpdate();
			
			ps4.setString(1, DateTime.sqlDateTime());
			ps4.setInt(2, reply.getN_id());
			ps4.executeUpdate();
			
		    connection.commit();
		
		    ps1.close();
		    ps2.close();
		    ps3.close();
		    ps4.close();
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		}finally{
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}


	@Override
	//删除回复
	public boolean delReply(REPLIES reply) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Replies set R_statedel = 1 where R_no = ?";
		String sql1 = "update Notes set N_repcount = N_repcount - 1 where N_id = ?";
		
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement ps1 = connection.prepareStatement(sql);
			PreparedStatement ps2 = connection.prepareStatement(sql1);
			ps1.setLong(1, reply.getR_no());
			ps1.executeUpdate();
			
			ps2.setInt(1, reply.getN_id());
			ps2.executeUpdate();
			
			connection.commit();
			
			ps1.close();
			ps2.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	//根据id查询回复
	public REPLIES selReplyById(int rid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "select * from Replies where R_no = ?";
		REPLIES reply = new REPLIES();
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, rid);
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next())
			{
				reply.setM_id(res1.getInt("M_id"));
				reply.setN_id(res1.getInt("N_id"));
				reply.setR_content(res1.getString("R_content"));
				reply.setR_floor(res1.getInt("R_floor"));
				reply.setR_no(res1.getInt("R_no"));
				reply.setR_pfloor(res1.getInt("R_pfloor"));
				reply.setR_reptime(res1.getString("R_reptime"));
				reply.setR_statedel(res1.getBoolean("R_statedel"));
			}
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reply;
	}


	@Override
	//发表二级回复楼中楼
	public boolean pubChildReply(REPLIES reply) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "insert into Replies select ?,?,?,?,?,?,?";
		String sql2 = "update Notes set N_reptime = ? where N_id = ?";
		String sql3 = "update Notes set N_repcount = N_repcount + 1 where N_id = ?";
		
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement ps1 = connection.prepareStatement(sql);
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			PreparedStatement ps3 = connection.prepareStatement(sql3);
			
			ps1.setInt(1, reply.getR_floor());
			ps1.setBoolean(2, reply.getR_statedel());
			ps1.setInt(3, reply.getR_pfloor());
			ps1.setString(4, reply.getR_content());
			ps1.setInt(5, reply.getN_id());
			ps1.setInt(6, reply.getM_id());
			ps1.setString(7, DateTime.sqlDateTime());
			ps1.executeUpdate();
			
			ps2.setString(1, DateTime.sqlDateTime());
			ps2.setInt(2, reply.getN_id());
			ps2.executeUpdate();
			
			ps3.setInt(1, reply.getN_id());
			ps3.executeUpdate();
			
			connection.commit();
			
		    ps1.close();
		    ps2.close();
		    ps3.close();
		    
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

}

















