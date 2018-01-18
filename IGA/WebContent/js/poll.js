

function loginPro(){
	var id = $('#id').val();
	var pw = $('#pw').val();
	$.ajax(
			{
				type: "POST",
				url : "loginPro.do",
				data : {
					id : id,
					pw : pw
				},
				dataType : 'text',
				success : function(data){
					if (data == "") {
						return;
					}
					var result = eval("(" + data + ")");
					if(result.data[0].rs==0){
						$('#check').empty();
						var noid = "아이디가 올바르지 않습니다.";
						$('#check').addClass('div');
						$('#check').append(noid);
					}else if(result.data[0].rs==-1){
						$('#check').empty();
						var nopw = "비밀번호가 올바르지 않습니다.";
						$('#check').addClass('div');
						$('#check').append(nopw);
					}else if(result.data[0].rs==1){
						localStorage.setItem("id", result.data[0].id);
						location.href = "adminpage.do";
					}
				},
				error : function(e) {
					$('#check').html(e);
				}
			}		
		);
}

function logout(){
	localStorage.removeItem("id");
	location.href = "login.do"
}

function getSurvey(){
	var id = localStorage.getItem("id");
	$('#id').append(id);
	$.ajax(
			{
				type: "POST",
				url : "getSurvey.do",
				data : {
					id : id,
				},
				dataType : 'text',
				success : function(data){
					if (data == "") {
						return;
					}
					var result = eval("(" + data + ")");
					if(result.data[0].title != "fs"){
						for(var i = 0; i<result.data.length; i++){
							var op = "<div class='r2'>"+
							"<span class='c col11'>"+result.data[i].title+"</span>"+
							"<span class='c col22'>"+result.data[i].ct+"</span>"+
							"<span class='c col33'><a href='#'onclick='confirmDelete("+result.data[i].s_num+",\""+id+"\")'>삭제하기</a></span>"+
							"<span class='c col33'><a href='#'onclick='location.href=\"report.do?s_num="+result.data[i].s_num+"&id="+id+"\"'>리포트</a></span>"+
							"<span class='c col33'><a href='#'onclick='location.href=\"memberlist.do?s_num="+result.data[i].s_num+"&id="+id+"\"'>참여자 로그</a></span>"+
							"<span class='c col44'><a href='#'onclick='location.href=\"surveyMember.do?s_num="+result.data[i].s_num+"\"'>배포주소로 바로가기</a></span>"+
							"</div>";
							$('#t').append(op);
						}
					}else{
						var op = "<div class='r2'><span class='c noS'>등록하신 설문이 없습니다. 등록해주시기바랍니다.</span></div>";
						$('#t').append(op);
					}	
				},
				error : function(e) {
					$('#check').html(e);
				}
			}		
		);
}


function confirmDelete(ds_num, id)
{
	
	 if(confirm("삭제하시겠습니까?")){
	        location.href = "delete.do?s_num="+ds_num+"&id="+id
	        return true;
	    } else {
	        return false;
	    }
}


