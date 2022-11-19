$(document).ready(function(){

	/*********************
		로그인폼 체크 함수
	**********************/
	$("#btnLogin").click(()=>{
		if($("#id").val() == ""){
			alert("아이디를 입력해주세요");
			$("#id").focus();
			return false;
		}else if($("#pass").val() == ""){
			alert("패스워드를 입력해주세요");
			$("#pass").focus();
			return false;
		}else{
			//서버전송
			loginForm.submit();
		}
	});

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
	
	/*********************
		게시판 등록폼 유효성 체크
	**********************/
	$("#btnBoardWrite").click(()=>{
		if($("#btitle").val() == ""){
			alert("제목을 입력해주세요");
			$("#btitle").focus();
			return false;
		}else{
			//서버전송
			boardWriteForm.submit();
		}
	}); //click-end
});