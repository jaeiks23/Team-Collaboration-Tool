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

import DAO.BoardDAO;
import DAO.CommentDAO;
import DTO.BoardDTO;
import DTO.CommentDTO;
import utils.PagingUtil;


@WebServlet("/board/view.do")
public class ViewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시물 읽어오기
		BoardDAO dao = new BoardDAO();
		String b_id = req.getParameter("b_id");
		dao.updateVisitCount(b_id);	// 조회수 증가
		BoardDTO dto = dao.getView(b_id);	// 내용 읽어오기
		dao.close();
		
		// 줄바꿈
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		// 댓글
		CommentDAO commentDAO = new CommentDAO();
		
		// 변수 저장
		Map<String, Object> map = new HashMap<>();

		// 전체 댓글 수 파악하기
		int totalCount = commentDAO.countAll(b_id);
		
		// 뎃글 페이지
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("PAGE_SIZE"));
		int pageBlock = Integer.parseInt(application.getInitParameter("PAGING_BLOCK"));
		pageSize = 5;
		
		// 페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}
		
		// 목록에서 보여줄 게시물 범위 계산
		int start = (pageNum-1)*pageSize+1;	// 전페이지의 끝에서 +1 현재페이지
		int end = pageNum*pageSize;
		map.put("b_id", b_id);
		map.put("start", start);
		map.put("end", end);
		map.put("noticeFlag", "0");
		
		// DB에서 게시물 정보 읽기
		List<CommentDTO> commentList = commentDAO.getComment(map);
		commentDAO.close();
		
		// 뷰에 값을 전달해 줄 것을 정리하기
		String pagingStr = PagingUtil.pagingCenter(totalCount, pageSize, pageBlock, pageNum, "../board/view.do", "b_id="+b_id );
		

		map.put("pagingStr", pagingStr);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);

		
		// 게시물 뷰로 전달
		req.setAttribute("map", map);
		req.setAttribute("dto", dto);
		req.setAttribute("commentList", commentList);
		req.getRequestDispatcher("/Board/View.jsp").forward(req, resp);
	}
}
