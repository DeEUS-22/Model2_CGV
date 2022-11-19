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