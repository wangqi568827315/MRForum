package com.chinasoft.model.services;

import java.util.List;

import com.chinasoft.model.entity.Pager;
import com.chinasoft.model.entity.REPLIES;

public interface IRepliesService {

	//显示某帖子下所有的一级回复
	public abstract List<REPLIES> selAllReplies(int nid, Pager mypage);
	
	//显示一级回复下面的二级回复
	public abstract List<REPLIES> selAllChildReplies(REPLIES reply);
	
	//发表一级回复
	public abstract boolean pubReply(int count, REPLIES reply);
	
	//删除回复
	public abstract boolean delReply(REPLIES reply);
	
	//根据id查询回复
	public abstract REPLIES selReplyById(int rid);
	
	//发表二级回复楼中楼
	public abstract boolean pubChildReply(REPLIES reply);
	
	
}
