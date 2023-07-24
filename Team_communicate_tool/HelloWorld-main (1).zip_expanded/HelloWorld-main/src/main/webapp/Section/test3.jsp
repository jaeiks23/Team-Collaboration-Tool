<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/system.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<title>Insert title here</title>
</head>
<body>
	<%@ include file="TodoPopUp.jsp"%>
	<div>
		<button id="show_modal">+ 작업 추가</button>
		<div>
			<div>
				<ul class="sect_id_check">
					<c:choose>
						<c:when test="${empty sectionList} ">
							<li>프로젝트 섹션이 나누어지지 않았습니다.</li>
						</c:when>
						<c:otherwise>
							<c:forEach items="${sectionList}" var="list" varStatus="stat">
								<li valign="top">
									<button class="btn_show_hide"
										type="button" name="${list.section_id}"
										value="${list.section_id}" />${list.section_name}
									
										<button>
											<i class="fas fa-ellipsis-h"></i>
										</button>
								</li>
								<ul style="display: none">
									<c:forEach items="${todoList}" var="tlist" varStatus="stat">
										<c:choose>
											<c:when test="${list.section_id eq tlist.section_Id}">
												<li>
													<input type="checkbox" class="td_check">
													<span class="td_todo_content">${tlist.todo_Content}</span>
													<span class="td_todo_part">${tlist.part_Name}</span>
													<span class="td_todo_date">${tlist.todo_Start_Date}</span>
													<span class="td_todo_date">${tlist.todo_End_Date}</span>
													<span class="td_todo_status">${tlist.todo_Status}</span>
													<span style="display:none">${tlist.todo_info}</span>
													<span style="display:none">${tlist.todo_Id}</span>
													<span style="display:none">${tlist.part_Id}</span>
													<span style="display:none">${list.section_id}</span>
													<span style="display:none">${list.section_name}</span>													
													<span>
														<button type="button">
															<i class="fas fa-ellipsis-h"></i>
														</button>
													</span>
													<span><button class="edit_todo_btn" value="${tlist.part_Name},${tlist.todo_Content}">수정</button></td>
													<span><button class="btn_delete" value="${tlist.todo_Id}">삭제</button></span>
														
													
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
								</ul>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
	<div>
		<%@ include file="EditTodo.jsp"%>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
		crossorigin="anonymous"></script>

	<script>
  function openModal() {
    var modal = document.getElementById('todoPopUp');
    modal.classList.remove('none');
    modal.classList.add('showTodo');
  }
  
  
  const modalBtn = document.querySelector('#show_modal');
  
  modalBtn.addEventListener('click', () => toggleDisplay('block'));


  
  
$(".btn_show_hide").on('click',(e)=>{
	let a = $(e.target).parent().next();
	if(a.is("ul")){
		a.toggle();
	}
})
	
$(".btn_delete").on('click',(e)=>{
	$.ajax({
 	    url : "../SectionTodo/todoDelete.do",  // 요청 URL
 	    type : "post",                  // HTTP 메서드
 	    data : {                       // 매개변수로 전달할 데이터
 	        todo_id : $(e.target).val()
 	        },
 	    dataType : "text",      // 응답 데이터 형식
 	    success : function(){
 	    	location.reload();
 	    },
 	    		  // 요청 성공 시 호출할 메서드 설정
 	    error : function(){
 	    	alert("tlqkf");
 	    }         // 요청 실패 시 호출할 메서드 설정
	});
})

$(".btn_delete").on('click',(e)=>{
	$.ajax({
 	    url : "../SectionTodo/todoDelete.do",  // 요청 URL
 	    type : "post",                  // HTTP 메서드
 	    data : {                       // 매개변수로 전달할 데이터
 	        todo_id : $(e.target).val()
 	        },
 	    dataType : "text",      // 응답 데이터 형식
 	    success : function(){
 	    	location.reload();
 	    },
 	    		  // 요청 성공 시 호출할 메서드 설정
 	    error : function(){
 	    	alert("tlqkf");
 	    }         // 요청 실패 시 호출할 메서드 설정
	});
})

$(".edit_todo_btn").on('click',(e)=>{
	var content = $(e.target).parent().parent().children()[1].textContent; //할일 내용
    var name = $(e.target).parent().parent().children()[2].textContent; //담당자 이름
    var todo_id = $(e.target).parent().parent().children()[7].textContent; //할일 id
    var name_id = $(e.target).parent().parent().children()[8].textContent; //담당자 이름
    var sect_id = $(e.target).parent().parent().children()[9].textContent; //
    var sect_name = $(e.target).parent().parent().children()[10].textContent;
    var end_date = $(e.target).parent().parent().children()[4].textContent;
    var status = $(e.target).parent().parent().children()[5].textContent;
    var info = $(e.target).parent().parent().children()[6].textContent;
	console.log(todo_id);
	//값 넘기기
	$("#edit_todo").val(content);	//수정 창에 할일 값
    $("#todo_name").val(name_id).prop("selected",true);		//작성자
    $("#edit_todo_id").val(todo_id);
    $("#edit_end_date").val(end_date); //마감일
    $("#edit_sectName").val(sect_id).prop("selected",true); //섹션 명
    $("#edit_status").val(status);	//상태
    $("#edit_info").val(info);	//설명
})


</script>

</body>
</html>