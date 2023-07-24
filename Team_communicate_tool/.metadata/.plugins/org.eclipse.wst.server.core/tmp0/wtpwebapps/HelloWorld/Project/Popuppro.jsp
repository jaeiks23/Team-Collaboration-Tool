<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
.promodal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
}

.pro-modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}
.selprosdda{
	display: none;
}

</style>
</head>
<body>
		<form  class ="promodal" id = "myModal">
		<div class="form-floating mb-3 pro-modal-content" >
			<table border="1">
				<c:choose>
					<c:when test="${ empty projectlist}">

						<tr>
							<td colspan="2" align="center">등록된 게시물이 없습니다.</td>
						</tr>

					</c:when>
					<c:otherwise>
						<c:forEach items="${projectlist}" var="list" varStatus="stat">


							<input class="form-control  selpro" id="proname" type="text"
								placeholder="Enter your last name" readonly="readonly"
								value="${list.pro_name}" 
							 />
							 <input type="text" class = "selprosdda" value="${list.pro_id}"/>
							<br>

						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</form>

</body>
	<script>
  const myForm = document.querySelector('#prolists');
  const modal = document.querySelector('#myModal');
	let asd = 0;

	$('.selpro').on('click', function() {
		
		 let valuesdasd = $(this).next().val();
	  
		 asd = valuesdasd;
		 sendid();
	
	});
	
  myForm.addEventListener('click', (event) => {
    event.preventDefault();
    modal.style.display = 'block';
  });

  window.addEventListener('click', (event) => {
    if (event.target == modal) {
      modal.style.display = 'none';
    }
  });
  
	

  
  function sendid(){
	  
		let newForm = document.createElement('form');
		
		newForm.name = 'newForm';
		newForm.method = 'post';
		newForm.action = '../Main.do';
		newForm.target ='_self';


		
		var input1 = document.createElement('input');

		input1.setAttribute("type","hidden");
      input1.setAttribute("name","pro_id");
      input1.setAttribute("value",asd);
      

      newForm.appendChild(input1);

      

      document.body.appendChild(newForm);

      newForm.submit();
		
		
	}
  
</script>
</html>