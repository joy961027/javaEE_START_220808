package com.aca.md2app.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.blood.controller.BloodController;
import com.aca.md2app.blood.model.BloodManager;
import com.aca.md2app.movie.controller.MovieController;
import com.aca.md2app.movie.controller.MovieController미사용;
import com.aca.md2app.movie.model.MovieManager;
/*
 * 모든 요청마다 1:1대응하는 컨트롤러를 전면에 내세우면 오히려 유지 보수성이 떨어진다.
 */
public class DispatcherServlet extends HttpServlet {
	Properties props;
	
	//jsp 내장객체
	/*
	 * request -> HttpServletRequest
	 * response -> HttpServletResponse
	 * session -> HttpSession
	 * application -> ServletContext : 어플리케이션의 전역적 정보를 가진 객체
	 											ex)getRealPath() 이 어플리케이션 내의 자원의 풀경로
	 												 */											
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		props= (Properties) config.getServletContext().getAttribute("props");
	}
	
	//모든 요청을 이 서블릿이 일단 받아야 한다.
	/*
	 * 1단계 요청을 받는다
	 * 2단계 요청을 분석한다(영화, 혈액형, 글쓰기인지 분석해야함)
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resposne) throws ServletException, IOException {
		doRequest(request, resposne);
	}
	//클라이언트의 요청이 어떤 방식이던 상관없이 요청을 받기 위해 공통메서드에서 컨트롤러의 요청처리를 진행한다.
	protected void doRequest(HttpServletRequest request, HttpServletResponse resposne) throws ServletException, IOException {
		System.out.println("요청을 받았습니다.");
		
		String uri = request.getRequestURI();
		request.setCharacterEncoding("utf-8");
		Controller controller = null;
		//2단계
		//영화에 대한 요청인지
		String controllerClassName=props.getProperty(uri);
		try {
			Class controllerClass = Class.forName(controllerClassName); //아직 인스턴스가 올라온거 아니다
			controller = (Controller) controllerClass.newInstance();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		controller.execute(request, resposne);
		String viewName = controller.getViewName();
		String viewPage = props.getProperty(viewName);
		//무조건 포워딩은 문제가 발생할 수 있다.
		if(controller.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, resposne);
		}else {
			resposne.sendRedirect(viewPage);
		}
		
	}
	@Override
	public void destroy() {
	}

}
