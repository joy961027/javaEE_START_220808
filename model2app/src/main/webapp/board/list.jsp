<%@page import="com.academy.web0829.util.Pager"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.web0829.domain.Board"%>
<%@page import="com.academy.web0829.board.repository.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! BoardDAO boardDAO  = new BoardDAO(); 
	  Pager pager = new Pager();
%>
<%
	List <Board> boardList = boardDAO.selectAll();
	pager.init(boardList, request);
	//jsp의 내장객체중 application 내장객체의 생명력을 테스트해보자
	//이름 그대로 application(웹어플리케이션)은 톰캣서버를  가동할때 생성 되어
	// 프로그램이 끝날때 까지 즉 톰캣 서버를 종료할때 까지 생명력을 유지 할 수 있음
	//어플리케이션 스코프
	out.print(application.getAttribute("nick"));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
button {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
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

.page-style{
	font-size:20px;
	font-weight:bold;
	color:red;
}
a{text-decoration:none;}
</style>
<script>
function regist(){
	location.href="/board/regist.jsp";
}

</script>
</head>
<body>
	<table>
		<tr>
			<th width="5%">No</th>
			<th width="70%">기사제목</th>
			<th width="10%">작성자</th>
			<th width="10%">작성일</th>
			<th width="5%">조회수</th>
		</tr>
		<!-- 하나의 페이지에 너무 많은 데이터가 있을경우, 원하는 크기로 분리하여 보여주는 기법을 페이징 처리라 한다
		페이징 처리는 결국 데이터에 대한 산수 계산이므로 개발자마다 본인 스스로 로직을 개발해야 함.  -->
		<% int curPos = pager.getCurPos();
			  int num = pager.getNum();
		%>
		<%for(int i=1; i<=pager.getPageSize(); i++){ %>
		<%if(num<1)break; %>
		<% Board board = boardList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/board/content.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle()%></a></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getRegdate() %></td>
			<td><%=board.getHit() %></td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan="5" style="text-align:center;">
			<a href="/board/list.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
			<%for (int i= pager.getFirstPage(); i<=pager.getLastPage(); i++) {%>
			<% if(i>pager.getTotalPage())break; %>
			<a href="/board/list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
			<%} %>
			<a href="/board/list.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			</td>
		</tr>
		<tr>
			<td colspan="5"><button onClick="regist()">글 작성</button></td>
		</tr>
	</table>

</body>
</html>
