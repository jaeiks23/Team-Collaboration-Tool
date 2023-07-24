<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" type="text/css"/>
<title>메인페이지</title>

</head>
<body class="sb-nav-fixed">
	<jsp:include page="../toolbar/toolbar_dark.jsp"/>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Main Page</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">Dashboard</li>
					</ol>
				<div class="row" style="min-height: 334px;">
                  <div class="col-xl-6">
                     <div class="card mb-4">
                        <div class="card-header">
                           <i class="fas fa-tasks me-1"></i> 나의 할일
                        </div>
                        <div class="card-body" style="overflow-y: scroll;">
                        	<ul style="list-style: none; display: flex; justify-content: space-evenly; font-weight: bold; background: lightgray; ">
                        		<li>남은 할일</li>
                        		<li>마감일</li>
                        	</ul>
                           <c:choose>
                              <c:when test="${mainTodo.size()==0}">
                                 <li style="list-style: none">할일 목록 없음.</li>
                              </c:when>
                              <c:otherwise>
                              <ul style="list-style: none;">
                                 <c:forEach items="${mainTodo}" var="list" varStatus="stat">
                                    <li>${list.section_Name} > ${list.todo_Content}</li>
                                 </c:forEach>
                              </ul>
                              </c:otherwise>
                           </c:choose>
                           <!-- private int section_Id;
	private String section_Name;
	private int todo_Id; // 할일 id
	private String part_Id; // 담당 ID
	private String todo_Content; // 작업 용
	private String todo_Start_Date;
	private String todo_End_Date;
	private String todo_info;
	private String todo_Status; //대기중, 진행중, 완료 /완료가 되면 체크 또는 체크하면 완료로 변환
	private String todo_Rank; -->

                        </div>
                     </div>
                  </div>
                  <div class="col-xl-6">
                     <div class="card mb-4">
                        <div class="card-header">
                           <i class="fas fa-chart-bar me-1"></i> 공지사항
                        </div>

                        <div class="card-body" style="overflow-y: scroll;">

                           <table width="90%" align="center">
                              <tr>
                                 <th class="tableHeader">번호</th>
                                 <th class="tableHeader">제목</th>
                                 <th class="tableHeader">작성자</th>
                                 <th class="tableHeader">내용</th>
                                 <th class="tableHeader">작성일</th>
                                 <th class="tableHeader">조회수</th>
                                 <th class="tableHeader">첨부파일</th>
                              </tr>
                              <c:choose>
                                 <c:when test="${empty mainBoard}">
                                    <tr>
                                       <td colspan="6" align="center">등록된 게시물이 없습니다.</td>
                                    </tr>
                                 </c:when>
                                 <c:otherwise>
                                    <c:forEach items="${mainBoard}" var="list" varStatus="stat">
                                       <tr align="center">
                                          <td>${list.pnum }</td>
                                          <td align="center"><a
                                             href="<%=request.getContextPath()%>/board/view.do?b_id=${list.b_id}">${list.title}</a></td>
                                          <td>${list.writer_id}</td>
                                          <td>${list.content}</td>
                                          <td>${list.boardDate}</td>
                                          <td>${list.visitcount}</td>
                                          <td><c:if test="${not empty list.ofile}">
                                                <a
                                                   href="../board/download.do?ofile=${list.ofile}&nfile=${list.nfile}&b_id=${list.b_id}">[${list.ofile}]</a>
                                             </c:if></td>
                                       </tr>
                                    </c:forEach>
                                 </c:otherwise>
                              </c:choose>
                           </table>
                        </div>
                     </div>
                  </div>
               </div>
					<div class="card mb-5">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> 캘린더
							<div id="calendar"></div>
						</div>
						<div class="card-body"></div>
					</div>
				</div>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
				</div>
			</footer>
		</div>
	</div>
	<style>
.tableHeader {
	background-color: lightgray;
	text-align: center;
	"
}
</style>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/js/scripts.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
		crossorigin="anonymous"></script>


</body>
</html>