var Qnum = 1;
var listQ = [];
function addSurvey(){
	var id = localStorage.getItem("id");
	$('#id').append(id);
	var question = "<h4>이름</h4>"+
			"<input id='surveyName' type='text' style='font-size:17px; height: 40px; width: 1110px;'class='inputT'>"+
			"<br><br>"+
			"<h4>질문</h4>"+
			"<div id='addq'><div id=\"qsort_"+Qnum+"\"><div id=\"delq"+Qnum+"\"><div style='text-align:right;'><a class='button1' onclick='addlist(\""+Qnum+"\")' href='#openModal'>순서변경</a>"+"<a class='button1' href='#'onclick=\"delQ('"+Qnum+"')\">삭제</a></div>"+
			"<div class='Q' id=\"q"+Qnum+"\">"+
				"<h5>질문 내용</h5>"+
				"<input id=\"q"+Qnum+"_name\" type='text' style='font-size:17px; height: 40px; width: 800px;'class='inputT'>"+
				"<br><br><h5>답변 형태</h5>"+
				"<select id=\"q"+Qnum+"_form\"  onchange='select(\""+Qnum+"\")' style='font-size:17px; height: 40px; width: 200px;'class='inputT'>"+
					"<option value='0'>Yes/No</option>"+
					"<option value='1'>한개 선택</option>"+
					"<option value='2'>여러 개 선택</option>"+
				"</select>"+
				"<br><br><h5>선택 항목</h5>"+
				"<div id=\"q"+Qnum+"_select\">"+
					"<div id=\"q"+Qnum+"_table\">"+
						"<div class='r'>"+
							"<span class='c3 s1'>YES</span>"+
							"<span class='c3 s2'><input id=\"q"+Qnum+"_y\" type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
							"<span class='c3 s3'></span>"+
						"</div>"+
						"<div class='r'>"+
							"<span class='c3 s1'>NO</span>"+
							"<span class='c3 s2'><input id=\"q"+Qnum+"_n\" type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
							"<span class='c3 s3'></span>"+
						"</div>"+
					"</div>"+
				"</div>"+
				"<br>"+
				"<div id=\"q"+Qnum+"_button\" style='text-align:center; margin-top:-13px;'></div>"+
			"</div><br></div><br></div></div>"+
			"<div style='text-align:center;'><input type='button' onclick='addQ()' value='+질문 추가'></div>";
	$('#q').append(question);
	listQ.push(Qnum);
	Qnum++;
}

function addlist(q_n){
	$('#sortable').empty();
	for(var i =0; i<listQ.length; i++){
		var qname = $("#q"+listQ[i]+"_name").val();
		var list = "<li value=\""+listQ[i]+"\">"+qname+"</li>";
		$('#sortable').append(list);
	}
}

var resortn = [];
function resort(){
	resortn = [];
	var $sortable = $('#sortable');
	var $li = $($sortable).find('li');
	var li = $li.length;
	for(var i=0; i<li; i++){
		var listn = $li.eq(i).val();
		resortn.push(listn);
	}
	sort();
}

function sort(){
	var same;
	for(var i =0; i<listQ.length-1; i++){
		$( "#qsort_"+resortn[i] ).insertBefore($( "#qsort_"+listQ[0] ));
	}
	relist();
}

function relist(){
	var length = listQ.length;
	listQ = [];
	for(var i =0; i<length; i++){
		listQ.push(resortn[i]);
	}
}

function delQ(qn){
	$('#delq'+qn).remove();
	listQ = jQuery.grep(listQ, function(value) { return value != qn; });
}

function addQ(){
		var addq = "<div id=\"qsort_"+Qnum+"\"><div id=\"delq"+Qnum+"\"><div style='text-align:right;'><a class='button1' onclick='addlist(\""+Qnum+"\")' href='#openModal'>순서변경</a>"+"<a class='button1' href='#'onclick=\"delQ('"+Qnum+"')\">삭제</a></div>"+
					"<div class='Q' id=\"q"+Qnum+"\">"+
						"<h5>질문 내용</h5>"+
						"<input id=\"q"+Qnum+"_name\" type='text' style='font-size:17px; height: 40px; width: 800px;'class='inputT'>"+
						"<br><br><h5>답변 형태</h5>"+
						"<select id=\"q"+Qnum+"_form\"  onchange='select(\""+Qnum+"\")' style='font-size:17px; height: 40px; width: 200px;'class='inputT'>"+
							"<option value='0'>Yes/No</option>"+
							"<option value='1'>한개 선택</option>"+
							"<option value='2'>여러 개 선택</option>"+
						"</select>"+
						"<br><br><h5>선택 항목</h5>"+
						"<div id=\"q"+Qnum+"_select\">"+
							"<div id=\"q"+Qnum+"_table\">"+
								"<div class='r'>"+
									"<span class='c3 s1'>YES</span>"+
									"<span class='c3 s2'><input id=\"q"+Qnum+"_y\" type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
									"<span class='c3 s3'></span>"+
								"</div>"+
								"<div class='r'>"+
									"<span class='c3 s1'>NO</span>"+
									"<span class='c3 s2'><input id=\"q"+Qnum+"_n\" type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
									"<span class='c3 s3'></span>"+
								"</div>"+
							"</div>"+
						"</div>"+
						"<br>"+
						"<div id=\"q"+Qnum+"_button\" style='text-align:center; margin-top:-13px;'></div>"+
					"</div><br></div></div>";
	$('#addq').append(addq);
	listQ.push(Qnum);
	Qnum++;
}





