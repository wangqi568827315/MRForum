package com.chinasoft.model.services.impl;

import com.chinasoft.model.dao.IUserpasswordDao;
import com.chinasoft.model.dao.impl.IUserpasswordDaoImpl;
import com.chinasoft.model.entity.USERPASSWORD;
import com.chinasoft.model.services.IUserpasswordService;

public class IUserpasswordServiceImpl implements IUserpasswordService {

	private IUserpasswordDao dao = null;
	
	public IUserpasswordServiceImpl(){
		dao = new IUserpasswordDaoImpl();
	}
	
	@Override
	//判断账号密码是否正确
	public int selAccount(String account, String password) {
		// TODO Auto-generated method stub
		return dao.selAccount(account, password);
	}

	@Override
	//判断账号是否存在
	public boolean uniqueAccount(String account) {
		// TODO Auto-generated method stub
		return dao.uniqueAccount(account);
	}

	@Override
	//判断电话号码是否存在
	public boolean uniquePhone(String phone) {
		// TODO Auto-generated method stub
		return dao.uniquePhone(phone);
	}

	@Override
	//查找安全码,判断安全码是否正确
	public String selSafeCode(String account) {
		// TODO Auto-generated method stub
		return dao.selSafeCode(account);
	}

	@Override
	//修改密码
	public boolean updatePwd(USERPASSWORD up) {
		// TODO Auto-generated method stub
		return dao.updatePwd(up);
	}
	
	

}
