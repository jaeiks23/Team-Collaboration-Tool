package comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DTO.CommentDTO;
import utils.AlertFunc;

@WebServlet("/comment/write.do")
public class WriteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("qwehrjfeghuorjewlc;jfhewih");
		req.getRequestDispatcher("./comment/Write.jsp").forward(req, resp);
	}
	
}
