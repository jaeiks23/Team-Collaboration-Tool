<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<form action="../SectionTodo/addSection.do" method="post">
		<table>
			<input name="work_content" type="text" placeholder="섹션 명을 입력해주세요.">
			<input type="submit" value="저장">
		</table>
		</form>
	</div>
	
	
</body>
</html>