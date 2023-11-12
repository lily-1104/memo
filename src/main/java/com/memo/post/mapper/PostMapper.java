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
	public List<Post> selectPostListByUserId(
			@Param("userId") int userId, 
			@Param("direction") String direction,	// paging
			@Param("standardId") Integer standardId,	// paging, standardId는 null 일 수 있음
			@Param("limit") int limit);		// paging
	
	
	// paging - 이전 페이지의 마지막인가?
	public int selectPostIdByUserIdAndSort(
			@Param("userId") int userId,
			@Param("sort") String sort);
	
	
	
	// 글쓰기 API
	public void insertPost(
			@Param("userId") int userId, 
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	
	
	// 글 상세 화면
	public Post selectPostByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	
	
	// 글 수정 API
	public void updatePostByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	
	
	// 글 삭제 API
	public void deletePostByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	
}
