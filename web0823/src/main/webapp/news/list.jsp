<%@page import="com.aca.web0823.domain.News"%>
<%@page import="java.util.List"%>
<%@page import="com.aca.web0823.news.model.NewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	NewsDAO newsDAO = new NewsDAO();
%>


<%
List<News> newsList = newsDAO.selectAll();


int totalRecord =newsList.size();//모든 레코드 수
int pageSize =10; //한페이지당 보여질 레코드 수
int totalPage=(int)Math.ceil((float)totalRecord/pageSize);
int blockSize =10; //한 블럭당 보여질 페이지 수
int currentPage =1;
if(request.getParameter("currentPage")!=null){
	currentPage=Integer.parseInt(request.getParameter("currentPage")); //현재 페이지
}
int firstPage=currentPage - (currentPage-1)%blockSize;
int lastPage =firstPage +blockSize-1;
int curPos = (currentPage-1)*pageSize; //페이지당 시작 index
int num = totalRecord-(curPos); //페이지당 시작번호1page 26 2page 16, 3page 6
		
%>
<%="totalRecord 는 " + totalRecord +"<br>" %>
<%="pageSize 는 " + pageSize +"<br>" %>
<%="totalPage 는 " + totalPage +"<br>" %>
<%="blockSize 는 " + blockSize +"<br>" %>
<%="currentPage 는 " + currentPage +"<br>" %>

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
	location.href="/news/regist.jsp";
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
		<%for(int i=0; i<pageSize; i++) { %>
		<%if(num<1)break; %>
		<%
			News news= newsList.get(curPos++);
		%>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/news/content.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle() %></a></td>
			<td><%=news.getWriter() %></td>
			<td><%=news.getRegdate().substring(0,10) %></td>
			<td><%=news.getHit()%></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" style="text-align:center;">
			<%if(firstPage-1>0) { %>
			<a href="/news/list.jsp?currentPage=<%=firstPage-1%>">◀</a>
			<%}else{ %>
			<a href="javascript:alert('이전페이지가 없습니다.');">◀</a>
			<%}%>
			<%for(int i=firstPage; i<=lastPage;i++){ %>
				<%if(i>totalPage)break;%>
				<a href="/news/list.jsp?currentPage=<%=i %>"  <%if(i== currentPage){%>class="page-style" <%} %>>[<%=i %>] <!--  end of a --></a>
			<%} %>
			<%if(lastPage+1<=totalPage) { %>
				<a href="/news/list.jsp?currentPage=<%=lastPage+1%>">▶</a>
			<%}else{ %>
				<a href="javascript:alert('마지막페이지 입니다.');">▶</a>
			<%}%>
			
			
			</td>
		</tr>
		<tr>
			<td colspan="5"><button onClick="regist()">뉴스 작성</button></td>
		</tr>
	</table>

</body>
</html>
