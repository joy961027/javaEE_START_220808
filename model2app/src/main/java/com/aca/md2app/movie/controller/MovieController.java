package com.aca.md2app.movie.controller;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.controller.Controller;
import com.aca.md2app.movie.model.MovieManager;

/*
 * 영화에 대한 전문적인 지식을 가진 전담 컨트롤러
 * 요청을 직접 받지 않으므로, 서블릿일 필요가 없음
 * */
public class MovieController implements Controller{
	MovieManager manager = new MovieManager();
	
	//요청을 처리
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		//3단계
		String msg = manager.getAdvice(request.getParameter("movie"));
		//4단계 뷰페이지로 가져갈 것이 있을경우 겨로가 저장해 놓기
		request.setAttribute("data", msg);
	}
	//형님컨트롤러가 어떤 뷰페이지를 보여줘야 할지를 여기서 결정해보다
	public String getViewName() {
		return "/movie/result";
	}
	
	@Override
	public boolean isForward() {
		return true;
	}

}
