package com.aca.md2app.gallery.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.md2app.controller.Controller;

public class ListController implements Controller{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	@Override
	public String getViewName() {
		return null;
	}

	
	@Override
	public boolean isForward() {

		return false;
	}

}
