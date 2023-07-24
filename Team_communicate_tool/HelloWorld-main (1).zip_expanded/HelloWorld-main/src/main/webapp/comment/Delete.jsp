<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="DAO.CommentDAO"%>
<%@ page import="DTO.CommentDTO"%>
<%@ page import="utils.AlertFunc"%>

<%
String com_id = request.getParameter("com_id");
String b_id = request.getParameter("b_id");
CommentDAO dao = new CommentDAO();
int res = dao.deleteComment(com_id);


dao.close();

if (res == 1) {
	AlertFunc.alertLocation("댓글 삭제 완료", "../board/view.do?b_id="+b_id, out);
	
} else {
	AlertFunc.alertBack("삭제 실패", out);
}
%>