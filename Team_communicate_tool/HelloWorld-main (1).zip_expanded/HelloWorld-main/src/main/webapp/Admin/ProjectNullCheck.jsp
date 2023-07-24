<%@page import="utils.AlertFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
if(session.getAttribute("projectId")==null) {
	AlertFunc.alertBack("프로젝트 아이디가 없습니다.", out);
}
%>