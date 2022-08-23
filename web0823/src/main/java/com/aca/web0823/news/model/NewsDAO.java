package com.aca.web0823.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * 기존 코드방식에서랑은 틀리게, Connection 객체는 커넥션 풀을 이용한다
 * 우리가 사용하는 커넥션 풀은 tomcat서버가 제공하는 풀을 이용하되, jndi로 자원에 접근할 예장
 */
public class NewsDAO {
	InitialContext context;
	DataSource ds;
	public NewsDAO() {
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle"); //풀얻기
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//c
	public void insert() {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = ds.getConnection();
			String sql="insert into news(news_id,title,writer,content values(seq_news.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	//r
	public void selectAll() {
		String sql = "select * from news order by news_id desc";
	}
	//r
	public void select() {
		String sql = "select * from news where news_id = ?";
	}
	//u
	public void update() {
		String sql = "update news set title=?, writer=?, cotent=? where news_id=?";
	}
	//d
	public void delete() {
		String sql = "delete news where news_id =?";
	}
	
}
