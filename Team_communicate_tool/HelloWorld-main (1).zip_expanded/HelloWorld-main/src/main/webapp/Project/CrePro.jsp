<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<title>Register - SB Admin</title>
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" />
</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container" style="margin-top: 8%;">
					<div class="row justify-content-center">
						<div class="col-lg-7">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4">project 생성</h3>
								</div>
								<div class="card-body">
									<form name=insertform>
										<div class="form-floating mb-3">
											<div class="form-floating">
												<input class="form-control" id="inputLastName" type="text"
													placeholder="Enter your last name" /> <label
													for="inputLastName">project Name</label>
											</div>
										</div>
										<div class="form-floating mb-3">
											<input class="form-control" id="inputEmail" type="text"
												placeholder=""
												onkeyup="if(window.event.keyCode==13){insertmem()}" /> <label
												for="inputEmail">추가할 ID 입력:</label>
										</div>
										<div id="createplace"></div>
										<br>
										<div class="mb-3">
											<div class="form-floating mb-3 mb-md-0">
												<input class="form-control" id="inputPassword" type="date"
													placeholder="Create a password" /> <label
													for="inputPassword">종료날짜</label>
											</div>
										</div>

										<div
											class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<a class="btn btn-primary btn-block" onclick="sendid();">프로젝트
												생성</a> <a class="btn btn-primary btn-block"
												onclick="location.href='../SelectPro.do';">취소</a>


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
	<script src="<%=request.getContextPath()%>/js/scripts.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous" />


	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
		crossorigin="anonymous"></script>
	<script>
	let idlist = [];

	
	
	document.insertform.addEventListener('keydown', function(event) {
	  if (event.keyCode === 13) {
		    event.preventDefault();
		  }
		});
	function insertmem(){
		
		let memlist = "<%=session.getAttribute("memidlist")%>";
		memlist = memlist.replace("[", "");
		memlist = memlist.replace("]", "");
        
        let idList2 = memlist.split(", ");
		let id = document.getElementById('inputEmail').value;
		let place = document.getElementById('createplace');
		let trues = 0;
		console.log(idList2);
		
		if(idlist.length !=0){
			for(let item of idlist){
				if(item == id){
					alert("이미 있습니다.");
					trues = 1;
				}
			}
		}
	
		
		if(trues == 0){
			{
				if(idList2.indexOf(id) != -1){
					place.innerHTML += '<input type ="button" class= "btn btn-primary btn-block" name="member"  readonly="readonly" value='+id+'> ';
					 
					idlist.push(id);
						 
					let cols = document.getElementsByName("member");
						for (var i = 0; i < cols.length; i++) {
							cols[i].addEventListener('click', function(e){
							for(let item of idlist){
								if(item == this.value){
									idlist = idlist.filter((element) => element != this.value);
									this.remove();
									}
								}
							});
						}
					
					}else{
						alert("회원 목록에 없습니다.");
					}
				
						
				}
			
			}
				
		document.getElementById('inputEmail').value="";
			
		}
		
	
	
	function sendid(){
		let newForm = document.createElement('form');
		
		newForm.name = 'newForm';
		newForm.method = 'post';
		newForm.action = '../createpro.do';
		newForm.target ='_self';
		let name = document.getElementById('inputLastName').value;
		let date = document.getElementById('inputPassword').value;
		
		var input1 = document.createElement('input');
		var input2 = document.createElement('input');
		var input3 = document.createElement('input');
		
		input1.setAttribute("type","hidden");
        input1.setAttribute("name","idlist");
        input1.setAttribute("value",idlist);
        
        input2.setAttribute("type","hidden");
        input2.setAttribute("name","project_Name");
        input2.setAttribute("value",name);
       
        input3.setAttribute("type","hidden");
        input3.setAttribute("name","PRO_E_DATE");
        input3.setAttribute("value",date);

        newForm.appendChild(input1);
        newForm.appendChild(input2);
        newForm.appendChild(input3);
        

        document.body.appendChild(newForm);

        newForm.submit();
		
		
	}
</script>
</body>
</html>
