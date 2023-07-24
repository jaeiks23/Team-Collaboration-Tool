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
<title>���� ������</title>


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
						
						<h2>���� ������</h2>
					</div>
					<%-- �α��ε� �̸��� ��� --%>
					<form action="../Controller/Logout.do" method="post">
							<input type="submit" value="�α׾ƿ�" id="logoutButton" style="float: right">
					</form>
					<br>
					<div style= "width: 100%; height: 100%; border: 1px solid black">
					<p>�̸�:  ${MyInfo.name} </p>
					<p>���̵�:  ${MyInfo.id } </p>
					<button id="changePasswordBtn" onclick="showPasswordFields()">��й�ȣ</button>
					<div id="passwordFields" style="display: none;">
					<form action="<%=request.getContextPath() %>/Controller/Update.do" method="post" name="login">
						<p>
							���� ��й�ȣ: <input type="password" id="currentPassword" name="currentPassword">
						</p>
						<p>
							���ο� ��й�ȣ: <input type="password" id="newPassword" name="newPassword">
						</p>
						<p>
							��й�ȣ Ȯ��: <input type="password" id="confirmPassword" name="confirmPassword">
						</p>
						
						<p>
							 <input type="submit" value="��й�ȣ ����">
						</p>
					</form>
				</div>
					<p>
						<br>
						�̸���: ${MyInfo.email }
						
						</p>
						</div>
	<script>
	function showPasswordFields() {
    // ��й�ȣ �Է�â���� ���� display ���¸� ������
    var passwordFields = document.getElementById("passwordFields");
    var display = passwordFields.style.display;

    // ���� display ���°� none�� ���, �� ��й�ȣ �Է�â���� ������ ���
    if (display === "none") {
        // ��й�ȣ �Է�â���� ���̵��� ����
        passwordFields.style.display = "block";
        // ��й�ȣ ���� ��ư�� �ؽ�Ʈ�� "���"�� ����
        document.getElementById("changePasswordBtn").innerText = "���";
    } else {
        // ��й�ȣ �Է�â���� ���⵵�� ����
        passwordFields.style.display = "none";
        // ��й�ȣ ���� ��ư�� �ؽ�Ʈ�� "��й�ȣ ����"���� ����
        document.getElementById("changePasswordBtn").innerText = "��й�ȣ ����";
    }
}
</script>

	</main>
	</div>
	</div>
		<!-- ��Ÿ ���� ������ ���� �߰� -->
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