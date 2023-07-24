package Project;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DAO.ProjectDAO;
import DTO.MemberDTO;
import DTO.ProjectDTO;

@WebServlet("/SelectPro.do")
public class ProListContoller extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProjectDAO pro_dao = new ProjectDAO();
		MemberDAO mem_dao = new MemberDAO();
		
		HttpSession session = req.getSession();
		String id = session.getAttribute("user_id").toString();
		
		List<ProjectDTO> projectlist = pro_dao.ProList(id);
	
		List<MemberDTO> memlist = mem_dao.getMemlist();
		List<String> memidlist = mem_dao.getMemlistexme(id);
	
		
		session.setAttribute("memlist", memlist);
		session.setAttribute("projectlist", projectlist);
		session.setAttribute("memidlist", memidlist);
		
		pro_dao.close();
		mem_dao.close();
		req.getRequestDispatcher("./Project/SelProject.jsp").forward(req, resp);
		
	}

	
	
}
