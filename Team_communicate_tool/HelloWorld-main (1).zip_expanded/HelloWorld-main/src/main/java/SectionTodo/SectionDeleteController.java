package SectionTodo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SectionDAO;
import DAO.TodoDAO;

@WebServlet("/SectionTodo/SecDel.do")
public class SectionDeleteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int section_id = Integer.parseInt(req.getParameter("section_id"));
		int project_id = (int)session.getAttribute("pro_id");
		System.out.println(section_id);
		System.out.println(project_id);
		TodoDAO t_dao = new TodoDAO();
		SectionDAO s_dao = new SectionDAO();
		int s_result = 0;
		int t_result = t_dao.deleteTodoAll(section_id);
		t_dao.close();
		
		s_result = s_dao.deleteSection(project_id, section_id);
		s_dao.close();
	}

}
