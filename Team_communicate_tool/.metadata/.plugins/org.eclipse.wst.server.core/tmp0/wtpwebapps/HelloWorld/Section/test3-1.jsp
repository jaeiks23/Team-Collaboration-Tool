<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<%@ include file="TodoPopUp.jsp" %>
		<button onclick="openModal()">+ 작업 추가</button>
		<ul>
		<c:choose>
			<c:when test="${empty sectionList} ">
				<li>
					p
				</li>
			</c:when>
			<c:otherwise>
				<c:forEach items="${sectionList}" var="list" varStatus="stat">
						<li valign="top"><button id="showTodo${list.section_id}" type="button" name="${list.section_id}" value="${list.section_id}"/>${list.section_name}</li>
						
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</ul>
	</div>
	
<script
  src="https://code.jquery.com/jquery-3.6.4.min.js"
  integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
  crossorigin="anonymous"></script>
  
<script>
function openModal() {
    var modal = document.getElementById('test5');
    modal.classList.remove('hide');
    modal.classList.add('show');
  }

function closeModal() {
    var modal = document.getElementById('test5');
    modal.classList.remove('show');
    modal.classList.add('hide');
  }
  
  
$("button[id^='showTodo']").on('click',(e)=>{
	e.preventDefault();
 	$.ajax({
 	    url : "../SectionTodo/todoView.do",  // 요청 URL
 	    type : "post",                  // HTTP 메서드
 	    data : {                       // 매개변수로 전달할 데이터
 	        sect : $(e.target).val(),                   // 검색어
 	        proc : ${projectID}
 	        },
 	    dataType : "text",      // 응답 데이터 형식
 	    success : function(data,textStatus){
 	    	$("button[id^='showTodo']").after(data);
 	    },  // 요청 성공 시 호출할 메서드 설정
 	    error : errFunc         // 요청 실패 시 호출할 메서드 설정
	});
});

function sucFuncJson(d) {
	
   
}
function errFunc(e) {
    alert("실패: " + e.status);
}
</script>

</body>
</html>