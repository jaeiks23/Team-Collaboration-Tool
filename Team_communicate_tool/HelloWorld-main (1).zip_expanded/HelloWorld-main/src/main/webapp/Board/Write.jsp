<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>게시판 글쓰기</title>
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="../toolbar/toolbar_dark.jsp"/>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">게시판 글 작성하기</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">게시판 글 작성</li>
						<li class="breadcrumb-item"><a href="list.do">공지게시판으로
								돌아가기</a></li>
						<li class="breadcrumb-item"><a href="list.do">자유게시판으로
								돌아가기</a></li>
					</ol>
				</div>
			</main>
			<form action="/HelloWorld/board/write.do" method="post" name="writeForm"
				enctype="multipart/form-data">
				<table border="1" width="90%">
					<tr>
						<td align="center">제목</td>
						<td><input type="text" name="title" style="width: 90%;" /></td>
					</tr>
					<tr>
						<td align="center">유형</td>
						<td><input type="checkbox" id="noticeFlag" name="noticeFlag"
							style="width: 30;" /> <label for="noticeFlag">공지</label></td>
					</tr>
					<tr>
						<td align="center">내용</td>
						<td><textarea name="content"
								style="width: 90%; height: 300px;"
								placeholder="2000자 내외로 입력해주세요."></textarea></td>
					</tr>
					<tr>
						<td align="center">첨부 파일</td>
						<td><input type="file" name="ofile" /></td>
					</tr>
				</table>
				<div align="center" style='padding-top: 50px;'>
					<button type="button" class="btn btn-info"
						onclick="location.href='../board/list.do';">목록 바로가기</button>
					<button type="reset" class="btn btn-danger">초기화</button>
					<button type="submit" class="btn btn-dark">작성완료</button>

				</div>
			</form>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					
				</div>
			</footer>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
