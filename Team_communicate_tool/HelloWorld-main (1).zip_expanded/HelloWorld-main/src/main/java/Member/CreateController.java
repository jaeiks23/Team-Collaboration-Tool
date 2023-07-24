package Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.MemberDTO;
import utils.AlertFunc;

@WebServlet("/Login/createMem.do")
public class CreateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Login/CreateMem.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();

		dto.setName(req.getParameter("regName"));
		dto.setId(req.getParameter("regID"));
		dto.setPw(req.getParameter("PW1"));
		dto.setEmail(req.getParameter("regEmail"));
		
		int result = 0;	
		result=dao.createMember(dto);
		dao.close();
		
		if(result == 1) {
			AlertFunc.alertLocation(resp, "회원가입 완료", "../Login/LoginPage.jsp");
		}
	}
}


























