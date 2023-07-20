<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<style>
#chatId {
	width: 158px;
	height: 24px;
	border: 1px solid #AAAAAA;
	background-color: #EEEEEE;
}
</style>
<body>
	<div id="sidePop">
		<form action="../SectionTodo/editTodo.do" method="post">
			<input type="text" name="edit_todo" id="edit_todo" placeholder="할 일 입력">
			<input type="text" name="edit_todo_id" id="edit_todo_id" style="display:none">
			<table>
				<tr>
					<th>담당자 : </th>
					<td><select name="edit_PIC_name" id="todo_name">
							<c:forEach items="${projectMem}" var="mem" varStatus="stat">
								<option name="edit_PIC" value="${mem.pro_mem_id}">${mem.pro_member}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>마감일</th>
					<td><input type="date" name="edit_end_date" id="edit_end_date" value="2023-01-01"></td>
				</tr>
				<tr>
					<th>섹션 명 : </th>
					<td>
						<select name="edit_Section" id="edit_sectName">
							<c:forEach items="${sectionList}" var="list" varStatus="stat">
								<option name="edit_Section" value="${list.section_id}">${list.section_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>상태</th>
					<td><input type="text" name="edit_status" id="edit_status" value="상태"></td>
				</tr>
				<tr>
					<th>설명</th>
					<td><input type="text" name="edit_info" id="edit_info" value="설 명"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="submit" value="변경"></td>
				</tr>
			</table>
			<div>
				<input type="text" id="chatMessage" onkeyup="enterKey();">
				<button id="edit_SendBtn" onclick="">전송</button>
			</div>
		</form>

	</div>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"
		integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
		crossorigin="anonymous"></script>
</body>


</html>