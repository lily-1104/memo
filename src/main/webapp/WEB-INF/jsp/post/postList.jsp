<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <div class="d-flex justify-content-center">
    
    	<div class="w-50">
    	
    		<h2 class="mt-4">글 목록</h2>
    		
    		<table class="table mt-4">
    			<thead>
    				<tr>
    					<th>NO.</th>
    					<th>제목</th>
    					<th>작성날짜</th>
    					<th>수정날짜</th>
    				</tr>
    			</thead>
    			
    			<tbody>
    				<c:forEach items="${postList }" var="post">
    				<tr>
    					<td>${post.id }</td>
    					<td><a href="/post/post-detail-view?postId=${post.id }">${post.subject }</a></td>
    					<td>
    						<%-- zonedDateTime -> Date -> String 순으로 변환해야함 --%>
    						<%-- <fmt:parseDate value="${post.createdAt }" var="parsedCreatedAt" pattern="yyyy-MM-dd'T'HH:mm:ss" /> --%>
    						<fmt:formatDate value="${post.createdAt }" pattern="yyyy년 M월 d일 HH:mm:dd" />
    					</td>
    					
    					<td>
    						<%-- <fmt:parseDate value="${post.updatedAt }" var="parsedUpdatedAt" pattern="yyyy-MM-dd'T'HH:mm:ss" /> --%>
    						<fmt:formatDate value="${post.updatedAt }" pattern="yyyy년 M월 d일 HH:mm:dd" />
    					</td>
    				</tr>
    				</c:forEach>
    			</tbody>
    		</table>
    		
    		<%-- paging --%>
    		<div class="text-center">
    			<c:if text="${prevId ne 0 }">	<!-- ne = != -->
    				<a href="/post/post-list-view?prevId=${prevId }" class="mr-5">&lt;&lt; 이전</a>		<%-- &lt; : 왼쪽 꺽쇠 --%>
    			</c:if> 
    			<c:if text="${nextId ne 0 }">
    				<a href="/post/post-list-view?nextId=${nextId }">다음 &gt;&gt;</a>		<%-- &gt; : 오른쪽 꺽쇠 --%>
    			</c:if> 
    		</div>
    		
    		<div class="d-flex justify-content-end">
    			<a href="/post/post-create-view" class="btn btn-secondary">글쓰기</a>
    		</div>
    	
    	</div>
    </div>