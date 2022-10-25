package paca_user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/pacaUser/*")
public class UserController extends HttpServlet {
	UserDAO userDAO;  // DAO객체를 생성


	public void init(ServletConfig config) throws ServletException {
		userDAO = new UserDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �������� ������ ��ũ ����
		String nextPage = "/index.html";
		
		// ���ڵ�, ������ Ÿ��
		response.setContentType("text/html; charset=utf-8");
		
		// getPathInfo로 요청 URL을 가져온다
		String action=request.getPathInfo();
		System.out.println("요청 URL : "+action);
		
		try {
			if(action.equals("/list.do")) {
				
			}else if(action.equals("/join.do")) {
				// ��û url�� ȸ������â�� ���
				nextPage = "/join.html";
			}else if(action.equals("/addUser.do")) {
				String name = request.getParameter("name");
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				
				// radi���� true false�� ���ڿ��� �޾Ƽ� ���� (����Ŭ�� boolean ����X)
				String ph_not = request.getParameter("ph_not");
				String email_not = request.getParameter("email_not");
				
				System.out.println(ph_not);
				System.out.println(email_not);
				
				
				UserVO userVO = new UserVO(name, id, pwd, phone, email, ph_not, email_not);
				userDAO.addUser(userVO);
			}else if(action.equals("/login.do")) {
				// 로그인을 했을경우
				
				//���� ������ ������ 
				HttpSession session = request.getSession();
				System.out.println("세션 ID : " + session.getId());
				
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				
				UserVO userVO = new UserVO();
				userVO.setId(id);
				userVO.setPwd(pwd);
				// 계정 존재 여부를 확인받음
				String loggedIn = userDAO.userLogin(userVO);
				
				if(loggedIn.equals("true")) {
					session.setAttribute("id", id);
				}
				session.setAttribute("loggedIn", loggedIn);
				
				//로그인된 계정, 로그인 여부를 한번 재 확인
				System.out.println(session.getAttribute("id"));
				System.out.println(session.getAttribute("loggedIn"));
				if(Boolean.parseBoolean(loggedIn)) {
					// 로그인이 되었을 경우 메인메뉴로 이동
					response.sendRedirect("/pacaBooks/index.html");
				}else {
					nextPage="/login.jsp";
				}
	
			}else if(action.equals("/myPage.do")) {
				// 마이페이지 아이콘을 클릭한 경우
				
				HttpSession session = request.getSession();
				
				// 세션에서 로그인이 되었는지 확인
				String loggedIn = (String)session.getAttribute("loggedIn");
				
				System.out.println(loggedIn+"123");
				boolean login = Boolean.parseBoolean(loggedIn);
				if(login) {
					response.sendRedirect("/pacaBooks/mypage.html");
				}else {
					nextPage="/login.jsp";
				}
			}else {
				response.sendRedirect("/index.html");
			}
			// nextPage에 받아온 링크를 포워드
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			System.out.println(nextPage);
			dispatcher.forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
