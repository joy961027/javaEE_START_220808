<%@page import="java.util.ArrayList"%>
<%@page import="com.aca.web0810.model.BoardDAO"%>
<%@page import="com.aca.web0811.domain.Board"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//직접 쿼리문을 수행하지 말고, 이미 만들어놓은 dao를 이용하자
	BoardDAO boardDAO = new BoardDAO();
	List<Board>boardlist = boardDAO.selectAll();

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
<script>
addEventListener("load",function(){
		document.querySelector("button").addEventListener("click",function(){
		location.href="/notice/write.jsp";
	});	
});
</script>
</head>
<body>
	<table>
		<tr>
			<th width="5%">No</th>
			<th width="65%">Title</th>
			<th width="15%">Writer</th>
			<th width="10%">Regdate</th>
			<th width="5%">Hit</th>
		</tr>
		
		<%for(int i=0; i<boardlist.size(); i++)  {%>
		<tr>
			<td>No</td>
			<td><a href="/notice/content.jsp?board_id=<%=boardlist.get(i).getBoard_id()%>"><%=boardlist.get(i).getTitle()%></a></td>
			<td><%=boardlist.get(i).getWriter()%></td>
			<td><%=boardlist.get(i).getRegdate().substring(0,10) %></td>
			<td><%=boardlist.get(i).getHit()%></td>
		</tr>
		<%}%>
		<tr>
			<td colspan="5">
				<button>글작성</button>
			</td>

		</tr>
	</table>

</body>
</html>
