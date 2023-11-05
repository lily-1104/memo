package com.memo.post.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data	// getter, setter 전부 만들어줌
public class Post {
	
	private int id;
	
	private String userId;
	
	private String subject;
	
	private String content;
	
	private String imagePath;
	
	private Date createdAt;
	
	private Date updatedAt;
	
}