var Anum = 1;
var listS = [];
function select(num){
	var form = "#q"+num+"_form";
	var select = "#q"+num+"_select";
	var table = "#q"+num+"_table";
	var button = "#q"+num+"_button";
	var aform = $(form+" option:selected").val();
	if(aform==0){
		$(select).empty();
		$(button).empty();
		//YES/NO
		var option = "<div id=\"q"+num+"_table\">"+
						"<div class='r'>"+
							"<span class='c3 s1'>YES</span>"+
							"<span class='c3 s2'><input id=\"q"+num+"_y\" type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
							"<span class='c3 s3'></span>"+
						"</div>"+
						"<div class='r'>"+
							"<span class='c3 s1'>NO</span>"+
							"<span class='c3 s2'><input id=\"q"+num+"_n\" type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
							"<span class='c3 s3'></span>"+
						"</div>"+
					"</div>";
		$(select).append(option);
	}else if(aform==1 || aform==2){
		$(select).empty();
		$(button).empty();
		//한개만 선택
		var option = "<div id=\"q"+num+"_table\">"+
						"<div value=\""+Anum+"\" id=\"a"+num+"_"+Anum+"\" class='r'>"+
							"<span class='c3 s4'><input id=\"s"+num+"_"+Anum+"\"type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
							"<span class='c3 s3'><a href='#' onclick=\"delA('"+num+"','"+Anum+"')\">삭제</a></span>"+
						"</div></div>";
		$(select).append(option);
		listS.push(Anum);
		Anum++;
		var option2 = 	"<div value=\""+Anum+"\" id=\"a"+num+"_"+Anum+"\" class='r'>"+
							"<span class='c3 s4'><input id=\"s"+num+"_"+Anum+"\"type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
							"<span class='c3 s3'><a href='#' onclick=\"delA('"+num+"','"+Anum+"')\">삭제</a></span>"+
						"</div>";
		$(table).append(option2);
		listS.push(Anum);
		Anum++;
		var addb = "<input type='button' value='선택 항목 추가' onclick=\"addSelect('"+num+"')\">";
		$(button).append(addb);
	}
}

function addSelect(n){
	var t = "#q"+n+"_table";
	var addoption = "<div value=\""+Anum+"\" id=\"a"+n+"_"+Anum+"\" class='r'>"+
						"<span class='c3 s4'><input id=\"s"+n+"_"+Anum+"\"type='text' style='font-size:17px; height: 40px; width: 730px;' class='inputT'></span>"+
						"<span class='c3 s3'><a href='#' onclick=\"delA('"+n+"','"+Anum+"')\">삭제</a></span>"+
					"</div>";
	listS.push(Anum);
	Anum++;
	$(t).append(addoption);
}

function delA(num, an){
	$('#a'+num+"_"+an).remove();
	listS = jQuery.grep(listS, function(value) { return value != an; });
}

function saveSurvey(){
	var surveyName = $('#surveyName').val();
	var id = localStorage.getItem("id");
	$.ajax(
			{
				type: "POST",
				url : "saveSurvey.do",
				data : {
					surveyName : surveyName,
					id : id
				},
				dataType : 'text',
				success : function(data){
					if (data == "") {
						return;
					}
					var result = eval("(" + data + ")");
					if(result.data[0].s_num != 'fs'){
						insertQ(result.data[0].s_num);
					}else{
						alert('설문지 저장에 실패하셨습니다.\n값을 올바르게 입력하였는지 확인해 주세요.')
					}
				},
				error : function(e) {
					$('#check').html(e);
				}
			}		
		);
	
}

