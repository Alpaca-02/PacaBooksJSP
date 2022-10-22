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
	
	// ȸ������ ��ȸ �޼���
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
			
			pstmt.executeUpdate();  // ������ ����
			
			conn.close();
			pstmt.close();
			
		}catch(Exception e) {
			System.out.println("DB���� ����� �����߻� : "+e.getMessage());
		}
	}
	
	
	
}
