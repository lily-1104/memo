package com.memo.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.memo.post.domain.Post;

@Repository
public interface PostMapper {
	
	
	// TestController
	public List<Map<String, Object>> selectPostList();
	
	
	
	// 게시글 리스트
	public List<Post> selectPostListByUserId(int userId);
	
	
	
	// 글쓰기 API
	public void insertPost(
			@Param("userId") int userId, 
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	
	
	
	
	// 글 작성 화면
	
	
}
