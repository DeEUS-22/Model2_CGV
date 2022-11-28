<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지/고객관리 - 고객 리스트</title>
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
	<h1>회원관리-리스트</h1>
	<table class="board">			
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>성명</th>
			<th>연락처</th>
			<th>가입날짜</th>
		</tr>
		<c:forEach var="vo"  items="${list}">
			<tr>
				<td>${vo.rno }</td>
				<td><a href="admin_member_content.do?id=${vo.id }">${vo.id }</a></td>
				<td>${vo.name }</td>
				<td>${vo.pnumber }</td>
				<td>${vo.mdate }</td>
			</tr>			
		</c:forEach>
		<tr>
			<td colspan="5">1234</td>
		</tr>
	</table>	
</div>
<!-------------------->
<!-- footer Include -->
<!-------------------->
<iframe src="http://localhost:9000/model2_cgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
</body>
</html>