package com.aca.md2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.controller.Controller;
import com.aca.md2app.domain.Notice;
import com.aca.md2app.model.repository.NoticeDAO;

public class EditController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNotice_id(notice_id);
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.update(notice);
		
		request.setAttribute("notice", notice);
	}
	
	@Override
	public String getViewName() {
		return "/notice/result/edit";
	}
	
	@Override
	public boolean isForward() {
		return true;
	}

}
