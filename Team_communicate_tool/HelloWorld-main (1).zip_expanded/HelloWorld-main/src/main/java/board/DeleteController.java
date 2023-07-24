package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import utils.AlertFunc;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=====" + req.getParameter("noticeFlag"));
		String b_id = req.getParameter("b_id");
		String noticeFlag = req.getParameter("noticeFlag");

		BoardDAO dao = new BoardDAO();
		int res = dao.deletePost(b_id);

		dao.close();

		if (res == 1) {
			if (noticeFlag.equals("1")) {
				AlertFunc.alertLocation(resp, "공지 삭제", "notice.do");
			} else {
				AlertFunc.alertLocation(resp, "공지 삭제", "list.do");
			}
		} else {
			AlertFunc.alertBack(resp,"삭제 실패");
		}
		//req.getRequestDispatcher("/Board/Delete.jsp").forward(req, resp);

	}
}
