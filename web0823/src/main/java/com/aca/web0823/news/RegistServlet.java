package com.aca.web0823.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.aca.web0823.domain.News;
import com.aca.web0823.news.model.NewsDAO;
/*
 * 뉴스기사 등록 요청을 처리하는 서블릿
 */
public class RegistServlet extends HttpServlet {
	NewsDAO newsDAO = new NewsDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//파라미터에 대한 인코딩
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out  = response.getWriter();
		String title 		= request.getParameter("title");
		String writer 	= request.getParameter("writer");
		String content = request.getParameter("content");
		//DTO담기
		News news = new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		int result = newsDAO.insert(news);
		out.print("<script>");
		if(result==0) {
			out.print("alert('등록실패');");
			out.print("histroy.back();");
		}else {
			out.print("alert('등록성공');");
			out.print("location.href='/news/list.jsp';");
		}
		out.print("</script>");
		
		
	}//end of post


}
