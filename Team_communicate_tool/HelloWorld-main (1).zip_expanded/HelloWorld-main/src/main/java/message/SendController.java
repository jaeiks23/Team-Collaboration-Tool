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
import utils.AlertFunc;

@WebServlet("/pro/send.do")
public class SendController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		String sendId = req.getParameter("sendId");
		req.setAttribute("sendId", sendId);
		req.getRequestDispatcher("../Message/Send.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("user_id");
		
		messageDTO dto = new messageDTO();
		dto.setMsg_title(req.getParameter("title"));
		dto.setMsg_content(req.getParameter("content"));
		dto.setMsg_sendId(id);
		String receiveId = req.getParameter("receiveId");
		dto.setMsg_receiveId(receiveId);
		messageDAO dao = new messageDAO();
		int result = dao.SendMessage(dto);
		dao.close();
		
		if(result==1) {
			AlertFunc.alertLocation(resp, "쪽지가 전송되었습니다.", "../Message/Sending.jsp");
			
		}else {
			AlertFunc.alertLocation(resp, "쪽지 송신에 실패했습니다.", "../Message/Sending.jsp");
		}
		
		
	}
	
}
