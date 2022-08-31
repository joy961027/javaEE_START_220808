package com.academy.web0829.emp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.academy.web0829.mybatis.ConfigManager;

public class EmpDAO {
	ConfigManager configManager = ConfigManager.getInstance();
	
	public List selectAll() {
		SqlSession  session= configManager.getSqlSession();
		List list = null;
		list = session.selectList("Emp.selectAll");
		configManager.closeSqlSession(session);
		return list;
	}
		

}
