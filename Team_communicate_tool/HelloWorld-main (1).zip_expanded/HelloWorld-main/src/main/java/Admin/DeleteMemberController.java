package Admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProjectDAO;


@WebServlet("/pro/test.do")
public class DeleteMemberController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		String memId = req.getParameter("name");
		HttpSession session = req.getSession();
		
		if(session.getAttribute("pro_id")==null) {
			//프로젝트 아이디 널체크
		}
		String projectId =  session.getAttribute("pro_id").toString();
		
		int res = dao.deleteMember(memId,projectId);
		dao.close();
		
		
	}
}
