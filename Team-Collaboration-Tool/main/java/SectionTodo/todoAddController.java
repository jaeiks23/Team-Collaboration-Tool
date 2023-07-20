package SectionTodo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TodoDAO;
import DTO.TodoDTO;
import utils.AlertFunc;

@WebServlet("/SectionTodo/addTodo.do")
public class todoAddController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int section_id = Integer.parseInt(req.getParameter("section_id_todo"));
		String content = req.getParameter("work_content");
		String pic = req.getParameter("PIC");
		String status = req.getParameter("work_status");
		String rank = req.getParameter("work_rank");
		String S_date = req.getParameter("work_start");
		String E_date = req.getParameter("work_end");
		
		
		TodoDTO dto = new TodoDTO();
		
		dto.setSection_Id(section_id);
		dto.setTodo_Content(content);
		dto.setPart_Id(pic);
		dto.setTodo_Status(status);
		dto.setTodo_Rank(rank);
		dto.setTodo_Start_Date(S_date);
		dto.setTodo_End_Date(E_date);
		
		TodoDAO dao = new TodoDAO();
		
		int result = dao.addTodo(dto);
		dao.close();
		
		
		if(result==0) {
			System.out.println("실패");
		}
		else {
			AlertFunc.alertLocation(resp, "작업이 등록되었습니다.", "../SectionTodo/SectionView.do?pro_id=0001");
		}
	}
}
