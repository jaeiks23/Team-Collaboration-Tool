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

@WebServlet("/Login/findPw.do")
public class FindPwController extends HttpServlet {
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("pw 수정 test");
	    MemberDTO dto = new MemberDTO();
	    MemberDAO dao = new MemberDAO();
	    
		String name = request.getParameter("searchName");
		String id = request.getParameter("searchId");
		String email = request.getParameter("searchEmail");
		int result =Integer.parseInt(request.getParameter("result"));
		
		dto = dao.FindPass(name, id, email);
		dao.close();
		
		if(dto.getPw() != null) {
			if(result == 1) {
				AlertFunc.alertLocation(response, "해당 정보의 비밀번호는 " + dto.getPw() + "입니다.", "../Login/LoginPage.jsp");
			} else if(dto.getPw() == null){
				AlertFunc.alertBack(response, "인증이 제대로 되지 않았습니다.");
			}
		}else {
			AlertFunc.alertBack(response, "정보가 없습니다 다시 확인해주세요");
		}
	}
}








































