package com.chinasoft.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinasoft.model.dao.INotesDao;
import com.chinasoft.model.dbfactory.DBFactory;
import com.chinasoft.model.entity.NOTES;
import com.chinasoft.model.entity.Pager;
import com.chinasoft.util.DateTime;

public class INotesDaoImpl implements INotesDao {

	private static Connection connection = null;
	
	
	@Override
	//分页查询所有帖子
	public List<NOTES> selAllNotesByPage(Pager mypage) {
		
		String sql = "{call [p_selectByPage](?,?,?)}";
		connection = DBFactory.getInstance();
		List<NOTES> list = new ArrayList<NOTES>();
		NOTES note = null;
		int mid = 0;
		
		try {
			CallableStatement cs1 = connection.prepareCall(sql);
			cs1.setInt(1, mypage.getPageIndex());
			cs1.setInt(2, mypage.getPageSize());
			cs1.registerOutParameter(3, java.sql.Types.INTEGER);
			
			ResultSet res1 = cs1.executeQuery();
			
			while(res1.next())
			{
				note = new NOTES();
				note.setM_id(res1.getInt("M_id"));
				note.setN_article(res1.getString("N_article"));
				note.setN_id(res1.getInt("N_id"));
				note.setN_image(res1.getString("N_image"));
				note.setN_repcount(res1.getInt("N_repcount"));
				note.setN_reptime(res1.getString("N_reptime"));
				note.setN_statedel(res1.getBoolean("N_statedel"));
				note.setN_statesup(res1.getBoolean("N_statesup"));
				note.setN_statetop(res1.getBoolean("N_statetop"));
				note.setN_title(res1.getString("N_title"));
				note.setN_pubtime(res1.getString("N_pubtime"));
				list.add(note);
			}
			mypage.setPageTotal(cs1.getInt(3));
			
			res1.close();
			cs1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	
	@Override
	//查询精品贴
	public List<NOTES> selSuperNotes(Pager mypage) {
		// TODO Auto-generated method stub
		
		String sql = "{call [p_selSupByPage](?,?,?)}";
		connection = DBFactory.getInstance();
		List<NOTES> list = new ArrayList<NOTES>();
		NOTES note = null;
		
		try {
			CallableStatement cs1 = connection.prepareCall(sql);
			cs1.setInt(1, mypage.getPageIndex());
			cs1.setInt(2, mypage.getPageSize());
			cs1.registerOutParameter(3, java.sql.Types.INTEGER);
			
			ResultSet res1 = cs1.executeQuery();
			
			while(res1.next())
			{
				note = new NOTES();
				note.setM_id(res1.getInt("M_id"));
				note.setN_article(res1.getString("N_article"));
				note.setN_id(res1.getInt("N_id"));
				note.setN_image(res1.getString("N_image"));
				note.setN_repcount(res1.getInt("N_repcount"));
				note.setN_reptime(res1.getString("N_reptime"));
				note.setN_statedel(res1.getBoolean("N_statedel"));
				note.setN_statesup(res1.getBoolean("N_statesup"));
				note.setN_statetop(res1.getBoolean("N_statetop"));
				note.setN_title(res1.getString("N_title"));
				note.setN_pubtime(res1.getString("N_pubtime"));
				list.add(note);
			}
			mypage.setPageTotal(cs1.getInt(3));
			
			res1.close();
			cs1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	@Override
	//根据id查询帖子
	public NOTES selNoteById(int noteid) {
		
		connection = DBFactory.getInstance();
		
		String sql = "select * from Notes where N_id = ? ";
		String sql1 = "select M_nickname from Members where M_id = ?";
		int mid = 0;
		NOTES note = new NOTES();
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, noteid);
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next())
			{
				note.setM_id(res1.getInt("M_id"));
				note.setN_article(res1.getString("N_article"));
				note.setN_id(res1.getInt("N_id"));
				note.setN_image(res1.getString("N_image"));
				note.setN_repcount(res1.getInt("N_repcount"));
				note.setN_reptime(res1.getString("N_reptime"));
				note.setN_statedel(res1.getBoolean("N_statedel"));
				note.setN_statesup(res1.getBoolean("N_statesup"));
				note.setN_statetop(res1.getBoolean("N_statetop"));
				note.setN_title(res1.getString("N_title"));
				note.setN_pubtime(res1.getString("N_pubtime"));
			}
			mid = note.getM_id();
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return note;
	}


	@Override
	//发帖
	public boolean pubNotes(int count, NOTES note) {
		// TODO Auto-generated method stub
		
		connection = DBFactory.getInstance();
		String sql = "insert into Notes select ?,0,?,?,?,0,0,0,?,?";
		String sql1 = "update Members set M_exp = M_exp + ? where M_id = ?";
		
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement ps1 = connection.prepareStatement(sql);
			PreparedStatement ps2 = connection.prepareStatement(sql1);
			ps1.setInt(1, note.getM_id());
			ps1.setString(2, note.getN_title().replace(" ", ""));
			ps1.setString(3, note.getN_article());  
			ps1.setString(4, note.getN_image());
			ps1.setString(5, DateTime.sqlDateTime());
			ps1.setString(6, DateTime.sqlDateTime());
			ps1.executeUpdate();
			
			ps2.setInt(1, count);
			ps2.setInt(2, note.getM_id());
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
		}finally
		{
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
	//删帖(软删除)
	public boolean delMyNote(int noteid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Notes set N_statedel = 1 where N_id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, noteid);
			
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
	//分页显示某个人自己的帖子
	public List<NOTES> selMyNotes(Pager mypage, int mid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "{call [p_selectMyNote](?,?,?,?)}";
		List<NOTES> list = new ArrayList<NOTES>();
		NOTES note = null;
		
		try {
			CallableStatement cs1 = connection.prepareCall(sql);
			cs1.setInt(1, mid);
			cs1.setInt(2, mypage.getPageIndex());
			cs1.setInt(3, mypage.getPageSize());
			cs1.registerOutParameter(4, java.sql.Types.INTEGER);
			
			ResultSet res1 = cs1.executeQuery();
			
			while(res1.next())
			{
				note = new NOTES();
				note.setM_id(res1.getInt("M_id"));
				note.setN_article(res1.getString("N_article"));
				note.setN_id(res1.getInt("N_id"));
				note.setN_image(res1.getString("N_image"));
				note.setN_repcount(res1.getInt("N_repcount"));
				note.setN_reptime(res1.getString("N_reptime"));
				note.setN_statedel(res1.getBoolean("N_statedel"));
				note.setN_statesup(res1.getBoolean("N_statesup"));
				note.setN_statetop(res1.getBoolean("N_statetop"));
				note.setN_title(res1.getString("N_title"));
				note.setN_pubtime(res1.getString("N_pubtime"));
				list.add(note);
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
	//帖子加精和取消加精
	public boolean editSupNote(int noteid, int statesup) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Notes set N_statesup = ? where N_id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, statesup);
			ps1.setInt(2, noteid);
			
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
	//帖子置顶和取消置顶
	public boolean editTopNote(int noteid, int statesup) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Notes set N_statetop = ? where N_id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, statesup);
			ps1.setInt(2, noteid);
			
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
	//查询置顶帖子数量
	public int SelTopNoteCount() {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		int count = 0 ; 
		String sql = "select count(*) from Notes where N_statetop = 1";
		
		try {
			Statement s1 = connection.createStatement();
			
			ResultSet res1 = s1.executeQuery(sql);
			
			while(res1.next())
			{
				count = res1.getInt(1);
			}
			
			res1.close();
			s1.close();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}


	@Override
	//查出所有置顶帖子
	public List<NOTES> selAllTopNotes() {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "select * from Notes where N_statetop = 1";
		NOTES note = null;
		List<NOTES> list = new ArrayList<NOTES>();
		
		try {
			Statement s1 = connection.createStatement(); 
			
			ResultSet res1 = s1.executeQuery(sql);
			
			while(res1.next())
			{
				note = new NOTES();
				note.setM_id(res1.getInt("M_id"));
				note.setN_article(res1.getString("N_article"));
				note.setN_id(res1.getInt("N_id"));
				note.setN_image(res1.getString("N_image"));
				note.setN_repcount(res1.getInt("N_repcount"));
				note.setN_reptime(res1.getString("N_reptime"));
				note.setN_statedel(res1.getBoolean("N_statedel"));
				note.setN_statesup(res1.getBoolean("N_statesup"));
				note.setN_statetop(res1.getBoolean("N_statetop"));
				note.setN_title(res1.getString("N_title"));
				note.setN_pubtime(res1.getString("N_pubtime"));
				list.add(note);
			}
			
			res1.close();
			s1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}







}












