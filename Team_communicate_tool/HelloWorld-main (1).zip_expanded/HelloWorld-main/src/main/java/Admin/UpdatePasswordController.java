package Admin;

import DAO.MemberDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DTO.MemberDTO;
import utils.AlertFunc;

@WebServlet("/Controller/Update.do")
public class UpdatePasswordController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 입력된 파라미터 값들을 가져온다.
		
    String currentPassword = req.getParameter("currentPassword");
    String newPassword = req.getParameter("newPassword");
    HttpSession session = req.getSession();
    String confirmPassword = req.getParameter("confirmPassword");
    String userid = session.getAttribute("user_id").toString();
    
    System.out.println(currentPassword+newPassword+confirmPassword);
    MemberDTO dto = new MemberDTO();
    
    if(!newPassword.equals(confirmPassword)) {
    	AlertFunc.alertLocation(resp, "비밀번호 확인이 일치하지 않습니다.", "../Admin/MyPage.jsp");	
    	return;
    }
 
    
    MemberDAO dao = new MemberDAO();
 
    int result = dao.updatePassword(userid, currentPassword, newPassword);
    dao.close();
    
    System.out.println("result : "+result);
    if(result==1) {
    	AlertFunc.alertLocation(resp, "비밀번호 변경 완료.", "../Mypage.do");
    }else {
    	AlertFunc.alertBack(resp, "현재 비밀번호와 일치하지 않습니다.");
    	//AlertFunc.alertLocation(resp, "현재 비밀번호와 일치하지 않습니다.", "../MyPage/MyPage.jsp");
    }
    session.invalidate();
	}
}

	


