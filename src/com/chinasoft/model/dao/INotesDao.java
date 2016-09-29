package com.chinasoft.model.dao;

import java.util.List;

import com.chinasoft.model.entity.NOTES;
import com.chinasoft.model.entity.Pager;

public interface INotesDao {

	//查询所有帖子
	public abstract List<NOTES> selAllNotesByPage(Pager mypage);
	
	//查询精品贴
	public abstract List<NOTES> selSuperNotes(Pager mypage);
	
	//根据id查询帖子
	public abstract NOTES selNoteById(int noteid);
	
	//发帖
	public abstract boolean pubNotes(int count, NOTES note);
	
	//删帖(软删除)
	public abstract boolean delMyNote(int noteid);
	
	//分页显示某个人自己的帖子
	public abstract List<NOTES> selMyNotes(Pager mypage, int mid);
	
	//帖子加精和取消加精
	public abstract boolean editSupNote(int noteid, int statesup);
	
	//帖子置顶和取消置顶
	public abstract boolean editTopNote(int noteid, int statesup);
	
	//查询置顶帖子数量
	public abstract int SelTopNoteCount();
	
	//查出所有置顶帖子
	public abstract List<NOTES> selAllTopNotes();
}
