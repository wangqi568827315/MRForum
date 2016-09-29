package com.chinasoft.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasoft.model.dao.IUserpasswordDao;
import com.chinasoft.model.dbfactory.DBFactory;
import com.chinasoft.model.entity.USERPASSWORD;

public class IUserpasswordDaoImpl implements IUserpasswordDao {

	private static Connection connection = null;
	
	public IUserpasswordDaoImpl()
	{
		connection = DBFactory.getInstance();
	}
	
	@Override
	//判断账号密码是否正确
	public int selAccount(String account, String password) {
		
		String sql = "select * from UserPassword where U_password=? and (U_account=? or U_phone=?)";
		int i = 0;
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, password);
			ps1.setString(2, account);
			ps1.setString(3, account);
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next()){
				i = res1.getInt("M_id");
			}
			
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		return i;
	}

	
	@Override
	//判断账号是否存在
	public boolean uniqueAccount(String account) {
		// TODO Auto-generated method stub
		String sql = "select * from UserPassword where U_account = ?";
		boolean i = false;
		connection = DBFactory.getInstance();
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, account);
			
			ResultSet res1 = ps1.executeQuery();
			
			if(res1.next())
			{
				i = true;
			}
			
			res1.close();
			ps1.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	//判断电话号码是否存在
	public boolean uniquePhone(String phone) {
		// TODO Auto-generated method stub
		String sql = "select * from UserPassword where U_phone = ?";
		boolean i = false;
		connection = DBFactory.getInstance();
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, phone);
			
			ResultSet res1 = ps1.executeQuery();
			
			if(res1.next())
			{
				i = true;
			}
		
			res1.close();
			ps1.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	//查找安全码,判断安全码是否正确
	public String selSafeCode(String account) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "select U_safecode from UserPassword where U_account = ?";
		String str = null;
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, account);
			
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next())
			{
				str = res1.getString("U_safecode");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}

	
	
	@Override
	//修改密码
	public boolean updatePwd(USERPASSWORD up) {
		// TODO Auto-generated method stub
		connection = DBFactory.getInstance();
		String sql = "update UserPassword set U_password = ? where U_account = ? or U_phone=?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setString(1, up.getU_password());
			ps1.setString(2, up.getU_account());
			ps1.setString(3, up.getU_phone());
			
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










