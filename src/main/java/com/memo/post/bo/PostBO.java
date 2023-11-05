package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;

@Service
public class PostBO {
	
	
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
	
	
}
