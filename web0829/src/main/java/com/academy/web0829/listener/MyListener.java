package com.academy.web0829.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
	String name ="superman";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서버 가동 감지했어");
		//application 내장 객체에 name을 담아 놓으면, 프로그램 끝날때까지 name을 사용할 수 있다.
		ServletContext application = sce.getServletContext();
		application.setAttribute("nick", name);
		
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서버 종료 감지했어");
	}
}
