package Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DTO.MemberDTO;

@WebServlet("/ChangePassword.do")
public class ChangePwController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("../Team2/Mypage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		//Session객체 idObj = session.getAttribute("어쩌구");
		String id = (String)session.getAttribute("user_id");
		MemberDAO dao = new MemberDAO();
		//MemberDTO dto = dao.getMemberInfo(id);
		
		String oldPw = req.getParameter("oldPw");
		String newPw = req.getParameter("newPw");
		String checkPw = req.getParameter("checkPw");
		PrintWriter out = resp.getWriter();
		
		
//		if(dto.getPw()!=oldPw) {
//			out.println("<script>");
//			out.println("alert('기존 비밀번호가 일치하지 않습니다');");
//			out.println("location.href='../ChangePassword.do';");
//			out.println("</script>");	
//		}else {
//			if(newPw!=checkPw) {
//				out.println("<script>");
//				out.println("alert('바꾸실 비밀번호가 일치하지 않습니다');");
//				out.println("location.href='../ChangePassword.do';");
//				out.println("</script>");
//			}else {
//				out.println("<script>");
//				out.println("alert('비밀번호가 변경되었습니다.');");
//				out.println("</script>");
//				dao.changePw(dto.getId(), newPw);
//				resp.sendRedirect("../Home.do"); // 집으로..
//			}
//		}

	}
}
