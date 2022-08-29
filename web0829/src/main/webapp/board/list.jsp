<%@page import="java.util.List"%>
<%@page import="com.academy.web0829.domain.Board"%>
<%@page import="com.academy.web0829.board.repository.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! BoardDAO boardDAO  = new BoardDAO(); %>
<%
	List <Board> boardList = boardDAO.selectAll();

	int totalRecord = boardList.size();
	int pageSize = 10;
	int blockSize =10;
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize);
	int currentPage = 1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage = currentPage -((currentPage-1)%blockSize);
	int lastPage = firstPage +blockSize-1;
	int curPos = (currentPage-1)*pageSize; //페이지당 시작 index 1page 0 2page 10  3page 20
	int num =  totalRecord-(curPos); //페이지당 시작번호1page 26 2page 16, 3page 6
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
		<%for(int i=0; i<pageSize; i++) {%>
		<%if(num<1)break;%>
		<%	Board board  = boardList.get(curPos++); %>
		<tr>
			<td><%=num--%></td>
			<td><%=board.getTitle() %></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getRegdate() %></td>
			<td><%=board.getHit() %></td>
		</tr>
		<%} %>
		
		<tr>
			<td colspan="5" style="text-align:center;"></td>
		</tr>
		<tr>
			<td colspan="5"><button onClick="regist()">글 작성</button></td>
		</tr>
	</table>

</body>
</html>
