package Main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BoardDAO;
import DAO.TodoDAO;
import DTO.BoardDTO;
import DTO.TodoDTO;

@WebServlet("/ConnectMain.do")
public class MainConnectController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션으로 받은 변수
		HttpSession session = req.getSession();
		int project_id = (int)session.getAttribute("pro_id");
		String user_id = (String)session.getAttribute("user_id");

		TodoDAO t_dao = new TodoDAO();
		List<TodoDTO> t_dto = t_dao.viewTodo(project_id, user_id, "END");
		t_dao.close();
		BoardDAO b_dao = new BoardDAO();
		List<BoardDTO> b_dto = b_dao.getListMain(project_id);

		req.setAttribute("mainTodo", t_dto);
		req.setAttribute("mainBoard", b_dto);
		req.getRequestDispatcher("/Main/Main.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션으로 받은 변수
		HttpSession session = req.getSession();
		int project_id = Integer.parseInt(req.getParameter("pro_id"));
		String user_id = (String) session.getAttribute("user_id");
		session.setAttribute("pro_id", project_id);

		TodoDAO t_dao = new TodoDAO();
		List<TodoDTO> t_dto = t_dao.viewTodo(project_id, user_id, "END");
		t_dao.close();
		BoardDAO b_dao = new BoardDAO();
		List<BoardDTO> b_dto = b_dao.getListMain(project_id);

		req.setAttribute("mainTodo", t_dto);
		req.setAttribute("mainBoard", b_dto);
		req.getRequestDispatcher("/Main/Main.jsp").forward(req, resp);

	}
}
