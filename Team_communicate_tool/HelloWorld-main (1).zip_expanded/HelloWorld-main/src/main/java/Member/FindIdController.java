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

@WebServlet("/Login/findId.do")
public class FindIdController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		//input에 입력한 값 불러오고
		String searchName = req.getParameter("search_name");
		String searchEmail = req.getParameter("search_email");

		dto = dao.FindId(searchName, searchEmail);
		dao.close();

		if(dto.getId() != null) {
			AlertFunc.alertLocation(resp, "해당 아이디는 " + dto.getId() + "입니다.", "../Login/LoginPage.jsp");
		} else {	
			AlertFunc.alertBack(resp, "해당하는 아이디가 없습니다.");
		}
	}
}





















