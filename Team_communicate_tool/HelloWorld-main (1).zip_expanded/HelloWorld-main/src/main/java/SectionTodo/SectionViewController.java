package SectionTodo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import DAO.ProjectDAO;
import DAO.SectionDAO;
import DAO.TodoDAO;
import DTO.ProjectDTO;
import DTO.SectionDTO;
import DTO.TodoJoinDTO;

@WebServlet("/SectionView.do")
public class SectionViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//int a = Integer.parseInt(req.getParameter("pro_id"));
		HttpSession session = req.getSession();
		int pro_id = (int)session.getAttribute("pro_id");
		String user_id = (String)session.getAttribute("user_id") ;
		List<Integer> section_id = new ArrayList<>(); 
		SectionDAO S_dao = new SectionDAO();
		ProjectDAO P_dao = new ProjectDAO();
		
		TodoDAO T_dao = new TodoDAO();
		
		List<SectionDTO> sec_li = S_dao.getSection(pro_id);
		List<ProjectDTO> pro_li = P_dao.getMember(pro_id);
		List<TodoJoinDTO> todo_li = T_dao.viewTodo();
		
		S_dao.close();
		P_dao.close();
		T_dao.close();
		
		req.setAttribute("sectionList", sec_li);
		req.setAttribute("projectMem", pro_li);
		req.setAttribute("todoList", todo_li);
		

		
		req.getRequestDispatcher("./Section/SectionPage.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
