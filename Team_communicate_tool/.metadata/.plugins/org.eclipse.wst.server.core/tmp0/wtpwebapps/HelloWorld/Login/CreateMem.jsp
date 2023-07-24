<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>
  <head>
    <title>Register</title>
    <link href="../css/styles.css" rel="stylesheet" />
  </head>
  <body class="bg-primary">
    <div id="layoutAuthentication">
      <div id="layoutAuthentication_content">
        <main>
          <div class="container" style="margin-top: 4%;">
            <div class="row justify-content-center">
              <div class="col-lg-7">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                  <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">Create
                      Account</h3>
                  </div>
                  <div class="card-body">
                    <form action="../Login/createMem.do" method="post">
                      <div class="row mb-3">
                        <div class="col-md-8">
                          <div class="form-floating mb-3 mb-md-0">
                            <input class="form-control" id="inputName" type="text"
                              placeholder="Enter your first name" name="regName" onchange="checkKorean()"/> 
                              <label for="inputFirstName">Name</label>
                              <a id="result"></a>
                          </div>
                        </div>
                      </div>
                      <div class="row mb-3">
                        <div class="col-md-8">
                          <div class="form-floating mb-3 mb-md-0">
                            <input class="form-control" id="inputID" type="text" placeholder="input ID" name="regID" />
                            <label for="inputFirstName">ID</label>
                            <button class="btn btn-primary btn-block" type="button" onclick="checkID();">중복 확인</button>
                            <span id="place" class="checked"></span>
                          </div>
                        </div>
                      </div>
                      <div class="row mb-3">
                        <div class="col-md-6">
                          <div class="form-floating mb-3 mb-md-0">
                            <input class="form-control" id="pwd" name="PW1" type="password" onchange="checkpwd_1()"
                              placeholder="Create a password" /> <label for="inputPassword">Password</label> <span
                              id="checkPW" class="checked"></span>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-floating mb-3 mb-md-0">
                            <input class="form-control" type="password" name="PW2" id="checkpwd" onchange="checkpwd_1()"
                              placeholder="Confirm password" /> <label for="inputPasswordConfirm">Confirm
                              Password</label> <span id="check" class="checked"></span>
                          </div>
                        </div>
                      </div>
                      <div class="form-floating mb-3">
                        <input class="form-control" id="email" name="regEmail" type="email" placeholder="Input Email" />
                        <label for="inputEmail">Email</label>
                        <button class="btn btn-primary" type="button" id="sendMailBtn">인증 메일 보내기</button>
                      </div>
                      <div>
                        <a class="small" style="display: none" id="sendText">메일을 보냈습니다.</a>
                      </div>
                      <div>
                        <input type="text" name="checkCode" id="myInput">
                        <button class="btn btn-primary" type="button" id="checkCodeBtn" onclick="insertEmail()">인증 확인</button>
                      </div>
                      <div class="mt-4 mb-0">
                        <div class="d-grid">
                          <button class="btn btn-primary btn-block" type ="submit">Create Account</button>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="card-footer text-center py-3">
                    <div class="small">
                      <a href="../Login/LoginPage.jsp">Have an account? Go to
                        login</a>
                    </div>
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
    <script src="js/scripts.js"></script>
    
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script>
    let idsuccess = 0;
    let pwsuccess = 0;
    let emailsuccess = 0;
    let namesuccess = 0;
    let pwsuccess2 = 0;
  
    
      let codenum = Math.floor((Math.random() * 900000) + 100000);
      let result = 0;
      $(function () {
        $('#sendMailBtn').click(function () {
          $('#sendText').css("display", "block");
          $.ajax({
            url: "EmailProcess.jsp",
            type: "post",
            data: {
              email: $('#email').val(),
              code: codenum
            }
          });
        });
      });
      
      $('form').submit(function(){
    	  if(idsuccess == 1 && pwsuccess == 1 && emailsuccess == 1 && namesuccess == 1 && pwsuccess2 == 1){
    		  return true;
    	  }
    	  else{
    		  alert("제대로 입력되지 않았습니다.");
    		  return false;
    	  }
      });
      
      function insertEmail() {
        let myInput = document.getElementById('myInput').value;
        if (myInput == codenum) {
          result = 1;
        } else if (myInput != codenum) {
          result = 0;
        } else {
          result = -1;
        }

        if (result == 1) {
          alert("인증되었습니다.");
          emailsuccess = 1;
          $("#myInput").attr("disabled", true);
        } else {
          alert("메일이 인증되지 않았습니다.");
        }
      }
      
      //문자 유효성 검사
      function combine(str) {
        var reg1 = /[a-z0-9]/;
        var reg2 = /[a-z]/;
        var reg3 = /[0-9]/;
        return (reg1.test(str) && reg2.test(str) && reg3.test(str));
      }
      
      //한글 유효성 검사
      function checkKorean() {
          var input = document.getElementById("inputName").value;
          var regex = /^[ㄱ-ㅎㅏ-ㅣ가-힣]*$/; // 한글 정규표현식
          if (regex.test(input)) {
        	  namesuccess = 1;
            document.getElementById("result").innerHTML = "<a style='color:green'>유효한 입력입니다.</a>";
          } else {
        	  namesuccess = 0;
            document.getElementById("result").innerHTML = "<a style='color:red'>한글만 입력 가능합니다.</a>";
          }
        }
      
      function checkID() {
        let idString = "<%=session.getAttribute("sameID")%>";
        idString = idString.replace("[", "");
        idString = idString.replace("]", "");
        console.log(idString);
        let idList = idString.split(", ");

        var id = document.getElementById('inputID');
        var place = document.getElementById('place');

        if (id.value.trim() == "") {
        	idsuccess = 0;
          place.innerHTML = "<a style='color:red'>빈칸입니다.</a>";
        } else {
          if (idList.indexOf(id.value.trim()) != -1) {
        	  idsuccess = 0;
            place.innerHTML = "<a style='color:red'>중복된 아이디입니다.</a>";
          } else {
        	  idsuccess = 1;
            place.innerHTML = "<a style='color:green'>사용 가능한 아이디입니다.</a>";
          }
        }
      }

      //비밀번호 확인
      var pwdd = document.getElementById("pwd");
      var checkpwdd = document.getElementById("checkpwd");
      var PWcheck = document.getElementById("checkPW");
      var okpw = document.getElementById("check");

      function checkpwd_1() {
    	  
        var SC = ["!", "@", "#", "$", "%", "*"];
        var check_SC = 0;

        for (var i = 0; i < SC.length; i++) {
          if (pwdd.value.indexOf(SC[i]) != -1) {
            check_SC = 1;
          }
        }

        if (pwdd.value) {
          if (pwdd.value.length <= 8 || check_SC == 0
            || !combine(pwdd.value)) {
        	  pwsuccess2 = 0;
            PWcheck.innerHTML = "<span style='color:red'>영문,숫자,특수문자(필수) 조합 8자 이상</span>";
            pwdd.focus();
          } else {
        	  pwsuccess2 = 1;
            PWcheck.innerHTML = "<span style='color:green'>안전한 비밀번호입니다.</span>";
          }
        } else {
          PWcheck.innerHTML = "";
        }

        if (pwdd.value && checkpwdd.value) {
          if (pwdd.value === checkpwdd.value) {
        	  pwsuccess = 1;
            okpw.innerHTML = "<span style='color:green'>비밀번호가 일치합니다.</span>";
          } else {
        	  pwsuccess = 0;
            okpw.innerHTML = "<span style='color:red'>비밀번호가 일치하지 않습니다.</span>";
          }
        } else {
          okpw.innerHTML = "";
        }
      }
      
    
    </script>
  </body>

  </html>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  