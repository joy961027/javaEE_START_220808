package com.academy.web0829.board.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.academy.web0829.domain.Board;

public class RegistServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//쿼리문이 들어있는 boardMapper.xml을 이용해본다!
		String resource = "com/academy/web0829/mybatis/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//mybatis를 이용하면 쿼리문을 수행하기 위한 객체인 sqlSession객체를 이용하여 sql문을 호출할 수 있다.
		SqlSession session =  factory.openSession();
		int result = session.insert("babo.insert", board);
		session.commit();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.print("등록 성공");
			out.print("<script>");
			out.print("location.href='/board/list.jsp';");
			out.print("</script>");
		}else {
			out.print("등록 실패");
		}			
		
		
	}
}
