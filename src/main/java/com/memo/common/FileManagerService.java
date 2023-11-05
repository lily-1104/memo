package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component		// spring bean
				// @Controller, Service, Repository, Configuration 외에 설정은 Component!
public class FileManagerService {
	
	
	// 실제 업로드가 된 이미지가 저장될 경로 (서버 주소)
	public static final	String FILE_UPLOAD_PATH = "C:\\Users\\User\\Desktop\\개발\\5_Spring_Project 2023\\memo\\workspace\\images/";	
			// static final : 상수, 한번 세팅 후 절대 안바꿀 때 사용, 보통 상수 뒤에는 대문자로 사용 (FILE_UPLOAD_PATH)
			// 폴더 경로 맨 뒤에 / 꼭 붙이기!!!
	
	
	
	// input : userLoginId, file(이미지파일) / output : web imagePath
	public String saveFile(String loginId, MultipartFile file) {
		
		// 폴더 생성
		// ex : aaaa_178945646/sun.png
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName;	  // C:\\Users\\User\\Desktop\\개발\\5_Spring_Project 2023\\memo\\workspace\\images/   
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			
			// 폴더 생성 실패 시 이미지 경로 null로 리턴
			return null;
			
		}
		
		
		// 파일 업로드 : byte 단위로 업로드
//			 byte[] bytes = file.getBytes();	 => 이 코드 try/catch 누르면 밑의 코드로 바뀜 (return null 추가)
		
		try {
			byte[] bytes = file.getBytes();
			
			// ★★★★★★ 한글 이름 이미지는 올릴 수 없으므로 나중에 영문자로 바꿔서 올리기
			Path path = Paths.get(filePath + "/" + file.getOriginalFilename());	// nio로 임포트
										// 디렉토리 경로 + 사용자가 올린 파일 명
			Files.write(path, bytes);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;	// 이미지 업로드 실패 시 null로 리턴
		}
		
		
		// 파일 업로드가 성공했으면 웹 이미지 URL path를 리턴
		// 주소는 이렇게 될 것이다 (예언)
		// /images/aaaa_178945646/sun.jpg
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
}
