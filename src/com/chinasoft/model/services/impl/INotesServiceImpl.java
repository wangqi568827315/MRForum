package com.chinasoft.model.services.impl;

import java.util.List;

import com.chinasoft.model.dao.INotesDao;
import com.chinasoft.model.dao.impl.INotesDaoImpl;
import com.chinasoft.model.entity.NOTES;
import com.chinasoft.model.entity.Pager;
import com.chinasoft.model.services.INotesService;

public class INotesServiceImpl implements INotesService {

	INotesDao dao = null;
	
	public INotesServiceImpl()
	{
		dao = new INotesDaoImpl();
	}
	
	@Override
	public List<NOTES> selAllNotesByPage(Pager mypage) {
		// TODO Auto-generated method stub
		return dao.selAllNotesByPage(mypage);
	}

	
	@Override
	//查询精品贴
	public List<NOTES> selSuperNotes(Pager mypage) {
		// TODO Auto-generated method stub
		return dao.selSuperNotes(mypage);
	}

	@Override
	//根据id查询帖子
	public NOTES selNoteById(int noteid) {
		// TODO Auto-generated method stub
		return dao.selNoteById(noteid);
	}

	@Override
	//发帖
	public boolean pubNotes(int count, NOTES note) {
		// TODO Auto-generated method stub
		return dao.pubNotes(count, note);
	}

	@Override
	//删帖(软删除)
	public boolean delMyNote(int noteid) {
		// TODO Auto-generated method stub
		return dao.delMyNote(noteid);
	}

	@Override
	//分页显示某个人自己的帖子
	public List<NOTES> selMyNotes(Pager mypage, int mid) {
		// TODO Auto-generated method stub
		return dao.selMyNotes(mypage, mid);
	}

	@Override
	//帖子加精和取消加精
	public boolean editSupNote(int noteid, int statesup) {
		// TODO Auto-generated method stub
		return dao.editSupNote(noteid, statesup);
	}

	@Override
	//帖子置顶和取消置顶
	public boolean editTopNote(int noteid, int statesup) {
		// TODO Auto-generated method stub
		return dao.editTopNote(noteid, statesup);
	}

	@Override
	//查询置顶帖子数量
	public int SelTopNoteCount() {
		// TODO Auto-generated method stub
		return dao.SelTopNoteCount();
	}

	@Override
	//查出所有置顶帖子
	public List<NOTES> selAllTopNotes() {
		// TODO Auto-generated method stub
		return dao.selAllTopNotes();
	}


	
	

}
