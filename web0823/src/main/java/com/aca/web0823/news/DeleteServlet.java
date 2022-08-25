package com.aca.web0823.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0823.news.model.NewsDAO;
/*
 * 뉴스기사 1건 삭제 욫처을 처리하는 서블릿
 * 주의)게시물의 댓글이 존재하는 경우, 삭제가 아닌 '원본삭제되었다'공지로 수정
 * 댓글이 존재하지 않으면 삭제
 */
public class DeleteServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int new_id = Integer.parseInt(request.getParameter("news_id"));
		
		int result = newsDAO.delete(new_id);
		out.print("<script>");
		if(result==0) {
			out.print("alert('삭제실패');");
			out.print("history.back();");
		}else {			
			out.print("alert('삭제성공');");
			out.print("location.href='/news/list.jsp';");
		}
		out.print("</script>");
		
	}

}
