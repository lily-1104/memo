package com.memo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.memo.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	
	// 로그인 아이디 중복 확인 API
	// UserEntity는 null이거나 채워져있거나(UserEntity 단건)
	public UserEntity findByLoginId(String loginId);
	
	
}
