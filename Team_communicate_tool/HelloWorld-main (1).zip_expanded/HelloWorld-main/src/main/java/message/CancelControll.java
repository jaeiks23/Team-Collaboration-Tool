package message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.messageDAO;

@WebServlet("/pro/cancel.do")
public class CancelControll extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		messageDAO dao = new messageDAO();
		String mnum = req.getParameter("mnum");
		int res = dao.cancel(mnum);
		resp.sendRedirect("../pro/keepList.do");
	}
	
}
