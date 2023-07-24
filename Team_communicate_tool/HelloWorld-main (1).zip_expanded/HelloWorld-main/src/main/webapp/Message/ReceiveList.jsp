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
<title>받은 쪽지함</title>
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
				   <h2 align="center" style="margin-right:50px;margin-top:10px">받은 쪽지함</h2>
   <form method="get">
      <table style="margin-left:650px; margin-bottom:10px;">
         <tr>
           
            <td>
               <select name="user">
                  <option value="sendId" <c:if test="${map.searchType=='sendId'}">selected</c:if>>아이디</option>
                  <option value="title" <c:if test="${map.searchType=='title'}">selected</c:if>>제목</option>
               </select>
            </td>
            <td><input type="text" name="search" placeholder="쪽지 검색"></td>
            <td><button type="submit">검색</button></td>
         </tr>
      </table>
      
      <button type="button" onclick="send();" style="margin-left:80px; margin-bottom:10px;">쪽지쓰기</button>    
      <button type="button" onclick="reply();">답장</button>
      <button type="button" onclick="sendMnum();">삭제</button>
      <button type="button" onclick="sendKeep();">보관</button>
     
     
      
      <table border="1" width="90%" style="margin-left:70px;">
         <tr>
            <th align="center"><center><input type="checkbox" name="checkbox" onclick="selectAll(this);"></center></th>
            <th>보낸 사람</th>
            <th>제목</th>
            <th>날짜</th>
            
         </tr>
         <c:choose>
         <c:when test="${empty receiveList}">
         	<tr>
         		<td colspan="5" align="center">받은 쪽지가 없습니다.</td>
         	</tr>
         </c:when>
        <c:otherwise>
       	<c:forEach items="${receiveList}" var="list">
	<tr>
		<td align="center"><input type="checkbox" name="checkbox" id="${list.msg_mnum}," value="${list.msg_sendId}"></td>
		<td>${list.msg_sendId}</td>
		<td><a href="../pro/viewcon.do?mode=receive&mnum=${list.msg_mnum}">${list.msg_title}</a></td>
		<td>${list.msg_messagedate}</td>
		
	</tr>
	</c:forEach>
        </c:otherwise>
	 </c:choose>
      </table>
   </form>
   
   <c:choose>
   <c:when test="${empty  receiveList}">
   </c:when>
   <c:otherwise>
   <table border="1" width="90%" style="margin-left:70px;">
		<tr align="center">
		<td>
			${map.pagingStr}
		</td>
		</tr>
	</table>
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

<script type="text/javascript">

function send(){
	window.open('../pro/send.do','쪽지 보내기','width=500,height=600');
}

function selectAll(selectAll)  {
	  const checkboxes 
	       = document.getElementsByName('checkbox');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
	}
	
	function sendMnum(){
	
	var checked='';
	const checkboxes = document.getElementsByName('checkbox');
	for(var i=1; i<checkboxes.length; i++){
		if(checkboxes[i].checked){
			checked += checkboxes[i].id;
		}
	}
	
	location.href='../pro/delete.do?mode=receive&mnum='+checked;
	
	}	
	
	function sendKeep(){
	
	var checked='';
	const checkboxes = document.getElementsByName('checkbox');
	for(var i=1; i<checkboxes.length; i++){
		if(checkboxes[i].checked){
			checked += checkboxes[i].id;
		}
	}
	
	location.href='../pro/keeping.do?mode=receive&mnum='+checked;
	
	}
	
	function reply(){
		
	var checked=[];
	const checkboxes = document.getElementsByName('checkbox');
	
	for(var i=1; i<checkboxes.length; i++){
		
		if(checkboxes[i].checked){
			checked.push(checkboxes[i].value);
		}				
		
	}
		if(checked.length>1){
			window.alert("하나만 선택해주세요");
		}else if(checked.length==0){
			window.alert("답장을 보낼 상대를 선택해주세요");
		}else{
			window.open('../pro/send.do?mode=reply&sendId='+checked,'답장 보내기','width=500,height=600');
		}
	
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