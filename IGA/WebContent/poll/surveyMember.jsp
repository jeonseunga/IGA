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
	<body onload="surveyMember('${s_num}')">
		<div class="container">
			<h1 id = 's_title'></h1>
			<div class="block"></div>
			<div align="center">
				<div>
					<label style="width:80px; text-align: center; display: inline-block">이름</label> 
					<input type="text" class="input" id="member_name"/>
				</div>
				<div>
					<input type="button" value="설문조사 시작" onclick="startSurvey('${s_num}')">
				</div>
				<div id="check"></div>
			</div>
		</div>
	</body>
</html>