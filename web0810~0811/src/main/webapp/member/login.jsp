<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%!
	String url ="jdbc:oracle:thin:@localhost:1521:XE";
	String user ="java";
	String db_pass ="1234";

%>
<%

	request.setCharacterEncoding("utf-8");
	String user_id = request.getParameter("user_id");
	String pass = request.getParameter("pass");

	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	Connection con =null; 
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	con = DriverManager.getConnection(url,user,db_pass);
	String sql ="select * from member where user_id=? and pass=?";
	pstmt = con.prepareStatement(sql);
	pstmt.setString(1, user_id);
	pstmt.setString(2,pass);
	rs=pstmt.executeQuery();
	
	String sid = session.getId();
	out.print("당신이 할당받은 세션 아이디 "+sid+"<br>");
	
	if(rs.next()){ //커서를 내릴수 있다면 즉 회원이 있다면 
	 /*
		클라이언트가 전송한 아이디, 패스워드를 db에서 조회하여 만일 가입된 회원이라면
		tomcat이 추후 다시 접속해도 나를 기억해준다.
		jsp의 내장객체중 session이라는 객체가 바로 세션을 구현해준다.
		최초의 접속자가 들어오면 세션 객체를 생성한후 고유 id를 할당하고, 다시 이id를 응답정보에
		심어놓아(쿠키) 다음번 클라이언트방문시 만일 이아이디가 존재한다면 아는체하고, 없다면 새로 세션을 만들고 아이디를 발급
	*/
		//세션인스턴스에는 개발자가 넣고 싶은 데이터를 넣을수 있는데, map구조로 되어 있음 
		//java collection framework : 자료구조 java.util..
		//map구조는 key-value의 쌍으로 데이터를 처리하는 방법을 말한다
		
		session.setAttribute("user_id",rs.getString("user_id"));
		session.setAttribute("pass",rs.getString("pass"));
		session.setAttribute("regdate",rs.getString("regdate"));
		out.print("당신의 정보를 알마 맞춰볼게요 <br>");
		out.print("당신의 아이디는 "+ session.getAttribute("user_id")+"<br>");
		out.print("당신의 비번은 "+ session.getAttribute("pass")+"<br>");
		out.print("당신의 가입일은 "+session.getAttribute("regdate")+"<br>");
	}	else{
		out.print("<script>");	
		out.print("alert\"회원정보가 올바르지 않습니다\")");	
		out.print("history.back()");
		out.print("<script>");	
	}
	
	if(rs!=null)rs.close();
	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
	

%>