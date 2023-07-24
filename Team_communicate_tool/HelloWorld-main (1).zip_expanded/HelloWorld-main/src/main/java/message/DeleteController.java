package message;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.messageDAO;

@WebServlet("/pro/delete.do")
public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mnum = req.getParameter("mnum");
		
		String[]mnums=mnum.split(",");
		
		System.out.println(mnum);
		
		System.out.println(Arrays.toString(mnums));
		
		messageDAO dao = new messageDAO();
		
		
		if(req.getParameter("mode").equals("send")) {
			for(String mnumres : mnums) {
				int res = dao.senddeleteMessage(mnumres);
			}
			dao.close();
			resp.sendRedirect("../pro/SendList.do");
		}else if(req.getParameter("mode").equals("receive")) {
			for(String mnumres : mnums) {
				int res = dao.receivedeleteMessage(mnumres);
			}
			dao.close();
			resp.sendRedirect("../pro/receiveList.do");
		}else {
			for(String mnumres : mnums) {
				int res = dao.receivedeleteMessage(mnumres);
			}
			dao.close();
			resp.sendRedirect("../pro/keepList.do");
		}
		
		
	}
	
}
