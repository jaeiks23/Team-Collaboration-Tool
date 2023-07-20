<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo Popup</title>
<link href="../css/system.css" rel="stylesheet" />
</head>
<body>
	<div class="window_modal">
		<div id="todoPopUp" class="popup_modal">
			<h2 style="text-align: center;">작업<c:if test="add">추가</c:if></h2>
			<span id="close" style="position: absolute; top: 10px; right: 20px; cursor:pointer;">x</span>
			<form action="../SectionTodo/addTodo.do" " method="post"
				id="SectionPopUp" style="width: 100%; height: 100%;">
				<table style="margin: auto;" id="PopUptable">
					<tr>
						<td>섹션 선택 :</td>
						<td colspan="3" style="align-content: center; padding-left: 6px">
							<select style="width: 50%;" name="section_id_todo">
								<c:forEach items="${sectionList}" var="list" varStatus="stat">
									<option name="section_id_todo" value="${list.section_id}">${list.section_name}</option>
								</c:forEach>
						</select>
					</tr>
					<tr>
						<td>작업 내용 :</td>
						<td style="padding-left: 6px"><input type="text"
							name="work_content" id="" placeholder="내용을 입력해주세요"></td>
						</td>
					</tr>
					<tr>
						<td>담당 선택 :</td>
						<td colspan="3" style="padding-left: 6px"><select
							style="width: 50%" name="PIC">
								<c:forEach items="${projectMem}" var="mem" varStatus="stat">
									<option name="PIC" value="${mem.pro_mem_id}">${mem.pro_member}</option>
								</c:forEach>
						</select>
					</tr>
					<tr>
						<td>상태</td>
						<td style="padding-left: 6px"><select style="width: 50%"
							name="work_status">
								<option value="READY">대기</option>
								<option value="START">진행중</option>
								<option value="END">완료</option>
						</select></td>
						<td>우선 순위</td>
						<td style="padding-left: 6px"><select style="width: 50%"
							name="work_rank">
								<option value="HIGH">높음</option>
								<option value="MIDDLE">보통</option>
								<option value="LOW">낮음</option>
						</select></td>
					</tr>
					<tr>
						<td>시작일</td>
						<td style="padding-left: 6px"><input type="date"
							name="work_start"></td>
						<td>종료일</td>
						<td style="padding-left: 6px"><input type="date"
							name="work_end"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="추가"
							style="margin: auto;"></td>
						<td colspan="2"><input type="button" onclick="" value="취소"></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
	<script>
	window.addEventListener('click', (event) => {
			var modal = document.querySelector('.window_modal');
	    	if (event.target == modal) {
	    	toggleDisplay('none')
	    	}
		});
		function closeModal() {
			var modal = document.getElementById('todoPopUp');
			modal.classList.remove('showTodo');
			modal.classList.add('none');
		}
		function show() {
			document.querySelector(".background_modal").className = "background_modal show_modal";
		}
		
		const modal = document.querySelector('.window_modal');
		
		function toggleDisplay(display) {
			modal.style.display = display;
		}
		
		document.querySelector("#close").addEventListener('click', () => toggleDisplay('none'))
	</script>

</body>
</html>