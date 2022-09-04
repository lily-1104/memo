package com.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.memo.post.model.Post;

@Repository
public interface PostDAO {
	
	public int insertPost(
			@Param("userId") int userId
			, @Param("title") String title
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	
	public List<Post> selectPostList(@Param("userId") int userId);
	
	
	public Post selectPost(@Param("id") int id);
	
	
	
	// 게시글 수정
	public int updatePost(
			@Param("postId") int postId
			, @Param("title") String title
			, @Param("content") String content);
	
	
	
	// 게시글 삭제
	public int deletePost(@Param("postId") int postId);
	
	
}
