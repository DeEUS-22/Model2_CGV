<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 공지사항 상세보기</title>
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
	<h1>공지사항-상세보기</h1>
	<table class="boardContent">	
		<tr>				
			<th>등록일자</th>
			<td>${vo.ndate }</td>
			<th>조회수</th>
			<td>${vo.nhits }</td>
		</tr>		
		<tr>				
			<th>제목</th>
			<td colspan="3">${vo.ntitle }</td>
		</tr>
		<tr>				
			<th>내용</th>
			<td colspan="3">${vo.ncontent }<br><br>
				<img src="http://localhost:9000/model2_cgv/resources/upload/${vo.nsfile }" width="50%">
			<br><br></td>
		</tr>
		<tr>
			<td colspan="4">
				<a href="admin_notice_update.do?nid=${vo.nid }"><button type="button" class="btn_style">수정하기</button></a>
				<a href="admin_notice_delete.do?nid=${vo.nid }"><button type="button" class="btn_style">삭제하기</button></a>
				<a href="admin_notice_list.do">
					<button type="button" class="btn_style">리스트</button></a>
				<a href="http://localhost:9000/model2_cgv/admin.do"><button type="button" class="btn_style">관리자홈</button></a>
			</td>
		</tr>			
	</table>	
</div>
<!-------------------->
<!-- footer Include -->
<!-------------------->
<iframe src="http://localhost:9000/model2_cgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>

</body>
</html>