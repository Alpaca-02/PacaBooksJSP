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
	UserDAO userDAO;  // 첫 실행시 DB객체 선언


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
		// 포워딩할 페이지 링크 변수
		String nextPage = "/index.html";
		
		// 인코딩, 컨텐츠 타입
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// getPathInfo로 요청 url을 가져옴
		String action=request.getPathInfo();
		System.out.println("요청 : "+action);
		
		if(action.equals("/list.do")) {
			
		}else if(action.equals("/join.do")) {
			// 요청 url이 회원가입창인 경우
			nextPage = "/join.html";
		}else if(action.equals("/addUser.do")) {
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			// radi에서 true false로 문자열을 받아서 저장 (오라클은 boolean 지원X)
			String ph_not = request.getParameter("ph_not");
			String email_not = request.getParameter("email_not");
			
			System.out.println(ph_not);
			System.out.println(email_not);
			
			
			UserVO userVO = new UserVO(name, id, pwd, phone, email, ph_not, email_not);
			userDAO.addUser(userVO);
		}else if(action.equals("/login.do")) {
			// 요청 url이 로그인인 경우
			
			//세션 정보를 가져옴 
			HttpSession session = request.getSession();
			System.out.println("세션 ID : " + session.getId());
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			UserVO userVO = new UserVO();
			userVO.setId(id);
			userVO.setPwd(pwd);
			// 유저 로그인 메서드 실행
			String loggedIn = userDAO.userLogin(userVO);
			
			if(loggedIn.equals("true")) {
				session.setAttribute("id", id);
			}
			session.setAttribute("loggedIn", loggedIn);
			
			// 로그인 여부 임시 출력
//			System.out.println(session.getAttribute("loggedIn"));
			
			nextPage="index.html";

		}else if(action.equals("/myPage.do")) {
			// 마이페이지 버튼을 눌렀을 때
			
			HttpSession session = request.getSession();
			
			// 세션에서 로그인 여부를 가져옴
			String loggedIn = (String)session.getAttribute("loggedIn");
			
			System.out.println(loggedIn);
			
			if(loggedIn.equals("ture")) {
				nextPage="/mypage.html";
			}else {
				nextPage="/login.html";
			}
		}else {
			nextPage="/index.html";
		}
		
		// nextPage의 링크에 바인딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		System.out.println(nextPage);
		dispatcher.forward(request, response);
	}

}
