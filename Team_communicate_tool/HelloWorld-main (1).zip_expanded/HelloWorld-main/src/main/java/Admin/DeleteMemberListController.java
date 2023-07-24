package Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DAO.ProjectDAO;
import DTO.ProjectDTO;

@WebServlet("/del/delmemlist.do")
public class DeleteMemberListController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		HttpSession session = req.getSession();
		
		if(session.getAttribute("pro_id")==null) {
			//프로젝트 아이디 널체크
		}
		int projectId = (int)session.getAttribute("pro_id");
		System.out.println(projectId);
		List<ProjectDTO> memdelList = dao.memdelList(projectId);
		dao.close();
		req.setAttribute("memdelList", memdelList);
		System.out.println(memdelList);
		req.getRequestDispatcher("../Admin/DeleteMember.jsp").forward(req, resp);
	}
	
}
