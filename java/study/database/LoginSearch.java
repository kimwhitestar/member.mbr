package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginSearch")
public class LoginSearch extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String)session.getAttribute("sMid");
		
		LoginDAO dao = new LoginDAO();
		LoginVO vo = dao.search(mid);
		PrintWriter out = response.getWriter();

		if (null == vo) {//자료를 못 찾았음
			out.println("<script>");
			out.println("alert('회원정보를 찾지 못했습니다');");
			out.println("location.href='"+request.getContextPath()+"/study/database/loginMain.jsp'");//이전페이지 내용유지하며 이동할까?
			out.println("</script>");
		}
		else { //자료를 찾았음
			request.setAttribute("vo", vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/study/database/loginSearch.jsp");
			dispatcher.forward(request, response);
		}
	}
}
