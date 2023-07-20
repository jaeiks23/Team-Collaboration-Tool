package SectionTodo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import DAO.ProjectDAO;
import DAO.SectionDAO;
import DAO.TodoDAO;
import DTO.ProjectDTO;
import DTO.SectionDTO;
import DTO.TodoJoinDTO;

@WebServlet("/SectionTodo/SectionView.do")
public class SectionViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//int a = Integer.parseInt(req.getParameter("pro_id"));
		int a = 0001;
		req.getSession().setAttribute("pro_id", a);
		System.out.println(req.getSession().getAttribute("pro_id"));
		int session_pro_id = (int)req.getSession().getAttribute("pro_id");
		List<Integer> section_id = new ArrayList<>(); 
		SectionDAO S_dao = new SectionDAO();
		ProjectDAO P_dao = new ProjectDAO();
		
		TodoDAO T_dao = new TodoDAO();
		
		List<SectionDTO> sec_li = S_dao.getSection(session_pro_id);
		List<ProjectDTO> pro_li = P_dao.getMember(session_pro_id);
		List<TodoJoinDTO> todo_li = T_dao.viewTodo();
		
		S_dao.close();
		P_dao.close();
		T_dao.close();
		
		req.setAttribute("sectionList", sec_li);
		req.setAttribute("projectMem", pro_li);
		req.setAttribute("todoList", todo_li);
		req.setAttribute("projectID", a); //이거 없어도 되요 session에서 계속 뽑아서 쓸거라 로그아웃할때 	session.removeAttribute(getServletName());이거로 지워줄거에요
		//req.setAt

		
		req.getRequestDispatcher("../Section/test3.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
