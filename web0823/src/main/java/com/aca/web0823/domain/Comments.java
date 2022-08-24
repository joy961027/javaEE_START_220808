package com.aca.web0823.domain;

public class Comments {
	private int commetns_id;
	private News news; //frkey
	private String detail;
	private String author;
	private String writeDate;
	
	public int getCommetns_id() {
		return commetns_id;
	}
	public void setCommetns_id(int commetns_id) {
		this.commetns_id = commetns_id;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
}
