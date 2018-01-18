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
	<body onload="partSurvey('${s_num}','${m_name}')">
		<div class="container_c">
			<h2 id = 'hello'></h2>
			<div class='block'></div>
			<div id='q'>
				
			</div>
			<div id='com' style='text-align:center;'></div>
		</div>
	</body>
</html>