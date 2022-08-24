package com.aca.web0823.pool;
//데이터베이스 접속을 얻거나, 해제해주는 전담객체
// 코드 중복을 피하기 위해 만들기

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class ConnectionManager {
	//커넥션 얻어오기
	public abstract Connection getConnection();
	
	//db 관련 자원 해제
	public abstract void freeConnection(Connection con); 
	public abstract void freeConnection(Connection con, PreparedStatement pstmt);  //DML	
	public abstract void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs);//select 

}