function insertQ(s_num){
	for(var i =0; i<listQ.length; i++){
		var liq9 = listQ[i];
		var qname = $("#q"+listQ[i]+"_name").val();
		var qform = $("#q"+listQ[i]+"_form option:selected").val();
		iQ(qname,qform,s_num,liq9);
	}
}
	
function iQ(qname5,qform5,s_num5,liq5){
		$.ajax(
				{
					type: "POST",
					url : "saveQ.do",
					data : {
						qname : qname5,
						qform : qform5,
						s_num : s_num5
					},
					dataType : 'text',
					success : function(data){
						if (data == "") {
							return;
						}
						var result = eval("(" + data + ")");
						if(result.data[0].s_q_num != 'fs'){
							insertA(result.data[0].s_q_num, liq5, qform5);
						}
					},
					error : function(e) {
						alert('설문지 저장에 실패하셨습니다.\n값을 올바르게 입력하였는지 확인해 주세요.')
						$('#check').html(e);
					}
				}		
			);
	}

function insertA(s_q_num,liq2,qform2){
	var $qtable = $('#q'+liq2+'_table');
	var $r = $($qtable).find('div');
	var alength = $r.length;
	
	var a;
	if(qform2==0){
		var yn = [];
		var y = $("#q"+liq2+"_y").val();
		yn.push(y);
		var n = $("#q"+liq2+"_n").val();
		yn.push(n);
		for(var i=0; i<yn.length; i++){
			a = yn[i];
			//ina(a,s_q_num);
			$.ajax(
					{
						type: "POST",
						url : "saveA.do",
						data : {
							s_q_num : s_q_num,
							a : a
						},
						dataType : 'text',
						success : function(data){
							if (data == "") {
								return;
							}
							var result = eval("(" + data + ")");
							if(result.data[0].s_q_num == 'fs'){
								alert('설문지 저장에 실패하셨습니다.\n값을 올바르게 입력하였는지 확인해 주세요.')
							}
						},
						error : function(e) {
							$('#check').html(e);
						}
					}		
				);
		}
	}else if(qform2==1 || qform2==2){
		for(var i=0; i<alength; i++){
			var anu = $r.eq(i).attr('value');
			a = $("#s"+liq2+"_"+anu).val();
			//ina(a,s_q_num);
			$.ajax(
					{
						type: "POST",
						url : "saveA.do",
						data : {
							s_q_num : s_q_num,
							a : a
						},
						dataType : 'text',
						success : function(data){
							if (data == "") {
								return;
							}
							var result = eval("(" + data + ")");
							if(result.data[0].s_q_num == 'fs'){
								alert('설문지 저장에 실패하셨습니다.\n값을 올바르게 입력하였는지 확인해 주세요.')
							}
						},
						error : function(e) {
							$('#check').html(e);
						}
					}		
				);
		}
	}
	location.href = "adminpage.do";
}


function surveyMember(s_num){
	var id = localStorage.getItem("id");
	$.ajax(
			{
				type: "POST",
				url : "getSurveyInfo.do",
				data : {
					s_num : s_num,
					id : id
				},
				dataType : 'text',
				success : function(data){
					if (data == "") {
						return;
					}
					var result = eval("(" + data + ")");
					var s_name = result.data[0].title;
					$('#s_title').append(s_name);
				},
				error : function(e) {
					$('#check').html(e);
				}
			}		
		);
}

