package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import utils.FileUtil;

@WebServlet("/board/download.do")
public class DownloadController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//매개변수 받기
		String ofile = req.getParameter("ofile");//원본 파일명
		String nfile = req.getParameter("nfile");//저장된 파일명
		String b_id = req.getParameter("b_id");//게시물 일련번호
		//파일 다운로드
		FileUtil.downloadFile(req,resp,"/Storage",nfile,ofile);
		//게시물의 다운로드 수 1증가
		BoardDAO dao = new BoardDAO();
		dao.updateDowncount(b_id);
		dao.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String b_id = req.getParameter("b_id");
		BoardDAO dao = new BoardDAO();
		int downcount = dao.getDowncount(b_id);
		dao.close();
		
		PrintWriter out = resp.getWriter();
		out.print(downcount);
	}
}