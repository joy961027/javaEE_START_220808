package com.aca.web0810.notice;

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

public class RegistServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 전송해 온 파라미터 받기
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String title 		= request.getParameter("title");
		String writer 	= request.getParameter("writer");
		String content = request.getParameter("content");
		
	
		//클래스의 인스턴스에 파라미터들을 담아두자.
		//사실 접속이란 행위는 내부적으로 상당히 많은 절차와 인증을 거쳐야하는 어려운 작업이다.
		//Connection Pooling 기법이란? 접속자가 없더라도 tomcat서버 메모리에 미리 다주의 connection 객체를 확보해 놓고 
		//메모리 pool에 모아놓는 connection 관리 기법
		//사용모습 1: 클라이언트가 접속 -> pool에 모여 있는 connection 중에 하나를 추출하여 요청을 처리할 connection 으로 처리함
		//이때 쿼리문 수행모두 종료되었을때도 connection을 close()하지 않고 다시 pool로 돌려보낸다.
		//장점 다수의 클라이언트를 처리할때 부하가 걸리지않음
		//코드 구현 방법 	1: 직접 개발 
		//				   	2: 나보다 실력이 뛰어난 다른 개발자거 사용
		//					3: 공식적인 라이브러리를 이용(apache.org에서 connection pool을 제공해준다 DBCP)
		//좋은소식 )TOMCAT 서버는 이미 DBCP를 이용한 커넥션 풀을 지원해준다.
		//선행학습으로서, JNDI(JAVA NAMING DIRECTORY INTERFACE)
		
		//이 시점부터는 절대로 자바클래스안에, 추후 변경 가능성이 큰 자원의 정보는 기재하지 않는다.
		//검색을 담당하는 검색 객체 생성 (jndi)
		Connection con =null;
		PreparedStatement pstmt=null;
		PrintWriter out = response.getWriter();
		try {
			InitialContext ctx = new InitialContext();
			//DataSource 는 connection pull을 구현한 객체
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle"); //매개변수로 검색어를 입력
			//커넥션 풀로부터 connection 하나를 빌려써버줘
			con = ds.getConnection();
			String sql ="insert into board(board_id,title,writer,content) values(seq_board.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			int result = pstmt.executeUpdate();
			
			out.print("<script>");
			if(result<1) {
				out.print("alert('등록실패');");
				out.print("history.back();");
			}else {
				out.print("alert('등록성공');");
				out.print("location.href='/notice/list.jsp';");
			}
			out.print("</script>");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
