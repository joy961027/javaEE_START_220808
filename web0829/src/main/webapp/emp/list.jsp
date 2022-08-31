<%@page import="com.academy.web0829.domain.Dept"%>
<%@page import="com.academy.web0829.domain.Emp"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.web0829.emp.repository.EmpDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! EmpDAO empDAO = new EmpDAO(); %>
<%
	List<Emp> empList = empDAO.selectAll();
	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100%", border="1px">
		<tr>
			<th>No</th>
			<th>empno</th>
			<th>ename</th>
			<th>job</th>
			<th>mgr</th>
			<th>hiredate</th>
			<th>sal</th>
			<th>comm</th>
			<th>deptno</th>
			<th>dname</th>
			<th>loc</th>
		</tr>
		<%for(int i=0; i<empList.size(); i++) {%>
		<%Emp emp = empList.get(i); %>
		<%Dept dept = emp.getDept(); %>
		<tr>
			<td><%=i+1 %></td>
			<td><%=emp.getEmpno()%></td>
			<td><%=emp.getEname() %></td>
			<td><%=emp.getJob() %></td>
			<td><%=emp.getMgr() %></td>
			<td><%=emp.getHiredate() %></td>
			<td><%=emp.getSal() %></td>
			<td><%=emp.getComm() %></td>
			<td><%=dept.getDeptno() %></td>
			<td><%=dept.getDname() %></td>
			<td><%=dept.getLoc() %></td>
		</tr>
		<%} %>
	</table>

</body>
</html>