package web0808.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
   고양이만 서블릿을 작성할 수 있는 것이 아니라, 개발자도 작성 할 수 있다.
   extends 두가지 관점으로 공부해야한다. 
   extends 란 is a 관계
   1)상속(부모의 자원을 마치 내것처럼 접근할 수 있는것)
   2 ) 자료형 (부모와 같은 자료형이 되는것)
*/
public class MyServlet extends HttpServlet{
									/* MyServlet은 서버에서 실행 될 수 있는 서블릿이다.*/
	//클라이언트가 아묵서도 명시하지 않고 요청을 할경우 default Get방식으로 들어오게 되는데
	//get방식의 용청을 받는 서블릿의 메서드가 아래와 같은 doGET()메서드이다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();//응답정보 객체로 부터 출력스트림을 얻는다!!
		out.print("안녕,나의 최초의 서블릿에 의한 응답정보야");
	}
	
}
