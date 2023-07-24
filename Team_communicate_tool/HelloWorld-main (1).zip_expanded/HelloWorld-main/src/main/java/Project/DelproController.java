package Project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProjectDAO;
import utils.AlertFunc;

@WebServlet("/delpro.do")
public class DelproController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjectDAO dao = new ProjectDAO();
		
		HttpSession session = req.getSession();
		String proid = req.getParameter("proid");
		
		
		System.out.println(proid);

		
		//int  num = dao.CreatePro(Pro_Name,host_id,Mem_id,PRO_END_DATE);
		int  num = dao.DeletePro(proid);
		System.out.println(num);
		if(num !=0) {
			
			req.getRequestDispatcher("/maybe/SelProject.jsp").forward(req, resp);
		}
		else {
			AlertFunc.alertBack(resp, "프로젝트 생성 실패");
		}
	}
	
}
