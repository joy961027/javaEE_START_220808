package com.aca.md2app.blood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.blood.model.BloodManager;
import com.aca.md2app.controller.Controller;

public class BloodController implements Controller{
	BloodManager manager = new BloodManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String msg =manager.getAdvice(request.getParameter("blood"));
		request.setAttribute("data", msg);
		
	}
	public String getViewPage() {
		return "/blood/advice.jsp";
	}
	
}

