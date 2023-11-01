package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;

@Service
public class PostBO {
	
	
	@Autowired
	private PostMapper postMapper;
	
	
	// 게시글 리스트
	// input : userId / output : List<Post>
	public List<Post> getPostListByUserId(int userId) {
		
		return postMapper.selectPostListByUserId(userId);
		
	}
	
	
	
	// 글쓰기 API
	// input : 파라미터들 / output : X
	public void addPost(int userId, String subject, String content) {	// 로그인을 해야 글을 작성할 수 있어서 userId도 필요
		
		String imagePath = null;
		
		// TODO : 이미지가 있으면 업로드
		
		postMapper.insertPost(userId, subject, content, imagePath);
		
	}
	
	
	
	
	
	// 글 작성 화면
	
	
	
	
	
	
	
	
	
	

}
