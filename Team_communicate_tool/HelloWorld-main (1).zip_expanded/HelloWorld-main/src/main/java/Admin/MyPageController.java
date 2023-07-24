package Admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.MemberDTO;

@WebServlet("/Mypage.do")
public class MyPageController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		MemberDAO dao = new MemberDAO();
		
		
		
		MemberDTO dto = dao.getMyInfo(user_id);
		
		req.setAttribute("MyInfo", dto);
		
		req.getRequestDispatcher("./Admin/MyPage.jsp").forward(req, resp);
		
		
	}
}
