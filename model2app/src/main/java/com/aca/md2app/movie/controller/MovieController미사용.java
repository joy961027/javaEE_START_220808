package com.aca.md2app.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//영화에 대한 조언을 처리하는 컨트롤러
import javax.servlet.http.HttpSession;

import com.aca.md2app.movie.model.MovieManager;
public class MovieController미사용 extends HttpServlet{
	MovieManager manager = new MovieManager();
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 *1)요청을 받는다
		 *2)요청을 분석한다
		 *3) 알맞는 로직 객체이 일시킨다
		 *4) 결과페이지로 전달할 거싱 잇다면 저장
		 *5) 결과페이지 보여주기
		 */
		//1단계
		req.setCharacterEncoding("utf-8");
		String movie = req.getParameter("movie");
		
		//3단계
		String msg = manager.getAdvice(movie);
		//4단계
		/*
		HttpSession session = req.getSession();
		session.setAttribute("data", msg);
		세션을 이용하면, 브라우저를 닫지 않는한 session이 유지되어 데이터를 보관할수 있지만 
		만일, 세션 방법 이외의 방법이 있따면, 이용 안할 이유가 없다.
		현실의 114서비스, 이메일 포워딩 처럼, javaEE에서도 서버측의 특정 자원으로 현재 요청을 포워딩항는 기술을
		지원하며 이때 사용되는 객체가 바로 requestdispatcher 이다
		*/
		req.setAttribute("data", msg); //request는 요청이 끊기면 사라지지만, 긍정적으로 보면
												//요청이 끊기기 전까지는 살아 있을 수 있다.
		RequestDispatcher dis =  req.getRequestDispatcher("/movie/result.jsp");
		dis.forward(req, resp);
		
		//5단계
		//resp.sendRedirect("/movie/result.jsp");
		
	
			
	}

}
