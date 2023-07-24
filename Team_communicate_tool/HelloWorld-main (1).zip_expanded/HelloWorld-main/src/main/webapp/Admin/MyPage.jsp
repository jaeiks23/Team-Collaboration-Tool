<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>	
<link href="../css/styles.css" rel="stylesheet" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>마이 페이지</title>


<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous">
	
</script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="../toolbar/toolbar_dark.jsp"/>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
				<br>
					<div style= "width: 100%; border: 1px solid black">
						
						<h2>마이 페이지</h2>
					</div>
					<%-- 로그인된 이메일 출력 --%>
					<form action="../Controller/Logout.do" method="post">
							<input type="submit" value="로그아웃" id="logoutButton" style="float: right">
					</form>
					<br>
					<div style= "width: 100%; height: 100%; border: 1px solid black">
					<p>이름:  ${MyInfo.name} </p>
					<p>아이디:  ${MyInfo.id } </p>
					<button id="changePasswordBtn" onclick="showPasswordFields()">비밀번호</button>
					<div id="passwordFields" style="display: none;">
					<form action="<%=request.getContextPath() %>/Controller/Update.do" method="post" name="login">
						<p>
							현재 비밀번호: <input type="password" id="currentPassword" name="currentPassword">
						</p>
						<p>
							새로운 비밀번호: <input type="password" id="newPassword" name="newPassword">
						</p>
						<p>
							비밀번호 확인: <input type="password" id="confirmPassword" name="confirmPassword">
						</p>
						
						<p>
							 <input type="submit" value="비밀번호 변경">
						</p>
					</form>
				</div>
					<p>
						<br>
						이메일: ${MyInfo.email }
						
						</p>
						</div>
	<script>
	function showPasswordFields() {
    // 비밀번호 입력창들의 현재 display 상태를 가져옴
    var passwordFields = document.getElementById("passwordFields");
    var display = passwordFields.style.display;

    // 현재 display 상태가 none인 경우, 즉 비밀번호 입력창들이 숨겨진 경우
    if (display === "none") {
        // 비밀번호 입력창들을 보이도록 설정
        passwordFields.style.display = "block";
        // 비밀번호 변경 버튼의 텍스트를 "취소"로 변경
        document.getElementById("changePasswordBtn").innerText = "취소";
    } else {
        // 비밀번호 입력창들을 숨기도록 설정
        passwordFields.style.display = "none";
        // 비밀번호 변경 버튼의 텍스트를 "비밀번호 변경"으로 변경
        document.getElementById("changePasswordBtn").innerText = "비밀번호 변경";
    }
}
</script>

	</main>
	</div>
	</div>
		<!-- 기타 마이 페이지 내용 추가 -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
			crossorigin="anonymous"></script>
		<script src="js/scripts.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
			crossorigin="anonymous"></script>
		<script src="assets/demo/chart-area-demo.js"></script>
		<script src="assets/demo/chart-bar-demo.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
			crossorigin="anonymous"></script>
		<script src="js/datatables-simple-demo.js"></script>
		<script src="Board/clock.js"></script>
</body>
</html>