function startSurvey(s_num){
	var member_name = $('#member_name').val();
	$.ajax(
			{
				type: "POST",
				url : "startSurvey.do",
				data : {
					member_name : member_name,
					s_num : s_num
				},
				dataType : 'text',
				success : function(data){
					if (data == "") {
						return;
					}
					var result = eval("(" + data + ")");
					var check = result.data[0].check;
					if(check==0){
						//아이디 없다
						location.href = "partSurvey.do?s_num="+s_num+"&m_name="+member_name;
					}else{
						//아이디 있다
						$('#check').empty();
						var yesid = "이미 참여하셨습니다.";
						$('#check').addClass('div');
						$('#check').append(yesid);
					}
				},
				error : function(e) {
					$('#check').html(e);
				}
			}		
		);
	
}

var arrqn = [];
function partSurvey(s_num,m_name){
	var hello = "안녕하세요.&nbsp;&nbsp;"+m_name+"님 설문을 시작합니다";
	$('#hello').append(hello);
	
	$.ajax(
			{
				type: "POST",
				url : "getSurveyQ.do",
				data : {
					s_num : s_num
				},
				dataType : 'text',
				success : function(data){
					if (data == "") {
						return;
					}
					var result = eval("(" + data + ")");
					for(var i = 0; i<result.data.length; i++){
						var c = i+1;
						var chose = "<div value='"+result.data[i].q_num+"' style='background:#efefef; padding:15px 5px 5px 15px;'>"+
											"<a style='font-size:20px; color:gray'>질문 "+c+". </a> <a style='font-size:25px;'>"+result.data[i].q+"</a>"+
											"<br><div id='msg"+result.data[i].q_num+"'></div>"+
											"<br>"+
											"<div id = 'select"+result.data[i].q_num+"' style='font-size:20px;'>"+
											"</div>"+
										"</div><br><br>";
						arrqn.push(result.data[i].q_num);
						$('#q').append(chose);
						appendform(result.data[i].aform,result.data[i].q_num);
					}
					var com = "<input type='button' onclick = 'comSurvey(\""+m_name+"\")' value='설문조사 완료'>";
					$('#com').append(com);
				},
				error : function(e) {
					$('#check').html(e);
				}
			}		
		);
}

function appendform(af,q_num){
	var type;
	if(af==0){
		var msg = "<a style='font-size:20px; color:#e55d87'>Yes/No 로 답해주세요.</a>";
		$('#msg'+q_num).append(msg);
		type='radio';
		$('#select'+q_num).val(type);
		$.ajax(
				{
					type: "POST",
					url : "getSurveyA.do",
					data : {
						q_num : q_num
					},
					dataType : 'text',
					success : function(data){
						if (data == "") {
							return;
						}
						var result = eval("(" + data + ")");
						for(var i = 0; i<result.data.length; i++){
							var msg2 = "<input type='"+type+"' name='a"+q_num+"' value='"+result.data[i].a_num+"'>"+result.data[i].a+"<br>";
							$('#select'+q_num).append(msg2);
						}
					},
					error : function(e) {
						$('#check').html(e);
					}
				}		
			);
		
	}else if(af==1){
		var msg = "<a style='font-size:20px; color:#e55d87'>한개만 선택 가능합니다.</a>";
		$('#msg'+q_num).append(msg);
		type='radio';
		$('#select'+q_num).val(type);
		$.ajax(
				{
					type: "POST",
					url : "getSurveyA.do",
					data : {
						q_num : q_num
					},
					dataType : 'text',
					success : function(data){
						if (data == "") {
							return;
						}
						var result = eval("(" + data + ")");
						for(var i = 0; i<result.data.length; i++){
							var msg2 = "<input type='"+type+"' name='a"+q_num+"' value='"+result.data[i].a_num+"'>"+result.data[i].a+"<br>";
							$('#select'+q_num).append(msg2);
						}
					},
					error : function(e) {
						$('#check').html(e);
					}
				}		
			);
	}else if(af==2){
		var msg = "<a style='font-size:20px; color:#e55d87'>여러 개 선택 가능합니다.</a>";
		$('#msg'+q_num).append(msg);
		type='checkbox'
		$('#select'+q_num).val(type);
			$.ajax(
					{
						type: "POST",
						url : "getSurveyA.do",
						data : {
							q_num : q_num
						},
						dataType : 'text',
						success : function(data){
							if (data == "") {
								return;
							}
							var result = eval("(" + data + ")");
							for(var i = 0; i<result.data.length; i++){
								var msg2 = "<input type='"+type+"' name='a"+q_num+"' value='"+result.data[i].a_num+"'>"+result.data[i].a+"<br>";
								$('#select'+q_num).append(msg2);
							}
								
						},
						error : function(e) {
							$('#check').html(e);
						}
					}		
				);
	}
	
	
}


