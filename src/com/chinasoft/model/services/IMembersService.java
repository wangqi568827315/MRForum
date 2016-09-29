package com.chinasoft.model.services;

import java.util.List;

import com.chinasoft.model.entity.MEMBERS;
import com.chinasoft.model.entity.USERPASSWORD;

public interface IMembersService {

		//根据id查询
		public abstract MEMBERS selMemById(int mid);
		
		//查询所有的论坛管理
		public abstract List<MEMBERS> selForumManagers();
		
		//签到加经验
		public abstract boolean signinExp(int mid);
		
		//注册
		public abstract boolean registMem(USERPASSWORD up);
		
		//判断昵称是否存在
		public abstract boolean checkNickname(String nickname);
		
		//上传头像修改头像路径
		public abstract boolean uploadIcon(MEMBERS mem);
		
}
