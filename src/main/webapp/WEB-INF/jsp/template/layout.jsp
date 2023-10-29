<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo 게시판</title>

<!-- 부트스트랩, 제이쿼리 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<!-- 내가 만든 스타일시트 -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">

</head>
<body>
	
	<div id="wrap">
	
		<header class="bg-info">
			<jsp:include page="../include/header.jsp" />	<!-- Ctrl 버튼 눌러서 연결 되는지 확인 -->
					<!-- 상대 경로로 할 거라서 ../ 붙임, 위의 폴더로 올라간다는 뜻 (절대 경로는 맨 앞에 / 붙음 => /WEB-INF 부터 시작) -->
		</header>
		
		
		<section class="contents">
		
			<jsp:include page="../${viewName }.jsp" />
		
		
		</section>
	
		
		<footer class="bg-primary">
			<jsp:include page="../include/footer.jsp" />
		</footer>
	
	</div>
	

</body>
</html>