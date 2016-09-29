package com.chinasoft.model.services.impl;

import com.chinasoft.model.dao.IMembersInfoDao;
import com.chinasoft.model.dao.impl.IMembersInfoDaoImpl;
import com.chinasoft.model.entity.MEMBERSINFO;
import com.chinasoft.model.services.IMembersInfoService;

public class IMembersInfoServiceImpl implements IMembersInfoService {

	IMembersInfoDao dao = null;
	
	public IMembersInfoServiceImpl()
	{
		dao = new IMembersInfoDaoImpl();
	}
	
	@Override
	public MEMBERSINFO selMemInfoById(int mid) {
		// TODO Auto-generated method stub
		return dao.selMemInfoById(mid);
	}

	@Override
	//根据id修改会员信息
	public boolean updateMemInfoById(MEMBERSINFO meminfo, String nickname) {
		// TODO Auto-generated method stub
		return dao.updateMemInfoById(meminfo,nickname);
	}

}
