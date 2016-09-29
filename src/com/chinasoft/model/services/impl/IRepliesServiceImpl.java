package com.chinasoft.model.services.impl;

import java.util.List;

import com.chinasoft.model.dao.IRepliesDao;
import com.chinasoft.model.dao.impl.IRepliesDaoImpl;
import com.chinasoft.model.entity.Pager;
import com.chinasoft.model.entity.REPLIES;
import com.chinasoft.model.services.IRepliesService;

public class IRepliesServiceImpl implements IRepliesService {

	IRepliesDao dao = null;
	
	public IRepliesServiceImpl()
	{
		dao = new IRepliesDaoImpl();
	}
	
	@Override
	//显示某帖子下所有的一级回复
	public List<REPLIES> selAllReplies(int nid, Pager mypage) {
		// TODO Auto-generated method stub
		return dao.selAllReplies(nid,mypage);
	}

	@Override
	//显示一级回复下面的二级回复
	public List<REPLIES> selAllChildReplies(REPLIES reply) {
		// TODO Auto-generated method stub
		return dao.selAllChildReplies(reply);
	}

	@Override
	//发表一级回复
	public boolean pubReply(int count, REPLIES reply) {
		// TODO Auto-generated method stub
		return dao.pubReply(count,reply);
	}

	@Override
	//删除回复
	public boolean delReply(REPLIES reply) {
		// TODO Auto-generated method stub
		return dao.delReply(reply);
	}

	@Override
	//根据id查询回复
	public REPLIES selReplyById(int rid) {
		// TODO Auto-generated method stub
		return dao.selReplyById(rid);
	}

	@Override
	//发表二级回复楼中楼
	public boolean pubChildReply(REPLIES reply) {
		// TODO Auto-generated method stub
		return dao.pubChildReply(reply);
	}

}













