package com.aca.md2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.controller.Controller;
import com.aca.md2app.domain.Notice;
import com.aca.md2app.model.repository.NoticeDAO;
//3단계 알맞는 모델 객체이 일시키기
//4단계 저장할 것이 있다면 저장 (dml의 경우 클라이언트에게 보여줄 것이 없다. 따라서 4단계 생략)
public class RegistController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//파리미터 받기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		//dto 생성
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		
		
	}

	@Override
	public String getViewName() {
		return "/notice/result/write";
	}
	//jsp로 가져갈 것이 없으므로, 데이터를 유지할 필요도 없다. 따라서 request죽어도 되므로, 응답을 해버리고
	//이때 응답을 하는순간 서버의 request,response,thread가 죽음
	public boolean isForward() {
		return false;
	}
}
