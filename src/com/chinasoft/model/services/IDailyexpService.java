package com.chinasoft.model.services;

import java.util.List;

import com.chinasoft.model.entity.DAILYEXP;

public interface IDailyexpService {

	//根据id查询dailyexp内的内容
	public abstract DAILYEXP selDailyExpById(int mid);
		
	//已经签到
	public abstract boolean updateSigned(int mid);
	
	//操作加每日获得经验
	public abstract boolean updateExp(int count, int mid);
	
	//清空每日获得经验
	public abstract boolean clearDailyExp(int mid);
}
