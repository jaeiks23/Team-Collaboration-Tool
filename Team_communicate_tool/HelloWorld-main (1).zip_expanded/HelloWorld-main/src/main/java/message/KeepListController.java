package message;

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

import DAO.messageDAO;
import DTO.messageDTO;
import utils.AlertFunc;
import utils.PagingUtil;

@WebServlet("/pro/keepList.do")
public class KeepListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		messageDAO dao = new messageDAO();
		messageDTO dto = new messageDTO();
		HttpSession session = req.getSession();
		
		
		if(session.getAttribute("user_id")==null) {
			AlertFunc.alertLocation(resp, "로그인 해주세요.","../JSPProjectMessage/notlogin.jsp");//추후 로그인 화면으로 연동 바람
			return;
		}
		
		String id = session.getAttribute("user_id").toString();
		
		Map<String,Object> map = new HashMap<>();
		String searchType=req.getParameter("user");
		String searchStr = req.getParameter("search");
		System.out.println(searchType);
		System.out.println(searchStr);
		if(searchStr!=null) {
			map.put("searchType", searchType);
			map.put("searchStr", searchStr);
		}
		
		int totalCount=dao.keepCountAll(map,id);
		
		ServletContext application = getServletContext();
		int pageSize = 20;
		int pageBlock = Integer.parseInt(application.getInitParameter("PAGING_BLOCK"));
	
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp!=null && !pageTemp.equals("")) {
			pageNum=Integer.parseInt(pageTemp);
		}
		
		int start =(pageNum-1)*pageSize+1;
		int end = pageNum*pageSize;
		map.put("start", start);
		map.put("end", end);
		
		List<messageDTO> keepList = dao.keepListMessage(id,map);
		dao.close();
		String pagingStr = PagingUtil.pagingCenter(totalCount, pageSize, end, pageNum, "../pro/keepList.do");
		
		System.out.println(totalCount);
		System.out.println(pageSize);
		System.out.println(end);
		System.out.println(pageNum);
		
		map.put("pagingStr", pagingStr);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		

		req.setAttribute("keepList", keepList);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/Message/KeepList.jsp").forward(req, resp);
	}
	
}
