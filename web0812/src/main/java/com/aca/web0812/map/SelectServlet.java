package com.aca.web0812.map;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.aca.web0812.domain.HotSpot;
import com.aca.web0812.model.HotSpotDAO;

public class SelectServlet extends HttpServlet {
	HotSpotDAO hotSpotDAO;

	@Override
	public void init() throws ServletException {
		hotSpotDAO = new HotSpotDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HotSpot dto = hotSpotDAO.select(3);
		out.print("{");
		out.print("\"hotspot\":" + dto.getHotspot_id() + ",");
		out.print("\"lati\":" + dto.getLati() + ",");
		out.print("\"longi\":" + dto.getLongi() + ",");
		out.print("\"icon\":\"" + dto.getIcon() + "\",");
		out.print("\"content\":\"" + dto.getContent() + "\"");
		out.print("}");
	}
}
