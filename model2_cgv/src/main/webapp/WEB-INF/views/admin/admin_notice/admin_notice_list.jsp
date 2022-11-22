<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지/공지사항 관리 - 공지사항 리스트</title>
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
	<h1>공지사항-리스트</h1>
	<table class="board">
		<tr>
			<td colspan="4">
				<a href="admin_notice_write.do">
				<button type="button" class="btn_style">글쓰기</button>
				</a>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>등록날짜</th>
			<th>조회수</th>
		</tr>
		<tr>
			<td>1</td>
			<td><a href="admin_notice_content.do">11월 행사 안내</a></td>
			<td>2022-11-22</td>
			<td>17</td>
		</tr>
		<tr>
			<td colspan="4">1234</div></td>
		</tr>
	</table>	
</div>
<!-------------------->
<!-- footer Include -->
<!-------------------->
<iframe src="http://localhost:9000/model2_cgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
</body>
</html>