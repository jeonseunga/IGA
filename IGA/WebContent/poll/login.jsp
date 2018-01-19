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
	<body>
		<div class="container">
			<h1>Survey Admin_login</h1>
			<div class="block"></div>
			<div align="center">
				<div>
					<label style="width:80px; text-align: center; display: inline-block">아 이 디</label> 
					<input type="text" class="input" id="id"/>
				</div>
				<div>
					<label style="width:80px; text-align: center; display: inline-block">비밀번호</label>
					<input type="password" class="input" id="pw"/>
				</div>
				<div>
					<input type="button" value="로그인" onclick="loginPro()">
				</div>
				<div id="check"></div>
			</div>
		</div>
	</body>
</html>