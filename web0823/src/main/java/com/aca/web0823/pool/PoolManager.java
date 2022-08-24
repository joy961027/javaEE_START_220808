package com.aca.web0823.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//web에서의 커넥션 풀로부터 커네셕은 얻기위한 전담객체
public class PoolManager extends ConnectionManager {
	private static PoolManager instance;
	InitialContext context ; //JNDI 검색 객체
	DataSource ds; 
	Connection con;
	
	private PoolManager() {
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/myoracle"); //검색 시작
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static PoolManager getInstance() {
		if(instance==null) {
			instance = new PoolManager();
		}
		return instance;
	}
	@Override
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection(); //새로운 접속이 아니고 기존 풀에 모여있는 Connection을 빌려오는것
		} catch (SQLException e) {
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
