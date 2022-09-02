package com.aca.md2app.model.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;
@Data
public class Pager {
	private int totalRecord; //총 레코드수
	private int pageSize=10; //한 페이지당 보여질 레코드 수
	private int totalPage;
	private int blockSize =10;
	private int currentPage=1;
	private int firstPage;
	private int lastPage;
	private int curPos;
	private int num;
	
	public void init(List list,HttpServletRequest request) {
		totalRecord = list.size();
		totalPage = (int)(Math.ceil((float)(totalRecord)/pageSize));
		if(request.getParameter("currentPage")!=null) {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage-(currentPage-1)%blockSize;
		lastPage = firstPage+blockSize -1;
		curPos = (currentPage-1)*pageSize;
		num = totalRecord-curPos;
		
		
	}
}
