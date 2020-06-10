package kr.or.ddit.basic;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Select 예제
 */

public class T02_JdbcTest {
/*
 	문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오
 	
 	문제2) lprod_id값을 2개 입력받아서 두 값 중 작은 값부터 큰값 사이의 자료를 출력하시오
 */
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("lprod_id값을 입력해주세요>");
		int input = s.nextInt();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int min, max;
		
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB에 접속 (Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "YOON";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			//3. Statement객체 생성 => Connection객체를 이용한다
			stmt = conn.createStatement();
			
			//4. SQL문을 Statement객체를 이용하여 실행하고 실행결과를 ResultSet에 저장한다
			String sql = "select * from lprod"; //실행할 SQL문
			
			//SQL문이 select일 경우에는 excuteQuery()메서드 이용하고,
			//insert, update, delete일 경우에는 executeUpdate()메서드 이용함
			rs = stmt.executeQuery(sql);
			
			//5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차례로 읽어와 처리한다
			System.out.println("실행할 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행결과 ===");
			
			//rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동시키고,
			//				그곳에 자료가 있으면 true, 없으면 false를 반환한다
			
			while(rs.next()) {
				if(input < rs.getInt("lprod_id")) {
				//컬럼의 자료를 가져오는 방법
				//방법1) rs.get자료형이름("컬럼명")
				//방법1) rs.get자료형이름("컬럼번호") => 컬럼번호는 1부터 시작
				
				System.out.println("lprod_id : " + rs.getInt("lprod_id")); //대소문자 가리지 않는다
				//System.out.println("lprod_id : " + rs.getInt(1)); 위와 동일..컬럼번호로 가져와도됨
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------");
				}
			}
			System.out.println("출력 끝....");
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//6. 종료(사용했던 자원을 모두 반납한다)
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(stmt != null) try {rs.close();}catch(SQLException e) {}
			if(conn != null) try {rs.close();}catch(SQLException e) {}
		}
		
		System.out.println("첫번째 lprod_id를 입력해주세요>");
		int input2 = s.nextInt();
		System.out.println("두번째 lprod_id를 입력해주세요>");
		int input3 = s.nextInt();
		
		if(input2 < input3) {
			min = input2;
			max = input3;
		}else{
			min = input3;
			max = input2;
		}
		
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB에 접속 (Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "YOON";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
			//3. Statement객체 생성 => Connection객체를 이용한다
			stmt = conn.createStatement();
			
			//4. SQL문을 Statement객체를 이용하여 실행하고 실행결과를 ResultSet에 저장한다
			String sql = " select * from lprod " + " where lprod_id between " + min + " and " + max; //실행할 SQL문
			
			//SQL문이 select일 경우에는 excuteQuery()메서드 이용하고,
			//insert, update, delete일 경우에는 executeUpdate()메서드 이용함
			rs = stmt.executeQuery(sql);
			
			//5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차례로 읽어와 처리한다
			System.out.println("실행할 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행결과 ===");
			
			//rs.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동시키고,
			//				그곳에 자료가 있으면 true, 없으면 false를 반환한다
			
			while(rs.next()) {
				//컬럼의 자료를 가져오는 방법
				//방법1) rs.get자료형이름("컬럼명")
				//방법1) rs.get자료형이름("컬럼번호") => 컬럼번호는 1부터 시작
				
				System.out.println("lprod_id : " + rs.getInt("lprod_id")); //대소문자 가리지 않는다
				//System.out.println("lprod_id : " + rs.getInt(1)); 위와 동일..컬럼번호로 가져와도됨
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------");
				}
			
			System.out.println("출력 끝....");
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//6. 종료(사용했던 자원을 모두 반납한다)
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(stmt != null) try {stmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		
	}
		
	}
	

