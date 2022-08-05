<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
	
</head>
<body>

	<div id="wrap">
	
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<form id="loginForm">	<%-- 로그인을 클릭 외에 엔터로도 가능하도록 하기위해 form 태그 사용 --%>
		<section class="content d-flex justify-content-center">
		
			
			<div class="py-5">
				<h1 class="text-center">로그인</h1>
				
				<input type="text" class="form-control mt-3" id="idInput" placeholder="아이디">
				<input type="password" class="form-control mt-3" id="passwordInput" placeholder="비밀번호">
				
				<button type="submit" id="loginBtn" class="btn btn-dark btn-block mt-3">로그인</button>
				
				<div class="text-center mt-3">
					<a href="/user/signup/view">회원가입</a>
				</div>
			</div>	
			
		</section>
		</form>
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		
	</div>
	
	
	<script>
		$(document).ready(function() {
			
			$("#loginForm").on("submit", function(e) {
				
				// 해당하는 이벤트에 포함된 모든 기능을 중단한다 (submit의 기능을 중단)
					// "submit"은 모든 페이지를 이동시켜서 위험함, 중단 기능 필요, 
					// 로그인을 클릭 외에 엔터로도 가능하도록 설정하기 위해 submit 사용 
				e.preventDefault();
				
				let loginId = $("#idInput").val();
				let password = $("#passwordInput").val();
				
				if (loginId == "") {
					alert("아이디를 입력하세요");
					return;
				}
				
				if (password == "") {
					alert("비밀번호를 입력하세요");
					return;
				}
				
				
				// 로그인 api를 호출해서 로그인 결과를 확인한다
				$.ajax({
					type:"post",
					url:"/user/signin",
					data:{"loginId":loginId, "password":password},
					
					success:function(data) {
						
						if(data.result == "success") {
							location.href = "/post/list/view";
							
						} else {
							alert("아이디/비밀번호를 확인해주세요");
						}
						
					},
					error:function() {
						alert("로그인 에러");
					}
					
				});
				
			});
			
		});
	
	</script>
	

</body>
</html>