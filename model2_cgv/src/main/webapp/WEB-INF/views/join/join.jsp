<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link rel="stylesheet"  href="http://localhost:9000/model2_cgv/resources/css/cgv.css">
<script src="http://localhost:9000/model2_cgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/model2_cgv/resources/js/cgv.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
		/*********************
			회원가입 폼 유효성 체크
		**********************/
		$("#btnJoin").click(function(){		
			if($("#id").val() == ""){
				alert("아이디를 입력해주세요");
				$("#id").focus();
				return false;
			}else if($("#pass").val() == ""){
				alert("패스워드를 입력해주세요");
				$("#pass").focus();
				return false;
			}else if($("#cpass").val() == ""){
				alert("패스워드 확인을 입력해주세요");
				$("#cpass").focus();
				return false;
			}else if($("#name").val() == ""){
				alert("성명을 입력해주세요");
				$("#name").focus();
				return false;
			}else if($("input[name='gender']:checked").length == 0){
				alert("성별을 선택해주세요");
				return false;
			}else if($("#email1").val() == ""){
				alert("이메일을 입력해주세요");
				$("#email1").focus();
				return false;
			}else if($("#email2").val() == ""){
				alert("이메일 주소를 선택해주세요");
				$("#email3").focus();
				return false;
			}else if($("#addr1").val() == ""){
				alert("주소를 입력해주세요");
				$("#addr1").focus();
				return false;
			}else if($("#addr2").val() == ""){
				alert("상세주소를 입력해주세요");
				$("#addr2").focus();
				return false;
			}else if($("input[name='hp']:checked").length == 0){
				alert("통신사를 선택해주세요");
				return false;
			}else if($("#pnum1").val() == "default"){
				alert("폰번호를 선택해주세요");
				$("#pnum1").focus();
				return false;
			}else if($("#pnum2").val() == ""){
				alert("폰번호를 입력해주세요");
				$("#pnum2").focus();
				return false;
			}else if($("#pnum3").val() == ""){
				alert("마지막 폰번호를 입력해주세요");
				$("#pnum3").focus();
				return false;
			}else if($("input[name='hobby']:checked").length == 0){
				alert("취미를 선택해주세요");
				return false;
			}else{
				//서버전송
				joinForm.submit();
			}
		}); //click()-end
		
		
		/*********************
			비밀번호, 비밀번호 확인 비교
		**********************/
		$("#cpass").on("blur",()=>{
			if($("#pass").val() != "" && $("#cpass").val() != ""){
				if($("#pass").val() == $("#cpass").val()){
					$("#passCheckMsg").text("*비밀번호가 동일합니다.").css("color","blue").css("font-size","12px");
				}else{
					$("#passCheckMsg").text("*비밀번호가 동일하지 않습니다. 다시 입력해주세요.").css("color","red").css("font-size","12px");
					$("#cpass").val("");
					$("#pass").val("").focus();
				}
			}//else{} --> 회원가입 폼의 유효성 체크로 진행됨
		}); //blur()-end
		
		
		/*********************
			이메일 주소 선택
		**********************/
		$("#email3").change(()=>{
			if($("#email3").val() == "default"){
				alert("이메일 주소를 선택해주세요");
				$("#email3").focus();
				$("#email2").val("");
			}else if($("#email3").val() == "self"){
				$("#email2").val("").focus();
			}else{
				$("#email2").val($("#email3").val());
			}	
		}); //change()-end
		
		
		/*********************
			회원가입 - 주소찾기
		 **********************/
		$("#btnSearchAddr").click(function(){
			new daum.Postcode({
		        oncomplete: function(data) {
		            //alert(data.address);
		            $("#zonecode").val(data.zonecode);
		            $("#addr1").val(data.address);
		            $("#addr2").focus();
		        }
		    }).open(); 
		}); //addr.click()-end
	}); // ready function - end
</script>
</head>
<body>
<!-------------------->
<!-- header Include -->
<!-------------------->
<iframe src="http://localhost:9000/model2_cgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>

<!-------------------->
<!----- content ------>
<!-------------------->
<div class="content">
	<h1>회원가입</h1>
	<form name="joinForm" action="joinCheck.do" method="post">
		<ul>
			<li>
				<label>아이디</label>
				<input type="text" name="id" id="id" placeholder="*영문자숫자포함 8자이상">
				<button type="button" class="btn_style" id="idCheck">중복확인</button>
			</li>
			<li>
				<label>비밀번호</label>
				<input type="password" name="pass" id="pass">
			</li>
			<li>
				<label>비밀번호 확인</label>
				<input type="password" name="cpass" id="cpass" >
				<span id="passCheckMsg">*비밀번호를 다시 입력해주세요</span>
			</li>
			<li>
				<label>성명</label>
				<input type="text" name="name" id="name">
			</li>
			<li>
				<label>성별</label>
				<input type="radio" name="gender" value="m"><span>남자</span>
				<input type="radio" name="gender" value="f"><span>여자</span>
			</li>
			<li>
				<label>이메일</label>
				<input type="text" name="email1" id="email1"><span>@</span>
				<input type="text" name="email2" id="email2">
				<select id="email3" >
					<option value="default">선택</option>
					<option value="naver.com">네이버</option>
					<option value="gmail.com">구글</option>
					<option value="daum.net">다음</option>
					<option value="self">직접입력</option>
				</select>
			</li>
			<li>
				<label>주소</label>
				<input type="text" name="zonecode" id="zonecode" placeholder="우편번호">
				<input type="text" name="addr1" id="addr1">
				<button type="button" class="btn_style" id="btnSearchAddr">주소찾기</button>
			</li>
			<li>
				<label>상세주소</label>
				<input type="text" name="addr2" id="addr2">
			</li>
			<li>
				<label>휴대폰</label>
				<div>
					<input type="radio" name="hp" value="SKT"><span>SKT</span>
					<input type="radio" name="hp" value="LGU+"><span>LGU+</span>
					<input type="radio" name="hp" value="KT"><span>KT</span>
				</div>
				<select name="pnum1" id="pnum1">
					<option value="default">선택</option>
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
				</select> - 
				<input type="text" name="pnum2" id="pnum2"> -
				<input type="text" name="pnum3" id="pnum3"> 
			</li>
			<li>
				<label>취미</label>
				<input type="checkbox" name="hobby" value="영화보기"><span>영화보기</span>
				<input type="checkbox" name="hobby" value="등산하기"><span>등산하기</span>
				<input type="checkbox" name="hobby" value="게임하기"><span>게임하기</span>
				<input type="checkbox" name="hobby" value="잠자기"><span>잠자기</span>
			</li>
			<li>
				<label>자기소개</label>
				<textarea name="intro">*200자 이내로 작성해주세요</textarea>
			</li>
			<li>				
				<button type="button" class="btn_style" id="btnJoin">가입하기</button>
				<button type="reset" class="btn_style">다시입력</button>				
			</li>
		</ul>
	</form>
</div>

<!-------------------->
<!-- footer Include -->
<!-------------------->
<iframe src="http://localhost:9000/model2_cgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
</body>
</html>