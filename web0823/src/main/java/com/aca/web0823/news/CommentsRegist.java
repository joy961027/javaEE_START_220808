package com.aca.web0823.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0823.domain.Comments;
import com.aca.web0823.domain.News;
//댓글 등록요청을 처히나는 서블릿
import com.aca.web0823.news.model.CommentsDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class CommentsRegist extends HttpServlet {
	CommentsDAO commentsDAO = new  CommentsDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String detail = request.getParameter("detail");
		String author = request.getParameter("author");
		String news_id = request.getParameter("news_id");
		//DTO에담기
		Comments comments = new Comments();
		comments.setDetail(detail);
		comments.setAuthor(author);
		
		News news = new News();
		news.setNews_id(Integer.parseInt(news_id));
		comments.setNews(news); //comments DTO안에 NEWS DTO넣기 자식이 부모를 HAS A로 보유했기 때문에
		
		commentsDAO.insert(comments);
		//클라이언트가 비동기방식으로 요청을 한다는 것은, 전체 html디자인을 바꾸겠다는 것이 아니라 현재 디자인 페이지는 유지하되 
		//오직 데이터만 주고받기 위함이다 따라서 
		PrintWriter out = response.getWriter();
		//클라이언트에게 등록과 동시에, 지금까지 누적된 댓글 목록을 보내주자!!
		//아래와 같이 json표기를 문자열로 처리할경우(=개고생)..
		//해결책 외부 라이브러리를 활용해보자 gson
		//목록 가져오기
		
		
		Gson gson = new Gson();
		String json = gson.toJson(comments);
		out.print(json); 
	}

}
