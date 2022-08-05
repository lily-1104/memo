package com.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	// 암호화 기능 메소드 
	public static String md5(String message) {
			// static : 객체 생성 없이 호출할 수 있는 메소드
		
		String resultData = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			// 대상 문자열 byte 화
			byte[] bytes = message.getBytes();
			
			md.update(bytes);
			
			
			// 암호화 된 결과 얻기
			byte[] digest = md.digest();
			
			
			// 암호화된 결과를 16진수 문자열로 변환 
			for (int i = 0; i < digest.length; i++) {
				resultData += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return resultData;
		
	}
	
}
