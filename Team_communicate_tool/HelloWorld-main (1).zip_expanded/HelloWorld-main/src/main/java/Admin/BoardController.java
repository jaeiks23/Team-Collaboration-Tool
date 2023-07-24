package Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DdateDAO;
import DTO.DdateDTO;
import utils.AlertFunc;

@WebServlet("/updateDdate.do")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("pro_id", 1111);

        int pro_id = (int) session.getAttribute("pro_id");
        String endDate = request.getParameter("end_date");
     
        DdateDAO dao = new DdateDAO();
        int result = dao.updateDdate(pro_id, endDate);
        
        DdateDTO dto = dao.getDdate(pro_id);
        dao.close();
        System.out.println(dto.getPro_e_date());
        if (result == 1) {
        	request.setAttribute("dto", dto);
            request.getRequestDispatcher("/Team/Update.jsp").forward(request, response);
        } else {
            AlertFunc.alertBack(response, "날짜 업데이트 실패");
        }
    }
}