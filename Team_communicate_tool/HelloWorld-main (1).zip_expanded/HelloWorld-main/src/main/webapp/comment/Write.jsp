<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="DAO.CommentDAO"%>
<%@ page import="DTO.CommentDTO"%>
<%@ page import="utils.AlertFunc"%>

<%
CommentDAO dao = new CommentDAO();
CommentDTO dto = new CommentDTO();
String b_id = request.getParameter("b_id").toString();
request.setCharacterEncoding("UTF-8");
dto.setB_id(b_id);
dto.setCom_content(request.getParameter("com_content").toString());
dto.setWriter_id(request.getParameter("writer_id").toString());
int res = dao.insertComment(dto);

dao.close();

if (res == 1) {
	AlertFunc.alertLocation("댓글 등록 완료", "../board/view.do?b_id="+b_id, out);
	
} else {
	AlertFunc.alertBack("등록 실패", out);
}
%>