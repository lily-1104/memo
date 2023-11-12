<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div class="d-flex justify-content-center">
    
    	<div class="w-50">
    		
    		<h2 class="mt-4">글 쓰기</h2>
    		
    		<input type="text" id="subject" class="form-control mt-4" placeholder="제목을 입력하세요">
    		<textarea id="content" class="form-control mt-3" rows="10" placeholder="내용을 입력하세요"></textarea>
    		
    		<div class="d-flex justify-content-end my-4">	<!-- my : margin 위에서 좀 뗄 때 -->
    			<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">	
    				<%-- accept는 파일 업로드 선택 시 내가 설정한 파일 형식만 뜨도록 설정, 이 외에 모든 파일 선택 시 다른 확장자도 선택 가능함 --%>
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
    			let fileName = $('#file').val();	// 파일 경로(임시 파일로 저장이 됨) : C:\fakepath\짱아코.jpg
    			
    			// alert(file);
    			
    			
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
				formData.append("subject", subject);	// key는 form 태그의 name 속성과 같고, request parameter 명이 된다
				formData.append("content", content);
				formData.append("file", $('#file')[0].files[0]);	// 파일 가져올 때는 이 코드 써야함 (파일 한개 들고올 때)
				
    			
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