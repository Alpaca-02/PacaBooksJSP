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
	UserDAO userDAO;  // 첫 실행시 DB객체 선언


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
		// 포워딩할 페이지 링크 변수
		String nextPage=null;
		
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
			System.out.println(id);
			String pwd = request.getParameter("pwd");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			// radi에서 true false로 문자열을 받아서 저장 (오라클은 boolean 지원X)
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
