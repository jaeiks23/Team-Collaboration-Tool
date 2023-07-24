package message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.messageDAO;

@WebServlet("/pro/keeping.do")
public class KeepingController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		messageDAO dao = new messageDAO();
		
		String mnum = req.getParameter("mnum");
		
		int res = dao.sendKeepList(mnum);
		
		resp.sendRedirect("../pro/receiveList.do");
		
	}
}
