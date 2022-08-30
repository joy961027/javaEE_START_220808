package com.academy.web0829.board.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.web0829.board.repository.BoardDAO;
import com.academy.web0829.domain.Board;

public class EditServlet extends HttpServlet {
	BoardDAO boardDAO = new BoardDAO();
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setBoard_id(board_id);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		PrintWriter out = response.getWriter();
		int result = boardDAO.update(board);
		
		out.print("<script>");
		if(result==0) {
			out.print("alert('수정실패');");
			out.print("history.back();");
		}else {
			
			out.print("alert('수정성공');");
			out.print("location.href='/board/content.jsp?board_id="+board_id+"';");
		}
		out.print("</script>");
		
	}
	

}
