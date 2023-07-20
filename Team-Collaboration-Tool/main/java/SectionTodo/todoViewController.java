package SectionTodo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TodoDAO;
import DTO.TodoDTO;

@WebServlet("/SectionTodo/todoView.do")
public class todoViewController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		int section_id = Integer.parseInt(req.getParameter("sect"));
//		int project_id = Integer.parseInt(req.getParameter("proc"));
//		System.out.println("두포스트 진입"+project_id);
//		TodoDAO dao = new TodoDAO();
//		List<TodoDTO> li = dao.viewTodo(section_id);
//		dao.close();
//		req.setAttribute("todoList", li);
//		req.getRequestDispatcher("../Section/test3.jsp").forward(req, resp);
//		System.out.println(li.toString());
//		PrintWriter writer = resp.getWriter();
//		
//
//		
//		//if(se)
	}
}
