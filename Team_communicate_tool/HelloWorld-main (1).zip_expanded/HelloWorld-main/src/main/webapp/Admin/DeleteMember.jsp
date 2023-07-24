<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./ProjectNullCheck.jsp" %>
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
<title>Dashboard - SB Admin</title>
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

h2{
	text-align: center;
}


</style>

<body class="sb-nav-fixed">
	<jsp:include page="../toolbar/toolbar_dark.jsp"/>
		<div id="layoutSidenav_content">
			<main>
				   <h2>관리자 홈페이지</h2>
   
      <table border="1" width="50%" align="center">
      <c:forEach items="${memdelList}" var="list" varStatus="stat">
           <c:choose>
           <c:when test="${list.pro_mem_id eq list.pro_host}">
           </c:when>
           <c:otherwise>
      	<tr align="center">
           <td style="margin-top:500px;">
            ${list.mem_Name} 님 <button type="button" value="${list.pro_mem_id}" onclick="excludeMember(this.value,this.parentNode)" >제외</button>
            </td>           
      	</tr>    
           </c:otherwise>
           </c:choose>
      </c:forEach>

      </table>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2023</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
  function excludeMember(name,id) {
   
    $.ajax({
      url: '../pro/test.do',
      type: 'post',
      data: { name: name },
      success: function() {
        alert(name + ' 님이 프로젝트에서 제외되었습니다.');
        $(id).css('display', 'none');
      },
      error: function() {
       console.log(name);
       console.log(btn.type);
        alert('프로젝트에서 멤버를 제외하는 동안 오류가 발생했습니다.');
      }
    });
  }
</script>
	
</body>
</html>
