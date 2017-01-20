package com.javalec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.javalec.ex.dto.boardDTO;


public class boardDAO {
	
	DataSource ds =null;
	Connection con = null;
	PreparedStatement ps = null;
	
	public boardDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void connect(){
		try {
			//con = DriverManager.getConnection(URL, UID, UPW);
			//sta = con.createStatement();
			con=ds.getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void disconnect(){	
		try {
			if(ps!=null)ps.close();
			if(con!=null)con.close();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	public ArrayList<boardDTO> list(){
		connect();
		ArrayList<boardDTO> dtos = new ArrayList<boardDTO>();
		ResultSet rs = null;
		
		try {
			String query = "select * from board order by ggroup desc, step asc";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int hit = rs.getInt("hit");
				int group = rs.getInt("ggroup");
				int step = rs.getInt("step");
				int indent = rs.getInt("indent");
				
				boardDTO dto = new boardDTO(id, hit, group, step, indent, name, pw, title, content, date);
				dtos.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
			return dtos;
			
		}
	public void write(String name, String pw, String title, String content){
		connect();
		ResultSet rs = null;
		
		int currval=0;
		String query0 = "select ifnull(max(id),0)+1 from board";
		try {
			ps = con.prepareStatement(query0);
		rs = ps.executeQuery();
		if(rs.next()) currval = rs.getInt(1);
		System.out.println(currval);
		
		String query = "insert into board (name, pw, title, content, ggroup, step, indent) values (?, ?, ?, ?, ?, 0, 0)";
		ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, pw);
		ps.setString(3, title);
		ps.setString(4, content);
		ps.setInt(5, currval);
		int rn = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			disconnect();
		}
	}
	
	public boardDTO contentView(String strID) {
		// TODO Auto-generated method stub
		
		upHit(strID);
		
		boardDTO dto = null;
		ResultSet rs = null;
		
		connect();
		try {
			String query = "select * from board where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(strID));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int hit = rs.getInt("hit");
				int group = rs.getInt("ggroup");
				int step = rs.getInt("step");
				int indent = rs.getInt("indent");
				
				dto = new boardDTO(id, hit, group, step, indent, name, pw, title, content, date);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			disconnect();
		}
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		connect();
		
		try {
			String query = "update board set name = ?, title = ?, content = ? where id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, Integer.parseInt(bId));
			int rn = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public void delete(String bId) {
		connect();
		
		try {
			String query = "delete from board where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(bId));
			int rn = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	public boardDTO reply_view(String str) {
		boardDTO dto = null;
		ResultSet rs = null;
		
		connect();
		try {
			String query = "select * from board where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(str));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int hit = rs.getInt("hit");
				int group = rs.getInt("ggroup");
				int step = rs.getInt("step");
				int indent = rs.getInt("indent");
				
				dto = new boardDTO(id, hit, group, step, indent, name, pw, title, content, date);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return dto;
	}
	
	public void reply(String id, String name, String title, String content, String group, String step, String indent) {
		// TODO Auto-generated method stub
		
		replyShape(group, step);
		connect();
		try {
			String query = "insert into board (name, title, content, ggroup, step, indent) values ( ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setInt(4, Integer.parseInt(group));
			ps.setInt(5, Integer.parseInt(step) + 1);
			ps.setInt(6, Integer.parseInt(indent) + 1);
			
			int rn = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}
	
	private void replyShape( String strGroup, String strStep) {
		
		connect();
		
		try {
			String query = "update board set step = step + 1 where ggroup = ? and step > ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(strGroup));
			ps.setInt(2, Integer.parseInt(strStep));
			
			System.out.println(query);
			int rn = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	private void upHit( String id) {
		connect();
		
		try {
			String query = "update board set hit = hit + 1 where id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			
			int rn = ps.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
}
