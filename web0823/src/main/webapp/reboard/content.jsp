<%@page import="com.aca.web0823.reboard.domain.ReBoard"%>
<%@page import="com.aca.web0823.reboard.model.ReBoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	ReBoardDAO reBoardDAO = new ReBoardDAO();
%>

<%
	int reboard_id = Integer.parseInt(request.getParameter("reboard_id"));
	ReBoard reboard = reBoardDAO.select(reboard_id);

%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

#comments-list {
	border: 1px solid red;
	overflow: hidden;
}

#comments-list * {
	float: left;
}

.title-style {
	width: 80%;
}

.writer-style {
	width: 10%;
}

.regdate-style {
	width: 10%;
}
</style>
<%@ include file ="/inc/header.jsp" %>
<script>
function replyForm(){
	$("#reply-container").css({
		display:"block"
	});
}

function reply(){
	$("form[name='form2']").attr({
		method:"post",
		action:"/reboard/reply.jsp"
	});
	$("form[name='form2']").submit();
}
</script>
</head>
<body>

	<h3>상세보기</h3>

	<div class="container">
		<form name="form1">
			<input type="text" name="title" value="<%=reboard.getTitle()%>"> 
			<input type="text"name="writer" value="<%=reboard.getWriter()%>">
			<textarea name="content" style="height: 200px"><%=reboard.getContent()%></textarea>

			<input type="button" value="답변등록" onClick="replyForm()"> 
			<input type="button"value="목록"> 
			<input type="button" value="수정"> 
			<input type="button" value="삭제">
		</form>
	</div>
	
	
	
	<div class="container" style="display:none" id="reply-container">
		<form name="form2">
			<input type="text" name="title" placeholder="답변제목"> 
			<input type="text" name="writer" placeholder="답변자명">
			<input type="hidden" name="team" value="<%= reboard.getTeam()%>" >
			<input type="hidden" name="step" value="<%= reboard.getStep()%>">
			<input type="hidden" name="depth" value="<%= reboard.getDepth()%>">
			
			<textarea name="content" style="height: 100px; background:yellow;" placeholder="내용입력"></textarea>

			<input type="button" value="답변등록" onClick="reply()"> 
		</form>
	</div>
	<%@ include file="/inc/footer.jsp" %>
</body>
</html>
