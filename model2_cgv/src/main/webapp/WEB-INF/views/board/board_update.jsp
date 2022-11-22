<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 - 수정하기</title>
<link rel="stylesheet"  href="http://localhost:9000/model2_cgv/resources/css/cgv.css">
<script src="http://localhost:9000/model2_cgv/resources/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/model2_cgv/resources/js/cgv.js"></script>
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
	<h1>게시판-수정하기</h1>
	<form name="boardUpdateForm" action="boardUpdateCheck.do" method="post">
		<ul>
			<li>
				<label>제목</label>
				<input type="text" name="btitle" id="btitle">
			</li>
			<li>
				<label>내용</label>
				<textarea name="bcontent"></textarea>
			</li>
			<li>
				<label>파일첨부</label>
				<input type="file" name="file1">
			</li>
			<li>
				<button type="button" class="btn_style" id="btnBoardUpdate">수정완료</button>
				<button type="reset" class="btn_style">다시쓰기</button>
				<a href="board_content.do"><button type="button" class="btn_style">이전페이지</button></a>
				<a href="board_list.do"><button type="button" class="btn_style">리스트</button></a>
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