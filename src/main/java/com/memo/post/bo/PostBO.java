package com.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	
	// userId, 제목, 내용 저장 기능 
	public int addPost(int userId, String title, String content) {
		
		return postDAO.insertPost(userId, title, content);
	}
	
	
	
	// userId가 일치하는 메모 리스트 조회
	public List<Post> getPostList(int userId) {	// <Post>는 model 명이 Post라서
		
		return postDAO.selectPostList(userId);
	}
	
	
	
	// id 일치하는 메모 조회 
	public Post getPost(int id) {
		
		return postDAO.selectPost(id);
		
	}

}
