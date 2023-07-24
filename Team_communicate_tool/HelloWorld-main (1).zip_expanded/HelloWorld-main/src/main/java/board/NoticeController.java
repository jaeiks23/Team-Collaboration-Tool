package board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BoardDAO;
import DTO.BoardDTO;
import utils.PagingUtil;

@WebServlet("/board/notice.do")
public class NoticeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// DAO 생성
		BoardDAO dao = new BoardDAO();

		// 변수 저장
		Map<String, Object> map = new HashMap<>();

		// 게시판 유형 선택 (공지 체크)
//		map.put("noticeFlag",req.getParameter("noticeFlag"));

		// 검색
		String searchType = req.getParameter("searchType");
		String searchStr = req.getParameter("searchStr");
		if (searchStr != null) {
			map.put("searchType", searchType);
			map.put("searchStr", searchStr);
		}
		// 전체 게시물 개수 파악하기
		int totalCount = dao.countAll(map);

		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("PAGE_SIZE"));
		int pageBlock = Integer.parseInt(application.getInitParameter("PAGING_BLOCK"));
		pageSize = 3;

		// 페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}

		// 목록에서 보여줄 게시물 범위 계산
		int start = (pageNum - 1) * pageSize + 1; // 전페이지의 끝에서 +1 현재페이지
		int end = pageNum * pageSize;
		map.put("start", start);
		map.put("end", end);
		map.put("noticeFlag", "1");

		// DB에서 게시물 정보 읽기
		List<BoardDTO> boardList = dao.getListPage(map);
		dao.close();

		// 뷰에 값을 전달해 줄 것을 정리하기
		String pagingStr = PagingUtil.pagingCenter(totalCount, pageSize, pageBlock, pageNum, "../board/notice.do", null);
		
		map.put("pagingStr", pagingStr);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		System.out.println(boardList.size());
		// 뷰에 최종 전달
		
		req.setAttribute("boardList", boardList);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/Board/Notice.jsp").forward(req, resp);
	}
}
