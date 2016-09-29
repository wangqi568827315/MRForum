package com.chinasoft.model.services.impl;

import java.util.List;

import com.chinasoft.model.dao.IMembersDao;
import com.chinasoft.model.dao.impl.IMembersDaoImpl;
import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.entity.USERPASSWORD;
import com.chinasoft.model.services.IMembersService;

public class IMembersServiceImpl implements IMembersService {

	IMembersDao dao = null;
	
	public IMembersServiceImpl()
	{
		dao = new IMembersDaoImpl();
	}
	
	@Override
	//根据id查询
	public MEMBERS selMemById(int mid) {
		// TODO Auto-generated method stub
		return dao.selMemById(mid);
	}

	@Override
	//查询所有的论坛管理
	public List<MEMBERS> selForumManagers() {
		// TODO Auto-generated method stub
		return dao.selForumManagers();
	}

	@Override
	//签到加经验
	public boolean signinExp(int mid) {
		// TODO Auto-generated method stub
		return dao.signinExp(mid);
	}

	@Override
	//注册
	public boolean registMem(USERPASSWORD up) {
		// TODO Auto-generated method stub
		return dao.registMem(up);
	}

	@Override
	//判断昵称是否存在
	public boolean checkNickname(String nickname) {
		// TODO Auto-generated method stub
		return dao.checkNickname(nickname);
	}

	@Override
	//上传头像修改头像路径
	public boolean uploadIcon(MEMBERS mem) {
		// TODO Auto-generated method stub
		return dao.uploadIcon(mem);
	}


}
