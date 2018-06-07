package com.fruit.dao;

import java.sql.*;
import java.util.ArrayList;
import com.fruit.vo.*;

public class FruitDAO {
	private static FruitDAO dao = new FruitDAO();
	
	private FruitDAO() {}
	
	public static FruitDAO getInstance() {
		return dao;
	}
	
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/fruit?useSSL=false&serverTimezone=Asia/Seoul";
			String user = "root";
			String password = "cs1234";
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception ex){
			System.out.println("error1 : "+ex);
		}return conn;
	}
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(Exception ex) {
				System.out.println("error2 : "+ex);
			}
		}
		close(conn,ps);
	}
	
	public void close(Connection conn, PreparedStatement ps) {
		if(ps!=null) {
			try {
				ps.close();
			}catch (Exception ex) {
				System.out.println("error3 : "+ex);
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			}catch(Exception ex) {
				System.out.println("error4 : "+ex);
			}
		}
	}
	
	public String fruitSellerLogin(String id) {
		String pw="";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			psmt = conn.prepareStatement("select pw from seller where id=?;");
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next())
				pw=rs.getString(1);
		}catch(Exception e) {
			System.out.println("error5 : "+e);
		}finally {
			close(conn,psmt,rs);
		}
		return pw;
	}
	
	public void fruitSellerRegister(FruitSeller fs) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn=connect();
			psmt = conn.prepareStatement("update seller set count=?,price=?,money=? where id=?");
			psmt.setString(1, fs.getCount());
			psmt.setString(2, fs.getPrice());
			psmt.setString(3, fs.getMoney());
			psmt.setString(4, fs.getId());
			psmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("error6 : "+ex);
		}finally {
			close(conn,psmt);
		}
	}
	
	public FruitSeller fruitSellerInformation(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		FruitSeller fs = null;
		
		try {
			conn = connect();
			psmt = conn.prepareStatement("select*from seller where id=?");
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				fs = new FruitSeller();
				fs.setCount(rs.getString(3));
				fs.setPrice(rs.getString(4));
				fs.setMoney(rs.getString(5));
			}
		}catch(Exception ex) {
			System.out.println("error7 : "+ex);
		}finally {
			close(conn,psmt,rs);
		}
		
		return fs;
	}
	
	public String fruitBuyerLogin(String id) {
		String pw="";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			psmt = conn.prepareStatement("select pw from buyer where id=?;");
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next())
				pw=rs.getString(1);
		}catch(Exception e) {
			System.out.println("error8 : "+e);
		}finally {
			close(conn,psmt,rs);
		}
		return pw;
	}
	
	public void fruitBuyerRegister(FruitBuyer fb) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn=connect();
			psmt = conn.prepareStatement("update buyer set money=? where id=?");
			psmt.setString(1, fb.getMoney());
			psmt.setString(2, fb.getId());
			psmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("error9 : "+ex);
		}finally {
			close(conn,psmt);
		}
	}
	
	public FruitBuyer fruitBuyerInformation(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		FruitBuyer fb = null;
		
		try {
			conn = connect();
			psmt = conn.prepareStatement("select*from buyer where id=?");
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				fb = new FruitBuyer();
				fb.setCount(rs.getString(3));
				fb.setMoney(rs.getString(4));
			}
		}catch(Exception ex) {
			System.out.println("error10 : "+ex);
		}finally {
			close(conn,psmt,rs);
		}
		
		return fb;
	}
	
	public ArrayList<FruitSeller>SellerList(){
		ArrayList<FruitSeller>list = new ArrayList<FruitSeller>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		FruitSeller fs = null;
		try {
			conn=connect();
			psmt = conn.prepareStatement("select*from seller");
			rs=psmt.executeQuery();
			while(rs.next()) {
				fs = new FruitSeller();
				fs.setId(rs.getString(1));
				fs.setCount(rs.getString(3));
				fs.setPrice(rs.getString(4));
				list.add(fs);
			}
		}catch(Exception ex) {
			System.out.println("error11 : "+ex);
		}finally {
			close(conn,psmt,rs);
		}
		return list;
	}
	
	public FruitSeller sellerSearch(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		FruitSeller fs = null;
		
		try {
			conn = connect();
			psmt = conn.prepareStatement("select*from seller where id=?");
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			if(rs.next()) {
				fs = new FruitSeller();
				fs.setCount(rs.getString(3));
				fs.setPrice(rs.getString(4));
				fs.setMoney(rs.getString(5));
			}
		}catch(Exception ex) {
			System.out.println("error12 : "+ex);
		}finally {
			close(conn,psmt,rs);
		}
		
		return fs;
	}
	
	public FruitBuyer buyerSearch(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		FruitBuyer fb = null;
		
		try {
			conn = connect();
			psmt = conn.prepareStatement("select*from buyer where id=?");
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			if(rs.next()) {
				fb = new FruitBuyer();
				fb.setCount(rs.getString(3));
				fb.setMoney(rs.getString(4));
			}
		}catch(Exception ex) {
			System.out.println("error13 : "+ex);
		}finally {
			close(conn,psmt,rs);
		}
		
		return fb;
	}
	
	public void sellerUpdate(FruitSeller fs) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn=connect();
			psmt = conn.prepareStatement("update seller set count=?, money=? where id=?");
			psmt.setString(1, fs.getCount());
			psmt.setString(2, fs.getMoney());
			psmt.setString(3, fs.getId());
			psmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("error14 : "+ex);
		}finally {
			close(conn,psmt);
		}
	}
	
	public void buyerUpdate(FruitBuyer fb) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn=connect();
			psmt = conn.prepareStatement("update buyer set count=?, money=? where id=?");
			psmt.setString(1, fb.getCount());
			psmt.setString(2, fb.getMoney());
			psmt.setString(3, fb.getId());
			psmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("error15 : "+ex);
		}finally {
			close(conn,psmt);
		}
	}
	
	public void sellerJoin(FruitSeller fs) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = connect();
			psmt = conn.prepareStatement("insert into seller values(?,?,'0','0','0')");
			psmt.setString(1, fs.getId());
			psmt.setString(2, fs.getPw());
			psmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("error16 : "+ex);
		}finally {
			close(conn,psmt);
		}
	}
	
	public void buyerJoin(FruitBuyer fb) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = connect();
			psmt = conn.prepareStatement("insert into buyer values(?,?,'0','0')");
			psmt.setString(1, fb.getId());
			psmt.setString(2, fb.getPw());
			psmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("error17 : "+ex);
		}finally {
			close(conn,psmt);
		}
	}

}
