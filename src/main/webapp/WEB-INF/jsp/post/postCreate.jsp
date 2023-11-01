<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div class="d-flex justify-content-center">
    
    	<div class="w-50">
    		
    		<h2 class="mt-4">글 쓰기</h2>
    		
    		<input type="text" id="subject" class="form-control mt-4" placeholder="제목을 입력하세요">
    		<textarea id="content" class="form-control mt-3" rows="10" placeholder="내용을 입력하세요"></textarea>
    		
    		<div class="d-flex justify-content-end my-4">	<!-- my : margin 위에서 좀 뗄 때 -->
    			<input type="file" id="file">
    		</div>
    		
    		<div class="d-flex justify-content-between mb-4">
    			<button type="button" id="postListBtn" class="btn btn-dark">목록</button>
    				<!-- 버튼에 마우스 올렸을 때 버튼 타입으로하면 주소 안뜸, a 태그는 주소 뜸 -->
    			
    			<div class="d-flex">
    				<button type="button" id="clearBtn" class="btn btn-secondary">모두 지우기</button>
    				<button type="button" id="saveBtn" class="btn btn-info ml-3">저장</button>
    			</div>
    		</div>
    		
    	</div>
    </div>
    
    
    <script>
    
    	$(document).ready(function() {
    		
    		// 목록 버튼 클릭 => 글 목록 화면 이동
    		$('#postListBtn').on('click', function() {
    			
    			// alert("클릭");
    			location.href = "/post/post-list-view";
    		});
    		
    		
    		// 모두 지우기 버튼
    		$('#clearBtn').on('click', function() {
    			
    			// alert("클릭");
    			$('#subject').val("");	// val() : 현재 값이 무엇인지, val(aaaaa) : 값 세팅, val("") : 작성한 글 내용 지워짐
    			$('#content').val("");
    		});
    		
    		
    		// 글 저장 버튼
    		$('#saveBtn').on('click', function() {
    			
    			// alert("클릭");
    			let subject = $('#subject').val().trim();	// trim() : 앞 뒤 여백 제거
    			let content = $('#content').val();
    			
    			// validation check
    			if (!subject) {
    				alert("제목을 입력하세요");
    				return;
    			}
    			
				if (!content) {
    				alert("내용을 입력하세요");
    				return;
    			}
				
				// request param 구성
				// 이미지를 업로드 할 때는 반드시 form 태그가 있어야한다
				let formData = new FormData();
				formData.append("subject", subject);	// key는 form 태그의 name 속성과 같고, request parameter 명이 된다
				formData.append("content", content);
				
				
    			
    			// AJAX
    			$.ajax({
    				
    				// request
    				type:"post"
    				, url:"/post/create"
    				, data:formData
    				, enctype:"multipart/form-data"		// 파일 업로드를 위한 필수 설정
    				, processData:false		// 파일 업로드를 위한 필수 설정
    				, contentType:false		// 파일 업로드를 위한 필수 설정
    				
    				// response
    				, success:function(data) {
    					
    					if (data.result == "성공") {
    						
    						alert("메모가 저장되었습니다");
    						location.href = "/post/post-list-view";
    						
    					} else {
    						
    						// 로직 실패
    						alert(data.errorMessage);
    					}
    					
    				}
    				, error:function(request, status, error) {
    					
    					alert("글을 저장하는데 실패했습니다");
    				}
    			});
    		});
    		
    	});
    </script>