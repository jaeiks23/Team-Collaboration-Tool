package SectionTodo;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SectionDAO;
import DTO.SectionDTO;
import utils.AlertFunc;

@WebServlet("/SectionTodo/addSection.do")
public class SectionAddController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int project_id = (int)session.getAttribute("pro_id");
		String user_id = (String)session.getAttribute("user_id");
		System.out.println("이게 왜 나와");
		SectionDTO dto = new SectionDTO();

		String content = req.getParameter("create_sec_Name");
		System.out.println(project_id);
		System.out.println(content);


		dto.setProject_id(project_id);
		dto.setSection_name(content);
		dto.setWrite_Id(user_id);
		SectionDAO dao = new SectionDAO();
		int result = dao.addSection(dto);
		dao.close();
		
		if(result==0) {
			System.out.println("실패");
		}
		else {
			AlertFunc.alertclose(resp, "섹션을 등록하셨습니다.");
		}
	}
}
