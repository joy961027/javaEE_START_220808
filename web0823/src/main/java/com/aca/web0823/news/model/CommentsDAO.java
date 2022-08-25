package com.aca.web0823.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.web0823.domain.Comments;
import com.aca.web0823.domain.News;
import com.aca.web0823.pool.ConnectionManager;
import com.aca.web0823.pool.PoolManager;

//DAO는 테이블 마다 1대1대응하게 생성해야한다. 따라서 오라클에 테이블이 만일 100개라면 DAO도 100개, DTO도 100개
//제작시에는 시간이 걸리지만, 추후 유지보수할때 시간이 단축됨, 유지보수시간 = 돈
public class CommentsDAO {
	ConnectionManager manager=PoolManager.getInstance();//다형성을 염두해두자(코드가 유연해진다)
	
	
	
	public int insert(Comments comments) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		con = manager.getConnection();
		String sql ="insert into comments(comments_id,detail,author,news_id) values(seq_comments.nextval,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comments.getDetail());
			pstmt.setString(2, comments.getAuthor());
			pstmt.setInt(3,comments.getNews().getNews_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		
		return result;
	}
	//해당 뉴스기사와 관련된 모든 레코드 갸져오기
	public List selectAll(int news_id){
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList<Comments>();
		con = manager.getConnection();
		String sql = "select * from comments where news_id =? order by comments_id";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Comments comments = new Comments();
				News news = new News();
				comments.setCommetns_id(rs.getInt("comments_id"));
				news.setNews_id(news_id);
				comments.setNews(news);
				comments.setDetail(rs.getString("detail"));
				comments.setAuthor(rs.getString("author"));
				comments.setWriteDate(rs.getString("writedate"));
				list.add(comments);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		
		
		return list;
	}
	
	
}
