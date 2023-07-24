package board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import DAO.BoardDAO;
import DTO.BoardDTO;
import utils.AlertFunc;
import utils.FileUtil;

@WebServlet("/board/edit.do")
public class EditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String b_id = req.getParameter("b_id");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getView(b_id);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/Board/Edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// [파일 업로드 처리]

		// 업로드 디렉토리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/Storage");

		// 파일 업로드를 위한 기본정보
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));

		// 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if (mr == null) {
			// 파일 업로드 실패하면
			AlertFunc.alertBack(resp, "첨부파일 업로드 실패");
			return;
		}

		// DB 정보 저장
		String b_id = mr.getParameter("b_id");
		String preOfile = mr.getParameter("preOfile");
		String preNfile = mr.getParameter("preNfile");

		HttpSession session = req.getSession();
//		String pass = (String) session.getAttribute("pass");

		// 폼값을 DTO에 저장
		BoardDTO dto = new BoardDTO();
		dto.setB_id(b_id);
		dto.setWriter_id(mr.getParameter("writer_id"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		System.out.println(mr.getParameter("writer_id"));
		System.out.println(mr.getParameter("title"));
		System.out.println(mr.getParameter("content"));
		

		// 원본 파일명과 수정된 파일명
		String filename = mr.getFilesystemName("ofile");
		if (filename != null) {
			// 새 파일명 생성
			String nfname = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = filename.substring(filename.lastIndexOf("."));
			String newFileName = nfname + ext;

			// 파일명 변경
			File oldFile = new File(saveDirectory + File.separator + filename);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			dto.setOfile(filename); // 원래 파일 이름
			dto.setNfile(newFileName); // 서버에 저장된 파일 이름

			// 기존 파일 삭제
			FileUtil.deleteFile(req, "/Storage", preNfile);
		} else {
			dto.setOfile(preOfile);
			dto.setNfile(preNfile);
		}

		// DAO를 통해 DB에 내용 저장
		BoardDAO dao = new BoardDAO();
		int result = dao.updatePost(dto);
		dao.close();

		// 성공 여부
		if (result == 1) { // 성공
			session.removeAttribute(null);
			resp.sendRedirect("../board/view.do?b_id=" + b_id);
		} else {
			AlertFunc.alertLocation(resp, "수정되지 않았습니다", "../board/view.do?b_id=" + b_id);
		}
		

	}

}
