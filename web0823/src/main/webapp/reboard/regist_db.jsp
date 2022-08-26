<%@page import="com.aca.web0823.reboard.domain.ReBoard"%>
<%@page import="com.aca.web0823.reboard.model.ReBoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/inc/result.jsp" %>

<%! 
	ReBoardDAO reBoardDAO = new ReBoardDAO(); 
%>
<% 
	//글쓰기 요청을 처리하는 jsp
	request.setCharacterEncoding("utf-8");

	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");

	//Dto에 채우기
	ReBoard reBoard = new ReBoard();
	reBoard.setTitle(title);
	reBoard.setWriter(writer);
	reBoard.setContent(content);
	
	int result = reBoardDAO.insert(reBoard);
	if(result ==0){
		out.print(getMsgBack("등록 실패"));
	}else{
		out.print(getMsgUrl("등록 성공", "/reboard/list.jsp"));
	}
	
	

%>