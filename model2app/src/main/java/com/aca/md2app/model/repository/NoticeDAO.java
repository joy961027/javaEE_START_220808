package com.aca.md2app.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.aca.md2app.controller.Controller;
import com.aca.md2app.domain.Notice;
import com.aca.md2app.mybatis.MybatisConfigManager;

public class NoticeDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();

	public int insert(Notice notice) {
		int result=0;
		SqlSession session = manager.getSqlSession();
		result =session.insert("Notice.insert", notice);
		session.commit();
		manager.closeSqlSession(session);
		return result;
		
	}
	
	public List<Notice> selectAll(){
		List list =null;
		SqlSession session = manager.getSqlSession();
		list = session.selectList("Notice.selectAll");
		manager.closeSqlSession(session);
		return list;
	}
	
	public Notice select(int notice_id) {
		Notice notice =null;
		SqlSession session = manager.getSqlSession();
		notice = session.selectOne("Notice.selectOne",notice_id);
		manager.closeSqlSession(session);
		return notice;
	}
	
	public int delete(int notice_id) {
		int result=0;
		SqlSession session = manager.getSqlSession();
		result = session.delete("Notice.delete", notice_id);
		session.commit();
		manager.closeSqlSession(session);
		return result;
	}
	
	public int update(Notice notice) {
		int result=0;
		SqlSession session = manager.getSqlSession();
		result = session.update("Notice.update",notice);
		session.commit();
		manager.closeSqlSession(session);
		return result; 
	}
	
}
