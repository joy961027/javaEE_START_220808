<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.aca.web0810.model.BoardDAO"%>
<%! BoardDAO boardDAO = new BoardDAO();%>
<!--jsp에서는 태그이지만 오직 서버에서만 실행 될수있는  빈즈 태그를 지원한다-->
<%request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="board" class="com.aca.web0811.domain.Board" />
<jsp:setProperty property="*" name="board"/>

<%
	int result =boardDAO.update(board);
	out.print("<script>");
	if(result==0){
		out.print("alert('수정 실패');");
		out.print("history.back();")	;	
	}else{
		out.print("alert('수정 성공');");
		out.print("location.href='/notice/content.jsp?board_id="+board.getBoard_id()+"';");
	}	
	out.print("</script>");	

%>