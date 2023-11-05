<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="d-flex justify-content-center">
    
    	<div class="w-50">
    		
    		<h2>글 상세</h2>
    		
    		<input type="text" id="subject" class="form-control mt-4" placeholder="제목을 입력하세요" value="${post.subject }">
    		<textarea id="content" class="form-control mt-3" rows="10" placeholder="내용을 입력하세요">${post.content }</textarea>
    		
    		<%-- 이미지가 있을 때에만 이미지 영역 추가 --%>
    		<c:if test="${not empty post.imagePath }">
    			<div class="my-4">
    				<img src="${post.imagePath }" alt="업로드 된 이미지" width="300" >
    			</div>
    		</c:if>
    		
    		<div class="d-flex justify-content-end my-4">	<!-- my : margin 위에서 좀 뗄 때 -->
    			<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">	
    				<%-- accept는 파일 업로드 선택 시 내가 설정한 파일 형식만 뜨도록 설정, 이 외에 모든 파일 선택 시 다른 확장자도 선택 가능함 --%>
    		</div>
    		
    		<div class="d-flex justify-content-between">
    			<button type="button" id="deleteBtn" class="btn btn-secondary">삭제</button>
    				<!-- 버튼에 마우스 올렸을 때 버튼 타입으로하면 주소 안뜸, a 태그는 주소 뜸 -->
    			
    			<div class="d-flex">
    				<a href="/post/post-list-view" class="btn btn-dark">목록</a>
    				<button type="button" id="saveBtn" class="btn btn-info ml-3">수정</button>
    			</div>
    		</div>
    		
    	</div>
    </div>