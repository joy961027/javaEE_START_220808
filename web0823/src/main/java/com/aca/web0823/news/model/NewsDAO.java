package com.aca.web0823.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.jni.Poll;

import com.aca.web0823.domain.News;
import com.aca.web0823.pool.ConnectionManager;
import com.aca.web0823.pool.DBManager;
import com.aca.web0823.pool.PoolManager;

/*
 * DAO는 오직 CRUD에만 집중한다.
 */
public class NewsDAO {
	ConnectionManager manager;//웹이건, 응용이건  둘다 포함할수 있는 객체
	
	public NewsDAO() {
		manager = PoolManager.getInstance();
//		manager = new DBManager();
	}
	//CREATE
	public int insert(News news) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			con = manager.getConnection(); //다형성 polymorphism ,비록 자료형은 connectionManager이지만 
			//호출되는 메서드 동작은 각각 틀리게 동작할 수 있다. ConnectionManager가 어떤 때는 PoolManager로 동작하고
			//또 다른때는 DBManager로 동작할수 있다.
			String sql="insert into news(news_id,title,writer,content) values(seq_news.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		
		return result;
		
		
	}
	//READ
	public List<News> selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<News> list= new ArrayList<News>();
		con= manager.getConnection();
		String sql = "select * from news order by news_id desc";
		try {
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				list.add(news);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return list;
		
	}
	//r
	public News select(int news_id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = manager.getConnection();
		News news =null;
		try {
			String sql = "select * from news where news_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return news;
		
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
