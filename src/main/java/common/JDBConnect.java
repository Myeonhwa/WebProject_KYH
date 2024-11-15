package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
//	멤버변수 : DB연결, 정적쿼리실행, 동적쿼리실행, select결과반환
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
//	기본생성자 : 매개변수가 없는 생성자
	public JDBConnect() {
		try {
//			오라클 드라이버 메모리에 로드
			Class.forName("oracle.jdbc.OracleDriver");
//			커넥션 uRL 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			계정의 아이디와 비번
			String id = "webproject_db";
			String pwd = "1234";
//			데이터베이스 연결 시도
			con = DriverManager.getConnection(url, id, pwd);
//			Connection 인스턴스가 반환되면 연결 성공
			System.out.println("DB 연결 성공(기본 생성자)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	인자생성자1 : 4개의 매개변수로 선언
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
//			오라클 드라이버 메모리에 로드
			Class.forName(driver);
//			데이터베이스 연결 시도
			con = DriverManager.getConnection(url, id, pwd);
//			Connection 인스턴스가 반환되면 연결 성공
			System.out.println("DB 연결 성공(인수 생성자1)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}






















