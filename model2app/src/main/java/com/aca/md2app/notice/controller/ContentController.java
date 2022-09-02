package com.aca.md2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.controller.Controller;
import com.aca.md2app.domain.Notice;
import com.aca.md2app.model.repository.NoticeDAO;

public class ContentController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		Notice notice = noticeDAO.select(notice_id);
		request.setAttribute("notice", notice);
	}
	
	@Override
	public String getViewName() {
		return "/notice/result/content";
	}
	
	@Override
	public boolean isForward() {
		return true;
	}

}
