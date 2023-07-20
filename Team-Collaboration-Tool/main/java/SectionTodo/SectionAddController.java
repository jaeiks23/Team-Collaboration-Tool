package SectionTodo;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SectionDAO;
import DTO.SectionDTO;
import utils.AlertFunc;

@WebServlet("/SectionTodo/addSection.do")
public class SectionAddController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("이게 왜 나와");
		SectionDTO dto = new SectionDTO();
		int project_id = 0001;//Integer.parseInt(req.getParameter("pro_id"));
		//project_id= 0001;
		String content = req.getParameter("create_sec_Name");
		String writer = "test1";
		System.out.println(project_id);
		System.out.println(content);
		System.out.println(writer);

		dto.setProject_id(project_id);
		dto.setSection_name(content);
		dto.setWrite_Id(writer);
		SectionDAO dao = new SectionDAO();
		int result = dao.addSection(dto);
		dao.close();
		
		if(result<0) {
			System.out.println("실패");
		}
	}
}
