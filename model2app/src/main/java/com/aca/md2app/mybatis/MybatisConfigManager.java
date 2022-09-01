package com.aca.md2app.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * config.xml을 파싱하여, 쿼리문 수행객체인 sqlsession을 모아놓고 관리해주는
 * sqlsessionfactory 를 생성해야하낟
 * 공식홈페이지에 의하면, sqlsessionfactory는 메모리에 싱글턴으로 관리하자
 */
public class MybatisConfigManager {
	private static MybatisConfigManager instance;
	SqlSessionFactory sqlSessionFactory;
	InputStream inputStream;
	
	
	private MybatisConfigManager() {
		String resource = "com/aca/md2app/mybatis/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeStream(InputStream is) {
		if(is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	public void closeSqlSession(SqlSession sqlSession) {
		sqlSession.close();
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance==null) {
			instance = new MybatisConfigManager();
		}
		return instance;
	}
	
}
