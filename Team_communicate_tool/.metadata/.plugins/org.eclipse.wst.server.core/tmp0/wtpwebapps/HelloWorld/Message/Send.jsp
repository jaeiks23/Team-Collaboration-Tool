<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 보내기</title>
</head>
<body>

	<form action="../pro/send.do" method="post">
		<c:choose>
		<c:when test="${empty sendId}">
		제목 :
		<br> 
		<input type="text" name="title" style="width:90%" placeholder="제목을 입력해주세요" />
		</c:when>
		<c:otherwise>
		제목 : 
		<br>
		<input type="text" name="title" style="width:90%" value="Re:"/>
		</c:otherwise>
		</c:choose>
		<br><br>
		받는 사람 :  
		<br>
		<input type="text" name="receiveId" style="width:90%" placeholder="여러명에게 보낼 시 ,를 입력해주세요" value="${sendId}"/><br><br>
		<textarea style="width:90%; height:100px;" name="content" placeholder="내용을 입력해주세요"></textarea><br><br>
		<center><button type="submit" value="입력완료">보내기</button></center>
		
	</form>

</body>
<script type="text/javascript">

</script>
</html>