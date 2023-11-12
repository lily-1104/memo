package com.memo.post.bo;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	// slf4j 인터페이스로 임포트
												// this : 해당 클래스 자신 (PostBO)
		// 임포트 후 LoggerFactory.getLogger(this.getClass()) 여기에 빨간줄 뜨면 위의 임포트 항목 중 org.mybatis.logging.LoggerFactory; 삭제 후 다시 임포트
	
	// 페이징 => 상수로 정의 (static final)
	private static final int POST_MAX_SIZE = 3;
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	
	
	// 게시글 리스트
	// input : userId / output : List<Post>
	public List<Post> getPostListByUserId(int userId, Integer prevId, Integer nextId) {
		
		// paging
		// 게시글 번호 : 10 9 8 | 7 6 5 | 4 3 2 | 1
		// 만약 4 3 2 페이지에 있을 때
		// 1) 다음 : 2보다 작은 3개 DESC
		// 2) 이전 : 4보다 큰 3개 ASC (5 6 7) => List reverse (7 6 5)
		// 3) 첫페이지 일 때는 이전, 다음 없음 => DESC 3개
		
		String direction = null;	// 방향
		Integer standardId = null;	// 기준이 되는 postId
		
		if (prevId != null) {	// 이전
			
			direction = "prev";
			standardId = prevId;
			
			List<Post> postList = postMapper.selectPostListByUserId(userId, direction, standardId, POST_MAX_SIZE);
			
			// reverse (5 6 7 => 7 6 5)
			Collections.reverse(postList); 	// 뒤집고 저장
			
			return postList;
			
		} else if (nextId != null) {	// 다움
			
			direction = "next";
			standardId = nextId;
		}
		
		// 첫 페이지 or 다음 일 때
		return postMapper.selectPostListByUserId(userId, direction, standardId, POST_MAX_SIZE);
		
	}
	
	
	// paging - 이전 페이지의 마지막인가?
	public boolean isPrevLastPageByUserId(int prevId, int userId) {
		
		int postId = postMapper.selectPostIdByUserIdAndSort(userId, "DESC");
		
		return postId == prevId;	// 같으면 끝 true, 아니면 false
	}
	
	
	// paging - 다음 페이지의 마지막인가?
	public boolean isNextLastPageByUserId(int nextId, int userId) {
		
		int postId = postMapper.selectPostIdByUserIdAndSort(userId, "ASC");
		
		return postId == nextId;	// 같으면 끝 true, 아니면 false
	}
	
	
	
	// 글쓰기 API
	// input : 파라미터들 / output : X
	public void addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {	// 로그인을 해야 글을 작성할 수 있어서 userId도 필요  
		
		String imagePath = null;
		
		// 이미지가 있으면 업로드 (서버 컴퓨터의 폴더에 이미지가 자동으로 저장되게 함)  =>  FileManagerService 클래스 
		if (file != null) {
			
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		postMapper.insertPost(userId, subject, content, imagePath);
		
	}
	
	
	
	// 글 상세 화면
	// input : postId, userId / output : Post(단건) or null(없으면)
	public Post getPostByPostIdAndUserId(int postId, int userId) {
		
		return postMapper.selectPostByPostIdAndUserId(postId, userId);
		
	}
	
	
	
	// 글 수정 API
	// input : 파라미터들 / output : X
	public void updatePost(int userId, String userLoginId, int postId, String subject, String content, MultipartFile file) {
		
		// 기존 글을 가져온다 => 이유 : 1) 이미지 교체 시 삭제를 위해서 / 2) 업데이트 대상이 있는지 확인하기 위해
		Post post = postMapper.selectPostByPostIdAndUserId(postId, userId);
		
		if (post == null) {
			
			logger.error("[글 수정] post is null. post id : {}, userId : {}", postId, userId);		// error, warning, info 중 내가 원하는 거로 써도 됨
			
			return;
		}
		
		// 파일이 있다면
		// 1) 새로운 이미지를 업로드 한다
		// 2) 새 이미지 업로드 성공 시 기존 이미지 제거 (기존 이미지가 있을 때)
		String imagePath = null;
		
		if (file != null) {
			
			// 업로드
			imagePath = fileManager.saveFile(userLoginId, file);
			
			// 업로드 성공 시 기존 이미지 제거 (기존 이미지가 있을 때)
			if (imagePath != null && post.getImagePath() != null) {
				
				// 업로드가 성공을 했고, 기존 이미지가 존재한다면 => 기존 이미지 삭제
				// 이미지 제거
				fileManager.deleteFile(post.getImagePath());
				
			}
		}
		
		// DB 글 update
		postMapper.updatePostByPostIdUserId(postId, userId, subject, content, imagePath);
		
	}
	
	
	
	// 글 삭제 API
	// input : 삭제할 글 번호, 글쓴이 번호 / output : X
	public void deletePostByPostIdUserId(int postId, int userId) {
		
		// 기존 글 가져옴 (기존 이미지가 있으면 삭제해야해서)
		Post post = postMapper.selectPostByPostIdAndUserId(postId, userId);
		
		if (post == null) {
			
			logger.info("[글 삭제] post가 null. postId:{}", postId, userId);
			
			return;
		}
		
		// 기존 이미지가 존재하면 삭제
		if (post.getImagePath() != null) {
			
			fileManager.deleteFile(post.getImagePath());
			
		}
		
		// DB 삭제
		postMapper.deletePostByPostIdUserId(postId, userId);
		
		
	}
	
}
