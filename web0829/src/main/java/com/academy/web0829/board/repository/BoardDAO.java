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
import com.academy.web0829.mybatis.ConfigManager;

public class BoardDAO {
	ConfigManager configManager = ConfigManager.getInstance();
	
	//한건 넣기
	public int insert(Board board) {
		SqlSession sqlSession = configManager.getSqlSession(); //mybatis 쿼리 수행 객체		
				//여기서 SQL문 작성 하지 말고 ,xml에 작성된 쿼리문을 호출 하자
		int result=sqlSession.insert("Board.insert", board);
		sqlSession.commit();//트랜잭션 확정이 아님 
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	//목록 가져오기
	public List<Board> selectAll() {
		SqlSession sqlSession = configManager.getSqlSession();
		List<Board> boardList = null;
		boardList = sqlSession.selectList("Board.selectAll");
		configManager.closeSqlSession(sqlSession);
		return boardList;
	}
	
	public Board select(int board_id) {
		SqlSession sqlSession = configManager.getSqlSession();
		Board board = null;
		board = sqlSession.selectOne("Board.select", board_id);
		configManager.closeSqlSession(sqlSession);
		return board;
	}
	
	public int update(Board board) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.update("Board.update", board);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	
	public int delete(int board_id) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result =sqlSession.delete("Board.delete",board_id);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	
}
