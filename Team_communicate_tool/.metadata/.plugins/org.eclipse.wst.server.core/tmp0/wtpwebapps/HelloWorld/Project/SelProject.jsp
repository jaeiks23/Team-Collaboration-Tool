<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Login - SB Admin</title>
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css" />
<style>
.selprosd {
	display: none;
}
</style>

</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container" style="margin-top: 3%;">
					<div class="row justify-content-center">
						<div class="col-lg-5">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">프로젝트 선택</h3>
								</div>
								<div class="card-body">
									<form>
										<div class="form-floating mb-3">
											<table style="width: 100%">
												<c:choose>
													<c:when test="${ empty projectlist}">

														<tr>
															<td colspan="2" align="center">등록된 게시물이 없습니다.</td>
														</tr>

													</c:when>
													<c:otherwise>
														<c:forEach items="${projectlist}" var="list"
															varStatus="stat">


															<input class="form-control  selpro" id="proname"
																type="text" placeholder="Enter your last name"
																readonly="readonly" value="${list.pro_name}"
																ondblclick="connectMain();" />
															<input type="text" class="selprosd"
																value="${list.pro_id}" />
															<br>

														</c:forEach>
													</c:otherwise>
												</c:choose>
											</table>
										</div>
										<div
											class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<a class="btn btn-primary" href='Project/CrePro.jsp'>생성하기</a>
											<a class="btn btn-primary" id="selbutton"
												onclick="connectMain();">접속</a>
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
		<div id="layoutAuthentication_footer">
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted"></div>
						<div>
							<a href="#"></a> &middot; <a href="#"></a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.4.js"
		integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
		crossorigin="anonymous"></script>
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/js/scripts.js"></script>

<script>
var selectedValue; 
$(document).ready(function(){

	$('.selpro').on('click', function() {
		
	    var valuesdasd = $(this).next().val();
	  
	    selectedValue = valuesdasd;
	});
	
	
	
	
});

function connectMain(){
	if(selectedValue == undefined){
		alert("선택해주세요");
		return;
	}
	
	var newForm = document.createElement('form');
	
	newForm.name = 'newForm';
	newForm.method = 'post';
	newForm.action = "./ConnectMain.do";
	newForm.target = '_self';
	var input1 = document.createElement('input');

	input1.setAttribute("type", "hidden");
	input1.setAttribute("name", "pro_id");
	input1.setAttribute("value", selectedValue);
	console.log(selectedValue);

	newForm.appendChild(input1);

	document.body.appendChild(newForm);

	newForm.submit();

		}
	</script>
</body>
</html>
