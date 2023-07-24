package message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.messageDAO;
import DTO.messageDTO;

@WebServlet("/pro/viewcon.do")
public class ViewCont extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		messageDAO dao = new messageDAO();
		String mnum = req.getParameter("mnum");
		messageDTO dto = new messageDTO();
		
		String id = (String)session.getAttribute("user_id");
		
		
		if(req.getParameter("mode").equals("send")) {
			messageDTO dto1 = dao.sendview(dto,mnum);
			dao.close();
			req.setAttribute("dto1", dto1);
		}else {
			messageDTO dto1 = dao.receiveview(dto,mnum);
			dao.close();
			req.setAttribute("dto1", dto1);
		}
		req.getRequestDispatcher("../Message/View.jsp").forward(req, resp);
		
	}
	
}
