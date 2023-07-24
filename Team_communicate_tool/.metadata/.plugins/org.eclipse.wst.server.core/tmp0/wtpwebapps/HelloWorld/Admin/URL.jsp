<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
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
<script>
$(function(){    
    $("#invite-btn").click(function(){ 
        var name = $("#name-input").val();
        var date = $("#date-input").val();
        var location = $("#location-input").val(); 
        $("#url-location").empty().text("URL 생성 위치: " + location); 
        $("#url-location").show().text("https://example.com/invite?name=" + name + "&date=" + date);
        $("#url-location").show();
       // $("#invite-url").show().text
       // ("https://example.com/invite?name=" + name + "&date=" + date).prop("hidden", true); 
    });
});
</script>
</head>
<body>
    <h2>초대장 URL</h2>
    <div class="container" align="center">
        <div class="box large-box" align="center">
            <h3>URL 생성 위치</h3>
            <div id="url-location" style="display:none;">
                URL 생성 위치를 입력하세요: <input type="text" id="location-input">
            </div>
        </div>
        <div class="box">
            <div>
                <button id="invite-btn">초대 URL 생성</button>
                <div id="invite-url" style="display:none;"></div>
            </div>
        </div>
    </div>
</body>
</html>