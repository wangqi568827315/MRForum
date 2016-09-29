package com.chinasoft.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasoft.model.dao.IMembersInfoDao;
import com.chinasoft.model.dbfactory.DBFactory;
import com.chinasoft.model.entity.MEMBERSINFO;

public class IMembersInfoDaoImpl implements IMembersInfoDao {

	Connection connection = null;
	
	@Override
	//根据ID查询会员信息
	public MEMBERSINFO selMemInfoById(int mid) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "select * from MembersInfo where M_id = ?";
		MEMBERSINFO meminfo = new MEMBERSINFO();
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, mid);
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next())
			{
				meminfo.setM_id(res1.getInt("M_id"));
				meminfo.setMI_name(res1.getString("MI_name"));
				meminfo.setMI_sex(res1.getString("MI_sex"));
				meminfo.setMI_age(res1.getShort("MI_age"));
				meminfo.setMI_birthday(res1.getString("MI_birthday"));
				meminfo.setMI_code(res1.getString("MI_code"));
				meminfo.setMI_location_sheng(res1.getString("MI_location_sheng"));
				meminfo.setMI_location_shi(res1.getString("MI_location_shi"));
				meminfo.setMI_location_qu(res1.getString("MI_location_qu"));
				meminfo.setMI_QMD(res1.getString("MI_QMD"));
				meminfo.setMI_explain(res1.getString("MI_explain"));
				meminfo.setMI_stateccn(res1.getBoolean("MI_stateccn"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return meminfo;
	}

	@Override
	//根据id修改会员信息
	public boolean updateMemInfoById(MEMBERSINFO meminfo, String nickname) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "  update MembersInfo set [M_id]=?,[MI_name]=?,[MI_sex]=?,[MI_age]=?,[MI_birthday]=?,"
			+"[MI_code]=?,[MI_location_sheng]=?,[MI_location_shi]=?,[MI_location_qu]=?,"
			+"[MI_QMD]=?,[MI_explain]=?,[MI_stateccn]=? where M_id = ?";
		String sql1 = "update Members set M_nickname = ? where M_id = ?";
		
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement ps = connection.prepareStatement(sql);
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps.setInt(1, meminfo.getM_id());
			ps.setString(2, meminfo.getMI_name());
			ps.setString(3, meminfo.getMI_sex());
			ps.setShort(4, meminfo.getMI_age());
			ps.setString(5, meminfo.getMI_birthday());
			ps.setString(6, meminfo.getMI_code());
			ps.setString(7, meminfo.getMI_location_sheng());
			ps.setString(8, meminfo.getMI_location_shi());
			ps.setString(9, meminfo.getMI_location_qu());
			ps.setString(10, meminfo.getMI_QMD());
			ps.setString(11, meminfo.getMI_explain());
			ps.setBoolean(12, meminfo.getMI_stateccn());
			ps.setInt(13, meminfo.getM_id());
			ps.executeUpdate();
			
			ps1.setString(1, nickname);
			ps1.setInt(2, meminfo.getM_id());
			ps1.executeUpdate();
			
			connection.commit();
			
			ps.close();
			ps1.close();
			
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

}














