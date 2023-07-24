<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 선택 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script>
let selectedValue; // 선택한 값을 저장할 변수

//각 셀(td)에 클릭 이벤트 리스너를 추가합니다.
$(document).ready(function(){
	$('.selproject').on('click', function() {
		
	    let value = $(this).find('td:first-child input').val();
	    selectedValue = value;
	});
	$("#selbutton").on('click',function(){
		if(selectedValue == undefined){
			alert("선택해주세요");
		}
		let pass = "test/Main.jsp?pro_id="+selectedValue;
		location.href=pass;
		
	});
	
});




</script>
</head>
<body>

		<a>프로젝트</a>
		<table style="width: 100%">
<c:choose>
	<c:when test ="${ empty projectlist}">
	
		<tr>
			<td colspan="2" align="center">등록된 게시물이 없습니다.</td>
		</tr>
		
	</c:when>
	<c:otherwise>
		<c:forEach items="${projectlist}" var="list" varStatus="stat">
		
			<tr align="center" class ="selproject"  ondblclick="location.href='test/Main.jsp?pro_id=${list.pro_id}';" >
				<td><input type="hidden" class ="selpro" name="pronum" value="${list.pro_id}"/>${list.pro_name}</td>
				<td >${list.pro_host }</td>
				<td >${list.pro_end_date }</td>
			</tr>
			
		</c:forEach>
	</c:otherwise>
</c:choose>
	
		<tr>
			<td>
				<button type="button" onclick="location.href='test/CreatePro.jsp';">생성하기</button>
			</td>
		
			<td>
				<button type ="button" id="selbutton"  >접속</button>
			</td>
		</tr>
		</table>


</body>
</html>