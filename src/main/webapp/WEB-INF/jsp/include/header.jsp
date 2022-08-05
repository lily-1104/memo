<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<header class="d-flex align-items-center justify-content-between">
				<!--  justify-content-between : 양 옆으로 끝에 달라붙도록 -->
	
		<h1 class="ml-3">Memo</h1>
		
		<%-- session에 userId가 값이 저장되어있으면 로그아웃 링크 보여주기 
			 (로그인 상태이면 '로그아웃'이 보이고, 로그인 상태가 아니면 '로그아웃'이 안보이도록 설정)
			     not empty userId : userId 값이 비어있지않다면,
				     empty userId : userId 값이 비어져있다면 --%>
		<c:if test="${not empty userId }">
			<div class="mr-3">${userName }님 <a href="/user/signout">로그아웃</a></div>
		</c:if>
		
	</header>

</body>
</html>