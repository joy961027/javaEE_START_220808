<%@page import="com.aca.web0823.reboard.model.ReBoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReBoardDAO reBoardDAO = new ReBoardDAO();%>
<jsp:useBean id="reBoard" class="com.aca.web0823.reboard.domain.ReBoard"/>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:setProperty property="*" name="reBoard"/>
<%@ include file="/inc/result.jsp" %>
<%=reBoard.getStep() %>
<%
	int result = reBoardDAO.reply(reBoard)	;
	if(result==0){
		out.print(getMsgBack("등록 실패"));
	}else{
		out.print(getMsgUrl("등록성공", "/reboard/list.jsp"));
	}
%>

