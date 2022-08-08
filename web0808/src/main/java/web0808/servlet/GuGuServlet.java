package web0808.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//구구단을 출력하는 서블릿
public class GuGuServlet extends HttpServlet {
	
	//post등을 명시하지 않는 한 기본적으로 브라우저의 요청을 default가 get방식이고 get방식으롤 들어온 요청은 
	//doget()메서드 가 처리한다.
	protected void service(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();// 문자기반의 출력 스트림
		//jsp가 아닌 서블릿을 직접 작성하여 사용할 경우 단점...
		//디자인 코드에 취약.. 너무 효욜성이 뛰어진다.. 모든 html을 문자열 취급하여 쌍따옴표 안에 넣어야 함
		//만일 아래와 같이 단순하 html 이 아니라 , 엄청난 수의 태그를 갖는 html 페이지일경우 개발이 힘듬 
		//개발시 디자인 코드가 들어가지 않는 컨트롤러등에 사용하기에 적당한다.
		
		
		out.print("<table width='200px' border='1px'>");
		for(int i=1; i<=9; i++) {
			out.print("<tr>");
			out.print("<td>");
			out.print("5*"+i+"="+5*i);
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
	
}
