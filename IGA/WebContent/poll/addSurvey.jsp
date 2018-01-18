<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/IGA/css/style.css">
		<script src="/IGA/js/poll.js"></script>
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
		<script>
		  $( function() {
		    $( "#sortable" ).sortable();
		    $( "#sortable" ).disableSelection();
		  } );
		</script>
	</head>
	<body onload='addSurvey()'>

		<div id="openModal" class="modalbg">
		  <div class="dialog">
		    <a href="#close" title="Close" class="close">X</a>
		  	<h2>질문 순서 조정</h2>
				<ul id="sortable">
				</ul>
				<div style='text-align:right;'><a href="#close" title="Close" onclick='resort()' class='button1'>순서 조정 완료</a></div>
			</div>
		</div>

		<div class="d">
			<div class="container_M">
				<div id="table">
					<div class="row">
						<span class="cell admin">설문조사 관리자</span>
						<span class="cell id"><a id='id'></a>님 안녕하세요.&nbsp;&nbsp;&nbsp;<a style="color:white;" href="#" onclick="logout()">로그아웃</a></span>
					</div>
					<div class="row2">
						<span class="cell2 list g"><a href='adminpage.do'>설문지 목록</a>>설문지 추가</span>
						<span class="cell2 button g2"><input type="button"  onclick="saveSurvey()"value="설문지 저장"></span>
					</div>
				</div>
				<div class="line"></div>
			</div>
		</div>
		<div id="q" class="container_c">
		</div>
		
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js'></script>
		
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
	</body>
</html>