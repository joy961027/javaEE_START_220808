package com.aca.md2app.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

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
}
