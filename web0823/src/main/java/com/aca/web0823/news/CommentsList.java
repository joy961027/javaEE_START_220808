package com.aca.web0823.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0823.domain.Comments;
import com.aca.web0823.news.model.CommentsDAO;
import com.google.gson.Gson;

public class CommentsList extends HttpServlet{
	CommentsDAO commentsDAO = new CommentsDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int news_id = Integer.parseInt(request.getParameter("news_id"));
		
		List<Comments> list = commentsDAO.selectAll(news_id);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
	}
}
