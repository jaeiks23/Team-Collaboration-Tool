package Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.MemberDTO;
import utils.AlertFunc;

@WebServlet("/Login/login.do")
public class LoginController extends HttpServlet {
	//DB 정보 가져오기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		HttpSession session = req.getSession();
		//검색
		String userId = req.getParameter("user_id");
		String userPw = req.getParameter("user_pw");
		
		dto = dao.getMember(userId, userPw);
		dao.close();
		if(!userId.equals(dto.getId()) || !userPw.equals(dto.getPw())) {
			AlertFunc.alertBack(resp, "아이디 또는 비밀번호가 틀렸습니다.");
		} else {
			session.setAttribute("user_id", userId);
			AlertFunc.alertLocation(resp, "../SelectPro.do");
		}
	}
}




















