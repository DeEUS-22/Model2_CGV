<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 공지사항 삭제하기</title>
<link rel="stylesheet"  href="http://localhost:9000/model2_cgv/resources/css/cgv.css">
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
	<h1>공지사항-삭제하기</h1>
	<form name="boardDeleteForm" action="adminDeleteNoticeCheck.do" method="post">
		<ul>
			<li>
				<img src="http://localhost:9000/model2_cgv/resources/images/delete.jpg">				
			</li>				
			<li>
				<div>정말로 삭제하시겠습니까?</div>
			</li>
			<li>
				<button type="submit" class="btn_style">삭제완료</button>					
				<a href="admin_notice_content.do"><button type="button" class="btn_style">이전페이지</button></a>
				<a href="admin_notice_list.do"><button type="button" class="btn_style">리스트</button></a>
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