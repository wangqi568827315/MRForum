package com.chinasoft.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasoft.model.dao.IDailyexpDao;
import com.chinasoft.model.dbfactory.DBFactory;
import com.chinasoft.model.entity.DAILYEXP;
import com.chinasoft.util.DateTime;

public class IDailyexpDaoImpl implements IDailyexpDao {

	private static Connection connection = null;
	
	@Override
	//根据id查询dailyexp内的内容
	public DAILYEXP selDailyExpById(int mid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "select * from Dailyexp where M_id = ?";
		DAILYEXP dailyexp = new DAILYEXP();
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, mid);
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next())
			{
				dailyexp.setM_id(res1.getInt("M_id"));
				dailyexp.setD_lsdate(res1.getString("D_lsdate"));
				dailyexp.setD_dsexp(res1.getShort("D_dsexp"));
				dailyexp.setD_ledate(res1.getString("D_ledate"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dailyexp;
	}

	@Override
	//已经签到
	public boolean updateSigned(int mid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Dailyexp set D_lsdate = ? where M_id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, DateTime.getDate("-"));
			ps1.setInt(2, mid);
			
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
	//操作加每日获得经验
	public boolean updateExp(int count, int mid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Dailyexp set D_dsexp = D_dsexp + ?,D_ledate=? where M_id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, count);
			ps1.setString(2, DateTime.getDate("-"));
			ps1.setInt(3, mid);
			
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
	//清空每日获得经验
	public boolean clearDailyExp(int mid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update Dailyexp set D_dsexp = 0 where M_id = ?";
		
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
}













