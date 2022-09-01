package com.aca.md2app.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aca.md2app.mybatis.MybatisConfigManager;

//서버가 가동될때 무언가 하고 싶을때
public class MyListener implements ServletContextListener{
	FileInputStream fis;
	Properties props;
	
	//웹컨테이너가 가동될때 호출되는 메서드
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context= sce.getServletContext();
		String path = context.getInitParameter("contextConfigLocation");
		
		try {
			System.out.println(context.getRealPath(path));
			fis = new FileInputStream(context.getRealPath(path));
			props = new Properties();
			props.load(fis);
			context.setAttribute("props", props);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//웹컨테이너가 중단될때 호출되는 메서드
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
