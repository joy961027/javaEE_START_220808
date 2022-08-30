package com.academy.web0829.util;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class Pager {
	private int totalRecord; //총 레코드수
	private int pageSize=10;
	private int totalPage=(int)Math.ceil((float)totalRecord/pageSize);
	private int blockSize =10;
	private int currentPage=1;
	private int firstPage = currentPage;
	private int lastPage;
	private int curPos;
	private int num;
	
	//공식을 대입하기 위한 메서드
	public void init(List list, HttpServletRequest request) {
		totalRecord = list.size();
		totalPage=(int)Math.ceil((float)totalRecord/pageSize);
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage-(currentPage-1)%blockSize;
		lastPage = firstPage +blockSize-1;
		curPos= (currentPage-1)*pageSize;
		num = totalRecord - curPos;
		
	}

}
