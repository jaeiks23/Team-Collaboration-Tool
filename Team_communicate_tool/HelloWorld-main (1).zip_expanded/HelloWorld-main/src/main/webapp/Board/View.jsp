<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="DAO.CommentDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세보기</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="../css/styles.css" rel="stylesheet" />
<style>
th, td {
	padding-top: 10px;
	padding-bottom: 10px;
	padding-left: 10px;
	padding-right: 10px;
}
</style>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="../toolbar/toolbar_dark.jsp" />
	<div id="layoutSidenav_content">
		<main>
			<div class="container">
				<h2>게시판 상세보기</h2>

				<div class="row">
					<input type="hidden" name="b_id" value="${dto.b_id}" /> <input
						type="hidden" name="preOfile" value="${dto.ofile}" /> <input
						type="hidden" name="preNfile" value="${dto.nfile}" />
					<table border="1" width="90%" align="center"
						style='padding-top: 50px;'>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="writer_id"
								style="width: 150px;" value="${dto.writer_id}" readonly></td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" style="width: 100%;"
								value="${dto.title}" readonly /></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content"
									style="width: 100%; height: 200px;" readonly>${dto.content}</textarea></td>
						</tr>
						<tr>
							<td>첨부파일</td>
							<td><c:if test="${not empty dto.ofile}">
								${dto.ofile}
								<a
										href="../board/download.do?ofile=${dto.ofile}&nfile=${dto.nfile}&b_id=${dto.b_id}">[다운로드]</a>
									<button type="button" id="downbtn">다운로드</button>
								</c:if></td>
						</tr>
						<tr>
							<td>다운로드수</td>
							<td id="dcount">${dto.downcount}</td>
						</tr>
						<tr>
							<td>조회수</td>
							<td id="vcount">${dto.visitcount}</td>
						</tr>
					</table>

					<div class="container" style='padding-top: 50px;'>
						<div class="row">
							<table class="table table-striped"
								style="text-align: center; border: 1px solid #dddddd">
								<tbody>
									<tr>
										<td align="left" bgcolor="skyblue">댓글</td>
									</tr>
							</table>
						</div>
					</div>
					<!--댓글 화면 -->
					<table class="table table-bordered"
						style="text-align: center; border: 1px solid #dddddd">
						<tbody>
							<tr>
								<td align="left">
									<table border="1" width="100%">
										<tr align="center">
											<th>댓글번호</th>
											<th>작성자</th>
											<th>내용</th>
											<th>작성일</th>
											<th>삭제</th>
										</tr>
										<c:choose>
											<c:when test="${empty commentList}">
												<tr>
													<td colspan="6" align="center">등록된 댓글이 없습니다.</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${commentList}" var="list"
													varStatus="stat">
													<tr align="center">
														<td>${map.totalCount-((map.pageNum-1)*map.pageSize)-stat.index}</td>
														<td>${list.writer_id}</td>
														<td>${list.com_content}</td>
														<td>${list.com_date}</td>
														<td><input type="button"
															class="btn btn-danger pull-right"
															onclick="location.href='../comment/delete.do?com_id=${list.com_id }&b_id=${list.b_id }';"
															value="삭제"></td>

													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</table>
									<table border="1" width="100%">
										<tr align="center">
											<td>${map.pagingStr}</td>
										</tr>
									</table>
								</td>
							</tr>
								<form action="<%=request.getContextPath() %>/comment/write.do" method="post" style="display: inherit;">
							<tr>
								<td><input type="hidden" name="b_id" value=${dto.b_id }>
									<input type="hidden" name="writer_id" value="test" /> <input
									type="text" class="form-control" placeholder="댓글 쓰기"
									name="com_content" maxlength="300"></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" class="btn btn-success pull-right"
						value="댓글 쓰기">
					</form>
				</div>
			</div>
		</main>
	</div>



	<div align="center" style='padding-top: 50px; padding-bottom: 50px;'>
		<button type="button" class="btn btn-info"
			onclick="location.href='../board/list.do';">게시물 목록 바로가기</button>
		<button type="button" class="btn btn-secondary"
			onclick="location.href='../board/edit.do?noticeFlag=${dto.noticeFlag }&b_id=${param.b_id }';">
			게시물 수정하기</button>
		<button type="button" class="btn btn-dark"
			onclick="location.href='../board/delete.do?noticeFlag=${dto.noticeFlag }&b_id=${param.b_id }';">
			게시물 삭제하기</button>

	</div>




	<script src="http://code.jquery.com/jquery-latest.min.js">
	$('#downbtn').on("click", (e)=>{
		e.preventDefault();
		console.log('btn click');
		$.ajax({
			type:'post',
			async:false,
			url:'/HelloTeams/board/download.do',
			dataType:'text',
			data:{b_id:'${dto.b_id}'},
			succes: function(data,textStatus){
				console.log(data);
				$('#dcount').text(data);
			},
			error: function(data,textStatus){
				colsole.log('error',data,textStatus);
			}
		})
	});
</script>
</body>
</html>

