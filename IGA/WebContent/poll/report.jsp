<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/IGA/css/style.css">
		<script src="/IGA/js/poll.js"></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
	</head>
	<body onload="chart('${s_num}')">
		<div class="d">
		<div class="container_M">
			<div id="table">
				<div class="row">
					<span class="cell admin">설문조사 관리자</span>
					<span class="cell id">${id}님 안녕하세요.&nbsp;&nbsp;&nbsp;<a style="color:white;" href="#" onclick="logout()">로그아웃</a></span>
				</div>
				<div class="row2">
					<span class="cell2 list g"><a href='adminpage.do'>설문지 목록</a> >${title} 리포트</span>
					<span class="cell2 button g2"><input type="button"  onclick="location.href='addSurvey.do'"value="+ 설문지 추가"></span>
				</div>
			</div>
			<div class="line"></div>
		</div>
		</div>
		<div class="container_c">
		<div style='font-align:center; background:lightgray; padding:50px; width:200px; float:left;'>
			<h3>총 참여자</h3>
			<h3>${count}명</h3>
		</div>
		<div style='font-align:center; background:lightgray; padding:50px; width:200px; margin-left:380px;'>
			<h3>금일 참여자</h3>
			<h3>${ct}명</h3>
		</div>
		<br><br>
		<div id='chart'></div>	
		</div>
	</body>
</html>