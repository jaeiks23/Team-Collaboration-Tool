<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>${dto1.msg_title}</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<!-- CSS -->
<style>
#buttonim:hover {
	background-color: darkgray;
}

#buttonim:active {
	background-color: darkslategray;
}

.test5 {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-contenty {
	background-color: #fefefe;
	margin: 20% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	max-width: 600px;
	text-align: center;
	position: relative;
	transform: translate(-100%);
	transition: transform 0.3s ease-out;
}

.test5	.show {
	display: block;
}

.test5.show .modal-contenty {
	transform: translateY(0);
}


</style>

<body class="sb-nav-fixed">
	<jsp:include page="../toolbar/toolbar_dark.jsp"/>
		<div id="layoutSidenav_content">
			<main>
				<table border="1" style="width:90%; margin-left:50px; margin-top:50px;">
		<tr>
		<td>
			<c:choose>
				<c:when test="${param.mode eq 'send'}">
					받은사람
				</c:when>
				<c:otherwise>
					보낸사람
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
			<c:when test="${param.mode eq 'send'}">
			${dto1.msg_name}(${dto1.msg_receiveId})			
			</c:when>
			<c:otherwise>
			${dto1.msg_name}(${dto1.msg_sendId})
			</c:otherwise>
			</c:choose>
		</td>
		<tr>
			<td>송신일자</td>
			<td>${dto1.msg_messagedate}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${dto1.msg_title}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${dto1.msg_content}</td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${param.mode eq 'send'}">
			<button type="button" onclick="location.href='../pro/SendList.do';" style="margin-top:10px; margin-left:750px;">목록으로</button>
		</c:when>
		<c:when test="${param.mode eq 'keep'}">
		<button type="button" onclick="location.href='../pro/keepList.do';" style="margin-top:10px; margin-left:750px;">목록으로</button>
		</c:when>
		<c:otherwise>
			<button type="button" onclick="location.href='../pro/receiveList.do';" style="margin-top:10px; margin-left:750px;">목록으로</button>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.mode eq 'send'}">
			<button type="button" onclick="location.href='../pro/delete.do?mode=send&mnum='+${dto1.msg_mnum};" style="margin-top:10px;">삭제</button>
		</c:when>
		<c:when test="${param.mode eq 'keep'}">
		<button type="button" onclick="location.href='../pro/delete.do?mode=keep&mnum='+${dto1.msg_mnum};" style="margin-top:10px;">삭제</button>
		</c:when>
		<c:otherwise>
			<button type="button" onclick="location.href='../pro/delete.do?mode=receive&mnum='+${dto1.msg_mnum};" style="margin-top:10px;">삭제</button>
		</c:otherwise>
	</c:choose>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					
				</div>
			</footer>
		</div>
	</div>



	<!-- JavaScript -->
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
</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="../js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="assets/demo/chart-area-demo.js"></script>
	<script src="assets/demo/chart-bar-demo.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
		crossorigin="anonymous"></script>
	<script src="../js/datatables-simple-demo.js"></script>
</body>
</html>