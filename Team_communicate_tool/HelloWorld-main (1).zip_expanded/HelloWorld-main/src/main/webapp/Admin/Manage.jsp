<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet" type="text/css" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous">
	
</script>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

header {
	background-color: #333;
	padding: 10px 20px;
	color: white;
	font-size: 20px;
}

.box {
	border: 1px solid black;
	padding: 10px;
	text-align: center;
	height: 350px;
}

.border {
	border: 1px solid black;
}

.tall {
	height: 700px;
}

.div_style {
	width: 30%;
}

.flex_manager {
	display: flex;
	margin-top: 43px;
	justify-content: space-around;
	height: 100%;
}
  	.container {
        display: flex;
        flex-direction: column;
        border: 1px solid black;
        padding: 10px;
        width: 100%; 
        height: 100%
        
    }
    .box {
        flex: 1;
        margin: 10px auto;
        padding: 10px;
        overflow: auto;
        max-width: 80%;
    }
    #invite-url {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .large-box {
        border: 1px solid black;
        padding: 10px;
        height: 100%;
</style>
</head>
<body>
	<jsp:include page="../toolbar/toolbar.jsp" />
	<div id="layoutSidenav_content">
		<main>
			<header> 관리자 페이지 </header>
			<div class="flex_manager">
				<div class="border div_style"></div>
				<div class="border div_style"></div>
				<div class="border div_style">
					<div class="box">
						<h2>초대장 URL</h2>
						<div class="container" align="center">
							<div class="box large-box" align="center">
								<h3>URL 생성 위치</h3>
								<div id="url-location" style="display: none;">
									URL 생성 위치를 입력하세요: <input type="text" id="location-input">
								</div>
							</div>
							<div class="box">
								<div>
									<button id="invite-btn">초대 URL 생성</button>
									<div id="invite-url" style="display: none;"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="box">
						<form action="../updateDdate.do" method="post">
							<ul>
								<li>종료일&nbsp;:&nbsp; <input type="text" id="end-date"
									name="end_date"> &nbsp;&nbsp;
									<button type="submit" id="update-btn">수정</button>
								</li>
							</ul>
						</form>


					</div>
				</div>
			</div>
		</main>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="script.js"></script>
	<script>
		$(function() {
			$("#invite-btn").click(
				function() {
					var name = $("#name-input").val();
					var date = $("#date-input").val();
					var location = $("#location-input").val();
					$("#url-location").empty().text(
							"URL 생성 위치: " + location);
					$("#url-location").show().text(
							"https://example.com/invite?name=" + name
									+ "&date=" + date);
					$("#url-location").show();
					// $("#invite-url").show().text
					// ("https://example.com/invite?name=" + name + "&date=" + date).prop("hidden", true); 
			});
		});
		$(document).ready(
				function() {
					$.datepicker.regional['ko'] = {
						closeText : '닫기',
						prevText : '이전달',
						nextText : '다음달',
						currentText : '오늘',
						monthNames : [ '1월(JAN)', '2월(FEB)', '3월(MAR)',
								'4월(APR)', '5월(MAY)', '6월(JUN)', '7월(JUL)',
								'8월(AUG)', '9월(SEP)', '10월(OCT)', '11월(NOV)',
								'12월(DEC)' ],
						monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
								'7월', '8월', '9월', '10월', '11월', '12월' ],
						dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
						dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
						dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
						weekHeader : 'Wk',
						dateFormat : 'yy-mm-dd',
						firstDay : 0,
						isRTL : false,
						showMonthAfterYear : true,
						yearSuffix : '',
						showOn : 'both',
						buttonText : "달력",
						changeMonth : true,
						changeYear : true,
						showButtonPanel : true,
						yearRange : 'c-20:c+20',
					};
					$.datepicker.setDefaults($.datepicker.regional['ko']);

					$('#end-date').datepicker();
				});
	</script>

</body>
</html>
