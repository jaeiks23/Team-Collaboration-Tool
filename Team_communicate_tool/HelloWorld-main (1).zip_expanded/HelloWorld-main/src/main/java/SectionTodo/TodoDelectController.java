package SectionTodo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TodoDAO;
import utils.AlertFunc;

@WebServlet("/SectionTodo/todoDelete.do")
public class TodoDelectController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int todo_id = Integer.parseInt(req.getParameter("todo_id"));
		TodoDAO dao = new TodoDAO();
		int result = dao.deleteTodo(todo_id);
		dao.close();
		if(result!=1) {
			AlertFunc.alertclose(resp, "삭제를 실패했습니다.");
		}
		System.out.println(req.getParameter("todo_id"));
	}
}
