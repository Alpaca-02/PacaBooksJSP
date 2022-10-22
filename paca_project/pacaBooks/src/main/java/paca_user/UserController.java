package paca_user;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pacaUser/*")
public class UserController extends HttpServlet {
	UserDAO userDAO;  // ù ����� DB��ü ����


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �������� ������ ��ũ ����
		String nextPage=null;
		
		// ���ڵ�, ������ Ÿ��
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// getPathInfo�� ��û url�� ������
		String action=request.getPathInfo();
		System.out.println("��û : "+action);
		
		if(action.equals("/list.do")) {
			
		}else if(action.equals("/join.do")) {
			// ��û url�� ȸ������â�� ���
			nextPage = "/join.html";
		}else if(action.equals("/addUser.do")) {
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			System.out.println(id);
			String pwd = request.getParameter("pwd");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			// radi���� true false�� ���ڿ��� �޾Ƽ� ���� (����Ŭ�� boolean ����X)
			String ph_not = request.getParameter("ph_not");
			String email_not = request.getParameter("email_not");
			
			System.out.println(ph_not);
			System.out.println(email_not);
			
			userDAO = new UserDAO();
			UserVO userVO = new UserVO(name, id, pwd, phone, email, ph_not, email_not);
			userDAO.addUser(userVO);
		}
	}

}