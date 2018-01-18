<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/IGA/css/style.css">
		<script src="/IGA/js/poll.js"></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
	</head>
	<body onload="getSurvey()">
		<div class="d">
		<div class="container_M">
			<div id="table">
				<div class="row">
					<span class="cell admin">설문조사 관리자</span>
					<span class="cell id"><a id='id'></a>님 안녕하세요.&nbsp;&nbsp;&nbsp;<a style="color:white;" href="#" onclick="logout()">로그아웃</a></span>
				</div>
				<div class="row2">
					<span class="cell2 list g"><a>설문지 목록 ></a></span>
					<span class="cell2 button g2"><input type="button"  onclick="location.href='addSurvey.do'"value="+ 설문지 추가"></span>
				</div>
			</div>
			<div class="line"></div>
		</div>
		</div>
		<div class="container_c">
			<div id="table">
				<div class="r">
					<span class="c col1">설문지 이름</span>
					<span class="c col2">질문 갯수</span>
					<span class="c col3">설문지 삭제</span>
					<span class="c col3">리포트</span>
					<span class="c col3">로그</span>
					<span class="c col4">설문지 배포</span>
				</div>
			</div>
			<div id="t">
			</div>
		</div>
	</body>
</html>