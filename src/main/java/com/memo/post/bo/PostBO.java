package com.memo.post.bo;

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
	
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	
	
	// 게시글 리스트
	// input : userId / output : List<Post>
	public List<Post> getPostListByUserId(int userId) {
		
		return postMapper.selectPostListByUserId(userId);
		
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
