<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="h-100 d-flex align-items-center justify-content-between">
	
	<%-- logo --%>
	<div>
		<h2 class="font-weight-bold ml-5">MEMO 게시판</h2>
	</div>
	
	<%-- 로그인 정보 --%>
	<div class="justify-items-end mr-4">
		<c:if test="${not empty userName }">
			<span>${userName }님 안녕하세요</span>
			<a href="/user/sign-out">로그아웃</a>
		</c:if>
	</div>
	
</div>