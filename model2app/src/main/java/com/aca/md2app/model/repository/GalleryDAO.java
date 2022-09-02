package com.aca.md2app.model.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.aca.md2app.domain.Gallery;
import com.aca.md2app.mybatis.MybatisConfigManager;

public class GalleryDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();

	public int insert(Gallery gallery) {
		int result =0;
		SqlSession session = manager.getSqlSession();
		result = session.insert("Gallery.insert",gallery);
		session.commit();
		manager.closeSqlSession(session);
		return result;
	}
}
