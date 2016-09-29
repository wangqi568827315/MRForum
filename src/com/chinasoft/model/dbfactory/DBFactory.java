package com.chinasoft.model.dbfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBFactory {

	private static final String DRIVERCLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String CONURL = "jdbc:sqlserver://localhost:1433;databaseName=MR";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "wangqi19950229.";
	
	private static Connection connection;
	
	static{
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//私有化构造方法
	protected DBFactory(){
		
	}
	
	//判断连接是否存在
	public static Connection getInstance(){
		
		if(connection == null){
			try {
				connection = DriverManager.getConnection(CONURL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	
	
}








