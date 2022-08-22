package com.aca.web0812.map;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//클라이언트의 등록 요청을 처리하는 서블릿

import com.aca.web0812.domain.HotSpot;
import com.aca.web0812.model.HotSpotDAO;
public class RegistServlet extends HttpServlet{
	/*
	 *클라이언트가 비동기로 용청을하면 화면전체를 바꾸려는 것이 아니라
	 *데이터만을 교환하기 위함이므로, 기존 동기방식처럼 html페이지 전체를 보내지 마록,
	 *데이터만을 응답정보로 보내느게 올바르다.. 
	 */
	HotSpotDAO hotSpotDAO;

	@Override
	public void init() throws ServletException {
		hotSpotDAO = new HotSpotDAO();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		String lati = request.getParameter("lati");
		String longi = request.getParameter("longi");
		String icon = request.getParameter("icon");
		String content = request.getParameter("content");
		HotSpot hotSpot = new HotSpot();
		hotSpot.setLati(Float.parseFloat(lati));
		hotSpot.setLongi(Float.parseFloat(longi));
		hotSpot.setIcon(icon);
		hotSpot.setContent(content);
		int result = hotSpotDAO.insert(hotSpot);
		
		
		System.out.println("lati is "+lati);
		System.out.println("longi is "+longi);
		System.out.println("icon is "+icon);
		System.out.println("content is "+content);
		
		//out.print()안에 인수로 넣은 스트링은 json 오브젝트는 아니고, 그냥 json표기법을 지킨 string 일 분이다
		//따라서 이 문자열을 전송받은 클라이언트는 반드시 파싱하고 사용해야 한다.
		String resData=null;
		if(result==0) {
			resData ="{";
			resData+="\"code\":0, ";
			resData+="\"msg\":\"데이터등록실패\"";
			resData+="}";
		}else {
			//한건이 들어간 경우이므로 그레코드르 반호나해주자!!
			HotSpot dto = hotSpotDAO.select(result);
			resData ="{";
			resData+="\"code\":1, ";
			resData+="\"list\" :[ ";
			resData+="{";
			resData+="\"hotspot\":"+dto.getHotspot_id()+",";
			resData+="\"lati\":"+dto.getLati()+",";
			resData+="\"longi\":"+dto.getLongi()+",";
			resData+="\"icon\":\""+dto.getIcon()+"\",";
			resData+="\"content\":\""+dto.getContent()+"\"";
			resData+="}";
			resData+="]";
			resData+="}";
		}
		out.print(resData);
	
	
	
	
	
	}
}
