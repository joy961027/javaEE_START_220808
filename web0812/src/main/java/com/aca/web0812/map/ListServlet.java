package com.aca.web0812.map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//데이터 목록을 구하는 서블릿

import com.aca.web0812.domain.HotSpot;
import com.aca.web0812.model.HotSpotDAO;
public class ListServlet extends HttpServlet {
	HotSpotDAO hotSpotDAO;
	
	public void init() throws ServletException {
		hotSpotDAO = new HotSpotDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<HotSpot> list = hotSpotDAO.selectAll();
		out.print("[");
		for(int i=0; i<list.size(); i++) {
			HotSpot dto=list.get(i);
			out.print("{");
			out.print("\"hotspot\":"+dto.getHotspot_id()+",");
			out.print("\"lati\":"+dto.getLati()+",");
			out.print("\"longi\":"+dto.getLongi()+",");
			out.print("\"icon\":\""+dto.getIcon()+"\",");
			out.print("\"content\":\""+dto.getContent()+"\"");
			
			if(i<list.size()-1) {
				out.print("},");
			}else {
				out.print("}");
			}
	
		}		
		out.print("]");
	}
}
