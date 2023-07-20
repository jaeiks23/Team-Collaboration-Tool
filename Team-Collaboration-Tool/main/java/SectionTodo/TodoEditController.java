package SectionTodo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TodoDAO;
import DTO.TodoDTO;
import DTO.TodoJoinDTO;
import utils.AlertFunc;

@WebServlet("/SectionTodo/editTodo.do")
public class TodoEditController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String edit_todo_content = req.getParameter("edit_todo");
		int edit_todo_id = Integer.parseInt(req.getParameter("edit_todo_id"));
		String edit_PIC = req.getParameter("edit_PIC_name");
		String edit_end_date = req.getParameter("edit_end_date");
		int edit_Section = Integer.parseInt(req.getParameter("edit_Section"));
		String edit_status = req.getParameter("edit_status");
		String edit_info = req.getParameter("edit_info");
		System.out.println(edit_todo_id);
		System.out.println(edit_todo_content);
		System.out.println(edit_PIC);
		System.out.println(edit_end_date);
		System.out.println(edit_Section);
		System.out.println(edit_status);
		System.out.println(edit_info);
		
		TodoDTO dto = new TodoDTO();
		dto.setTodo_Id(edit_todo_id);
		dto.setTodo_Content(edit_todo_content);
		dto.setPart_Id(edit_PIC);
		dto.setTodo_End_Date(edit_end_date);
		dto.setSection_Id(edit_Section);
		dto.setTodo_Status(edit_status);
		dto.setTodo_info(edit_info);
		
		TodoDAO dao = new TodoDAO();
		int result = dao.UpdateTodo(dto);
		dao.close();
		if(result>0) {
			//req.getRequestDispatcher("/SectionTodo/SectionView.do").forward(req, resp);
			AlertFunc.alertLocation(resp, "작업이 등록되었습니다.", "../SectionTodo/SectionView.do");
			
		}
		else {
			AlertFunc.alertBack(resp, "잘못된 이유로 변경에 실패했습니다.");
		}
		
	}
}
