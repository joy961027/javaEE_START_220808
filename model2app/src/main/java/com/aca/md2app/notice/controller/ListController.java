package com.aca.md2app.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.controller.Controller;
import com.aca.md2app.domain.Notice;
import com.aca.md2app.model.repository.NoticeDAO;

public class ListController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<Notice> noticeList =noticeDAO.selectAll();
		//4단계 결과페이지로 가져갈 것이 있을때는 결과를 저장해 놓는다.(dispatcherServlet에게 전달하기 위해)
		request.setAttribute("noticeList", noticeList);
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/notice/result/list";
	}
	
	//jsp인 뷰로 가져갈것이 있으므로, request는 죽으면 안된다. 따라서 응답을 하지말고, 서버의 jsp자원으로
	//포워딩을 시도하여 request를 살려두자
	public boolean isForward() {
		return true; 
	}

}
