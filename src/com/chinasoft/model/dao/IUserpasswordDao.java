package com.chinasoft.model.dao;

import com.chinasoft.model.entity.USERPASSWORD;

public interface IUserpasswordDao {

	//判断账号密码是否正确
	public abstract int selAccount(String account, String password);
	
	//判断账号是否存在
	public abstract boolean uniqueAccount(String account);
	
	//判断电话号码是否存在
	public abstract boolean uniquePhone(String phone);
	
	//查找安全码,判断安全码是否正确
	public abstract String selSafeCode(String account);
	
	//修改密码
	public abstract boolean updatePwd(USERPASSWORD up);
}
