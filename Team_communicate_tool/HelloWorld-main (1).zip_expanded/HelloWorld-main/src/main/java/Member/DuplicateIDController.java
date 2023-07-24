package Member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.MemberDTO;

@WebServlet("/Login/checkID.do")
public class DuplicateIDController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		
		List<String> list = dao.CheckSameID();
		session.setAttribute("sameID", list);
		
		dao.close();
		req.getRequestDispatcher("../Login/CreateMem.jsp").forward(req, resp);
	}
}
