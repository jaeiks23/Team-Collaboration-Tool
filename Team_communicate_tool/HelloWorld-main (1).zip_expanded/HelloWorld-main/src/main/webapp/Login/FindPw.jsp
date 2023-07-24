<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Find Password</title>
        <link href="../css/styles.css" rel="stylesheet" />   
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container" style="margin-top: 8%;">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Search Password</h3></div>
                                    <div class="card-body">
                                        <form>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="name" name="searchName" type="text" placeholder="Input Name"/>
                                                <label for="inputName">Name</label>                                             
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="ID" name="searchID" type="text" placeholder="Input ID"/>
                                                <label for="inputID">ID</label>                                            
                                            </div>                                                                                    
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="email" name="searchEmail" type="email" placeholder="Input Email"/>
                                                <label for="inputEmail">Email</label>
                                                <button class="btn btn-primary" type="button" id="sendMailBtn">인증 메일 보내기</button>                                               
                                            </div>
                                            <div>
                                            	<a class="small" style="display:none" id="sendText">메일을 보냈습니다.</a>
                                            </div>
                                            <div>
                                            	<input type="text" name="checkCode" id="myInput" >
                                            	<button class="btn btn-primary" type="button" id="checkCodeBtn" onclick="insertEmail()">인증 확인</button>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="LoginPage.jsp">Return to login</a>
                                                <button onclick="sendid()" type ="button"  class="btn btn-primary" id = "search">Search Password</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="../Login/createMem.do">Need an account? Sign up!</a></div>
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
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted"></div>
                            
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../js/scripts.js"></script>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script>
			let codenum = Math.floor((Math.random()*900000)+100000);
			let result = 0;
      		$(function () {
        		$('#sendMailBtn').click(function () {
        			$('#sendText').css("display", "block");
          			$.ajax({
            			url: "EmailProcess.jsp",
            			type: "post",                 
            			data: {                       
            				email:$('#email').val(),
            				code:codenum            				
            			}
          			});
        		});
      		});
      		
           	function insertEmail(){
    		 	let myInput = document.getElementById('myInput').value;	 	
     	 		if(myInput==codenum) {
     	 			result = 1;
     	 		} else if(myInput!=codenum){
     	 			result = 0;
     	 		} else{
     	 			result = -1;
     	 		}
     	 		
     	 		if(result == 1){
     	 			alert("인증되었습니다.");
     	 			$("#email").attr("disabled",true); 
     	 			$("#myInput").attr("disabled",true);
     	 		} else {
    				alert("메일이 인증되지 않았습니다.");
     	 		}
          	}
           	
            function sendid(){
                let newForm = document.createElement('form');
                
                newForm.name = 'newForm';
                newForm.method = 'post';
                newForm.action = '../Login/findPw.do';
                newForm.target ='_self';
                
                let name = document.getElementById('name').value;
                let id = document.getElementById('ID').value;
                let email = document.getElementById('email').value;
                
                var inName = document.createElement('input');
                var inId = document.createElement('input');
                var inEmail = document.createElement('input');
                var inResult = document.createElement('input');
                
                inName.setAttribute("type","hidden");
                inName.setAttribute("name","searchName");
                inName.setAttribute("value",name);
                 
                inId.setAttribute("type","hidden");
                inId.setAttribute("name","searchId");
                inId.setAttribute("value",id);
                
                inEmail.setAttribute("type","hidden");
                inEmail.setAttribute("name","searchEmail");
                inEmail.setAttribute("value",email);
                
                inResult.setAttribute("type","hidden");
                inResult.setAttribute("name","result");
                inResult.setAttribute("value",result);
				              
                newForm.appendChild(inName);
                newForm.appendChild(inId);
                newForm.appendChild(inEmail);
                newForm.appendChild(inResult);

                document.body.appendChild(newForm);

                newForm.submit();         
           	}
    </script>
        
    </body>
</html>