function comSurvey(mname){
	for(var i =0; i<arrqn.length; i++){
		var q1 = arrqn[i];
		var fm = $('#select'+q1).val();
		var che;
		if(fm=='checkbox'){
			$('input[name=a'+q1+']:checked').each(function() {
				 che = $(this).val();
				 insertCount(che,mname);
			});
		}else if(fm=='radio'){
			che=$('input:radio[name=a'+q1+']:checked').val();
			insertCount(che,mname);
		}
	}
}



function insertCount(cnt,mn){
	 $.ajax(
				{
					type: "POST",
					url : "insertCount.do",
					data : {
						anum : cnt
					},
					dataType : 'text',
					success : function(data){
						 location.href = "complete.do?id="+mn;
					},
					error : function(e) {
						$('#check').html(e);
					}
				}		
			);

}


function chart(s_num){
	$.ajax(
			{
				type: "POST",
				url : "getSurveyQ.do",
				data : {
					s_num : s_num
				},
				dataType : 'text',
				success : function(data){
					if (data == "") {
						return;
					}
					var result = eval("(" + data + ")");
					for(var i = 0; i<result.data.length; i++){
						var c = i+1;
						var ch = "<a style='font-size:20px; color:gray'>질문 "+c+". </a> <a style='font-size:25px;'>"+result.data[i].q+"</a>"+
								"</div><div value='"+result.data[i].q_num+"' id='table'>"+
									"<div id='per"+result.data[i].q_num+"'><div class='r'>"+
										"<span class='c col1'>항목 이름</span>"+
										"<span class='c col2'>응답자 수</span>"+
										"<span class='c col3'>응답 비율</span>"+
									"</div></div>"+
								"</div><br><br>";
						arrqn.push(result.data[i].q_num);
						$('#chart').append(ch);
						appendChart(result.data[i].q_num);
					}
				},
				error : function(e) {
					$('#check').html(e);
				}
			}		
		);
	

}

function appendChart(qnum3){
	var total = 0;
		$.ajax(
				{
					type: "POST",
					url : "getSurveyA.do",
					data : {
						q_num : qnum3
					},
					dataType : 'text',
					success : function(data){
						if (data == "") {
							return;
						}
						var result = eval("(" + data + ")");
						
						for(var i = 0; i<result.data.length; i++){
							total +=  parseInt(result.data[i].count);
							
							var ch2 = "<div class='r2'>"+
										"<span class='c col11'>"+result.data[i].a+"</span>"+
										"<span class='c col22'><a id='ct"+qnum3+i+"'>"+result.data[i].count+"<a/></span>"+
										"<span class='c col33'><a id='percent"+qnum3+i+"'></a></span>"+
									"</div>";
							$('#per'+qnum3).append(ch2);
							
						}
						
						percent(qnum3,total);
					},
					error : function(e) {
						$('#check').html(e);
					}
				}		
			);

}

function percent(qnum3,tt){
	var $l = $('#per'+qnum3)
	var $ll = $($l).find('div');
	var lll=$ll.length;
	for(var i=0; i<lll; i++){
		var p = $('#ct'+qnum3+i).text();
		var pe = p/tt*100;
		var perc = Math.round(pe);
		var percen = perc+"%";
		$('#percent'+qnum3+i).append(percen);
	}
	
	
}
