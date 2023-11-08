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
    				<button type="button" id="saveBtn" class="btn btn-info ml-3" data-post-id="${post.id }">수정</button>
    			</div>
    		</div>
    		
    	</div>
    </div>
    
    
    <script>
    
    	$(document).ready(function() {
    		
    		// 글 수정 버튼
    		$('#saveBtn').on('click', function() {
    			
    			// alert("클릭");
    			
    			let postId = $(this).data("postId");
    			let subject = $('#subject').val().trim();
    			let content = $('#content').val();
    			let fileName = $('#file').val();
    			
    			// alert(postId);
    			
    			
    			// validation check
    			if (!subject) {
    				alert("제목을 입력하세요");
    				return;
    			}
    			
				if (!content) {
    				alert("내용을 입력하세요");
    				return;
    			}
				
				// 파일이 업로드 된 경우에만 확장자 체크
				if (fileName) {
					
					// alert("파일이 있다");
					// C:\fakepath\짱아코.jpg
					// 확장자만 뽑은 후 소문자로 변경한다
					let ext = fileName.split(".").pop().toLowerCase();	// split으로 사진 저장 경로에서 분리 => '.jpg' 분리
														// pop : 가장 위에 있는 값을 뽑아낸다 (stack의 맨 마지막 순서라고 생각하면 됨 (LIFO))
					
					// alert(ext); => 선택한 이미지 파일의 형식 확인  ex) jpg, jpeg
					
					
					if ($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1) {	// 이미지를 찾을 수 없을 때
						alert("이미지 파일만 업로드 할 수 있습니다");
						$('#file').val("");		// 파일을 비운다
						return;
					}
				}
				
				// request param 구성
				// 이미지를 업로드 할 때는 반드시 form 태그가 있어야한다
				let formData = new FormData();
				formData.append("postId", postId);
				formData.append("subject", subject);	// key는 form 태그의 name 속성과 같고, request parameter 명이 된다
				formData.append("content", content);
				formData.append("file", $('#file')[0].files[0]);
				
    			
				$.ajax({
    				
    				// request
    				type:"put"
    				, url:"/post/update"
    				, data:formData
    				, enctype:"multipart/form-data"		// 파일 업로드를 위한 필수 설정
    				, processData:false		// 파일 업로드를 위한 필수 설정
    				, contentType:false		// 파일 업로드를 위한 필수 설정
    				
    				// response
    				, success:function(data) {
    					
    					if (data.result == "성공") {
    						
    						alert("메모가 수정되었습니다");
    						location.reload(true);
    						
    					} else {
    						
    						// 로직 실패
    						alert(data.errorMessage);
    					}
    					
    				}
    				, error:function(request, status, error) {
    					
    					alert("글을 수정하는데 실패했습니다");
    				}
    			});
				
    		});
    	});
    
    </script>