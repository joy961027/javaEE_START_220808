package com.aca.web0809.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 브라우저에서 전송된 파라미터를 넘겨 받아 오라클에 insert하자 
//jsp에서해도 된다. 하지만jsp의 주 용도는 디자인이 포함된 경우에 많이 쓰이므로, 이번 실습에서는 서블릿으로 가자!!
public class RegistServlet extends HttpServlet{
	//게시판 폼 양식 중 글내용이 textarea이므로 상당한 양의 데이터가 전송되어질것이므로 post방식으로 전송된다
	//따라서 doxxx형 메소드 중에서 doPost로 처리하자
	
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String password="1234";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이메서드는 servlet의 service 메서드의 의해 호출되어지는 메서드이다.
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		//클라이언트가 받게될 응답벙보 문자열은, response객체가 가진 적재시켜야 된다.
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("title is " + title + "<br>");
		out.print("wirter is " + writer + "<br>");
		out.print("내용 is " + content + "<br>");
		
		//1.드라이버 로드 2.접속 3.쿼리수행 4.db해재   
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("드라이버로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con=null;
		try {
			con = DriverManager.getConnection(url,user,password);
			if(con==null) {
				out.print("접속실패<br>");
			}else {
				out.print("접속성공<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement pstmt =null;
		String sql = "insert into board(board_id,title,writer,content) values(seq_board.nextval,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int result = pstmt.executeUpdate();
			if(result<1) {
				out.print("등록실패");
			}else {
				out.print("등록성공");
			     //톰캣이 응답정보를 클라이언트에게 보낼때, 해당 클라이언트의 브라우저로 하여금
			    //재접속할 주소를 기입하는것임 지금 이동하는것이 아니다.
				response.sendRedirect("/board/list.jsp");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(pstmt!=null) {
			 try {	pstmt.close();} catch (SQLException e) {}
			}
		if(con!=null) {
		 try {	con.close();} catch (SQLException e) {}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
