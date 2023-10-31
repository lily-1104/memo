package com.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.entity.UserEntity;
import com.memo.user.repository.UserRepository;

@Service
public class UserBO {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	// 로그인 아이디 중복 확인 API
	// input : loginId / output : UserEntity (null 이거나 entity(채워져있거나))
	public UserEntity getUserEntityByLoginId(String loginId) {
		
		return userRepository.findByLoginId(loginId);
		
	}
	
	
	
	// 로그인
	// input : loginId, 해싱된 password / output : UserEntity (null 이거나 entity(채워져있거나))
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		
		return userRepository.findByLoginIdAndPassword(loginId, password);
		
	}
	
	
	
	// 회원가입
	// input : 4개 파라미터 / output : id(pk)
	public Integer addUser(String loginId, String password, String name, String email) {
		
		// UserEntity = save(UserEntity);
		UserEntity userEntity = userRepository.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build());
		
		return userEntity == null ? null : userEntity.getId();
			// userEntity == null : id가 null이면 null로 리턴, null : userEntity.getId() : id가 있으면 id로 리턴
		
	}
	
	
	
	
	
	
	
	
	
	
}
