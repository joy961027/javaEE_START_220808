package com.aca.web0812.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aca.web0812.domain.HotSpot;
import com.mysql.fabric.xmlrpc.base.Array;

//오직 hotspot테이블에 대한 crud를 담당하는 dao객체
public class HotSpotDAO {
	String url = "jdbc:mysql://localhost:3306/javastudy?useUnicode=true&characterEncoding=utf8";
	String user = "root";
	String pass = "1234";

	public int insert(HotSpot hotSpot) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			String sql = "insert into hotspot(lati,longi,icon, content) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, hotSpot.getLati());
			pstmt.setFloat(2, hotSpot.getLongi());
			pstmt.setString(3, hotSpot.getIcon());
			pstmt.setString(4, hotSpot.getContent());
			result = pstmt.executeUpdate(); //insert쿼리 수행 후 이 connection에 대한 세션이 닫히기 전에
			//insert 에 의해 증가된 pk얻어오기
			sql = "select last_insert_id() as hotspot";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("hotspot");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}// end of insert
	
	//목록 가져오기
	public List selectAll() {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			String sql = "select * from hotspot order by hotspot asc";
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				HotSpot dto = new HotSpot();

				dto.setHotspot_id(rs.getInt("hotspot"));
				dto.setLati(rs.getFloat("lati"));
				dto.setLongi(rs.getFloat("longi"));
				dto.setIcon(rs.getString("icon"));
				dto.setContent(rs.getString("content"));
				list.add(dto);//이차원 구조
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
		}finally {
			if(rs!=null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
			
			
		}
		return list;
	}// end of select
	//last_insert_ind 현재 나의 세션내에서 나에 의해 증가된 id값을 가져오기
	public HotSpot select(int hotspot) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotSpot dto =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			String sql = "select * from hotspot where hotspot = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hotspot);
			rs= pstmt.executeQuery();
		if(rs.next()){
			dto = new HotSpot();// 레코드가 있을때만, dto 생성
			dto.setHotspot_id(rs.getInt("hotspot"));
			dto.setLati(rs.getFloat("lati"));
			dto.setLongi(rs.getFloat("longi"));
			dto.setIcon(rs.getString("icon"));
			dto.setContent(rs.getString("content"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dto;
	}
	

}// end of class
