<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet"  href="http://localhost:9000/model2_cgv/resources/css/cgv.css">
<script src="http://localhost:9000/model2_cgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/model2_cgv/resources/js/cgv.js"></script>
<script>
	let join_result = '${join_result}';
	let login_result = '${login_result}';
	let auth_result = '${auth}';
	
	if(auth_result == "fail"){
		alert("접근 권한이 없습니다. 로그인을 진행해 주세요.");
	}
	
	if(login_result == 'fail'){
		alert("아이디 또는 패스워드가 다릅니다. 다시 로그인해주세요");	
	}
	
	if(join_result == 'ok'){
		alert("회원가입에 성공하셨습니다.");
	}
	
</script>
</head>
<body>
<!-------------------->
<!-- header Include -->
<!-------------------->
<iframe src="header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>

<!-------------------->
<!----- content ------>
<!-------------------->
<div class="content">
	<h1>Login</h1>
	<form name="loginForm" action="loginCheck.do" method="post">
	<ul>
		<li>
			<label>아이디</label>
			<input type="text" name="id" id="id" placeholder="아이디를 입력해주세요">
		</li>
		<li>
			<label>패스워드</label>
			<input type="password" name="pass" id="pass">
		</li>
		<li>
			<button type="button" id="btnLogin">로그인</button>
			<button type="reset">다시쓰기</button>
		</li>
	</ul>
	</form>
</div>

<!-------------------->
<!-- footer Include -->
<!-------------------->
<iframe src="footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>

</body>
</html>