package Project;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProjectDAO;
import utils.AlertFunc;

@WebServlet("/createpro.do")
public class CreatePro extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjectDAO pro_dao = new ProjectDAO();
		
		HttpSession session = req.getSession();
		String my_id = session.getAttribute("user_id").toString();
		String Pro_Name = req.getParameter("project_Name");
		//String Mem_id = req.getParameter("Mem_id");
		String Mem_id = (String) req.getParameter("idlist");
		String host_id = session.getAttribute("user_id").toString();
		//String host_id = "111";
		String PRO_END_DATE = req.getParameter("PRO_E_DATE");
		
		System.out.println(my_id);
		System.out.println(Pro_Name);
		System.out.println(Mem_id);
		System.out.println(host_id);
		System.out.println(PRO_END_DATE);
		//int  num = dao.CreatePro(Pro_Name,host_id,Mem_id,PRO_END_DATE);
		int  num = pro_dao.CreatePro(Pro_Name,host_id,Mem_id,PRO_END_DATE);

		pro_dao.close();
		if(num !=0) {
			AlertFunc.alertLocation(resp, "성공", "./SelectPro.do");
		}
		else {
			AlertFunc.alertBack(resp, "프로젝트 생성 실패");
		}
	}
	
}
