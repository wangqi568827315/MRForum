package com.chinasoft.model.services.impl;

import java.util.List;

import com.chinasoft.model.dao.IDailyexpDao;
import com.chinasoft.model.dao.impl.IDailyexpDaoImpl;
import com.chinasoft.model.entity.DAILYEXP;
import com.chinasoft.model.services.IDailyexpService;

public class IDailyexpServiceImpl implements IDailyexpService {

	IDailyexpDao dao = null;
	
	public IDailyexpServiceImpl()
	{
		dao = new IDailyexpDaoImpl();
	}
	
	@Override
	//根据id查询dailyexp内的内容
	public DAILYEXP selDailyExpById(int mid) {
		// TODO Auto-generated method stub
		return dao.selDailyExpById(mid);
	}

	@Override
	//已经签到
	public boolean updateSigned(int mid) {
		// TODO Auto-generated method stub
		return dao.updateSigned(mid);
	}

	@Override
	//操作加每日获得经验
	public boolean updateExp(int count, int mid) {
		// TODO Auto-generated method stub
		return dao.updateExp(count, mid);
	}

	@Override
	//清空每日获得经验
	public boolean clearDailyExp(int mid) {
		// TODO Auto-generated method stub
		return dao.clearDailyExp(mid);
	}

}
