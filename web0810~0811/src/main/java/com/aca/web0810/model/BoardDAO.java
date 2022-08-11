package com.aca.web0810.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.web0811.domain.Board;

//이클래스는 웹기반 뿐만 아니라 스탠다드 기반에서도 공요으로 쓸 수 있는 수준으로 정의해놓자
//왜?? 재사용을 위해..
// 아래의 클래스는 오직 crud만을 처리하기 위한 객체이므로
//객체지향의 설계분야에서 이러한 역할을 수행하는 객체를 가리켜 Data Access Object

public class BoardDAO {
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String pass="1234";
		
	public int insert(Board board) {
		Connection con = null;
		PreparedStatement pstmt= null;
		int result=0; //멤버변수가 아닌 지역변수는 컴파일러가 초기화 해주지 않는다!1 따럿 반드시 초기화하자
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			String sql="insert into board(board_id,title,writer,content)";
			sql+= " values(seq_board.nextval,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result = pstmt.executeUpdate();
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
	
		return result;
		
	} // end of insert
	
	
	//게시물 목록 가져오기
	public List<Board> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<Board>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			String sql="select * from board order by board_id desc";
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//rs는 finally문에서 con, pstmt와 함께 곧 닫히게 되므로 반환할수 없다.
			//rs를 대신할 객체를 이용하자
			//테이블 레코드 1건 -- DTO의 인스턴스 1개로 대체
			//테이블 레코드의 순서는 LIST로 대체
			
			while(rs.next()) {				
				Board board = new Board();
				board.setBoard_id(rs.getInt("board_id"));//pk
				board.setTitle(rs.getString("title")); //제목 채우기
				board.setWriter(rs.getString("writer"));//작성자 채우기
				board.setContent(rs.getString("content")); //내용 채우기
				board.setRegdate(rs.getString("regdate")); // 날짜 채우기
				board.setHit(rs.getInt("hit")); //조회수 채우기
				list.add(board); //리스트의 추가
			}
			
			
			
			
			
			ArrayList<Board> board = new ArrayList<>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		return list;
	}//end of selectAll()
	
	
	public Board select(int board_id) {
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		Board board = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			String sql ="select * from board where board_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,board_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));//pk
				board.setTitle(rs.getString("title")); //제목 채우기
				board.setWriter(rs.getString("writer"));//작성자 채우기
				board.setContent(rs.getString("content")); //내용 채우기
				board.setRegdate(rs.getString("regdate")); // 날짜 채우기
				board.setHit(rs.getInt("hit")); //조회수 채우기
			}		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		return board;
	}//end of select() 한건만 가져오기

	
	public int delete(int board_id) {//한거만 삭제
		
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			String sql = "delete board where board_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		return result;
	}//end of delete()//한건만 삭제
	
	
	public int update(Board board) {
		Connection con=null;
		PreparedStatement pstmt= null;
		int result=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			String sql = "update board set title =?, writer=?, content=? where board_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getBoard_id());
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return result;
				
	}
	
	
}//end of class
