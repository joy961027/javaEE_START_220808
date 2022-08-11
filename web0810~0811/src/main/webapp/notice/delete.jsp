<%@page import="com.aca.web0810.model.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	BoardDAO boardDAO = new BoardDAO();
%>
<%
	request.setCharacterEncoding("utf-8");
	String board_id = request.getParameter("board_id");
	out.print("board_id is "+ board_id);
	int result = boardDAO.delete(Integer.parseInt(board_id));
	out.print("<script>");
	if(result==0){
		out.print("alert('삭제에 실패했습니다.');");
		out.print("history.back();"); //브라우저 뒤로 
	}else{
		out.print("alert('삭제에 성공했습니다.');");
		out.print("location.href='/notice/list.jsp';");
	}
	
	out.print("</script>");
%>