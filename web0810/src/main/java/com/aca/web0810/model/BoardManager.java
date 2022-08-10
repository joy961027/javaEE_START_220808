package com.aca.web0810.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//이클래스는 웹기반 뿐만 아니라 스탠다드 기반에서도 공요으로 쓸 수 있는 수준으로 정의해놓자
//왜?? 재사용을 위해..

public class BoardManager {
		
	public int insert(String title, String writer, String content) {
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="java";
		String pass="1234";
		Connection con = null;
		PreparedStatement pstmt= null;
		int result=0;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			String sql="insert into board(board_id,title,writer,content)";
			sql+= " values(seq_board.nextval,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			result = pstmt.executeUpdate();
			return result;
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}finally {
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	
		
		
	}

}
