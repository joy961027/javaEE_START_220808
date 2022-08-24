package com.aca.web0823.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//독립실행형 에서 커넥션을 관리하기 위한 객체
public class DBManager extends ConnectionManager{
	private static DBManager instance= new DBManager();
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String pass="1234";
	
	private DBManager() {
	}
	
	public static DBManager getInstance() {
		return instance;
	}
	@Override
	public Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				con = DriverManager.getConnection(url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	@Override
	public void freeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();//다시 풀로 돌려보냄
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();//다시 풀로 돌려보냄
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();//다시 풀로 돌려보냄
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();//다시 풀로 돌려보냄
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();//다시 풀로 돌려보냄
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();//다시 풀로 돌려보냄
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
