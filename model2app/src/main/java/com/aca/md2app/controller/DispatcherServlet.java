package com.aca.md2app.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		//2단계
		//영화에 대한 요청인지
		Controller controller = null;
		if(uri.equals("/movie.do")) {
			//3단계 :알맞은 로직객체에 일시키기
			controller = new MovieController();
		}else if(uri.equals("/blood.do")) {//혈액형에 대한 요청인지
			//3단계 :알맞은 로직객체에 일시키기
			controller = new BloodController();
		}
		controller.execute(request, resposne);
		RequestDispatcher dis = request.getRequestDispatcher(controller.getViewPage());
		dis.forward(request, resposne);
		
	}

}
