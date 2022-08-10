package com.aca.web0809.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelServlet extends HttpServlet{

	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String password="1234";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String board_id = request.getParameter("board_id");
		
		response.setContentType("/text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection con=null;
		PreparedStatement pstmt =null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			String sql = "delete board where board_id =" + board_id;
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if(result<1) {
				out.print("삭제실패");
			}else {
				response.sendRedirect("/board/list.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(pstmt!=null) {
			 try {	pstmt.close();} catch (SQLException e) {}
			}
		if(con!=null) {
		 try {	con.close();} catch (SQLException e) {}
		}
		
		
		
	
	
	
	}

}
