<%@page import="com.aca.web0811.domain.Board"%>
<%@page import="com.aca.web0810.model.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	BoardDAO boardDAO = new BoardDAO();
	
%>
<%
	String board_id = request.getParameter("board_id");
	Board board= boardDAO.select(Integer.parseInt(board_id));
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
</style>
<script>
function regist(){
	form1.action="/notice/regist";
	form1.method="post";
	form1.submit();
}

function edit(){
	if(confirm("수정 하시겠습니까")){
		form1.action="/notice/edit.jsp";
		form1.method="post";
		form1.submit();
	}
}

function del(){
	if(confirm("삭제 하시겠습니까")){
		location.href="/notice/delete.jsp?board_id=<%=board.getBoard_id()%>"
	}
	
}
</script>
</head>
<body>

	<h3>content</h3>

	<div class="container">
		<form name="form1">
			<input type="hidden" name ="board_id" value="<%=board.getBoard_id()%>">
			<input type="text" id="fname" name="title" value="<%=board.getTitle()%>"> 
			<input type="text" id="lname" name="writer" value="<%=board.getWriter()%>">
			<textarea name="content" style="height: 200px"><%=board.getContent()%></textarea>
			<input type="button" value="수정	" onClick="edit()">
			<input type="button" value="삭제" onClick="del()">
			<input type="button" value="목록" onClick="location.href='notice/list.jsp'">
		</form>
	</div>

</body>
</html>
