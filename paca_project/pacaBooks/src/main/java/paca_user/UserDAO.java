package paca_user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	// ��Ĺ ������ context.xml���� resource�� ������ ó��
	private DataSource dataFactory;
	
	private Connection conn;
	
	private PreparedStatement pstmt;

	public UserDAO() {
		try {
			// ���ؽ�Ʈ �ʱ�ȭ
			Context ctx = new InitialContext();
			// �ڹ� ȯ�汸�� ���� ����
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			
			// ����Ŭ ����
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("DB���� ���� : "+ e.getMessage());
		}
	}
	
	// 유저 리스트를 가져옴
	public List<UserVO> listUsers(){
		List<UserVO> usersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			// ������
			String query = "select * from usertbl order by joinDate desc";
			
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(); // ������ ����
			
			while(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				Date joinDate = rs.getDate("joinDate");
				
				String ph_not = rs.getString("ph_not");
				String email_not = rs.getString("email_not");
				
				String isAdmin = rs.getString("isAdmin");
				
				// UserVO ��ü ����
				UserVO userVO = new UserVO(name, id, pwd, phone, email, joinDate, ph_not, email_not, isAdmin);
				// ��ü�������� ���� ����Ʈ�� ����
				usersList.add(userVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("DB��ȸ�� ���� �߻�" + e.getMessage());
		}
		return usersList;
		
	}
	
	// ���� ��� �޼���
	public void addUser(UserVO user) {
		try {
			// DB����
			conn=dataFactory.getConnection();
			
			String name = user.getName();
			String id = user.getId();
			System.out.println(id);
			String pwd = user.getPwd();
			String phone = user.getPhone();
			String email = user.getEmail();
			
			String ph_not = user.getPh_not();
			String email_not = user.getEmail_not();
			// ���� ���������̱⿡ ������ false�� 
			String isAdmin = "false";
			
			String query = "insert into usertbl (name, id, pwd, phone, email, ph_not, email_not, isAdmin)"
					+ " values (?, ?, ?, ?, ?, ?, ? ,?)";  // ������ �ۼ�
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			
			pstmt.setString(6, ph_not);
			pstmt.setString(7, email_not);
			
			pstmt.setString(8, isAdmin);
			
			pstmt.executeUpdate();  // 쿼리문 실행
			
			conn.close();
			pstmt.close();
			
		}catch(Exception e) {
			System.out.println("DB���� ����� �����߻� : "+e.getMessage());
		}
	}
	
	
	// 유저 로그인 메서드
	public String userLogin(UserVO user) {
		// 로그인 기본값에 false를 담음
		String loggedIn = "false";
		
		try {
			conn=dataFactory.getConnection();
			
			String id = user.getId();
			String pwd = user.getPwd();
			
			
			String query = "select pwd from usertbl where id = ?"; // 해당 id의 pwd를 가져옴
			pstmt = conn.prepareStatement(query);
			// id를 쿼리문에 담음
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			// 입력한 pwd가 해당 계정의 pwd와 일치하는지 확인
			String ck_pwd = rs.getString("pwd");

			if(pwd.equals(ck_pwd)) {
				loggedIn = "true";
				
			}else {
				loggedIn = "false";
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("DB �α����� ���� : "+e.getMessage());
		}
		
		return loggedIn;
	}
	
	// id를 String으로 받아오면 유저정보를 VO로 담아주는 메서드
	public UserVO userSelect(String id) {
		UserVO userVO = new UserVO();
		try {
			conn = dataFactory.getConnection();
			
			String query = "select * from usertbl where id=?"; //쿼리문 작성
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			String _id = rs.getString("id");
			String _pwd = rs.getString("pwd");
			String _name = rs.getString("name");
			String _phone = rs.getString("phone");
			String _email = rs.getString("email");
			Date _joinDate = rs.getDate("joinDate");
			String _ph_not = rs.getString("ph_not");
			String _email_not = rs.getString("email_not");
			
			userVO.setId(_id);
			userVO.setPwd(_pwd);
			userVO.setName(_name);
			userVO.setPhone(_phone);
			userVO.setEmail(_email);
			userVO.setJoinDate(_joinDate);
			userVO.setPh_not(_ph_not);
			userVO.setEmail_not(_email_not);
			
			
		}catch(Exception e) {
			System.out.println("DB 유저정보 조회중 에러 : "+ e.getMessage());
		}
		
		return userVO;
	}
	
	
	
}
