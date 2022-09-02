<%@page import="com.aca.md2app.domain.Notice"%>
<%@page import="com.aca.md2app.model.util.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! Pager pager = new Pager(); %>
<% List<Notice> noticeList = (List)request.getAttribute("noticeList"); 
	pager.init(noticeList, request);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Notice</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
$(function(){
	$("button").click(function(){
		$(location).attr({"href":"/notice/write.jsp"});
	});
});
</script>
</head>
<body>

<div class="container">
  <h2>Dark Striped Table</h2>
  <p>Combine .table-dark and .table-striped to create a dark, striped table:</p>            
  <table class="table table-dark table-striped">
    <thead>
      <tr>
        <th width="5%">No</th>
        <th width="65%">제목</th>
        <th width="10%">작성자</th>
        <th width="10%">작성일</th>
        <th width="10%">조회수</th>
      </tr>
    </thead>
    <tbody>
    <%int curPos = pager.getCurPos(); %>
    <%int num =pager.getNum(); %>
    <%for(int i=0; i<pager.getPageSize(); i++ ){ %>
    <%if(num<1)break; %>
    <%Notice notice = noticeList.get(i); %>
      <tr>
        <td><%=num-- %></td>
        <td><a href="/notice/content.do?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle() %></a></td>
        <td><%=notice.getWriter() %></td>
        <td><%=notice.getRegdate().substring(0,10) %></td>
        <td><%=notice.getHit() %></td>
      </tr>
      <%} %>
      <tr>
      	<td colspan="5"><button>글 작성</button></td>
      </tr>
      
    </tbody>
  </table>
</div>

</body>
</html>
