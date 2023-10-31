package com.memo.post.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.memo.post.domain.Post;

@Repository
public interface PostMapper {
	
	
	// TestController
	public List<Map<String, Object>> selectPostList();
	
	
	
	// 게시글 리스트
	public List<Post> selectPostListByUserId(int userId);
	
}
