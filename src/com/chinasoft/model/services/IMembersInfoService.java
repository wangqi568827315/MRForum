package com.chinasoft.model.services;

import com.chinasoft.model.entity.MEMBERSINFO;

public interface IMembersInfoService {

	//根据ID查询会员信息
	public abstract MEMBERSINFO selMemInfoById(int mid);
	
	//根据id修改会员信息
	public abstract boolean updateMemInfoById(MEMBERSINFO meminfo, String nickname);
}
