<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>자료 게시판</title>
<link href="../css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<style>
a {
	text-decoration: none;
}
</style>
</head>
<body class="sb-nav-fixed">
	<h2>공지게시판</h2>
	<jsp:include page="../toolbar/toolbar_dark.jsp"/>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">공지게시판</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">공지게시판 글 목록</li>
						<li class="breadcrumb-item"><a href="../ConnectMain.do">메인페이지로
								돌아가기</a></li>
					</ol>
				</div>
			</main>
			<!-- 검색 -->
			<form method="get">
				<table border="1" width="90%">
					<tr>
						<td align="center"><select name="searchType">
								<option value="title"
									<c:if test="${map.searchType=='title'}">selected</c:if>>제목
								</option>
								<option value="content"
									<c:if test="${map.searchType=='content'}">selected</c:if>>내용
								</option>
								<option value="writer_id"
									<c:if test="${map.searchType=='writer_id'}">selected</c:if>>작성자
								</option>
						</select> <input type="search" name="searchStr" value="${map.searchStr}" />
							<input type="submit" value="검색" /></td>
					</tr>
				</table>
				<!-- 목록 -->
				<table border="1" width="90%">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>첨부파일</th>
					</tr>
					<c:choose>
						<c:when test="${empty boardList}">
							<tr>
								<td colspan="6" align="center">등록된 게시물이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${boardList}" var="list" varStatus="stat">
								<tr align="center">
									<td>${list.pnum }</td>
									<td align="center"><a
										href="../board/view.do?b_id=${list.b_id}">${list.title}</a></td>
									<td>${list.writer_id}</td>
									<td>${list.boardDate}</td>
									<td>${list.visitcount}</td>
									<td><c:if test="${not empty list.ofile}">
											<a
												href="../board/download.do?ofile=${list.ofile}
					&nfile=${list.nfile}&b_id=${list.b_id}">[${list.ofile}]</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</form>
			<!-- 하단 메뉴(페이징 글쓰기) -->
			<table border="1" width="90%">
				<tr align="center">
					<td>${map.pagingStr}</td>
					<td width="100">
						<button type="button" class="btn btn-primary"
							onclick="location.href='../board/write.do';">글쓰기</button>
					</td>
				</tr>
			</table>
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
</body>
</html>





