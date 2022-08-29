package com.academy.web0829.board.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.academy.web0829.domain.Board;

public class BoardDAO {

	public int insert(Board board) {
		int result=0;
		
		//여기서 SQL문 작성 하지 말고 ,xml에 작성된 쿼리문을 호출 하자
		
		
		
		
		return result;
	}
	
	public List<Board> selectAll() {
		String resource = "com/academy/web0829/mybatis/config.xml";
		InputStream inputStream =null;
		List<Board> boardList = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
			//mybatis를 이용하면 쿼리문을 수행하기 위한 객체인 sqlSession객체를 이용하여 sql문을 호출할 수 있다.
			SqlSession session =  factory.openSession();
			boardList=  session.selectList("babo.select");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return boardList;
	}
	
}
