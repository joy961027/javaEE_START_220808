package com.aca.md2app.controller;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//어떠한 종류의 컨트롤러이건 상관없이 모든 컨트롤러의 최상위객체를 정의한다
//MoiveController, BloodController 건 모두 같은 자료형화 시키자
//대신 어플리케이션의 모든 컨트롤러가 반드시 구현해야할 메서들 강제시키자!

public interface Controller {
	//업무를 처리하는 메서드 execute
	public void execute(HttpServletRequest request, HttpServletResponse response);
	//뷰페이지를 반환하는 메서드
	public String getViewName();
	//포워딩해야 할지 말지를 결정해야하는 메서드
	public boolean isForward();
}
