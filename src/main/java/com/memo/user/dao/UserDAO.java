package com.memo.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.memo.user.model.User;

@Repository
public interface UserDAO {
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);
	
	
	public User selectUser(		// model 패키지 있는 user로 임포트 
			@Param("loginId") String loginId
			, @Param("password") String password);

}
