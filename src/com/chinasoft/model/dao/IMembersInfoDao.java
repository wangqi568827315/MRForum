package com.chinasoft.model.dao;

import com.chinasoft.model.entity.MEMBERSINFO;

public interface IMembersInfoDao {

	//根据ID查询会员信息
	public abstract MEMBERSINFO selMemInfoById(int mid);
	
	//根据id修改会员信息
	public abstract boolean updateMemInfoById(MEMBERSINFO meminfo, String nickname);
}
