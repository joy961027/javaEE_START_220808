<%@page import="com.aca.web0823.reboard.domain.ReBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.aca.web0823.reboard.model.ReBoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	ReBoardDAO reBoardDAO = new ReBoardDAO();
%>
<%//페이징 처리
	List<ReBoard> list=reBoardDAO.selectAll();
	int totalRecord=list.size();//총레코드수
	int pageSize=10; //한페이지당 보여질 레코드 수
	int totalPage =(int)Math.ceil(((float)totalRecord/pageSize));
	int blockSize = 10; //블럭당 보여질 페이지 수
	int currentPage = 1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage = currentPage-((currentPage-1)%blockSize);
	int lastPage = firstPage+blockSize-1;
	int curPos = (currentPage-1)*pageSize;  //페이지당 list의 시작 INDEX
	int num = totalRecord -curPos;
	
	
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
#reply{
 transform: rotate(180deg);
 width:15px;
}

</style>
<%@ include file ="/inc/header.jsp" %>
<script>
$(document).ready(function(){
	$("button").click(function(){
		location.href="/reboard/regist.jsp";
		//$(location).attr("href","/reboard/regist.jsp");
	});
}); //addeventListener(function(){})와 동일
</script>
</head>
<body>


		<table>
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<%for(int i = 0;i<pageSize; i++) {%>
			<%if(num<1)break; %>
			<%ReBoard reBoard = list.get(curPos++); %>
			<tr>
				<td><%=num-- %></td>
				<td>
					<%if(reBoard.getDepth()>0){ %>
						<img id="reply" src ="/res/images/reply.png" style="margin-left:<%=reBoard.getDepth()*20%>px">
					<% }%>
					<a href="/reboard/content.jsp?reboard_id=<%=reBoard.getReboard_id()%>"><%=reBoard.getTitle() %></a>	
				</td>
				<td><%=reBoard.getWriter() %></td>
				<td><%=reBoard.getRegdate() %></td>
				<td><%=reBoard.getHit()%></td>
			</tr>
			<%} %>
			<tr>
				<td colspan="5" style="text-align:center;">
					<%for (int i=1; i<=totalPage; i++){  %>
						<a href="/reboard/list.jsp?currentPage=<%=i%>">[<%= i%>]</a>
					<%} %>
				</td>
			</tr>
			
			<tr>
				<td colspan="5"><button>글 등록</button></td>
			</tr>
			
		</table>
	<%@ include file="/inc/footer.jsp" %>
</body>
</html>
